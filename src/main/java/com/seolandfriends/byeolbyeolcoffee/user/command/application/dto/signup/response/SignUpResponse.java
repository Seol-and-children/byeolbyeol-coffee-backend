package com.seolandfriends.byeolbyeolcoffee.user.command.application.dto.signup.response;

import java.util.UUID;

import com.seolandfriends.byeolbyeolcoffee.user.command.domain.aggregate.entity.User;

import io.swagger.v3.oas.annotations.media.Schema;

// 사용자 회원가입 응답 데이터를 담는 DTO(Data Transfer Object) 클래스입니다.
public class SignUpResponse {

	// 회원 고유 식별자(UUID)를 나타내는 변수입니다.
	@Schema(description = "회원 고유키")
	private UUID id;

	// 회원 아이디를 나타내는 문자열 변수입니다.
	@Schema(description = "회원 아이디")
	private String account;

	// SignUpResponse 객체를 생성하는 생성자입니다. 회원 고유 식별자(UUID)와 회원 아이디를 매개변수로 받아 초기화합니다.
	public SignUpResponse(UUID id, String account) {
		this.id = id;
		this.account = account;
	}

	// 회원 고유 식별자(UUID)를 반환하는 메서드입니다.
	public UUID getId() {
		return id;
	}

	// 회원 아이디를 반환하는 메서드입니다.
	public String getAccount() {
		return account;
	}

	// User 엔터티 객체에서 SignUpResponse 객체로 변환하는 정적 메서드입니다.
	public static SignUpResponse from(User user) {
		return new SignUpResponse(
			user.getId(),
			user.getAccount()
		);
	}
}
