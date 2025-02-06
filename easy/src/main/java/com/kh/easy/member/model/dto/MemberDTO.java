package com.kh.easy.member.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class MemberDTO {
	private String userId;
	private String userPwd;
	private String email;
	private String role;
	private String status;
	private String enrollDate;
	
}
