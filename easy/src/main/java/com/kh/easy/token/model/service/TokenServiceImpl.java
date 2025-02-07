package com.kh.easy.token.model.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.kh.easy.auth.util.JwtUtil;
import com.kh.easy.token.model.dto.RefreshTokenDTO;
import com.kh.easy.token.model.mapper.TokenMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {
	
	private final JwtUtil tokenUtil;
	private final TokenMapper tokenMapper;

	@Override
	public Map<String, String> generateToken(String username) {
		Map<String, String> tokens = createTokens(username);
		saveToken(tokens.get("refreshToken"), username);
		return null;
	}
	
	private Map<String, String> createTokens(String username){
		String accessToken = tokenUtil.getAccessToken(username);
		String refreshToken = tokenUtil.getRefreshToken(username);
		Map<String, String> tokens = new HashMap();
		tokens.put("accessToken", accessToken);
		tokens.put("refreshToken", refreshToken);
		return tokens;
	}
	
	private void saveToken(String refreshToken, String username) {
		RefreshTokenDTO token = RefreshTokenDTO.builder().token(refreshToken).username(username)
								.expiration(System.currentTimeMillis() + 3600000L * 72).build();
		tokenMapper.saveToken(token);
	}
	
}
