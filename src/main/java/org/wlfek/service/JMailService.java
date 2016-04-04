package org.wlfek.service;

import javax.mail.Store;

import com.sun.mail.imap.IMAPFolder;

public interface JMailService {
	
	/**
	 * IMAP Connection 객체
	 * @param id
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public Store getConnection(String id, String password) throws Exception;
	
	/**
	 * IMAP 전체 폴더
	 * @param store
	 * @return
	 * @throws Exception
	 */
	public IMAPFolder[] getFolderList(Store store) throws Exception;
	
	/**
	 * IMAP 요청 하는 폴더명에 대한 폴더 메시지
	 * @param folderName
	 * @return
	 * @throws Exception
	 */
	public IMAPFolder getMessageList(String fullFolderName) throws Exception;
	

}
