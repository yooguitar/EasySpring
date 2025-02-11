package com.kh.easy.member.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kh.easy.auth.service.AuthenticationService;
import com.kh.easy.member.model.dto.ChangePasswordDTO;
import com.kh.easy.member.model.dto.LoginResponse;
import com.kh.easy.member.model.dto.MemberDTO;
import com.kh.easy.member.model.service.MemberService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j 
@RestController
@RequestMapping(value = "member", produces = "application/json; charset=UTF-8")
@RequiredArgsConstructor
public class MemberController {
	private final MemberService memberService;
	private final AuthenticationService authService;
	
	@PostMapping("join")
	public ResponseEntity<String> join(@Valid @RequestBody MemberDTO requestMember){
		/* 더미 데이터 생성기		 
		for(int i = 100; i != 200; i ++) {
			MemberDTO users = new MemberDTO();
			users.setUserId("user" + i);
			users.setUserPwd("pass" + i);
			users.setEmail("mail" + i + "@kh.com");
			memberService.join(users);
		}
		*/
		memberService.join(requestMember);
		return ResponseEntity.ok("회원가입에 성공했습니다.");
	}
	
	@PostMapping("login")
	public ResponseEntity<LoginResponse> login(@Valid @RequestBody MemberDTO requestMember) {
		Map<String, String> tokens = authService.login(requestMember);
		LoginResponse response = LoginResponse.builder().username(requestMember.getUserId()).tokens(tokens).build();
		return ResponseEntity.ok(response);
	}
	
	@PutMapping("changePassword")
	public ResponseEntity<?> changePassword(@Valid @RequestBody ChangePasswordDTO changeEntity){
		memberService.changePassword(changeEntity);
		return ResponseEntity.ok("비밀번호 변경 성공!");
	}
	
	@PutMapping("delete")
	public ResponseEntity<String> deletePassword(@RequestBody Map<String, String> password){
		memberService.deleteByPassword(password);
		return ResponseEntity.ok("삭제 완료!");
	}
	
	
}
