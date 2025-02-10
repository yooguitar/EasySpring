package com.kh.easy.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(DuplicateUserException.class)
	public ResponseEntity<String> handleDuplicateUser(DuplicateUserException e){
		return ResponseEntity.badRequest().body(e.getMessage());
	}
	
	@ExceptionHandler(MismatchPasswordException.class)
	public ResponseEntity<?> handleMismatchPassword(MismatchPasswordException e){
		return ResponseEntity.badRequest().body(e.getMessage());
	}
	
	@ExceptionHandler(NoSuchDataException.class)
	public ResponseEntity<?> handleNoData(NoSuchDataException e){
		return ResponseEntity.badRequest().body(e.getMessage());
	}
}
