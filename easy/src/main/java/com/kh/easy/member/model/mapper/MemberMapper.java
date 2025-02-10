package com.kh.easy.member.model.mapper;

import java.util.ArrayList;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.kh.easy.member.model.dto.Member;
import com.kh.easy.member.model.dto.MemberDTO;

@Mapper
public interface MemberMapper { 

	/* 회원 서비스 */
	void join(Member member);

	MemberDTO login(String request);

	MemberDTO findByUserId(String userId);

	void changePassword(Map<String, String> changeRequest);

	void deleteByPassword(String username);

	/* 관리자 회원 관리 */
	ArrayList<Member> findMembers();

	ArrayList<Member> findMembersAsc();

	ArrayList<Member> findAdmin();

	ArrayList<Member> findAliveMembers();
	
	ArrayList<Member> findDeadMembers();

	ArrayList<Member> sortIdAsc();

	ArrayList<Member> sortIdDesc();

	ArrayList<Member> searchById(String searched);
	
	ArrayList<Member> searchByEmail(String searched);


	
	

}
