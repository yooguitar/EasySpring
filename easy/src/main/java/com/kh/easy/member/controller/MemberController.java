package com.kh.easy.member.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
import com.kh.easy.token.model.service.TokenService;

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
	private final TokenService tokenService;
	
	@PostMapping("join")
	public ResponseEntity<String> join(@Valid @RequestBody MemberDTO requestMember){
		log.info("{}", requestMember);
		memberService.join(requestMember);
		return ResponseEntity.ok("회원가입에 성공했습니다.");
		
//		for(int i = 100; i != 200; i ++) {
//			MemberDTO users = new MemberDTO();
//			users.setUserId("user" + i);
//			users.setUserPwd("pass" + i);
//			users.setEmail("mail" + i + "@kh.com");
//			memberService.join(users);
//		}
//		return null;
	}
	
	@PostMapping("login")
	public ResponseEntity<LoginResponse> login(@Valid @RequestBody MemberDTO requestMember) {
		Map<String, String> tokens = authService.login(requestMember);
		String role = memberService.getRole(requestMember);
		LoginResponse response = LoginResponse.builder().username(requestMember.getUserId()).role(role).tokens(tokens).build();
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("findUser")
	public ResponseEntity<MemberDTO> findUser(@RequestParam("username") String username){
		MemberDTO result = memberService.findUser(username);
		return ResponseEntity.ok(result);
	}
	
	@PutMapping("updateInfo")
	public ResponseEntity<?> updateInfo(@Valid @RequestBody ChangePasswordDTO changeEntity){
		memberService.updateInfo(changeEntity);
		return ResponseEntity.ok("정보 수정 성공!");
	}
	
	@PostMapping("findInfo")
	public ResponseEntity<String> findInfo(@Valid @RequestBody Map<String, String> email){
		return ResponseEntity.ok(memberService.findInfo(email));
	}
	
	@PostMapping("findPwd")
	public ResponseEntity<?> findPwd(@Valid @RequestBody Map<String, String> request){
		memberService.findPwd(request);
		return ResponseEntity.ok("인증 메일이 발송 되었습니다.");
	}
	
	@PostMapping("matchRandomNum")
	public ResponseEntity<?> matchRandomNum(@RequestBody Map<String, String> request){
		memberService.matchRandomNum(request);
		return null;
	}
	
	@PutMapping("delete")
	public ResponseEntity<String> deletePassword(@RequestBody Map<String, String> password){
		memberService.deleteByPassword(password);
		return ResponseEntity.ok("삭제 완료!");
	}
	
	@PostMapping("refresh")
	public ResponseEntity<Map> refresh(@RequestBody String refreshToken){
		System.out.println("리프레쉬 토큰 확인 :" + refreshToken);
		Map<String, String> newTokens = tokenService.refreshTokens(refreshToken);
		return ResponseEntity.ok(newTokens);
	}
	
	@DeleteMapping("deleteRefToken")
	public ResponseEntity<?> deleteRefToken(@RequestBody Map<String, String> username) {
		tokenService.deleteRefToken(username.get("username"));
		return ResponseEntity.ok("로그아웃 성공");
	}
	
}
