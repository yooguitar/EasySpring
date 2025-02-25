package com.kh.easy.member.model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kh.easy.admin.service.AdminServiceImpl;
import com.kh.easy.auth.model.vo.CustomUserDetails;
import com.kh.easy.exception.member.DuplicateUserException;
import com.kh.easy.exception.member.ExpiredTimeException;
import com.kh.easy.exception.member.MismatchPasswordException;
import com.kh.easy.exception.member.NoSuchDataException;
import com.kh.easy.member.model.dto.ChangePasswordDTO;
import com.kh.easy.member.model.dto.MailDTO;
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
	 * 메일 인증(비밀번호 변경) 
	 * 
	 * 1단계 findPwd(): 
	 * 1. 사용자 입력 userId, email로 DB 조회(두 값이 같은 행에 존재하는 값이 없다면 예외 발생) 
	 * 2. 임의값(문자열 4자리 수 랜덤)생성 후 MailDTO 객체 생성하여
	 * adminService.mailForUser(메일 전송 모듈)에 전달
	 * 3. userId, 생성된 임의 값, ms단위의 현재 시각 맵에 담아서 DB에 저장
	 * 	(이 때 앞단에서 입력 대기시간 초과로 인증번호만 발급되고 인증이 중단되는 경우 발생 가능.
	 *  DB에는 한 명의 회원이 하나의 인증 번호만 갖고 있어야 하므로 저장 전 id 키 값으로 일치하는 행 모두 삭제. 
	 * 	이를 통해 회원은 새로 발급받은 인증번호만 사용 할 수 있음!)
	 * 4. response 200ok => 다음 단계 이동 setIsSuccess(1)
	 * 
	 * 2단계 matchRandomNum(): 
	 * 1. userId로 DB를 조회하여 인증 정보 행을 불러옴 (이 때 사용자는 무조건 한 행의 정보를 갖고 있는 상태)
	 * 2. DB에서 조회한 인증 번호와 사용자 입력 인증 번호가 일치하지 않는다면 예외 발생
	 * 3. 만료 시각도 비교. DB에 저장 된 시각 기준 현재 시각과 600000ms(10분) 넘게 차이가 난다면 예외 발생 
	 * 4. response 200ok => 다음 단계 이동 setIsSuccess(2)
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
		
		Map<String, String> verification = new HashMap<>();
		String expiredAt = String.valueOf(System.currentTimeMillis()); // 인증번호는 10분 후 만료
		verification.put("userId", userId);
		verification.put("randomNum", randomNum);
		verification.put("expiredAt", expiredAt);
		memberMapper.clearVerification(userId);
		memberMapper.verification(verification);
		

		MailDTO mail = new MailDTO();
		mail.setSender("admin");
		List<String> reciever = new ArrayList<>();
		reciever.add(userId);
		mail.setReciever(reciever);
		mail.setMailTitle("비밀번호 변경 메일입니다.");
		mail.setMailContent(randomNum + " //알맞는 칸에 입력 해주세요");
		adminService.mailForUser(mail);
	}

	@Override
	public void matchRandomNum(Map<String, String> request) {
		String userId = request.get("userId");
		
		Map<String, String> result = memberMapper.findVerification(userId);
		
		if(!request.get("randomNum").equals(String.valueOf(result.get("RANDOM_NUM")))) {
			throw new NoSuchDataException("일치하는 정보를 찾을 수 없습니다.");
		}
		
		String expStr = String.valueOf(result.get("EXPIRED_AT"));
		long expLong = Long.parseLong(expStr);
		long currentTime = System.currentTimeMillis();
		/* 
		 * DB에서 EXPIRED_AT이 NUMBER로 반환 되는데
		 * BigDecimal cannot be cast to java.lang....
		 * String 으로 강제 형변환 하거나 Long 으로 바로 받기 XXX
		 * => String.valueOf로 받고 long 으로 파싱
		 * */
		if((currentTime - expLong) > 600000) {
			System.out.println("이건 만료야~");
			throw new ExpiredTimeException("인증 시간이 만료되었습니다.");
		}
		
		memberMapper.clearVerification(userId);
	}
	
	@Override
	public void newPwd(Map<String, String> request) {
		String encode = passwordEncoder.encode(request.get("newPwd"));
		request.put("newPwd", encode);
		memberMapper.newPwd(request);
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
