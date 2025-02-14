package com.kh.easy.member.model.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.RowBounds;

import com.kh.easy.member.model.dto.Member;
import com.kh.easy.member.model.dto.MemberDTO;

@Mapper
public interface MemberMapper { 

	/* 회원 서비스 */
	void join(Member member);

	MemberDTO login(String request);

	MemberDTO findByUserId(String userId);

	void updateInfo(Map<String, String> changeRequest);

	void deleteByPassword(String username);
	
	/* 토큰 */
	@Select("SELECT ROLE FROM MEMBER WHERE USER_ID=#{userId}")
	String getRole(MemberDTO username);

	/* 관리자 회원 관리 */
	
	int findTotalCount();

	List<Member> findMemberList(RowBounds rowBounds);
	
	List<Member> findMemberListAsc(RowBounds rowBounds);

	List<Member> findByMail(RowBounds memberList);

	List<Member> findByMailAsc(RowBounds memberList);

	List<Member> findByStatus(RowBounds memberList);

	List<Member> findByStatusAsc(RowBounds memberList);

	List<Member> findByDate(RowBounds memberList);

	List<Member> findByDateAsc(RowBounds memberList);

	List<Member> findAdmin(RowBounds memberList);


	
	ArrayList<Member> searchById(String searched);
	
	ArrayList<Member> searchByEmail(String searched);

	
	

}
