package com.stackroute.workflowengineservice.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InternalUnixCommandException extends Exception{
//	static final long serialVersionUID = 42L;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private String errormessage;
	
	public InternalUnixCommandException(String errormessage) {
		super(errormessage);
		this.errormessage = errormessage;
	}

	public InternalUnixCommandException() {
		// TODO Auto-generated constructor stub
	}

	public String getErrormessage() {
		return errormessage;
	}
}
