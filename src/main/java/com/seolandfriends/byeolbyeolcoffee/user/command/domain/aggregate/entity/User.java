package com.seolandfriends.byeolbyeolcoffee.user.command.domain.aggregate.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.seolandfriends.byeolbyeolcoffee.common.entity.BaseEntity;
import com.seolandfriends.byeolbyeolcoffee.user.command.application.dto.sign_up.request.SignUpRequest;
import com.seolandfriends.byeolbyeolcoffee.user.command.application.dto.user.request.UserUpdateRequest;
import com.seolandfriends.byeolbyeolcoffee.user.command.domain.aggregate.entity.enumtype.Role;

import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;

// JPA 엔터티를 나타내는 어노테이션입니다.
@Entity
// 매개변수 없는 생성자를 자동으로 생성합니다. (protected로 제한됩니다.)
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@Getter
// USER 테이블과 매핑됨을 나타냅니다.
@Table(name = "USER")
public class User extends BaseEntity {

	// 회원 고유 식별자
	@Id
	// 자동 생성되는 값으로 설정됩니다.
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private UUID id;

	// 로그인 아이디
	@Column(name = "account", nullable = false, unique = true)
	private String account;

	// 비밀번호
	@Column(name = "password", nullable = false)
	private String password;

	// 닉네임
	@Column(name = "nickname", nullable = false, unique = true)
	private String nickname;

	// 이메일 주소
	@Column(name = "email", nullable = false, unique = true)
	private String email;

	// 카카오 아이디
	@Column(name = "kakao_id")
	private String kakaoId;

	// 카카오 닉네임
	@Column(name = "kakaoName")
	private String kakaoName;

	// 회원 상태 (true = 정상 회원, false = 삭제된 회원)
	@Column(name = "status")
	private boolean status;

	// 회원 역할 (Admin 또는 User)
	@Enumerated(EnumType.STRING)
	@Column(name = "role", nullable = false)
	private Role role;

	// OAuth 인증 제공자 (kakao 등)
	private String provider;

	// 해당 OAuth의 키 (id)
	private String provideID;

	// User 엔터티의 빌더 패턴 생성자
	@Builder
	public User(UUID id, String account, String password, String nickname, String email, String kakaoId,
		String kakaoName, boolean status, Role role, String provider, String provideID) {
		this.id = id;
		this.account = account;
		this.password = password;
		this.nickname = nickname;
		this.email = email;
		this.kakaoId = kakaoId;
		this.kakaoName = kakaoName;
		this.status = status;
		this.role = role;
		this.provider = provider;
		this.provideID = provideID;
	}

	// SignUpRequest 객체에서 User 엔터티로 변환하는 메서드입니다.
	public static User from(SignUpRequest request, PasswordEncoder encoder) {
		return User.builder()
			.account(request.getAccount())
			// 비밀번호를 인코딩하여 저장합니다.
			.password(encoder.encode(request.getPassword()))
			.nickname(request.getNickname())
			.email(request.getEmail())
			// 회원 역할을 USER로 설정하고, 회원 상태를 정상(true)으로 초기화합니다.
			.role(Role.USER)
			.status(true)
			.build();
	}

	// UserUpdateRequest 객체로 User 엔터티를 업데이트하는 메서드입니다.
	public void update(UserUpdateRequest newUser, PasswordEncoder encoder) {
		// 새로운 비밀번호가 제공되지 않았거나 빈 문자열인 경우 현재 비밀번호를 유지합니다.
		this.password = newUser.getNewPassword() == null || newUser.getNewPassword().isBlank()
			? this.password : encoder.encode(newUser.getNewPassword());
		// 새로운 닉네임으로 업데이트합니다.
		this.nickname = newUser.getNickname();
	}
}
