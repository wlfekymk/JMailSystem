package org.wlfek.service.domain;

import javax.mail.Address;

import lombok.Data;

@Data
public class MailDomain{
    private String uid;
    private int messageNumber;
    private String folderName;
    private String korName;
    private String subject;
    private String sentDate;
    private String receivedDate;
    private String content;
    private String priority;
    private String fileInfoBigDate;

    private boolean seen;
    private boolean flagged;
    private boolean existFile;

    private Address[] to;
    private Address[] from;
   
	private Address[] cc;
    private Address[] bcc;
    private Address[] replyTo;
}