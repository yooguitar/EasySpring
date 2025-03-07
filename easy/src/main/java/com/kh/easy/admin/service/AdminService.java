package com.kh.easy.admin.service;

import java.util.List;

import com.kh.easy.exception.member.MessagingException;
import com.kh.easy.member.model.dto.MailDTO;

import jakarta.validation.Valid;

public interface AdminService {

	/* 회원 관리 콘솔 */
	String findMembers(int page);
	String findMembersAsc(int page);
	String findByMail(int page);
	String findByMailAsc(int page);
	String findByStatus(int page);
	String findByStatusAsc(int page);
	String findByDate(int page);
	String findByDateAsc(int page);
	String findAdmin(int page);
	int findTotalCount();
	
	// 검색 기능
	void searchById(String searched);
	void searchByEmail(String searched);
	void blockUser(List<String> users);
	void unblockUser(List<String> users);
	void mailForUser(MailDTO mails);
	void mailForAll(MailDTO mails);
}
