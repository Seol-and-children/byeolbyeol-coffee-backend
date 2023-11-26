package com.seolandfriends.byeolbyeolcoffee.recipe.command.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seolandfriends.byeolbyeolcoffee.recipe.command.domain.aggregate.entity.Recipe;
import com.seolandfriends.byeolbyeolcoffee.recipe.command.domain.aggregate.entity.RecipeLike;
import com.seolandfriends.byeolbyeolcoffee.recipe.command.domain.aggregate.vo.LikeUserVO;

public interface RecipeLikeRepository extends JpaRepository<RecipeLike, Long> {
	Optional<RecipeLike> findByRecipeAndLikeUserVO(Recipe recipe, LikeUserVO likeUserVO);
}