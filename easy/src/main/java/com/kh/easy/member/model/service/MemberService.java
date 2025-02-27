package com.kh.easy.member.model.service;

import java.util.Map;

import com.kh.easy.member.model.dto.ChangePasswordDTO;
import com.kh.easy.member.model.dto.MemberDTO;

import jakarta.validation.Valid;

public interface MemberService { 

	void join(MemberDTO requestMember);

	void updateInfo(ChangePasswordDTO changeEntity);

	void deleteByPassword(Map<String, String> password);

	String getRole(MemberDTO requestMember);

	MemberDTO findUser(String username);

	String findInfo(Map<String, String> email);

	void findPwd(Map<String, String> request);

	void matchRandomNum(Map<String, String> request);

	void newPwd(Map<String, String> request);


}
