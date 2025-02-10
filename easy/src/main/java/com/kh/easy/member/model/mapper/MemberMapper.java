package com.kh.easy.member.model.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.kh.easy.member.model.dto.Member;
import com.kh.easy.member.model.dto.MemberDTO;

@Mapper
public interface MemberMapper { 

	void join(Member member);

	MemberDTO login(String request);

	MemberDTO findByUserId(String userId);

	void changePassword(Map<String, String> changeRequest);

	void deleteByPassword(String username);
	
	

}
