package com.kh.easy.member.model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kh.easy.admin.service.AdminServiceImpl;
import com.kh.easy.auth.model.vo.CustomUserDetails;
import com.kh.easy.exception.member.DuplicateUserException;
import com.kh.easy.exception.member.MismatchPasswordException;
import com.kh.easy.exception.member.NoSuchDataException;
import com.kh.easy.member.model.dto.ChangePasswordDTO;
import com.kh.easy.member.model.dto.MailDTO;
import com.kh.easy.member.model.dto.Member;
import com.kh.easy.member.model.dto.MemberDTO;
import com.kh.easy.member.model.mapper.MemberMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

	private final MemberMapper memberMapper;
	private final PasswordEncoder passwordEncoder;
	private final AdminServiceImpl adminService;

	@Override
	public void join(MemberDTO requestMember) {
		MemberDTO searched = memberMapper.findByUserId(requestMember.getUserId());
		if (searched != null) {
			throw new DuplicateUserException("이미 존재하는 아이디 입니다");
		}
		isMailUnique(requestMember.getEmail());
		Member member = Member.builder().userId(requestMember.getUserId())
				.userPwd(passwordEncoder.encode(requestMember.getUserPwd())).email(requestMember.getEmail()).build();
		memberMapper.join(member);
	}

	@Override
	public void updateInfo(@Valid ChangePasswordDTO changeEntity) {
		String username = passwordMatches(changeEntity.getCurrentPassword());
		String encodedPassword = passwordEncoder.encode(changeEntity.getNewPassword());
		isMailUnique(changeEntity.getEmail());
		Map<String, String> changeRequest = new HashMap();
		changeRequest.put("username", username);
		changeRequest.put("password", encodedPassword);
		changeRequest.put("email", changeEntity.getEmail());
		memberMapper.updateInfo(changeRequest);
	}

	@Override
	public void deleteByPassword(Map<String, String> password) {
		String username = passwordMatches(password.get("password"));
		memberMapper.deleteByPassword(username);
	}

	@Override
	public String findInfo(Map<String, String> email) {
		String result = memberMapper.findInfo(email);
		if (result == null) {
			throw new NoSuchDataException("조회 결과가 없습니다");
		}
		return result;
	}

	/**
	 * 메일 인증(비밀번호 변경) 1단계 findPwd(): 1. 사용자 입력 userId, email로 DB 조회(두 값이 같은 행에 존재하는
	 * 값이 없다면 예외 발생) 2. 임의값(문자열 4자리 수 랜덤)생성 후 MailDTO 객체 생성하여
	 * adminService.mailForUser(메일 전송 모듈)에 전달 3_1. HttpSession 사용을 위해 필드 주입 3_2. 세션에
	 * userId(Key), randomNum(Value) 저장 => 사용자가 메일 확인 후 입력하는 randomNum을 userId 키값으로
	 * 식별하기 위함 4. response 200ok => 다음 단계 이동 setIsSuccess(1)
	 * 
	 * 2단계 matchRandomNum(): 1. getAttribute(userId) => 앞단에서 localStorage 사용자 userId
	 * 값을 같이 넘겼음. 이를 키 값 으로 세션에서 randomNum 찾기 2. 일단 여기서 세션 찾으면 null 입니다. 3. 사용자 입력
	 * randomNum과 세션value(randomNum) 비교하여 다르면 예외 발생 4. response 200ok => 다음 단계 이동
	 * setIsSuccess(2)
	 * 
	 * 3단계 1. 변경할 비밀번호 입력 받고 인코딩하여 저장 => Valid 통과할 경우 다른 예외는 발생 X 2. response 200ok
	 * => 변경 완료
	 * 
	 */
	@Override
	public void findPwd(Map<String, String> request) {
		String userId = request.get("userId");
		String result = memberMapper.matchIdEmail(request);
		if (result == null) {
			throw new NoSuchDataException("조회 결과가 없습니다");
		}
		Random random = new Random();
		String randomNum = Integer.toString(1000 + random.nextInt(9000));

		MailDTO mail = new MailDTO();
		mail.setSender("admin");
		List<String> reciever = new ArrayList<>();
		reciever.add(userId);
		mail.setReciever(reciever);
		mail.setMailTitle("비밀번호 변경 메일입니다.");
		mail.setMailContent(randomNum + "   **알맞는 칸에 입력 해주세요");
		adminService.mailForUser(mail);
	}

	@Override
	public void matchRandomNum(Map<String, String> request) {
		String userId = request.get("userId");
		String randomNum = request.get("randomNum");

		// String sessions = (String)session.getAttribute(userId); // 사용자 요청 Id와 세션의 키값이
		// 같은걸 찾음

	}

	@Override
	public String getRole(MemberDTO requestMember) {
		return memberMapper.getRole(requestMember);
	}

	@Override
	public MemberDTO findUser(String username) {
		return memberMapper.login(username);
	}

	/* modules */
	private String passwordMatches(String password) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
		if (!passwordEncoder.matches(password, userDetails.getPassword())) {
			throw new MismatchPasswordException("비밀번호가 일치하지 않습니다.");
		}
		return userDetails.getUsername();
	}

	private void isMailUnique(String email) {
		String result = memberMapper.isMailUnique(email);
		if (result != null) {
			throw new DuplicateUserException("중복 email 입니다");
		}
	}

}
