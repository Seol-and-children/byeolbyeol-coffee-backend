package com.seolandfriends.byeolbyeolcoffee.user.command.domain.aggregate.entity.enumtype;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
// 사용자 역할을 정의한 열거형(enum) 타입입니다.
public enum Role {

	// 각 역할에 대한 키(key)와 표시 제목(title)을 정의한 상수입니다.
	GUEST("ROLE_GUEST", "게스트"), // 게스트 역할을 나타냅니다.
	USER("ROLE_USER", "일반 사용자"), // 일반 사용자 역할을 나타냅니다.
	ADMIN("ROLE_ADMIN", "관리자"); // 관리자 역할을 나타냅니다.

	// 역할을 식별하는 키 값입니다.
	private final String key;
	// 역할의 표시 제목입니다.
	private final String title;
}
