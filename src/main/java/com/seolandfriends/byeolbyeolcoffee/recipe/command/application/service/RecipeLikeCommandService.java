package com.seolandfriends.byeolbyeolcoffee.recipe.command.application.service;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seolandfriends.byeolbyeolcoffee.recipe.command.application.dto.RecipeLikeDto;
import com.seolandfriends.byeolbyeolcoffee.recipe.command.domain.aggregate.entity.Recipe;
import com.seolandfriends.byeolbyeolcoffee.recipe.command.domain.aggregate.entity.RecipeLike;
import com.seolandfriends.byeolbyeolcoffee.recipe.command.domain.aggregate.vo.LikeUserVO;
import com.seolandfriends.byeolbyeolcoffee.recipe.command.domain.repository.RecipeLikeCommandRepository;
import com.seolandfriends.byeolbyeolcoffee.recipe.command.domain.repository.RecipeCommandRepository;

@Service
public class RecipeLikeCommandService {

	private final RecipeLikeCommandRepository recipeLikeCommandRepository;
	private final RecipeCommandRepository recipeCommandRepository;

	ModelMapper modelMapper = new ModelMapper();

	@Autowired
	public RecipeLikeCommandService(RecipeLikeCommandRepository recipeLikeCommandRepository, RecipeCommandRepository recipeCommandRepository) {
		this.recipeLikeCommandRepository = recipeLikeCommandRepository;
		this.recipeCommandRepository = recipeCommandRepository;
	}

	/* 좋아요 토글 메소드 */
	public RecipeLikeDto toggleRecipeLike(RecipeLikeDto recipeLikeDto, Long recipeId) {
		Recipe recipe = recipeCommandRepository.findById(recipeId)
			.orElseThrow(() -> new EntityNotFoundException("레시피를 찾을 수 없습니다."));
		LikeUserVO likeUserVO = new LikeUserVO(recipeLikeDto.getUserId());

		// 사용자가 이미 해당 레시피에 좋아요를 눌렀는지 확인
		Optional<RecipeLike> existingLike = recipeLikeCommandRepository.findByRecipeAndLikeUserVO(recipe, likeUserVO);

		if (existingLike.isPresent()) {
			// 좋아요가 이미 등록되어 있다면 좋아요를 취소하고 카운트 감소
			recipe.decrementLikesCount();
			recipeLikeCommandRepository.delete(existingLike.get());
		} else {
			// 좋아요가 등록되어 있지 않다면 좋아요를 등록하고 카운트 증가
			RecipeLike newRecipeLike = RecipeLike.builder()
				.recipe(recipe)
				.likeUserVO(likeUserVO)
				.build();
			recipe.incrementLikesCount();
			recipeLikeCommandRepository.save(newRecipeLike);
		}
		recipeCommandRepository.save(recipe);
		return modelMapper.map(recipeLikeDto, RecipeLikeDto.class);
	}

	public boolean checkIfUserLikedRecipe(Long recipeId, int userId) {
		Recipe recipe = recipeCommandRepository.findById(recipeId)
			.orElseThrow(() -> new EntityNotFoundException("레시피를 찾을 수 없습니다."));
		LikeUserVO likeUserVO = new LikeUserVO(userId);

		// 좋아요 상태 확인
		Optional<RecipeLike> existingLike = recipeLikeCommandRepository.findByRecipeAndLikeUserVO(recipe, likeUserVO);
		return existingLike.isPresent();
	}
}