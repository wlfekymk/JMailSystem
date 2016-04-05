package org.wlfek.service.impl;

import javax.mail.Address;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Store;
import javax.mail.UIDFolder;
import javax.mail.internet.MimeBodyPart;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sun.mail.imap.IMAPFolder;

public class JMailServiceImplTest {
	
	private JMailServiceImpl jMailService;
	private String id;
	private String password;
	
	@Before
	public void init() throws Exception{
		jMailService = new JMailServiceImpl();
		id = "wlfekymk@gmail.com";
		password = "@dlfhdia33@";
//		jMailService.getConnection(id, password);
	}
	
	//@Test
	public void getConnectionTest() throws Exception{
		jMailService.getConnection(id, password);
	}
	
	//@Test
	public void getFolderList() throws Exception{
		Store store = jMailService.getConnection(id, password);
		IMAPFolder[] imapFolders = jMailService.getFolderList(store);
		
		for(IMAPFolder imapFolder : imapFolders){
			System.out.println(imapFolder.getFullName());
		}
	}
	
	@Test 
	public void 메시지Uids만뽑기() throws Exception{
		Store store = jMailService.getConnection(id, password);
		UIDFolder uidFolder = jMailService.getMessageList("[Gmail]/별표편지함");
		
		System.out.println(uidFolder.getUIDValidity());
	}

	//@Test
	public void getMessageListForFullFolderName() throws Exception{
		Store store = jMailService.getConnection(id, password);
		IMAPFolder imapFolder = jMailService.getMessageList("[Gmail]/별표편지함");
		
//		System.out.println(imapFolder.getMessageCount());
//		System.out.println(imapFolder.getNewMessageCount());
		Message[] messages = imapFolder.getMessages();
//		Message message = imapFolder.getMessage(5);
//		System.out.println(message.getSubject());
		for(Message message: messages){
//			
//			message.get
//			UIDFolder uf = (UIDFolder) imapFolder;
//			long uid = uf.getUID(message);
//			System.out.println("uid : " + uid);
		}
//		Message message = imapFolder.getMessageByUID(arg0)
		
		//Message message = imapFolder.getMessageByUID(2);
//		System.out.println(message.getSubject());
		for(Message message : messages){
			
			System.out.println(message.getSubject());
//			System.out.println(message.getMessageNumber());
//			System.out.println(message.getContentType());
			Address[] addresss = message.getFrom();
			for(Address address : addresss){
				System.out.println(address.toString());
			}
//						
//			Part part = (Part) message.getContent();
//			System.out.println(part.getContentType());
//		}
//		
		}
		
	}
	


	
}
