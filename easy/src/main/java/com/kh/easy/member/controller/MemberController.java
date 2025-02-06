package com.kh.easy.member.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.easy.member.model.dto.MemberDTO;
import com.kh.easy.member.model.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j 
@RestController
@RequestMapping(value = "member", produces = "application/json; charset=UTF-8")
@RequiredArgsConstructor
public class MemberController {
	private final MemberService memberService;

	@PostMapping("join")
	public ResponseEntity<String> join(@RequestBody MemberDTO requestMember){
		memberService.join(requestMember);
		log.info("json {}:", requestMember);
		return null;
	}
	
	@PostMapping("login")
	public ResponseEntity<?> login() {
		return null;
	}
}
