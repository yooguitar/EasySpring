package com.kh.easy.auth.service;

import java.util.Collections;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.kh.easy.auth.model.vo.CustomUserDetails;
import com.kh.easy.member.model.dto.MemberDTO;
import com.kh.easy.member.model.mapper.MemberMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserDetailsService{
	
	private final MemberMapper mapper;
	
	@Override
	public UserDetails loadUserByUsername(String userId){
		/*
		 * 매개변수 userId는 받은 토큰에서 뽑은 username String
		 * UserDetails 오버라이드해서 Builder 쓰는 방법?
		 */
		MemberDTO user = mapper.findByUserId(userId);
		if(user == null) { 
			//없는 아이디 예외처리 
		}
		return CustomUserDetails.builder()
				.username(user.getUserId())
				.password(user.getUserPwd())
				.authorities(Collections.singletonList(new SimpleGrantedAuthority(user.getRole())))
				.build();
	}

}
