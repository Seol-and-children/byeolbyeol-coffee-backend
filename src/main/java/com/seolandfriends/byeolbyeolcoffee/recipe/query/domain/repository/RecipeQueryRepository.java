package com.seolandfriends.byeolbyeolcoffee.recipe.query.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seolandfriends.byeolbyeolcoffee.recipe.command.domain.aggregate.entity.Recipe;

public interface RecipeQueryRepository extends JpaRepository<Recipe, Long> {
}
