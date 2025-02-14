package com.kh.easy.travelplan.model.dto;

import java.util.List;

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
public class TravelPlanRequestDTO {
	
	// 배열로 넘어온 플랜을 List로 받는 DTO클래스
    private List<TravelPlanDTO> travelPlan;
    
}