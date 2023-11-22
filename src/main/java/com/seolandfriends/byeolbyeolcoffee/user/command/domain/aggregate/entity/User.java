package com.seolandfriends.byeolbyeolcoffee.user.command.domain.aggregate.entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.util.List;
import java.util.UUID;


@Entity
@Getter
@AllArgsConstructor
@Table(name = "TBL_USER")
@ToString
public class User {

	// 회원 고유 식별자
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "USER_ID")
	private UUID userId;

	// 로그인 아이디
	@Column(name = "user_account", nullable = false, unique = true)
	private String userAccount;

	// 비밀번호
	@Column(name = "user_password", nullable = false)
	private String userPassword;

	// 닉네임
	@Column(name = "user_nickname", nullable = false, unique = true)
	private String userNickName;

	// 이메일 주소
	@Column(name = "user_email", nullable = false, unique = true)
	private String userEmail;

	// 카카오 아이디
	@Column(name = "kakao_id")
	private String kakaoId;

	// 카카오 닉네임
	@Column(name = "kakaoName")
	private String kakaoName;

	// 회원 상태 (true = 정상 회원, false = 삭제된 회원)
	@Column(name = "user_status")
	private boolean status;

	// 회원 역할 (Admin 또는 User)
	@OneToMany
	@JoinColumn(name = "USER_ID")
	private List<UserRole> userRole;

	// OAuth 인증 제공자 (kakao 등)
	private String provider;
	// 해당 OAuth의 키 (id)
	private String provideID;
	// User 엔터티의 빌더 패턴 생성자

	protected User() {}

	public void setUserId(UUID userId) {
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

	public void setKakaoId(String kakaoId) {
		this.kakaoId = kakaoId;
	}

	public void setKakaoName(String kakaoName) {
		this.kakaoName = kakaoName;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public void setUserRole(
		List<UserRole> userRole) {
		this.userRole = userRole;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public void setProvideID(String provideID) {
		this.provideID = provideID;
	}

	public User build() {
		return new User(userId, userAccount, userPassword, userNickName, userEmail, kakaoId, kakaoName, status, userRole, provider, provideID);
	}

	@Override
	public String toString() {
		return "User{" +
			"userId=" + userId +
			", userAccount='" + userAccount + '\'' +
			", userPassword='" + userPassword + '\'' +
			", userNickName='" + userNickName + '\'' +
			", userEmail='" + userEmail + '\'' +
			", kakaoId='" + kakaoId + '\'' +
			", kakaoName='" + kakaoName + '\'' +
			", status=" + status +
			", userRole=" + userRole +
			", provider='" + provider + '\'' +
			", provideID='" + provideID + '\'' +
			'}';
	}
}