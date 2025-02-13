package com.kh.easy.auth.service;

import java.util.Map;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.kh.easy.auth.model.vo.CustomUserDetails;
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

		CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();

		Map<String, String> tokens = tokenService.generateToken(user.getUsername());
		
		return tokens;
	}

	public CustomUserDetails getAuthenticatedUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();
		return user;
	}
}
