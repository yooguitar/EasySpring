package com.kh.easy.auth.util;

import java.util.Base64;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtUtil {

	@Value("${jwt.secret}")
	private String secretKey; 
	private SecretKey key;

	private long ACCESS_TOKEN_EXPIRED = 3600000 * 24;
	private long REFRESH_TOKEN_EXPIRED = 3600000 * 72;

	@PostConstruct
	public void init() {
		byte[] keyArr = Base64.getDecoder().decode(secretKey);
		this.key = Keys.hmacShaKeyFor(keyArr); 
	}

	private Date buildExpirationDate(long date) {
		long now = System.currentTimeMillis();
		return new Date(now + date);
	}

	public String getAccessToken(String username) {
		return Jwts.builder().subject(username).issuedAt(new Date())
				.expiration(buildExpirationDate(ACCESS_TOKEN_EXPIRED)).signWith(key).compact();
	}

	public String getRefreshToken(String username) {
		return Jwts.builder().subject(username).issuedAt(new Date())
				.expiration(buildExpirationDate(REFRESH_TOKEN_EXPIRED)).signWith(key).compact();
	}

	public Claims parseJwt(String token) {
		return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();
	}
}
