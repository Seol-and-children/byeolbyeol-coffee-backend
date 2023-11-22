package com.seolandfriends.byeolbyeolcoffee.recipe.command.application.service;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seolandfriends.byeolbyeolcoffee.recipe.command.application.dto.RecipeLikeDto;
import com.seolandfriends.byeolbyeolcoffee.recipe.command.domain.aggregate.entity.Recipe;
import com.seolandfriends.byeolbyeolcoffee.recipe.command.domain.aggregate.entity.RecipeLike;
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

	/* 좋아요 등록 메소드 */
	public RecipeLikeDto createRecipeLike(RecipeLikeDto recipeLikeDto, Long recipeId) {
		Recipe recipe = recipeRepository.findById(recipeId)
			.orElseThrow(() -> new EntityNotFoundException("레시피를 찾을 수 없습니다."));
		RecipeLike newRecipeLike = RecipeLike.builder()
			.recipe(recipe)
			.likeUser(recipeLikeDto.getLikeUser())
			.build();
		RecipeLike savedLike = recipeLikeRepository.save(newRecipeLike);
		recipe.incrementLikesCount();
		recipeRepository.save(recipe);
		return modelMapper.map(savedLike, RecipeLikeDto.class);
	}

	/* 좋아요 삭제 메소드 */
	public void deleteRecipeLike(Long likeId, Long recipeId) {
		Recipe recipe = recipeRepository.findById(recipeId)
			.orElseThrow(() -> new EntityNotFoundException("레시피를 찾을 수 없습니다."));
		if (!recipeLikeRepository.existsById(likeId)) {
			throw new RuntimeException("좋아요를 찾을 수 없습니다. ID: " + likeId);
		}
		recipe.decrementLikesCount();
		recipeRepository.save(recipe);
		recipeLikeRepository.deleteById(likeId);
	}
}