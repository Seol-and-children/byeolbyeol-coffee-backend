package com.seolandfriends.byeolbyeolcoffee.user.command.domain.repositories;

import com.seolandfriends.byeolbyeolcoffee.user.command.domain.aggregate.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
	Optional<User> findByAccount(String account);
}
