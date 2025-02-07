package com.kh.easy.admin.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.kh.easy.member.model.dto.Member;

@Mapper
public interface AdminMapper {

	ArrayList<Member> findAll();

}
