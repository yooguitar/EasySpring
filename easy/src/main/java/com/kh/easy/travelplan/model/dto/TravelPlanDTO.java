package com.kh.easy.travelplan.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class TravelPlanDTO {

	// seq로 생성
	private long planId;
	// 몇번째 여행 플랜인지
	private long planCode;
	// 플랜에 추가된 장소의 순번을 알기위함
	private long planOrder;
	// 사용자 구분
	private String userId;
	// 장소이름
	private String placeName;
	// 주소
	private String placeAddress;
	// 전화번호
	private String phone;
	// 카테고리
	private String category;
	// lat
	private String lat;
	// lng
	private String lng;
	// 바로가기 링크
	private String linkUrl;
	// 사용자 메모
	private String userMemo;
	
}
