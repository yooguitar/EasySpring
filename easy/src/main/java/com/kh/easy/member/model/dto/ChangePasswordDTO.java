package com.kh.easy.member.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ChangePasswordDTO {
	
	@Pattern(regexp = "[a-zA-Z0-9]*$", message = "비밀번호는 영어나 숫자만 입력 가능합니다.")
	@Size(min = 4, max = 20, message = "비밀번호는 4-20자 사이로 입력하세요.")
	@NotBlank(message="현재 비밀번호를 입력해주세요")
	private String currentPassword;
	
	@Pattern(regexp = "[a-zA-Z0-9]*$", message = "비밀번호는 영어나 숫자만 입력 가능합니다.")
	@Size(min = 4, max = 20, message = "비밀번호는 4-20자 사이로 입력하세요.")
	@NotBlank(message="새 비밀번호를 입력해주세요")
	private String newPassword;
}
