package com.stackroute.workflowengineservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlingController {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<CustomExceptionResponse> invalidInput(MethodArgumentNotValidException exception) {
		
		BindingResult result = exception.getBindingResult();
        CustomExceptionResponse exceptionresponse = new CustomExceptionResponse();
        
        exceptionresponse.setErrorMessage("Invalid Inputs.");
        exceptionresponse.setErrors(result.getFieldErrors().get(0).getDefaultMessage());
        
        return new ResponseEntity<CustomExceptionResponse>(exceptionresponse, HttpStatus.BAD_REQUEST);
	}
	
	
	
	@ExceptionHandler(InternalRepositoryException.class)
	public ResponseEntity<CustomExceptionResponse> repositoryException(InternalRepositoryException exception) {
        CustomExceptionResponse exceptionresponse = new CustomExceptionResponse();
        
        exceptionresponse.setErrorMessage("Repository Error.");
        exceptionresponse.setErrors(exception.getErrormessage());
        
        return new ResponseEntity<CustomExceptionResponse>(exceptionresponse, HttpStatus.SERVICE_UNAVAILABLE);
	}
	
	
	
	@ExceptionHandler(UrlException.class)
	public ResponseEntity<CustomExceptionResponse> urlNotRepositoryException(UrlException exception) {
        CustomExceptionResponse exceptionresponse = new CustomExceptionResponse();
        
        exceptionresponse.setErrorMessage("URL Error.");
        exceptionresponse.setErrors(exception.getErrormessage());
        
        return new ResponseEntity<CustomExceptionResponse>(exceptionresponse, HttpStatus.SERVICE_UNAVAILABLE);
	}
	
	
	@ExceptionHandler(InternalUnixCommandException.class)
	public ResponseEntity<CustomExceptionResponse> unixCommandException(InternalUnixCommandException exception) {
        CustomExceptionResponse exceptionresponse = new CustomExceptionResponse();
        
        exceptionresponse.setErrorMessage("Unix command not able to run : Error.");
        exceptionresponse.setErrors(exception.getErrormessage());
        
        return new ResponseEntity<CustomExceptionResponse>(exceptionresponse, HttpStatus.SERVICE_UNAVAILABLE);
	}
	
	
	@ExceptionHandler(FileGenerationException.class)
	public ResponseEntity<CustomExceptionResponse> fileGenerationError(FileGenerationException exception) {
        CustomExceptionResponse exceptionresponse = new CustomExceptionResponse();
        
        exceptionresponse.setErrorMessage("File not able to generate not able to generate.");
        exceptionresponse.setErrors(exception.getErrormessage());
        
        return new ResponseEntity<CustomExceptionResponse>(exceptionresponse, HttpStatus.SERVICE_UNAVAILABLE);
	}
		
	
}
