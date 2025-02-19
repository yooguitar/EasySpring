package com.kh.easy.admin.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kh.easy.admin.service.AdminService;
import com.kh.easy.member.model.dto.MailDTO;

import jakarta.validation.Valid;
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
	 * findMembers: 		일반 회원 ID 내림차순 조회		(오름차순 구현)
	 * findByMail: 			일반 회원 Mail 내림차순 조회	(오름차순 구현)
	 * findByStatus: 		일반 회원 상태별 정렬			(오름차순 구현)
	 * findByDate: 			일반 회원 가입일 내림차순 조회	(오름차순 구현)
	 * findAdmin: 			관리자 목록 전체 조회
	 * 
	 * @return [{response}, {response}..]
	 */
	@GetMapping("findMembers")
	public ResponseEntity<?> findMembers(@RequestParam(value="page", defaultValue="1") int page){
		return ResponseEntity.ok(adminService.findMembers(page));
	}
	@GetMapping("findMembersAsc")
	public ResponseEntity<String> findMembersAsc(@RequestParam(value="page", defaultValue="1") int page){
		return ResponseEntity.ok(adminService.findMembersAsc(page));
	}
	@GetMapping("findByMail")
	public ResponseEntity<String> findByMail(@RequestParam(value="page", defaultValue="1") int page){
		return ResponseEntity.ok(adminService.findByMail(page));
	}
	@GetMapping("findByMailAsc")
	public ResponseEntity<String> findByMailAsc(@RequestParam(value="page", defaultValue="1") int page){
		return ResponseEntity.ok(adminService.findByMailAsc(page));
	}
	@GetMapping("findByStatus")
	public ResponseEntity<String> findByStatus(@RequestParam(value="page", defaultValue="1") int page){
		return ResponseEntity.ok(adminService.findByStatus(page));
	}
	@GetMapping("findByStatusAsc")
	public ResponseEntity<String> findByStatusAsc(@RequestParam(value="page", defaultValue="1") int page){
		return ResponseEntity.ok(adminService.findByStatusAsc(page));
	}
	@GetMapping("findByDate")
	public ResponseEntity<String> findByDate(@RequestParam(value="page", defaultValue="1") int page){
		return ResponseEntity.ok(adminService.findByDate(page));
	}
	@GetMapping("findByDateAsc")
	public ResponseEntity<String> findByDateAsc(@RequestParam(value="page", defaultValue="1") int page){
		return ResponseEntity.ok(adminService.findByDateAsc(page));
	}
	@GetMapping("findAdmin")
	public ResponseEntity<String> findAdmin(@RequestParam(value="page", defaultValue="1") int page){
		return ResponseEntity.ok(adminService.findAdmin(page));
	}
	@GetMapping("findTotalCount")
	public int findTotalCount() {
		return adminService.findTotalCount();
	}
	
	// 회원 수정 관련
	@PostMapping("blockUser")
	public ResponseEntity<?> blockUser(@RequestBody List<String> users){
		adminService.blockUser(users);
		return ResponseEntity.ok("선택 회원을 비활성화 했습니다.");
	}
	
	@PostMapping("unblockUser")
	public ResponseEntity<?> unblockUser(@RequestBody List<String> users){
		adminService.unblockUser(users);
		return ResponseEntity.ok("선택 회원을 활성화 했습니다.");
	}
	
	// 메일링
	@PostMapping("mailForUser")
	public ResponseEntity<?> mailForUser(@Valid @RequestBody MailDTO mails){
		adminService.mailForUser(mails);
		return ResponseEntity.ok("메일 발송 성공!");
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
