package com.seolandfriends.byeolbyeolcoffee.user.command.application.dto.user.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.UUID;

import com.seolandfriends.byeolbyeolcoffee.user.command.domain.aggregate.entity.User;
import com.seolandfriends.byeolbyeolcoffee.user.command.domain.aggregate.entity.enumtype.Role;

// 사용자 정보 응답 데이터를 담는 DTO(Data Transfer Object) 클래스입니다.
public class UserInfoResponse {

	// 회원 고유 식별자(UUID)를 나타내는 변수입니다.
	@Schema(description = "회원 고유키")
	private UUID id;

	// 회원 아이디를 나타내는 문자열 변수입니다.
	@Schema(description = "회원 아이디")
	private String account;

	// 회원 닉네임을 나타내는 문자열 변수입니다.
	@Schema(description = "회원 닉네임")
	private String nickname;

	// 회원 이메일 주소를 나타내는 문자열 변수입니다.
	@Schema(description = "회원 이메일")
	private String email;

	// 회원의 카카오 아이디를 나타내는 문자열 변수입니다.
	@Schema(description = "회원 카카오 아이디")
	private String kakaoId;

	// 카카오에서 제공하는 닉네임을 나타내는 문자열 변수입니다.
	@Schema(description = "카카오 닉네임")
	private String kakaoName;

	// 회원 상태를 나타내는 논리 변수입니다. (true = 정상 회원, false = 삭제된 회원)
	@Schema(description = "회원 상태")
	private boolean status;

	// 회원의 권한을 나타내는 Role 열거형 변수입니다.
	@Schema(description = "회원 권한")
	private Role role;

	// UserInfoResponse 객체를 생성하는 생성자입니다. 각 변수를 매개변수로 받아 초기화합니다.
	public UserInfoResponse(UUID id, String account, String nickname, String email, String kakaoId, String kakaoName, boolean status, Role role) {
		this.id = id;
		this.account = account;
		this.nickname = nickname;
		this.email = email;
		this.kakaoId = kakaoId;
		this.kakaoName = kakaoName;
		this.status = status;
		this.role = role;
	}

	// User 엔터티 객체에서 UserInfoResponse 객체로 변환하는 정적 메서드입니다.
	public static UserInfoResponse from(User user) {
		return new UserInfoResponse(
			user.getId(),
			user.getAccount(),
			user.getNickname(),
			user.getEmail(),
			user.getKakaoId(),
			user.getKakaoName(),
			user.isStatus(),
			user.getRole()
		);
	}

	// 아래는 각 변수의 Getter 메서드들입니다.

	public UUID getId() {
		return id;
	}

	public String getAccount() {
		return account;
	}

	public String getNickname() {
		return nickname;
	}

	public String getEmail() {
		return email;
	}

	public String getKakaoId() {
		return kakaoId;
	}

	public String getKakaoName() {
		return kakaoName;
	}

	public boolean isStatus() {
		return status;
	}

	public Role getRole() {
		return role;
	}
}
