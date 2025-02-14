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

	/* 회원 관리 콘솔 */
	private String JsonTranslator(ArrayList<Member> result) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			return objectMapper.writeValueAsString(result);
		} catch (JsonProcessingException e) {
			throw new NoSuchDataException("조회 결과가 없습니다.");
		}
	}
	
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

	@Override
	public Map<String, String> findMembers(int currentPage) {
		// 앞단에 조회 결과와 총 행 개수를 같이 전달해줘야 함
		int countRows = 0;
		countRows = totalCount(currentPage);
		PageInfo pageInfo = getPageInfo(countRows, currentPage);
		List<Member> result = memberMapper.findMemberList(getMemberList(pageInfo));
		String resultString = JsonTranslator(result);
		Map<String, String> resultMap = new HashMap();
		resultMap.put("resultString", resultString);
		resultMap.put("countRows", Long.valueOf(countRows));
		
		//반환타입 고쳐야됨
		
		
		return resultMap; 
	}

	public String findMembersAsc(int currentPage) {
		int count = totalCount(currentPage);
		PageInfo pageInfo = getPageInfo(count, currentPage);
		List<Member>result = memberMapper.findMemberListAsc(getMemberList(pageInfo));
		return JsonTranslator(result);
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
		List<Member>result = memberMapper.findByMailAsc(getMemberList(pageInfo));
		return JsonTranslator(result);
	}

	@Override
	public String findByStatus(int currentPage) {
		int count = totalCount(currentPage);
		PageInfo pageInfo = getPageInfo(count, currentPage);
		List<Member>result = memberMapper.findByStatus(getMemberList(pageInfo));
		return JsonTranslator(result);
	}

	@Override
	public String findByStatusAsc(int currentPage) {
		int count = totalCount(currentPage);
		PageInfo pageInfo = getPageInfo(count, currentPage);
		List<Member>result = memberMapper.findByStatusAsc(getMemberList(pageInfo));
		return JsonTranslator(result);
	}

	@Override
	public String findByDate(int currentPage) {
		int count = totalCount(currentPage);
		PageInfo pageInfo = getPageInfo(count, currentPage);
		List<Member>result = memberMapper.findByDate(getMemberList(pageInfo));
		return JsonTranslator(result);
	}

	@Override
	public String findByDateAsc(int currentPage) {
		int count = totalCount(currentPage);
		PageInfo pageInfo = getPageInfo(count, currentPage);
		List<Member>result = memberMapper.findByDateAsc(getMemberList(pageInfo));
		return JsonTranslator(result);
	}

	@Override
	public String findAdmin(int currentPage) {
		int count = totalCount(currentPage);
		PageInfo pageInfo = getPageInfo(count, currentPage);
		List<Member>result = memberMapper.findAdmin(getMemberList(pageInfo));
		return JsonTranslator(result);
	}
	
	// 검색
	@Override
	public void searchById(String searched) {

	}

	@Override
	public void searchByEmail(String searched) {

	}



	
}
