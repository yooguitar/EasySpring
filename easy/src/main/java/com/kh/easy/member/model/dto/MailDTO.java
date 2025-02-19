package com.kh.easy.member.model.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MailDTO {
	private String sender;
	private List<String> reciever;
	@NotBlank
	@Size(min = 1, max = 100, message = "너무 길거나 짧습니다.")
	private String mailTitle;
	@NotBlank
	@Size(min = 1, max = 2000, message = "너무 길거나 짧습니다.")
	private String mailContent;
	
}
