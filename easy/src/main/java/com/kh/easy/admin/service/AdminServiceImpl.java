package com.kh.easy.admin.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kh.easy.admin.mapper.AdminMapper;
import com.kh.easy.common.model.vo.PageInfo;
import com.kh.easy.common.template.Pagination;
import com.kh.easy.exception.NoSuchDataException;
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
	


	
	
	
	
	
	
	
	

	
	// 검색
	@Override
	public void searchById(String searched) {
		
	}
	
	@Override
	public void searchByEmail(String searched) {
		
	}
}
