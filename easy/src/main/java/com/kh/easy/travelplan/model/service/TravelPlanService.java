package com.kh.easy.travelplan.model.service;

import java.util.List;
import java.util.Map;

import com.kh.easy.travelplan.model.dto.TravelPlanDTO;
import com.kh.easy.travelplan.model.dto.TravelPlanRequestDTO;

public interface TravelPlanService {

	void addTravlePlan(List<TravelPlanDTO> list);
	
	Map<Long, List<TravelPlanDTO>> selectTravelPlan(String userId);
	
	void saveTravelPlan();
	
	void updateTravelPlan(TravelPlanDTO plan);
	
	void deleteTravelPlace(TravelPlanDTO plan);

	void updateUserMemo(String userId, String userMemo);
	
}
