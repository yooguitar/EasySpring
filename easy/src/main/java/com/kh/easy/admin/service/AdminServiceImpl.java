package com.kh.easy.admin.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kh.easy.admin.mapper.AdminMapper;
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

	@Override
	public String findMembers() {
		return JsonTranslator(memberMapper.findMembers());
	}

	public String findMembersAsc() {
		return JsonTranslator(memberMapper.findMembersAsc());
	}

	@Override
	public String findAdmin() {
		return JsonTranslator(memberMapper.findAdmin());
	}

	@Override
	public String findAliveMembers() {
		return JsonTranslator(memberMapper.findAliveMembers());
	}

	@Override
	public String findDeadMembers() {
		return JsonTranslator(memberMapper.findDeadMembers());
	}

	@Override
	public String sortIdAsc() {
		return JsonTranslator(memberMapper.sortIdAsc());
	}

	@Override
	public String sortIdDesc() {
		return JsonTranslator(memberMapper.sortIdDesc());
	}

	// 검색
	@Override
	public void searchById(String searched) {

	}

	@Override
	public void searchByEmail(String searched) {

	}

}
