package com.kh.easy.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.kh.easy.exception.travel.NotEnoughLocationsException;
import com.kh.easy.exception.travel.PlanStorageFullException;
import com.kh.easy.exception.travel.TooManyLocationsException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(DuplicateUserException.class)
	public ResponseEntity<String> handleDuplicateUser(DuplicateUserException e) {
		return ResponseEntity.badRequest().body(e.getMessage());
	}

	@ExceptionHandler(PlanStorageFullException.class)
	public ResponseEntity<?> handlerFullPlanStorage(PlanStorageFullException e) {
		return ResponseEntity.badRequest().body(e.getMessage());
	}

	@ExceptionHandler(NotEnoughLocationsException.class)
	public ResponseEntity<?> handlerNotEnoughLocations(NotEnoughLocationsException e) {
		return ResponseEntity.badRequest().body(e.getMessage());
	}

	@ExceptionHandler(TooManyLocationsException.class)
	public ResponseEntity<?> handlerTooMantLocations(TooManyLocationsException e) {
		return ResponseEntity.badRequest().body(e.getMessage());
	}

	@ExceptionHandler(MismatchPasswordException.class)
	public ResponseEntity<?> handleMismatchPassword(MismatchPasswordException e) {
		return ResponseEntity.badRequest().body(e.getMessage());
	}

	@ExceptionHandler(NoSuchDataException.class)
	public ResponseEntity<?> handleNoData(NoSuchDataException e) {
		return ResponseEntity.badRequest().body(e.getMessage());
	}
}
