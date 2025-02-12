package com.kh.easy.travelplan.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
	public ResponseEntity<?> addTravlePlan(@RequestBody TravelPlanRequestDTO travlePlan){
		
		//log.info("{}", travlePlan.getTravlePlan());
		
		service.addTravlePlan(travlePlan.getTravlePlan());
		
		return ResponseEntity.status(HttpStatus.CREATED).body("플랜 생성 성공");
	}
	
	@GetMapping("/list")
	public ResponseEntity<Map<Long, List<TravelPlanDTO>>> selectTravlePlan(){
		
		String userId = "user01";
		
		Map<Long, List<TravelPlanDTO>> plans = service.selectTravelPlan(userId);
		
		return ResponseEntity.ok(plans);
	}
	
	
}
