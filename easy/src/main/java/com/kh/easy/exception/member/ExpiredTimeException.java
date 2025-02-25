package com.kh.easy.exception.member;

public class ExpiredTimeException extends RuntimeException {
	
	public ExpiredTimeException(String message) {
		super(message);
	}

}
