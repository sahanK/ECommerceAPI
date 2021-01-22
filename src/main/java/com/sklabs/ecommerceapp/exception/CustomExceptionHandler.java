package com.sklabs.ecommerceapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {
	// Exception handler for ResourceNotFoundException
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleException(ResourceNotFoundException exc){
		ErrorResponse response = new ErrorResponse(HttpStatus.NOT_FOUND.value(), exc.getMessage(), System.currentTimeMillis());
		return new ResponseEntity<ErrorResponse>(response, HttpStatus.NOT_FOUND);
	}
	
	// Exception handler for any other exception
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleException(Exception exc){
		ErrorResponse response = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), exc.getMessage(), System.currentTimeMillis());
		return new ResponseEntity<ErrorResponse>(response, HttpStatus.BAD_REQUEST);
	}
}
