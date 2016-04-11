package org.wlfek.service.impl;

import javax.mail.Store;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sun.mail.imap.IMAPFolder;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = JMailServiceImpl.class)
public class JMailServiceImplTest {
	
	@Autowired
	private JMailServiceImpl jMailService;
	private String id;
	private String password;
	
	@Before
	public void init() throws Exception{
		id = "wlfekymk@gmail.com";
		password = "@dlfhdia33@";
		jMailService.getConnection(id, password);
	}
	
	@Test
	public void getFolderListTest() throws Exception{
		String[] checkString = {"INBOX", "Junk", "Personal", "Receipts", "Sent", "Travel", "Unwanted", "Work", "[Gmail]", "[Gmail]/별표편지함",
				"[Gmail]/보낸편지함", "[Gmail]/스팸함", "[Gmail]/임시보관함", "[Gmail]/전체보관함", "[Gmail]/중요", "[Gmail]/휴지통"  };
		String[] result = null;
		IMAPFolder[] imapFolders = null;
		imapFolders = jMailService.getFolderList();
		result = new String[imapFolders.length];

		for(int i = 0 ; i < imapFolders.length; i++){
			result[i] = imapFolders[i].getFullName();
		}
		
		assertArrayEquals(checkString, result);

	}
	
	
	@Test
	public void getFolderUidsTest(){
		
		try {
			jMailService.getFolderUids("");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
		
}
