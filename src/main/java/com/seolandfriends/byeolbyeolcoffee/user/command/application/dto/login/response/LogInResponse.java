package com.seolandfriends.byeolbyeolcoffee.user.command.application.dto.login.response;

import com.seolandfriends.byeolbyeolcoffee.user.command.domain.aggregate.entity.enumtype.Role;

import io.swagger.v3.oas.annotations.media.Schema;

// 사용자 로그인 응답 데이터를 담는 DTO(Data Transfer Object) 클래스입니다.
public class LogInResponse {

	// 회원 닉네임을 나타내는 문자열 변수입니다.
	@Schema(description = "회원 닉네임")
	private String nickname;

	// 회원 이메일 주소를 나타내는 문자열 변수입니다.
	@Schema(description = "회원 이메일")
	private String email;

	// 회원 역할을 나타내는 Role 열거형 변수입니다.
	@Schema(description = "회원 유형")
	private Role role;

	// 사용자의 접근 토큰(Access Token)을 나타내는 문자열 변수입니다.
	@Schema(description = "Access Token")
	private String accessToken;

	// 사용자의 리프레시 토큰(Refresh Token)을 나타내는 문자열 변수입니다.
	@Schema(description = "Refresh Token")
	private String refreshToken;

	// SignInResponse 객체를 생성하는 생성자입니다.
	public LogInResponse(String nickname, String email, Role role, String accessToken, String refreshToken) {
		this.nickname = nickname;
		this.email = email;
		this.role = role;
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
	}

	// 회원 닉네임을 반환하는 메서드입니다.
	public String getNickname() {
		return nickname;
	}

	// 회원 이메일을 반환하는 메서드입니다.
	public String getEmail() {
		return email;
	}

	// 회원 역할(Role)을 반환하는 메서드입니다.
	public Role getRole() {
		return role;
	}

	// 사용자의 접근 토큰(Access Token)을 반환하는 메서드입니다.
	public String getAccessToken() {
		return accessToken;
	}

	// 사용자의 리프레시 토큰(Refresh Token)을 반환하는 메서드입니다.
	public String getRefreshToken() {
		return refreshToken;
	}
}
