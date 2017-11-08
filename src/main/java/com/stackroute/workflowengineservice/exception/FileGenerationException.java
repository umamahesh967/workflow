package com.stackroute.workflowengineservice.exception;

public class FileGenerationException extends Exception{
//	static final long serialVersionUID = 42L;
	
	private String errormessage;
	
	public FileGenerationException(String errormessage) {
		super(errormessage);
		this.errormessage = errormessage;
	}

	public String getErrormessage() {
		return errormessage;
	}
}
