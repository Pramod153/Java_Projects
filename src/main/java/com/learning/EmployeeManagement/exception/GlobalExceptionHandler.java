package com.learning.EmployeeManagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.learning.EmployeeManagement.Payload.APIResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<APIResponse> resourceNotFoundHandler(ResourceNotFoundException  e){
		String message = e.getMessage();
		boolean status = false;
		APIResponse response = new APIResponse(message,status);
		
		return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
		
	}
}
