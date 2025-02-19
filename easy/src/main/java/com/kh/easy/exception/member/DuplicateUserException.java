package com.kh.easy.exception.member;

public class DuplicateUserException extends RuntimeException{
	
	public DuplicateUserException(String message) {
		super(message);
	}

}
