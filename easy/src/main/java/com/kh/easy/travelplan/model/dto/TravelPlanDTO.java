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

	private long planId;
	private long planCode;
	private String userId;
	private String placeName;
	private String placeAddress;
	private String phone;
	private String category;
	private String lat;
	private String lng;
	private String linkUrl;
	private String userMemo;
	
}
