package com.kh.easy.auth.service;

import java.util.Collections;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kh.easy.auth.model.vo.CustomUserDetails;
import com.kh.easy.member.model.dto.MemberDTO;
import com.kh.easy.member.model.mapper.MemberMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserDetailsService {

	private final MemberMapper mapper;

	@Override
	public UserDetails loadUserByUsername(String userId) {
		MemberDTO user = mapper.login(userId);
		if (user == null) {
			throw new UsernameNotFoundException("존재하지 않는 사용자입니다.");
		}
		return CustomUserDetails.builder().username(user.getUserId()).password(user.getUserPwd())
				.authorities(Collections.singletonList(new SimpleGrantedAuthority(user.getRole()))).build();
	}

}
