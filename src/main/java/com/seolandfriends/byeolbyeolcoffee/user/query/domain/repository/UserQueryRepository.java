package com.seolandfriends.byeolbyeolcoffee.user.query.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seolandfriends.byeolbyeolcoffee.user.command.domain.aggregate.entity.User;

public interface UserQueryRepository extends JpaRepository<User, Integer> {
	User findByUserId(Integer userId);

	User findByUserAccount(String userAccount);
	Boolean existsByUserAccount(String userAccount);

	User findByUserEmail(String userEmail);
	Boolean existsByUserEmail(String userEmail);

	User findByUserRole(Integer userRole);
	User findByUserNickName(String userNickName);
	Boolean existsByUserNickName(String userNickName);

	Boolean existsByUserPassword(String userPassword);


}