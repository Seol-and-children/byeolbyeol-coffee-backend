package com.seolandfriends.byeolbyeolcoffee.recipe.query.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.seolandfriends.byeolbyeolcoffee.recipe.command.domain.aggregate.entity.Recipe;
import com.seolandfriends.byeolbyeolcoffee.recipe.command.domain.aggregate.vo.CustomOptionVO;

public interface RecipeQueryRepository extends JpaRepository<Recipe, Long> {
	@Query("SELECT r FROM Recipe r JOIN FETCH r.author JOIN FETCH r.franchiseCafeVO")
	List<Recipe> findAllRecipesWithUser();

	@Query("SELECT r.author.userNickname FROM Recipe r WHERE r.recipeId = :recipeId")
	String findUserNicknameByRecipeId(@Param("recipeId") Long recipeId);

	@Query("SELECT r.franchiseCafeVO.franchiseName FROM Recipe r WHERE r.recipeId = :recipeId")
	String findFranchiseNameByRecipeId(@Param("recipeId") Long recipeId);

	@Query("SELECT co FROM CustomOptionVO co WHERE co.customOptionId = :customOptionId")
	CustomOptionVO findCustomOptionById(@Param("customOptionId") Long customOptionId);
}
