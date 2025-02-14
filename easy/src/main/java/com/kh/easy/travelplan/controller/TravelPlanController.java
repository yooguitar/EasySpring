package com.kh.easy.travelplan.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.easy.travelplan.model.dto.TravelPlanDTO;
import com.kh.easy.travelplan.model.dto.TravelPlanRequestDTO;
import com.kh.easy.travelplan.model.service.TravelPlanService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequestMapping("map")
@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class TravelPlanController {

	private final TravelPlanService service;
	
	@PostMapping
	public ResponseEntity<?> addTravlePlan(@RequestBody TravelPlanRequestDTO travelPlan){
		
		//log.info("{}", travlePlan.getTravlePlan());
		
		service.addTravlePlan(travelPlan.getTravelPlan());
		
		return ResponseEntity.status(HttpStatus.CREATED).body("플랜 생성 성공");
	}
	
	@GetMapping("/list")
	public ResponseEntity<Map<Long, List<TravelPlanDTO>>> selectTravlePlan(){
		
		String userId = "user01";
		
		Map<Long, List<TravelPlanDTO>> plans = service.selectTravelPlan(userId);
		
		return ResponseEntity.ok(plans);
	}
	
	@PutMapping
	public ResponseEntity<?> updateTravelPlan(@RequestBody TravelPlanDTO plan){
		
		service.updateTravelPlan(plan);
		
		return ResponseEntity.ok("플랜 업데이트 성공");
	}
	
	@PutMapping("/memo")
	public ResponseEntity<?> updateUserMemo(@RequestBody String userMemo){
		
		String userId = "user01";
		
		service.updateUserMemo(userId, userMemo);
		
		return ResponseEntity.status(HttpStatus.CREATED).body("메모 저장 성공");
	}
	
	@DeleteMapping
	public ResponseEntity<?> deleteTravelPlace(@RequestBody TravelPlanDTO plan){
		
		service.deleteTravelPlace(plan);
		
		return ResponseEntity.ok("장소 삭제 성공");
	}
	
}
