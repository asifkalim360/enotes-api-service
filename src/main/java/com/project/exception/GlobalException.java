package com.project.exception;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {
	
//	@ExceptionHandler(Exception.class)
//	public ResponseEntity<?> handleException(Exception e)
//	{
//		return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//	}
	
	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<?> handleNullPointerException(Exception e)
	{
		return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}	
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleResourceNotFoundException(Exception e) 
	{
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) 
	{
		List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
		
		Map<String, Object> error = new LinkedHashMap<>(); 
		allErrors.stream().forEach(err -> {
			String msg = err.getDefaultMessage(); 
			String field = ((FieldError) (err)).getField(); 
			error.put(field, msg);
		});		
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
}
