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
import com.seolandfriends.byeolbyeolcoffee.common.entity.enumtype.Role;

import java.util.UUID;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@Getter
@Table(name = "USER")
public class User extends BaseEntity {

	// 회원 고유 식별자
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	// 로그인 아이디
	@Column(nullable = false, scale = 20, unique = true)
	private String account;

	// 비밀번호
	@Column(nullable = false)
	private String password;

	// 닉네임
	@Column(nullable = false)
	private String nickname;

	// 이메일 주소
	@Column(nullable = false)
	private String email;

	// 카카오 아이디
	private String kakaoId;

	// 카카오 닉네임
	private String kakaoName;

	// 회원 상태 (true = 정상 회원, false = 삭제된 회원)
	private boolean status;

	// 회원 역할 (Admin 또는 User)
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
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
}
