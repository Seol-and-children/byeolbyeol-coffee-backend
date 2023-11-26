package com.seolandfriends.byeolbyeolcoffee.recipe.command.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seolandfriends.byeolbyeolcoffee.recipe.command.domain.aggregate.entity.RecipeComment;

public interface RecipeCommentRepository extends JpaRepository<RecipeComment, Long> {
	long countByParentCommentId(Long parentCommentId);

	List<RecipeComment> findByRecipe_RecipeId(Long recipeId);
}
