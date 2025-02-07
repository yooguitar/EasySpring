package com.kh.easy.member.model.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kh.easy.member.model.dto.Member;
import com.kh.easy.member.model.dto.MemberDTO;
import com.kh.easy.member.model.mapper.MemberMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j 
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
	
	private final MemberMapper memberMapper;
	private final PasswordEncoder passwordEncoder;
	
	@Override
	public void join(MemberDTO requestMember) {
		MemberDTO searched = memberMapper.findByUserId(requestMember.getUserId());
		if(searched != null) {
			// 중복 id 예외처리 필요
		}
		Member member = 
				Member.builder()
				.userId(requestMember.getUserId())
				.userPwd(passwordEncoder.encode(requestMember.getUserPwd()))
				.email(requestMember.getEmail()).build(); 
		memberMapper.join(member);
	}

//	@Override
//	public void login(MemberDTO requestMember) {
//		String request = requestMember.getUserId();
//		MemberDTO test = memberMapper.login(request);
//	}

}
