package com.seolandfriends.byeolbyeolcoffee.user.command.domain.repository;


import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.seolandfriends.byeolbyeolcoffee.user.command.domain.aggregate.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findByUserId(Integer userId);

	User findByUserAccount(String userAccount);

	User findByUserEmail(String userEmail);

	User findByUserRole(Integer userRole);
	User findByUserNickName(String userNickName);

	// @Query("SELECT a.userId FROM User a WHERE a.userAccount = ?1")
	// UUID findUserIdByUserAccount(String orderUserAccount);


}