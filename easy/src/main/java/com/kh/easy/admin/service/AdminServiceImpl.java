package com.kh.easy.admin.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kh.easy.admin.mapper.AdminMapper;
import com.kh.easy.common.model.vo.PageInfo;
import com.kh.easy.common.template.Pagination;
import com.kh.easy.exception.member.NoRecieverException;
import com.kh.easy.exception.member.NoSuchDataException;
import com.kh.easy.member.model.dto.MailDTO;
import com.kh.easy.member.model.dto.Member;
import com.kh.easy.member.model.mapper.MemberMapper;

import jakarta.activation.DataSource;
import jakarta.activation.FileDataSource;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
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

	private RowBounds getMemberList(PageInfo pageInfo) {
		int offset = (pageInfo.getCurrentPage() - 1) * pageInfo.getBoardLimit();
		return new RowBounds(offset, pageInfo.getBoardLimit());
	}

	private int totalCount(int currentPage) {
		int totalCount = memberMapper.findTotalCount();
		if (totalCount == 0) {
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
	
	/**
	 * **메일링
	 * mailForUser() 
	 * 요청된 username으로 email을 조회하여 메일 발송 모듈로 전달.
	 * mailForAll()
	 * 모든 회원의 email을 조회하여 메일 발송 모듈로 전달
	 * mailSender()
	 * 매개변수로 받은 email(List<String>)을 반복문으로 메일 발송
	 * 순서 상관 없이 빠르면 되는 작업이기 때문에 Set이 유리하지만
	 * 설계 시 email 컬럼을 제약조건 없이 만들었으므로 중복값 발생 가능하여 List
	 */
	@Override
	public void mailForUser(MailDTO mails) {
		if (mails.getReciever() == null) { throw new NoRecieverException("수신자가 없습니다."); }
		List<String> emails = memberMapper.findEmail(mails.getReciever());
		mailSender(mails, emails);
	}

	@Override
	public void mailForAll(MailDTO mails) {
		List<String> emails = memberMapper.findEmailAll();
		mailSender(mails, emails);
	}
	
	private void mailSender(MailDTO mails, List<String> emails) {
		DataSource source = 
				new FileDataSource("src/main/resources/static/images/include.png"); // 빌드하게 되면 이미지 경로 외부로 빼야함

		for (String s : emails) {
			System.out.println("메일발송 성공 목록: " + s);
			try {
				MimeMessage message = sender.createMimeMessage();
				MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

				helper.setTo(s);
				// helper.setFrom(new InternetAddress("admin@kh.com", "EasySchedule")); 
				// 발신자도 지정하고 싶었으나 어차피 실제 메일 안쓰면 NPE 발생함. 생략
				helper.setSubject("[EasySchedule] " + mails.getMailTitle());
				helper.setText(mails.getMailContent());
				helper.addAttachment(source.getName(), source);

				sender.send(message);

			} catch (MessagingException e) {
				throw new RuntimeException("오류 발생. 메일 전송 실패!");
			}
		}

	}

	// 검색
	@Override
	public void searchById(String searched) {

	}

	@Override
	public void searchByEmail(String searched) {

	}

}
