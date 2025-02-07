package com.kh.easy.exception;

public class DuplicateUserException extends RuntimeException{
	
	public DuplicateUserException(String message) {
		super(message);
	}

}
