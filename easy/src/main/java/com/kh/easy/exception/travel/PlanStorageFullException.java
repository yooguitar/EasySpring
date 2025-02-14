package com.kh.easy.exception.travel;

public class PlanStorageFullException extends RuntimeException{

	/* 저장가능한 여행플랜은 6개인데, 그 이상 저장하려 할 때 발생하는 예외를 처리함 */
	
	public PlanStorageFullException(String message) {
		super(message);
	}
	
}
