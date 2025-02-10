package com.kh.easy.admin.service;

public interface AdminService {
 
	/* 회원 관리 콘솔 */
	String findMembers();
	String findMembersAsc();
	String findAdmin();
	String findAliveMembers();
	String findDeadMembers();
	String sortIdAsc();
	String sortIdDesc();
	// 검색 기능
	void searchById(String searched);
	void searchByEmail(String searched);
}
