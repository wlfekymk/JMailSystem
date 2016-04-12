package org.wlfek.service;

import java.util.List;

import javax.mail.Store;

import com.sun.mail.imap.IMAPFolder;

public interface JMailService {
	
	/**
	 * Connection 객체 생성
	 * @param id
	 * @param password
	 * @throws Exception
	 */
	public void getConnection(String id, String password) throws Exception;
	
	/**
	 * IMAP 전체 폴더
	 * @param store
	 * @return
	 * @throws Exception
	 */
	public IMAPFolder[] getFolderList();
	
	/**
	 *
	 * @param fullFolderName
	 * @return
	 * @throws Exception
	 */
	public IMAPFolder getFolder(String fullFolderName);
	
	/**
	 * IMAP 요청 하는 폴더명에 대한 폴더 메시지
	 * @param folderName
	 * @return
	 * @throws Exception
	 */
	public IMAPFolder getMessageList(String fullFolderName);
	
	/**
	 * IMAP 요청 하는 폴더의 uid들 검색
	 * @param fullFolderName
	 * @return 요청 폴더의 uid list
	 * @throws Exception
	 */
	public List<Long> getFolderUids(String fullFolderName);

	

}
