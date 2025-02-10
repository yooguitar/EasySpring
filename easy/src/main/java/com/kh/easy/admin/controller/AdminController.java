package com.kh.easy.admin.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.easy.admin.service.AdminService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
 
@Slf4j
@RestController
@RequestMapping("admin")
@RequiredArgsConstructor
public class AdminController {
	private final AdminService adminService;
	
	/**
	 * **회원 관리 콘솔
	 * findMembers: 		일반등급 회원 가입일 내림차순 전체 조회
	 * findMembersAsc: 		일반등급 회원 가입일 오름차순 전체 조회
	 * findAdmin: 			관리자 목록 전체 조회
	 * findAliveMembers: 	활동중 회원만 전체 조회
	 * findDeadMembers: 	정지 회원만 전체 조회
	 * SortIdAsc:			일반등급 회원ID 알파벳 오름차순 조회(활동중)
	 * SortIdDesc:			일반등급 회원ID 알파벳 내림차순 조회(활동중)
	 * 
	 * @return [{response}, {response}..]
	 */
	@GetMapping("findMembers")
	public ResponseEntity<String> findMembers(){
		return ResponseEntity.ok(adminService.findMembers());
	}
	@GetMapping("findMembersAsc")
	public ResponseEntity<String> findMembersAsc(){
		return ResponseEntity.ok(adminService.findMembersAsc());
	}
	@GetMapping("findAdmin")
	public ResponseEntity<String> findAdmin(){
		return ResponseEntity.ok(adminService.findAdmin());
	}
	@GetMapping("findAliveMembers")
	public ResponseEntity<String> findAliveMembers(){
		return ResponseEntity.ok(adminService.findAliveMembers());
	}
	@GetMapping("findDeadMembers")
	public ResponseEntity<String> findDeadMembers(){
		return ResponseEntity.ok(adminService.findDeadMembers());
	}
	@GetMapping("sortIdAsc")
	public ResponseEntity<String> sortIdAsc(){
		return ResponseEntity.ok(adminService.sortIdAsc());
	}
	@GetMapping("sortIdDesc")
	public ResponseEntity<String> sortIdDesc(){
		return ResponseEntity.ok(adminService.sortIdDesc());
	}
	// 검색 기능
	@GetMapping("searchById")
	public ResponseEntity<?> searchById(){
		return null;
	}
	@GetMapping("searchByEmail")
	public ResponseEntity<?> searchByEmail(){
		return null;
	}
	
}
