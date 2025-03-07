package com.kh.easy.token.model.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kh.easy.token.model.dto.DeleteTokenDTO;
import com.kh.easy.token.model.dto.RefreshTokenDTO;

@Mapper
public interface TokenMapper {

	void saveToken(RefreshTokenDTO token);

	void deleteExpiredRefreshToken(DeleteTokenDTO expiredToken);

	RefreshTokenDTO findByToken(String refreshToken);

	void deleteRefToken(String username);

}
