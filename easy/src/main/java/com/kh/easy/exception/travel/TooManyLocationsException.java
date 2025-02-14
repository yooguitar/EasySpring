package com.kh.easy.exception.travel;

public class TooManyLocationsException extends RuntimeException {

	/* 여행장소는 최대 6곳까지 추가 가능한데, 6개 이상 선택했을 경우를 처리함 */
	
	public TooManyLocationsException(String message) {
		super(message);
	}
	
	
}
