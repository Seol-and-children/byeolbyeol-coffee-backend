package com.seolandfriends.byeolbyeolcoffee.jwt.handler;


import com.seolandfriends.byeolbyeolcoffee.common.AuthConstants;
import com.seolandfriends.byeolbyeolcoffee.user.command.application.dto.TokenDTO;
import com.seolandfriends.byeolbyeolcoffee.user.command.application.dto.UserDTO;
import com.seolandfriends.byeolbyeolcoffee.user.command.domain.aggregate.entity.User;
import com.seolandfriends.byeolbyeolcoffee.util.TokenUtils;

import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TokenProvider {

	private final UserDetailsService userDetailsService;
	private ModelMapper modelMapper;

	public TokenProvider(UserDetailsService userDetailsService, ModelMapper modelMapper) {
		this.userDetailsService = userDetailsService;
		this.modelMapper = modelMapper;
	}

	public TokenDTO generateTokenDTO(User user) {
		UserDTO userDTO = modelMapper.map(user, UserDTO.class);

		if (userDTO == null || userDTO.getUserAccount() == null || userDTO.getUserRole() == 0) {
			throw new IllegalArgumentException("UserDTO 매핑 오류");
		}

		String jwtToken;
		try {
			jwtToken = TokenUtils.generateJwtToken(userDTO);
		} catch (Exception e) {
			log.error("JWT 토큰 생성 중 오류 발생", e);
			throw new RuntimeException("JWT 토큰 생성 실패");
		}

		return new TokenDTO(AuthConstants.TOKEN_TYPE, user.getUserId(), jwtToken);
	}

	public String getUserId(String token) {
		return TokenUtils.getClaimsFromToken(token).getSubject();
	}

	public Authentication getAuthentication(String token){

		log.info("[TokenProvider] getAuthentication Start =============================== ");
		Claims claims = TokenUtils.getClaimsFromToken(token);

		if(claims.get("auth") == null) {
			throw new RuntimeException("권한 정보가 없는 토큰입니다.");
		}

		UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUserId(token));
		log.info("[TokenProvider] ===================== {}",  userDetails.getAuthorities());

		log.info("[TokenProvider] getAuthentication End =============================== ");
		return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
	}

	public void blacklistToken(String token) {
	}


}