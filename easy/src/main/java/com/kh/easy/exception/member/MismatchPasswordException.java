package com.kh.easy.exception.member;

public class MismatchPasswordException extends RuntimeException {
	public MismatchPasswordException(String message) {
		super(message);
	}
}
