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
	
	@GetMapping("member")
	public ResponseEntity<?> memberList(){
		
		adminService.findAll();
		
		
		
		return null;
	}
	
	
}
