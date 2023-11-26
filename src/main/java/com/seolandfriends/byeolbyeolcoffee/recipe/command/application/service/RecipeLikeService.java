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
import com.seolandfriends.byeolbyeolcoffee.recipe.command.domain.repository.RecipeLikeRepository;
import com.seolandfriends.byeolbyeolcoffee.recipe.command.domain.repository.RecipeRepository;

@Service
public class RecipeLikeService {

	private final RecipeLikeRepository recipeLikeRepository;
	private final RecipeRepository recipeRepository;

	ModelMapper modelMapper = new ModelMapper();

	@Autowired
	public RecipeLikeService(RecipeLikeRepository recipeLikeRepository, RecipeRepository recipeRepository) {
		this.recipeLikeRepository = recipeLikeRepository;
		this.recipeRepository = recipeRepository;
	}

	/* 좋아요 토글 메소드 */
	public RecipeLikeDto toggleRecipeLike(RecipeLikeDto recipeLikeDto, Long recipeId) {
		Recipe recipe = recipeRepository.findById(recipeId)
			.orElseThrow(() -> new EntityNotFoundException("레시피를 찾을 수 없습니다."));
		LikeUserVO likeUserVO = new LikeUserVO(recipeLikeDto.getUserId());

		// 사용자가 이미 해당 레시피에 좋아요를 눌렀는지 확인
		Optional<RecipeLike> existingLike = recipeLikeRepository.findByRecipeAndLikeUserVO(recipe, likeUserVO);

		if (existingLike.isPresent()) {
			// 좋아요가 이미 등록되어 있다면 좋아요를 취소하고 카운트 감소
			recipe.decrementLikesCount();
			recipeLikeRepository.delete(existingLike.get());
		} else {
			// 좋아요가 등록되어 있지 않다면 좋아요를 등록하고 카운트 증가
			RecipeLike newRecipeLike = RecipeLike.builder()
				.recipe(recipe)
				.likeUserVO(likeUserVO)
				.build();
			recipe.incrementLikesCount();
			recipeLikeRepository.save(newRecipeLike);
		}
		recipeRepository.save(recipe);
		return modelMapper.map(recipeLikeDto, RecipeLikeDto.class);
	}
}