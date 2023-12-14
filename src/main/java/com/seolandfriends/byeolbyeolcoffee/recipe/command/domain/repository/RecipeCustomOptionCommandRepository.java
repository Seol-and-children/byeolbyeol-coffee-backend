package com.seolandfriends.byeolbyeolcoffee.recipe.command.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seolandfriends.byeolbyeolcoffee.recipe.command.domain.aggregate.entity.RecipeCustomOption;

public interface RecipeCustomOptionCommandRepository extends JpaRepository<RecipeCustomOption, Long> {
}
