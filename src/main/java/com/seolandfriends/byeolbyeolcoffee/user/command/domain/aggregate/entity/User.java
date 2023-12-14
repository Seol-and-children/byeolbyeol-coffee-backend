package com.seolandfriends.byeolbyeolcoffee.user.command.domain.aggregate.entity;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.util.List;


@Entity
@Getter
@AllArgsConstructor
@Table(name = "user")
public class User {

	// 회원 고유 식별자
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id", unique = true)
	private Integer userId;

	// 로그인 아이디
	@Column(name = "user_Account", unique = true)
	private String userAccount;

	// 비밀번호
	@Column(name = "user_password")
	private String userPassword;

	// 닉네임
	@Column(name = "user_nickname", unique = true)
	private String userNickName;

	// 이메일 주소
	@Column(name = "user_email", unique = true)
	private String userEmail;

	//자기소개
	@Column(name = "user_bio")
	private String userBio;

	// 카카오 아이디
	@Column(name = "kakao_id")
	private String kakaoId;

	// 카카오 닉네임
	@Column(name = "kakaoName")
	private String kakaoName;

	// 회원 상태 (true = 정상 회원, false = 삭제된 회원)
	@Column(name = "user_status", columnDefinition = "boolean default true")
	private Boolean status = true;

	// 회원 역활
	@Column(name = "user_role", columnDefinition = "boolean default 2")
	private int userRole = 2;

	// OAuth 인증 제공자 (kakao 등)
	private String provider;
	// 해당 OAuth의 키 (id)
	private String provideID;
	// User 엔터티의 빌더 패턴 생성자

	protected User() {}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public void setUserNickName(String userNickName) {
		this.userNickName = userNickName;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public void setUserBio(String userBio) { this.userBio = userBio; }

	public void setKakaoId(String kakaoId) {
		this.kakaoId = kakaoId;
	}

	public void setKakaoName(String kakaoName) {
		this.kakaoName = kakaoName;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public  void  setUserRole(Integer userRole) { this.userRole = userRole; }

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public void setProvideID(String provideID) {
		this.provideID = provideID;
	}

	public User build() {
		return new User(userId, userAccount, userPassword, userNickName, userEmail, userBio, kakaoId, kakaoName, status, userRole, provider, provideID);
	}

	@Override
	public String toString() {
		return "User{" +
			"userId=" + userId +
			", userAccount='" + userAccount + '\'' +
			", userPassword='" + userPassword + '\'' +
			", userNickName='" + userNickName + '\'' +
			", userEmail='" + userEmail + '\'' +
			", userBio='" + userBio + '\'' +
			", kakaoId='" + kakaoId + '\'' +
			", kakaoName='" + kakaoName + '\'' +
			", status=" + status +
			", userRole=" + userRole +
			", provider='" + provider + '\'' +
			", provideID='" + provideID + '\'' +
			'}';
	}

}