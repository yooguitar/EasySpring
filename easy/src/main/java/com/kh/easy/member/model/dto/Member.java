package com.kh.easy.member.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
@Builder
public class Member {
	private String userId;
	private String userPwd;
	private String email;
	private String role;
	private String status;
	private String enrollDate;
}
