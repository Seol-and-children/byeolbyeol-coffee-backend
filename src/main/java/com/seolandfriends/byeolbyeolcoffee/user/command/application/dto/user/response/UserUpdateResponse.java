package com.seolandfriends.byeolbyeolcoffee.user.command.application.dto.user.response;

import com.seolandfriends.byeolbyeolcoffee.user.command.domain.aggregate.entity.User;

import io.swagger.v3.oas.annotations.media.Schema;

// 사용자 정보 수정 응답 데이터를 담는 DTO(Data Transfer Object) 클래스입니다.
public class UserUpdateResponse {

	// 회원 정보 수정 성공 여부를 나타내는 논리 변수입니다.
	@Schema(description = "회원 정보 수정 성공 여부")
	private boolean result;

	// 수정된 회원의 닉네임을 나타내는 문자열 변수입니다.
	@Schema(description = "회원 닉네임")
	private String nickname;

	// UserUpdateResponse 객체를 생성하는 생성자입니다. 수정 결과와 수정된 회원 닉네임을 매개변수로 받아 초기화합니다.
	public UserUpdateResponse(boolean result, String nickname) {
		this.result = result;
		this.nickname = nickname;
	}

	// 회원 정보 수정 결과를 담은 UserUpdateResponse 객체를 생성하는 정적 메서드입니다.
	public static UserUpdateResponse of(boolean result, User user) {
		// User 엔터티에서 닉네임 정보를 가져와서 UserUpdateResponse 객체를 생성하여 반환합니다.
		return new UserUpdateResponse(result, user.getNickname());
	}

	// 아래는 각 변수의 Getter 메서드들입니다.

	public boolean isResult() {
		return result;
	}

	public String getNickname() {
		return nickname;
	}
}
