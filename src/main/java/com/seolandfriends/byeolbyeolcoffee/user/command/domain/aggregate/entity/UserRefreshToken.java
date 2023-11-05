package com.seolandfriends.byeolbyeolcoffee.user.command.domain.aggregate.entity;

import java.util.UUID;

import javax.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

// NoArgsConstructor 어노테이션을 통해 매개변수 없는 생성자를 자동으로 생성합니다.
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
// 이 클래스가 JPA 엔터티임을 나타냅니다.
@Entity
// TBL_USER_REFRESH_TOKEN 테이블과 매핑됨을 나타냅니다.
@Table(name = "TBL_USER_REFRESH_TOKEN")
public class UserRefreshToken {
	// 엔터티의 기본 키로 사용되는 UUID 값입니다.
	@Id
	private UUID userId;

	// User 엔터티와 일대일 관계를 나타냅니다. 지연 로딩 방식으로 설정되어 있습니다.
	@OneToOne(fetch = FetchType.LAZY)
	@MapsId
	// user_id 컬럼과 매핑됨을 나타냅니다.
	@JoinColumn(name = "user_id")
	private User user;

	// Refresh Token 값을 저장하는 문자열 변수입니다.
	private String refreshToken;

	// Refresh Token 재발급 횟수를 저장하는 변수입니다. 기본값은 0입니다.
	private int reissueCount = 0;

	// User 엔터티와 Refresh Token 값을 받아 새로운 객체를 생성하는 생성자입니다.
	public UserRefreshToken(User user, String refreshToken) {
		this.user = user;
		this.refreshToken = refreshToken;
	}

	// Refresh Token 값을 업데이트하는 메서드입니다.
	public void updateRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	// 주어진 Refresh Token 값이 유효한지 확인하는 메서드입니다.
	public boolean validateRefreshToken(String refreshToken) {
		return this.refreshToken.equals(refreshToken);
	}

	// Refresh Token 재발급 횟수를 증가시키는 메서드입니다.
	public void increaseReissueCount() {
		reissueCount++;
	}
}
