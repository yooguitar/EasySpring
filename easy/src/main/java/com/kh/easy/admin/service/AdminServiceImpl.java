package com.kh.easy.admin.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.kh.easy.admin.mapper.AdminMapper;
import com.kh.easy.member.model.dto.Member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
	private final AdminMapper adminMapper; 
	 
	@Override
	public ArrayList<Member> findAll() {
		ArrayList<Member> result = adminMapper.findAll();
		for(Member m : result) {
			System.out.println(m);
		}
		return null; 
	}

}
