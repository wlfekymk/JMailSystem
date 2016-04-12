/**
 * AUTHOR : Yang Min Kyu
 * DATE   : 2016. 4. 1.
 * TITLE  : Java Mail IMAP Service 구현 체 
 * MAIL   : wlfekymk@gmail.com
 **/
package org.wlfek.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.UIDFolder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wlfek.service.JMailService;

import com.sun.mail.imap.IMAPFolder;

public class JMailServiceImpl implements JMailService{

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	private static boolean MAIL_IMAP_DEBUG_MODE = false;
	private static String MAIL_IMAP_CONNECTION_TIMEOUT = "5000"; // 밀리 세컨드
	private static String MAIL_IMAP_TIMEOUT = "5000";
	private static String MAIL_IMAP_STARTTLS_ENABLE = "";
	private static String MAIL_IMAP_PARTIALFETCH = "";
	private static String MAIL_IMAP_AUTH_PLANIN_DISABLE = "";
	private static String MAIL_ADDRESS = "imap.gmail.com";
	private static int MAIL_IP = 993;
	private static Store store;
	
	@Override
	public void getConnection(String id, String password) throws Exception {
		logger.debug("IMAP Connection Start");
		Properties properties = new Properties();
		properties = System.getProperties();
		properties.setProperty("mail.imap.debug", String.valueOf(MAIL_IMAP_DEBUG_MODE));
		properties.setProperty("mail.imap.connectiontimeout", MAIL_IMAP_CONNECTION_TIMEOUT);
		properties.setProperty("mail.imap.timeout", MAIL_IMAP_TIMEOUT);
		//properties.setProperty("mail.imap.starttls.enable", MAIL_IMAP_STARTTLS_ENABLE);
		//properties.setProperty("mail.imap.partialfetch", MAIL_IMAP_PARTIALFETCH);
		//properties.setProperty("mail.imap.auth.plain.disable", MAIL_IMAP_AUTH_PLANIN_DISABLE);
		
		Session session = Session.getDefaultInstance(properties);
		session.setDebug(MAIL_IMAP_DEBUG_MODE);
	
		try {
			store = session.getStore("imaps");
			store.connect(MAIL_ADDRESS, MAIL_IP, id, password);
			logger.debug("IMAP Connection End");
		} catch (Exception e){
			logger.error(e.getMessage());
			try { store.close();} 
			catch (MessagingException e1) {}
		}
	}

	@Override
	public IMAPFolder[] getFolderList(){
		
		IMAPFolder[] imapFolders = null;
		try {
			imapFolders = (IMAPFolder[]) store.getDefaultFolder().list("*");
		} catch (Exception e) {
			logger.error(e.getMessage());
		} 
		return imapFolders; 
	}
	
	@Override
	public IMAPFolder getMessageList(String fullFolderName) {
		IMAPFolder imapFolder = null;
		try {
			imapFolder = (IMAPFolder) store.getFolder(fullFolderName);
			imapFolder.open(Folder.READ_ONLY);
			Message[] messages = imapFolder.getMessages();
			for(Message message: messages){
				System.out.println("=========================");
				System.out.println(message.getFlags());
				if (message.isSet(Flags.Flag.DELETED)) {
					System.out.println("Deleted");
				}
				if (message.isSet(Flags.Flag.ANSWERED)) {
					System.out.println("Answered");
				}
				if (message.isSet(Flags.Flag.DRAFT)) {
			        System.out.println("Draft");
				}
				if (message.isSet(Flags.Flag.FLAGGED)) {
					System.out.println("Marked");
				}	
				if (message.isSet(Flags.Flag.RECENT)) {
					System.out.println("Recent");
				}
				if (message.isSet(Flags.Flag.SEEN)) {
					System.out.println("Read");
				}
			     
				System.out.println(message.getSubject());
				
				for(Address addres : message.getFrom() ){
					System.out.println(addres.getType());
				}
				
				System.out.println(message.getFrom());
				System.out.println(message.getSentDate());
				System.out.println(message.getDescription());
				System.out.println(message.getContentType());
				System.out.println(message.getMessageNumber());
				System.out.println(message.getDisposition());
				System.out.println(message.getSize());
				System.out.println("=========================");
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return imapFolder;
	}
	
	@Override
	public List<Long> getFolderUids(String fullFolderName) {
		IMAPFolder imapFolder = null;
		List<Long> folderUids = new ArrayList<Long>();
		try {
			imapFolder = (IMAPFolder) store.getFolder(fullFolderName);
			imapFolder.open(Folder.READ_ONLY);
			Message[] messages = imapFolder.getMessages();
			for(Message message: messages){
				UIDFolder uf = (UIDFolder) imapFolder;
				long uid = uf.getUID(message);
				System.out.println(uid);
				folderUids.add(uid);
			}			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		return folderUids;
	}

	@Override
	public IMAPFolder getFolder(String fullFolderName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteMessage(String fullFolderName, long[] uids) {
		IMAPFolder imapFolder = null;
		try {
			imapFolder = (IMAPFolder) store.getFolder(fullFolderName);
			imapFolder.open(Folder.READ_WRITE);
			Message[] messages = imapFolder.getMessagesByUID(uids);
			imapFolder.setFlags(nonNull(messages), new Flags(Flags.Flag.DELETED), true);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		} 
		return true;
	}
	
	static <T> T nonNull(T argument) {
		if (argument == null) {
			throw new IllegalArgumentException("argument cannot be null");
		}
		return argument;
	}

	@Override
	public boolean setSeenFlag(String fullFolderName, boolean seenFlag, long[] uids) {
		IMAPFolder imapFolder = null;
		try {
			imapFolder = (IMAPFolder) store.getFolder(fullFolderName);
			imapFolder.open(Folder.READ_WRITE);
			Message[] messages = imapFolder.getMessagesByUID(uids);
			imapFolder.setFlags(nonNull(messages), new Flags(Flags.Flag.SEEN), seenFlag);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
		return true;
	}

	
}
