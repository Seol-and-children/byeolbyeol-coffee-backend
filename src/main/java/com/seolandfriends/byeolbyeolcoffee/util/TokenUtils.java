package com.seolandfriends.byeolbyeolcoffee.util;

import com.seolandfriends.byeolbyeolcoffee.exception.TokenException;
import com.seolandfriends.byeolbyeolcoffee.user.command.application.dto.UserDTO;
import com.seolandfriends.byeolbyeolcoffee.user.command.domain.aggregate.entity.UserRole;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;

@Component
@Slf4j
public class TokenUtils {

	private static String jwtSecretKey;
	private static Long tokenValidateTime;

	@Value("${jwt.key}")
	public void setJwtSecretKey(String jwtSecretKey) {
		TokenUtils.jwtSecretKey = jwtSecretKey;
	}

	@Value("${jwt.time}")
	public void setTokenValidateTime(Long tokenValidateTime) {
		TokenUtils.tokenValidateTime = tokenValidateTime;
	}

	public static String splitHeader(String header) {
		if(!header.equals("")) {
			return header.split(" ")[1];

		} else {
			return null;
		}
	}

	public static boolean isValidToken(String token) {
		try {
			Jwts.parser()
				.setSigningKey(DatatypeConverter.parseBase64Binary(jwtSecretKey))
				.parseClaimsJws(token);
			return true;
		} catch(SecurityException | MalformedJwtException e) {
			log.info("[TokenProvider] 잘못된 JWT 서명입니다.");
			throw new TokenException("잘못된 JWT 서명입니다.");
		} catch (ExpiredJwtException e){
			log.info("[TokenProvider] 만료된 JWT 토큰입니다.");
			throw new TokenException("만료된 JWT 토큰입니다.");
		} catch (UnsupportedJwtException e){
			log.info("[TokenProvider] 지원되지 않는 JWT 토큰입니다.");
			throw new TokenException("지원되지 않는 JWT 토큰입니다.");
		} catch (IllegalArgumentException e){
			log.info("[TokenProvider] JWT 토큰이 잘못되었습니다.");
			throw new TokenException("JWT 토큰이 잘못되었습니다.");
		}
	}

	public static Claims getClaimsFromToken(String token) {
		try {
			return Jwts
				.parser()
				.setSigningKey(DatatypeConverter.parseBase64Binary(jwtSecretKey))
				.parseClaimsJws(token).getBody();
		} catch (ExpiredJwtException e){
			return e.getClaims();
		}
	}

	public static String generateJwtToken(UserDTO user) {
		Date expireTime = new Date(System.currentTimeMillis() + tokenValidateTime);

		JwtBuilder builder = Jwts
			.builder()
			.setHeader(createHeader())
			.setClaims(createClaims(user))
			.setSubject(user.getUserAccount())
			.setExpiration(expireTime)
			.signWith(SignatureAlgorithm.HS256, createSignature());

		return builder.compact();
	}

	private static Map<String, Object> createHeader() {
		Map<String, Object> header = new HashMap<>();

		header.put("type", "jwt");
		header.put("alg", "HS256");
		header.put("date", System.currentTimeMillis());

		return header;
	}

	private static Map<String, Object> createClaims(UserDTO user) {
		Map<String, Object> claims = new HashMap<>();

		List<String> roles = new ArrayList<>();
		for(UserRole userRole : user.getUserRole()){
			roles.add(userRole.getRole().getRoleName());
		}

		claims.put("memberName", user.getUserNickName());
		claims.put("auth", roles);

		return claims;
	}

	private static Key createSignature() {
		byte[] secretBytes = DatatypeConverter.parseBase64Binary(jwtSecretKey);
		return new SecretKeySpec(secretBytes, SignatureAlgorithm.HS256.getJcaName());
	}
}