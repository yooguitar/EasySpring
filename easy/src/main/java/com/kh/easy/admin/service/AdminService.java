package com.kh.easy.admin.service;

import java.util.Map;

public interface AdminService {

	/* 회원 관리 콘솔 */
	Map<String, String> findMembers(int page);
	String findMembersAsc(int page);
	String findByMail(int page);
	String findByMailAsc(int page);
	String findByStatus(int page);
	String findByStatusAsc(int page);
	String findByDate(int page);
	String findByDateAsc(int page);
	String findAdmin(int page);
	
	// 검색 기능
	void searchById(String searched);
	void searchByEmail(String searched);
}
