package com.kh.easy.member.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {
	
	@Pattern(regexp = "[a-zA-Z0-9]*$", message = "아이디는 영어나 숫자만 입력 가능합니다.")
	@Size(min = 4, max = 20, message = "아이디는 4-20자 사이로 입력하세요.")
	@NotBlank(message = "아이디를 입력해주세요.")
	private String userId;
	
	@Pattern(regexp = "[a-zA-Z0-9]*$", message = "비밀번호는 영어나 숫자만 입력 가능합니다.")
	@Size(min = 4, max = 20, message = "비밀번호는 4-20자 사이로 입력하세요.")
	@NotBlank(message = "비밀번호를 입력해주세요.")
	private String userPwd;
	
	@Pattern(regexp = "^[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+$", message = "올바른 이메일 형식을 입력 해주세요.")
	@Size(min = 6, max = 80, message = "너무 길거나 짧습니다.")
	@NotBlank(message = "이메일을 입력해주세요.")
	private String email;
	
	private String role;
	private String status;
	private String enrollDate;
	
}
