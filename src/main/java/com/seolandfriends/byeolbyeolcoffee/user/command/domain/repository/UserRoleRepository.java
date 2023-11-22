package com.seolandfriends.byeolbyeolcoffee.user.command.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seolandfriends.byeolbyeolcoffee.user.command.domain.aggregate.entity.UserRole;
import com.seolandfriends.byeolbyeolcoffee.user.command.domain.aggregate.entity.UserRolePK;

public interface UserRoleRepository extends JpaRepository<UserRole, UserRolePK> {
}