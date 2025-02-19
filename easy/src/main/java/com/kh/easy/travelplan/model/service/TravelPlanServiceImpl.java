package com.kh.easy.travelplan.model.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kh.easy.exception.travel.NotEnoughLocationsException;
import com.kh.easy.exception.travel.PlanStorageFullException;
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
			throw new PlanStorageFullException("최대 6개의 계획만 저장할 수 있습니다.");
		}
		
		
		log.info("들어온 장소의 개수 : {}", travelPlan.size());

		// 선택된 장소의 개수가 3개보다 적으면 생성 불가능.
		if(travelPlan.size() < 3) {
			throw new NotEnoughLocationsException("장소를 최소 3곳 선택해야합니다.");
		}
		
		// 선택된 장소의 개수가 7개보다 적어야 생성 가능
		if(travelPlan.size() > 6) {
			throw new NotEnoughLocationsException("장소는 최대 6곳까지 선택 가능합니다.");
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
	public void updateTravelPlan(TravelPlanDTO plan) {

		mapper.updateTravelPlan(plan);
		
	}

	
	@Override
	public void deleteTravelPlace(TravelPlanDTO plan) {
		
		mapper.deleteTravelPlace(plan);
		
	}

	@Override
	public void deleteTravelPlan(TravelPlanDTO plan) {

		mapper.deleteTravelPlan(plan);
		
	}
	
	@Override
	public void updateUserMemo(TravelPlanDTO plan) {
		
		
		
		
	}



}
