package com.seolandfriends.byeolbyeolcoffee.admin.command.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.seolandfriends.byeolbyeolcoffee.admin.command.domain.aggregate.entity.CustomOption;
@Repository
public interface CustomOptionRepository extends JpaRepository<CustomOption, Long> {
}
