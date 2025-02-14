package com.kh.easy.member.model.service;

import java.util.Map;

import com.kh.easy.member.model.dto.ChangePasswordDTO;
import com.kh.easy.member.model.dto.MemberDTO;

public interface MemberService { 

	void join(MemberDTO requestMember);

	void updateInfo(ChangePasswordDTO changeEntity);

	void deleteByPassword(Map<String, String> password);

	String getRole(MemberDTO requestMember);

	MemberDTO findUser(String username);


}
