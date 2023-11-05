package com.seolandfriends.byeolbyeolcoffee.user.command.application.dto.signup.request;

import io.swagger.v3.oas.annotations.media.Schema;

// 사용자 회원가입 요청 데이터를 담는 DTO(Data Transfer Object) 클래스입니다.
public class SignUpRequest {

	// 회원 아이디를 나타내는 문자열 변수입니다.
	@Schema(description = "회원 아이디")
	private String account;

	// 회원 비밀번호를 나타내는 문자열 변수입니다.
	@Schema(description = "회원 비밀번호")
	private String password;

	// 회원 닉네임을 나타내는 문자열 변수입니다.
	@Schema(description = "회원 닉네임")
	private String nickname;

	// 회원 이메일 주소를 나타내는 문자열 변수입니다.
	@Schema(description = "회원 이메일")
	private String email;

	// SignUpRequest 객체를 생성하는 생성자입니다. 회원 아이디, 비밀번호, 닉네임, 이메일을 매개변수로 받아 초기화합니다.
	public SignUpRequest(String account, String password, String nickname, String email) {
		this.account = account;
		this.password = password;
		this.nickname = nickname;
		this.email = email;
	}

	// 회원 아이디를 반환하는 메서드입니다.
	public String getAccount() {
		return account;
	}

	// 회원 비밀번호를 반환하는 메서드입니다.
	public String getPassword() {
		return password;
	}

	// 회원 닉네임을 반환하는 메서드입니다.
	public String getNickname() {
		return nickname;
	}

	// 회원 이메일을 반환하는 메서드입니다.
	public String getEmail() {
		return email;
	}
}
