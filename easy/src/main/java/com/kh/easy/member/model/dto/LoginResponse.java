package com.kh.easy.member.model.dto;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Getter
public class LoginResponse {
	private String username;
	private String role;
	private Map<String, String> tokens;

}
