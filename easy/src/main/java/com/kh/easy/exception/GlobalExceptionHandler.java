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
}
