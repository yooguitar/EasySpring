package com.kh.easy.member.model.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kh.easy.member.model.dto.Member;
import com.kh.easy.member.model.dto.MemberDTO;

@Mapper
public interface MemberMapper { 

	void join(Member member);

	MemberDTO login(String request);

	MemberDTO findByUserId(String userId);
	
	

}
