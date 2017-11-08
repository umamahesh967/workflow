package com.stackroute.workflowengineservice.exception;

import org.eclipse.jgit.api.errors.GitAPIException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JgitInternalException  extends GitAPIException{
//	static final long serialVersionUID = 42L;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private String errormessage;
	
	public JgitInternalException(String errormessage) {
		super(errormessage);
		this.errormessage = errormessage;
	}

	public String getErrormessage() {
		return errormessage;
	}
}
