package com.kh.easy.member.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("easy")
@RequiredArgsConstructor
public class MemberController {

	@GetMapping("member")
	public String test() {
		System.out.println("컨트롤러 호출");
		return "테스트성공";
	}
}
