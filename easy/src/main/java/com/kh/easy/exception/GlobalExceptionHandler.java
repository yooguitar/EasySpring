package com.kh.easy.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.kh.easy.exception.member.DuplicateUserException;
import com.kh.easy.exception.member.ExpiredTimeException;
import com.kh.easy.exception.member.MessagingException;
import com.kh.easy.exception.member.MismatchPasswordException;
import com.kh.easy.exception.member.NoRecieverException;
import com.kh.easy.exception.member.NoSuchDataException;
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

	@ExceptionHandler(MismatchPasswordException.class)
	public ResponseEntity<?> handleMismatchPassword(MismatchPasswordException e) {
		return ResponseEntity.badRequest().body(e.getMessage());
	}

	@ExceptionHandler(NoSuchDataException.class)
	public ResponseEntity<?> handleNoData(NoSuchDataException e) {
		return ResponseEntity.badRequest().body(e.getMessage());
	}
	
	@ExceptionHandler(ExpiredTimeException.class)
	public ResponseEntity<?> handleTimeover(ExpiredTimeException e){
		return ResponseEntity.badRequest().body(e.getMessage());
	}
	
	@ExceptionHandler(MessagingException.class)
	public ResponseEntity<?> handleMailError(MessagingException e){
		return ResponseEntity.badRequest().body(e.getMessage());
	}
	
	
	/* 여행플랜 관련 예외처리 */
	
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

	
	@ExceptionHandler(NoRecieverException.class)
	public ResponseEntity<?> handleEmptyRecieverError(NoRecieverException e){
		return ResponseEntity.badRequest().body(e.getMessage());
	}
	
}
