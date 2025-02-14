package com.kh.easy.member.model.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kh.easy.auth.model.vo.CustomUserDetails;
import com.kh.easy.exception.DuplicateUserException;
import com.kh.easy.exception.MismatchPasswordException;
import com.kh.easy.member.model.dto.ChangePasswordDTO;
import com.kh.easy.member.model.dto.Member;
import com.kh.easy.member.model.dto.MemberDTO;
import com.kh.easy.member.model.mapper.MemberMapper;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

	private final MemberMapper memberMapper;
	private final PasswordEncoder passwordEncoder;

	@Override
	public void join(MemberDTO requestMember) {
		MemberDTO searched = memberMapper.findByUserId(requestMember.getUserId());
		if (searched != null) {
			throw new DuplicateUserException("이미 존재하는 아이디 입니다");
		}
		Member member = Member.builder().userId(requestMember.getUserId())
				.userPwd(passwordEncoder.encode(requestMember.getUserPwd())).email(requestMember.getEmail()).build();
		memberMapper.join(member);
	}

	@Override
	public void changePassword(@Valid ChangePasswordDTO changeEntity) {
		String username = passwordMatches(changeEntity.getCurrentPassword());
		String encodedPassword = passwordEncoder.encode(changeEntity.getNewPassword());
		Map<String, String> changeRequest = new HashMap();
		changeRequest.put("username", username);
		changeRequest.put("password", encodedPassword);
		memberMapper.changePassword(changeRequest);

	}

	@Override
	public void deleteByPassword(Map<String, String> password) {
		String username = passwordMatches(password.get("password"));
		memberMapper.deleteByPassword(username);
	}

	private String passwordMatches(String password) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
		if (!passwordEncoder.matches(password, userDetails.getPassword())) {
			throw new MismatchPasswordException("비밀번호가 일치하지 않습니다.");
		}
		return userDetails.getUsername();
	}

	@Override
	public String getRole(MemberDTO requestMember) {
		return memberMapper.getRole(requestMember);
	}

	@Override
	public MemberDTO findUser(String username) {
		return memberMapper.login(username);
	}

}
