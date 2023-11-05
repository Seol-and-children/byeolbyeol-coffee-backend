package com.seolandfriends.byeolbyeolcoffee.user.command.application.dto.login.request;

import io.swagger.v3.oas.annotations.media.Schema;

// 사용자 로그인 요청 데이터를 담는 DTO(Data Transfer Object) 클래스입니다.
public class LogInRequest {

	// 회원 아이디를 나타내는 문자열 변수입니다.
	@Schema(description = "회원 아이디")
	private String account;

	// 회원 비밀번호를 나타내는 문자열 변수입니다.
	@Schema(description = "회원 비밀번호")
	private String password;

	// SignInRequest 객체를 생성하는 생성자입니다. 회원 아이디와 비밀번호를 매개변수로 받아 초기화합니다.
	public LogInRequest(String account, String password) {
		this.account = account;
		this.password = password;
	}

	// 회원 아이디를 반환하는 메서드입니다.
	public String getAccount() {
		return account;
	}

	// 회원 비밀번호를 반환하는 메서드입니다.
	public String getPassword() {
		return password;
	}
}
