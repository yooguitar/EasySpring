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
		memberMapper.join(requestMember);
	}

	@Override
	public void login(MemberDTO requestMember) {
		String request = requestMember.getUserId();
		MemberDTO test = memberMapper.login(request);
	}

}
