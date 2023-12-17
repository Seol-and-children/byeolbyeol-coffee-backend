package com.seolandfriends.byeolbyeolcoffee.user.query.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seolandfriends.byeolbyeolcoffee.user.command.domain.aggregate.entity.User;

public interface UserQueryRepository extends JpaRepository<User, Integer> {
	User findByUserId(Integer userId);

	User findByUserAccount(String userAccount);

	User findByUserEmail(String userEmail);

	User findByUserRole(Integer userRole);
	User findByUserNickName(String userNickName);

}