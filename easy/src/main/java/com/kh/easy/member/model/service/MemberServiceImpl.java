package com.kh.easy.member.model.service;

import org.springframework.stereotype.Service;

import com.kh.easy.member.model.dto.MemberDTO;
import com.kh.easy.member.model.mapper.MemberMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j 
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
	
	private final MemberMapper memberMapper;
	
	@Override
	public void join(MemberDTO requestMember) {
		if("".equals(requestMember.getUserId()) || "".equals(requestMember.getUserPwd())) {
			// 빈 문자열 입력 예외처리
		}
		MemberDTO searched = memberMapper.findByUserId(requestMember.getUserId());
		if(searched != null) {
			// 중복 id 예외처리
		}
		// 인코딩 해서 저장해야됨 import
		//MemberDTO member = MemberDTO.builder().userId(requestMember.getUserId()).userPwd(passwordEncoder.encode(requestMember.getUserPwd()).email(requestMember.getEmail()).build(); 
		
		// 회원가입 마저 해결하기 + 시큐리티
		
		memberMapper.join(requestMember);
	}

	@Override
	public void login(MemberDTO requestMember) {
		String request = requestMember.getUserId();
		MemberDTO test = memberMapper.login(request);
	}

}
