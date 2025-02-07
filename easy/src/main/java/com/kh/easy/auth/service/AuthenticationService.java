package com.kh.easy.auth.service;

import java.util.Map;

import com.kh.easy.member.model.dto.MemberDTO;

public interface AuthenticationService {
	Map<String, String> login(MemberDTO requestMember);
}
