package com.kh.easy.member.model.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kh.easy.member.model.dto.MemberDTO;

@Mapper
public interface MemberMapper { 

	void join(MemberDTO requestMember);

	MemberDTO login(String request);
	
	

}
