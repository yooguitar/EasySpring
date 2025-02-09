package com.kh.easy.token.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeleteTokenDTO {
	private String username;
	private Long currentTime;
}
