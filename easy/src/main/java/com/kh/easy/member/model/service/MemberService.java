package com.kh.easy.member.model.service;

import java.util.Map;

import com.kh.easy.member.model.dto.ChangePasswordDTO;
import com.kh.easy.member.model.dto.MemberDTO;

import jakarta.validation.Valid;

public interface MemberService { 

	void join(MemberDTO requestMember);

	void changePassword(@Valid ChangePasswordDTO changeEntity);

	void deleteByPassword(Map<String, String> password);


}
