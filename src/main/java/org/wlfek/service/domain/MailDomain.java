package org.wlfek.service.domain;

import java.lang.reflect.Array;

import javax.mail.Address;

import lombok.Data;

@Data
public class MailDomain{
	private long uid;
	
	private int size;

	private String subject;
	private String SendDate;
	
	private Address[] from;
	private Address[] to;
	private Address[] replayTo;
	
	private boolean flagged;
	
	
}