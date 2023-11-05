package com.seolandfriends.byeolbyeolcoffee.user.command.domain.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.seolandfriends.byeolbyeolcoffee.user.command.domain.aggregate.entity.UserRefreshToken;

@Repository
@Transactional
public interface UserCommandRefreshTokenRepository extends JpaRepository<UserRefreshToken, UUID> {

	// 특정 사용자 ID에 해당하는 리프레시 토큰을 삭제하는 메서드
	void deleteByUserId(UUID userId);
}
