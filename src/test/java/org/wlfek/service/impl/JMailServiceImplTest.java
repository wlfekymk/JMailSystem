package org.wlfek.service.impl;

import javax.mail.Folder;
import javax.mail.Store;

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
	
	@Test
	public void getConnectionTest() throws Exception{
		jMailService.getConnection(id, password);
	}
	
	@Test
	public void getFolderList() throws Exception{
		Store store = jMailService.getConnection(id, password);
		IMAPFolder[] imapFolders = jMailService.getFolderList(store);
		
		for(IMAPFolder imapFolder : imapFolders){
			System.out.println(imapFolder.getFullName());
		}
	}

	@Test
	public void getMessageList() throws Exception{
		Store store = jMailService.getConnection(id, password);
		IMAPFolder imapFolder = jMailService.getMessageList("[Gmail]/전체보관함");
		//imapFolder.get
		
		
	}
	


	
}
