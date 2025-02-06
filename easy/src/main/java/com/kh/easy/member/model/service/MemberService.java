package com.kh.easy.member.model.service;

import com.kh.easy.member.model.dto.MemberDTO;

public interface MemberService { 

	void join(MemberDTO requestMember);

	void login(MemberDTO requestMember);

}
