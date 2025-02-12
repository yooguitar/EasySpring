package com.kh.easy.travelplan.model.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kh.easy.travelplan.model.dto.TravelPlanDTO;
import com.kh.easy.travelplan.model.mapper.TravelPlanMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class TravelPlanServiceImpl implements TravelPlanService {

	private final TravelPlanMapper mapper;
	
	@Override
	public void addTravlePlan(List<TravelPlanDTO> travelPlan) {

		
		//log.info(travelPlan.toString());
		
		// userId는 첫번째 DTO의 userId를 기준으로 처리함 
		//String userId = list.get(0).getUserId();
		// 예시로 user01
		String userId = "user01";
		
		
		// planCode는 미리 구하고 값을 넣은상태에서 진행
		int planCode = mapper.selectPlanCode(userId);
		if(planCode > 6) {
			throw new RuntimeException("최대 6개의 계획만 저장할 수 있습니다.");
		}

		// List에 들어있는 값 만큼 반복
		for(TravelPlanDTO dto : travelPlan) {
			dto.setPlanCode(planCode);
			dto.setUserId(userId);
			int result = mapper.addTravelPlan(dto);
			log.info("저장된 행 갯수 : {}", result);
		}
		
		
	}

	@Override
	public Map<Long, List<TravelPlanDTO>> selectTravelPlan(String userId) {
		
        List<TravelPlanDTO> plans = mapper.selectTravelPlan(userId);

        Map<Long, List<TravelPlanDTO>> groupedPlans = plans.stream()
            .collect(Collectors.groupingBy(TravelPlanDTO::getPlanCode));
        
        return groupedPlans;
	}

	@Override
	public void saveTravelPlan() {

	}

	@Override
	public void updateTravelPlan() {

	}

	@Override
	public void deleteTravelPlan() {

	}

}
