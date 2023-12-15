package com.seolandfriends.byeolbyeolcoffee.search.command.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.seolandfriends.byeolbyeolcoffee.user.command.domain.aggregate.entity.User;

@Repository
public interface SearchNicknameRepository extends JpaRepository<User, Long> {
	List<User> findByUserNickNameContaining(String query);
}
