package com.seolandfriends.byeolbyeolcoffee.recipe.query.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seolandfriends.byeolbyeolcoffee.recipe.command.domain.aggregate.entity.RecipeComment;

public interface RecipeCommentQueryRepository extends JpaRepository<RecipeComment, Long> {
	List<RecipeComment> findByRecipe_RecipeId(Long recipeId);
}
