package com.seolandfriends.byeolbyeolcoffee.recipe.query.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seolandfriends.byeolbyeolcoffee.recipe.command.domain.aggregate.entity.RecipeCustomOption;

public interface RecipeCustomOptionQueryRepository extends JpaRepository<RecipeCustomOption, Long> {
	List<RecipeCustomOption> findByRecipeId(Long recipeId);
}
