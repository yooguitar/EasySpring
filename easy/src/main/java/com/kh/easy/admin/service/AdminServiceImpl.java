package com.kh.easy.admin.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kh.easy.admin.mapper.AdminMapper;
import com.kh.easy.common.model.vo.PageInfo;
import com.kh.easy.common.template.Pagination;
import com.kh.easy.exception.NoSuchDataException;
import com.kh.easy.member.model.dto.MailDTO;
import com.kh.easy.member.model.dto.Member;
import com.kh.easy.member.model.mapper.MemberMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
	private final AdminMapper adminMapper;
	private final MemberMapper memberMapper;
	private final JavaMailSenderImpl sender;

	/* 모듈 */
	private String JsonTranslator(List<Member> result) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			return objectMapper.writeValueAsString(result);
		} catch (JsonProcessingException e) {
			throw new NoSuchDataException("조회 결과가 없습니다.");
		}
	}
	private PageInfo getPageInfo(int totalCount, int page) {
		return Pagination.getPageInfo(totalCount, page, 10, 10);
	}
	private RowBounds getMemberList(PageInfo pageInfo){
		int offset = (pageInfo.getCurrentPage() -1) * pageInfo.getBoardLimit();
		return new RowBounds(offset, pageInfo.getBoardLimit());
	}
	private int totalCount(int currentPage) {
		int totalCount = memberMapper.findTotalCount();
		if(totalCount == 0) {
			throw new NoSuchDataException("등록된 회원 정보가 없습니다.");
		}
		return totalCount;
	}
	
	/* 회원 관리 콘솔 */
	@Override
	public String findMembers(int currentPage) {	
		int count = totalCount(currentPage);
		PageInfo pageInfo = getPageInfo(count, currentPage);
		return JsonTranslator(memberMapper.findMemberList(getMemberList(pageInfo)));
	}

	public String findMembersAsc(int currentPage) {
		int count = totalCount(currentPage);
		PageInfo pageInfo = getPageInfo(count, currentPage);
		return JsonTranslator(memberMapper.findMemberListAsc(getMemberList(pageInfo)));
	}
	
	@Override
	public String findByMail(int currentPage) {
		int count = totalCount(currentPage);
		PageInfo pageInfo = getPageInfo(count, currentPage);
		return JsonTranslator(memberMapper.findByMail(getMemberList(pageInfo)));
	}

	@Override
	public String findByMailAsc(int currentPage) {
		int count = totalCount(currentPage);
		PageInfo pageInfo = getPageInfo(count, currentPage);
		return JsonTranslator(memberMapper.findByMailAsc(getMemberList(pageInfo)));
	}

	@Override
	public String findByStatus(int currentPage) {
		int count = totalCount(currentPage);
		PageInfo pageInfo = getPageInfo(count, currentPage);
		return JsonTranslator(memberMapper.findByStatus(getMemberList(pageInfo)));
	}

	@Override
	public String findByStatusAsc(int currentPage) {
		int count = totalCount(currentPage);
		PageInfo pageInfo = getPageInfo(count, currentPage);
		return JsonTranslator(memberMapper.findByStatusAsc(getMemberList(pageInfo)));
	}

	@Override
	public String findByDate(int currentPage) {
		int count = totalCount(currentPage);
		PageInfo pageInfo = getPageInfo(count, currentPage);
		return JsonTranslator(memberMapper.findByDate(getMemberList(pageInfo)));
	}

	@Override
	public String findByDateAsc(int currentPage) {
		int count = totalCount(currentPage);
		PageInfo pageInfo = getPageInfo(count, currentPage);
		return JsonTranslator(memberMapper.findByDateAsc(getMemberList(pageInfo)));
	}

	@Override
	public String findAdmin(int currentPage) {
		int count = totalCount(currentPage);
		PageInfo pageInfo = getPageInfo(count, currentPage);
		return JsonTranslator(memberMapper.findAdmin(getMemberList(pageInfo)));
	}
	
	@Override
	public int findTotalCount() {
		return memberMapper.findTotalCount();
	}
	
	@Override
	public void blockUser(List<String> users) {
		memberMapper.blockUser(users);
	}

	@Override
	public void unblockUser(List<String> users) {
		memberMapper.unblockUser(users);
	}
	
	@Override
	public void mailForUser(MailDTO mails) {
		// 할 일
		// 1. reciever(List<String>)로 email 찾아오기
		List<String> emails = memberMapper.findEmail(mails.getReciever());
		/* 
		 * 찾아 왔다. emails에는 string배열이 담겨있다.
		 * */
		// 2. 메일 보내주기
		
		
		
		
	}


	
	
	
	
	
	
	
	

	
	// 검색
	@Override
	public void searchById(String searched) {
		
	}
	
	@Override
	public void searchByEmail(String searched) {
		
	}
}
