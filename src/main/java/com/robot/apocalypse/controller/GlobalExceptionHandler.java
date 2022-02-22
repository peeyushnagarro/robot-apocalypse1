package com.robot.apocalypse.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.robot.apocalypse.exception.ContaminationAlreadyReportedException;
import com.robot.apocalypse.exception.SurvivorInfectedException;

import javax.persistence.EntityNotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;

@ControllerAdvice
public class GlobalExceptionHandler {
	private static String ENTITY_NOT_FOUND_EXCEPTION_MSG = "Survivor not found for given survivor id : %s";
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, Map<String, String>>> handleValidationExceptions(MethodArgumentNotValidException exception) {
		Map<String, String> errors = new HashMap<>();
		
	    exception.getBindingResult().getAllErrors().forEach((error) -> {
	        String fieldName = ((FieldError) error).getField();
	        String errorMessage = error.getDefaultMessage();
	        
	        errors.put(fieldName, errorMessage);
	    });
	    
	    Map<String, Map<String, String>> error = new HashMap<>();
	    error.put("errors", errors);
	    
	    return new ResponseEntity<Map<String, Map<String, String>>>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<Map<String, String>> handleEntityNotFoundException(EntityNotFoundException exception) {
		String[] messageParts = exception.getMessage().split(" ");
		String survivorId = messageParts[messageParts.length - 1];
	    
	    return errorResponse(String.format(ENTITY_NOT_FOUND_EXCEPTION_MSG, survivorId), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ContaminationAlreadyReportedException.class)
	public ResponseEntity<Map<String, String>> handleContaminationAlreadyReportedException(ContaminationAlreadyReportedException exception) {
		return errorResponse(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(SurvivorInfectedException.class)
	public ResponseEntity<Map<String, String>> handleSurvivorInfectedException(SurvivorInfectedException exception) {
		return errorResponse(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public ResponseEntity<Map<String, String>> handleEmptyResultDataAccessException(EmptyResultDataAccessException exception) {
		return errorResponse(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	private ResponseEntity<Map<String, String>> errorResponse(String message, HttpStatus httpStatus) {
		Map<String, String> response = new HashMap<>();
	    
		response.put("result", "Error");
		response.put("message", message);
	    
	    return new ResponseEntity<Map<String, String>>(response, httpStatus);
	}
}
