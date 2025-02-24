package com.kh.easy.token.model.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.kh.easy.auth.model.vo.CustomUserDetails;
import com.kh.easy.auth.util.JwtUtil;
import com.kh.easy.token.model.dto.DeleteTokenDTO;
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
		deleteExpiredRefreshToken(username);
		return tokens;
	}

	@Override
	public Map<String, String> refreshTokens(String refreshToken) {
		RefreshTokenDTO token = tokenMapper.findByToken(refreshToken);
		if (token == null || token.getExpiration() < System.currentTimeMillis()) {
			throw new RuntimeException("사용할 수 없는 토큰입니다.");
		}
		//Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		//CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();
		//return generateToken(user.getUsername());
		
		/*
		 * 만료일 검사 => 만료 시 예외 추가
		 * 남았다면 액세스 토큰 새로 만들어주기
		 * */
		
		return generateToken(token.getUsername());

	}

	private Map<String, String> createTokens(String username) {
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

	private void deleteExpiredRefreshToken(String username) {
		DeleteTokenDTO expiredToken = new DeleteTokenDTO(username, System.currentTimeMillis());
		tokenMapper.deleteExpiredRefreshToken(expiredToken);
	}

	@Override
	public void deleteRefToken(String username) {
		tokenMapper.deleteRefToken(username);
	}

}
