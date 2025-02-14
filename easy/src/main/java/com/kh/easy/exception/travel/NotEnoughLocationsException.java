package com.kh.easy.exception.travel;

public class NotEnoughLocationsException extends RuntimeException {
	
	/* 선택된 장소가 3개 미만일때 발생하는 예외를 처리함 */
	
	public NotEnoughLocationsException(String message) {
		super(message);
	}
}
