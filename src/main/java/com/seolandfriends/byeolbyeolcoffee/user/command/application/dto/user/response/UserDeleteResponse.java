package com.seolandfriends.byeolbyeolcoffee.user.command.application.dto.user.response;

import io.swagger.v3.oas.annotations.media.Schema;

// 사용자 삭제 응답 데이터를 담는 DTO(Data Transfer Object) 클래스입니다.
public class UserDeleteResponse {

	// 회원 삭제 성공 여부를 나타내는 논리 변수입니다.
	@Schema(description = "회원 삭제 성공 여부")
	private boolean result;

	// UserDeleteResponse 객체를 생성하는 생성자입니다. 삭제 결과를 매개변수로 받아 초기화합니다.
	public UserDeleteResponse(boolean result) {
		this.result = result;
	}

	// 삭제 결과를 반환하는 메서드입니다.
	public boolean isResult() {
		return result;
	}
}
