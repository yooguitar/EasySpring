package com.kh.easy.auth.service;

import java.util.Map;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.kh.easy.member.model.dto.MemberDTO;
import com.kh.easy.token.model.service.TokenService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

	private final AuthenticationManager authenticationManager;
	private final TokenService tokenService;

	@Override
	public Map<String, String> login(MemberDTO requestMember) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(requestMember.getUserId(), requestMember.getUserPwd()));
		UserDetails user = (UserDetails)authentication.getPrincipal();
		
		tokenService.generateToken(user.getUsername());
		
		return null;
	}

}
