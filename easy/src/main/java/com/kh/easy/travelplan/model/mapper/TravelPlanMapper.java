package com.kh.easy.travelplan.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.easy.travelplan.model.dto.TravelPlanDTO;

@Mapper
public interface TravelPlanMapper {

	// planCode가 있는지 확인하기위함
	int selectPlanCode(String userId);
	
	// planCode를 검증하고 userId로 사용자를 확인할수있게 저장
	int addTravelPlan(TravelPlanDTO dto);

	// userId로 비교하여 모든 여행계획 가져오기
	List<TravelPlanDTO> selectTravelPlan(String userId);
	
	// 플랜에 추가되어있는 장소 업데이트하기
	void updateTravelPlan(TravelPlanDTO plan);

	// 플랜에 추가되어있는 장소 삭제하기
	void deleteTravelPlan(TravelPlanDTO plan);

	// 플랜 삭제하기
	void deleteTravelPlace(TravelPlanDTO plan);
	
}
