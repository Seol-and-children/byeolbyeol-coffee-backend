package com.seolandfriends.byeolbyeolcoffee.recipeOption.command.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.seolandfriends.byeolbyeolcoffee.recipeOption.command.domain.aggregate.entity.Franchise;
@Repository
public interface FranchiseRepository extends JpaRepository<Franchise, Long> {
}
