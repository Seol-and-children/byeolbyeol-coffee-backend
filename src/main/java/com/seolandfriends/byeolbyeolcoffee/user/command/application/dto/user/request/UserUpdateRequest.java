package com.seolandfriends.byeolbyeolcoffee.user.command.application.dto.user.request;

import io.swagger.v3.oas.annotations.media.Schema;

// 사용자 정보 수정 요청 데이터를 담는 DTO(Data Transfer Object) 클래스입니다.
public class UserUpdateRequest {

	// 현재 비밀번호를 나타내는 문자열 변수입니다.
	@Schema(description = "회원 비밀번호", example = "1234")
	private String password;

	// 새로운 비밀번호를 나타내는 문자열 변수입니다.
	@Schema(description = "회원 새 비밀번호", example = "12345")
	private String newPassword;

	// 수정할 닉네임을 나타내는 문자열 변수입니다.
	@Schema(description = "회원 닉네임", example = "qwe")
	private String nickname;

	// UserUpdateRequest 객체를 생성하는 생성자입니다. 현재 비밀번호, 새로운 비밀번호, 닉네임을 매개변수로 받아 초기화합니다.
	public UserUpdateRequest(String password, String newPassword, String nickname) {
		this.password = password;
		this.newPassword = newPassword;
		this.nickname = nickname;
	}

	// 현재 비밀번호를 반환하는 메서드입니다.
	public String getPassword() {
		return password;
	}

	// 새로운 비밀번호를 반환하는 메서드입니다.
	public String getNewPassword() {
		return newPassword;
	}

	// 수정할 닉네임을 반환하는 메서드입니다.
	public String getNickname() {
		return nickname;
	}
}
