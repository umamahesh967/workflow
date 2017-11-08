package com.stackroute.workflowengineservice.exception;

public class CustomExceptionResponse {
    private String errorMessage;
    private String error;
	
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	public String getErrors() {
		return error;
	}
	public void setErrors(String error) {
		this.error = error;
	}
}
