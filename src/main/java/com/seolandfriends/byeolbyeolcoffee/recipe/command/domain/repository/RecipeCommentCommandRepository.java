package com.seolandfriends.byeolbyeolcoffee.recipe.command.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seolandfriends.byeolbyeolcoffee.recipe.command.domain.aggregate.entity.RecipeComment;

public interface RecipeCommentCommandRepository extends JpaRepository<RecipeComment, Long> {
	long countByParentCommentId(Long parentCommentId);
}
