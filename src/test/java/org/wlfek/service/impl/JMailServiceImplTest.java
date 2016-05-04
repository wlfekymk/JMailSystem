package org.wlfek.service.impl;

import org.junit.*;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sun.mail.imap.IMAPFolder;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = JMailServiceImpl.class)
public class JMailServiceImplTest {
	
	@Spy JMailServiceImpl jMailService;
	
	private String id;
	private String password;
	private String fullFolderName;
	
	@Before
	public void init() throws Exception{
		MockitoAnnotations.initMocks(this);
		id = "wlfektestmail@gmail.com";
		password = "@testgmail@";
		jMailService.getConnection(id, password);
	}
	
	@Test
	public void sampleMock(){
		MockitoAnnotations.initMocks(this);
		assertNotNull(jMailService);
	}

	@Test
	public void getMessageListTest(){

	}
	
	@Test
	public void getFolderListTest() {

		String[] checkString = {"INBOX", "[Gmail]", "[Gmail]/별표편지함", "[Gmail]/보낸편지함", "[Gmail]/스팸함",
				"[Gmail]/임시보관함", "[Gmail]/전체보관함", "[Gmail]/중요", "[Gmail]/휴지통"};
		String[] result = null;
		IMAPFolder[] imapFolders = null;
		imapFolders = jMailService.getFolderList();
		assertNotNull(imapFolders);
		result = new String[imapFolders.length];
		for(int i = 0 ; i < imapFolders.length; i++){
			result[i] = imapFolders[i].getFullName();
		}
		assertArrayEquals(checkString, result);
	}
	
	@Test
	public void getFolderUidsTest() {
		fullFolderName = "[Gmail]/별표편지함"; 
		List<Long> checkList = Arrays.asList((long)2, (long)3, (long) 7);
		List<Long> result = null; 
		result = jMailService.getFolderUids(fullFolderName);
		System.out.println("folder uids : " + result);
		assertEquals(checkList, result);	
	}
	
	@Test
	public void getMessageTest() {
		fullFolderName = "[Gmail]/별표편지함";
		IMAPFolder folder = jMailService.getMessageList(fullFolderName);
		assertNotNull(folder);
	}
	
	//@Test
	public void deleteMessageTest(){
		fullFolderName = "[Gmail]/별표편지함";
		long[] uids = { 6 }; 
		boolean result = jMailService.deleteMessage(fullFolderName, uids);
		System.out.println(result);
		assertTrue(result);
	}

}
