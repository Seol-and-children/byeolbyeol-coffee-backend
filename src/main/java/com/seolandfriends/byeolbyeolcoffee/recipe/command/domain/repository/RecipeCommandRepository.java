package com.seolandfriends.byeolbyeolcoffee.recipe.command.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seolandfriends.byeolbyeolcoffee.recipe.command.domain.aggregate.entity.Recipe;

public interface RecipeCommandRepository extends JpaRepository<Recipe, Long> {

}