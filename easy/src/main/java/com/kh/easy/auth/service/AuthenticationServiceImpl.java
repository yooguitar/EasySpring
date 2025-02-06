package com.kh.easy.auth.service;

import java.util.Map;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.kh.easy.member.model.dto.MemberDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

	private final AuthenticationManager authenticationManager;

	@Override
	public Map<String, String> login(MemberDTO requestMember) {

		//Authentication authentication = authenticationManager.authenticate(
		//		new UsernamePasswordAuthenticationToken(requestMember.getUserId(), requestMember.getUserPwd()));

		return null;
	}

}
