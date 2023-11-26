package com.seolandfriends.byeolbyeolcoffee.recipe.query.application.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.seolandfriends.byeolbyeolcoffee.recipe.command.application.dto.RecipeDto;
import com.seolandfriends.byeolbyeolcoffee.recipe.command.domain.aggregate.entity.Recipe;
import com.seolandfriends.byeolbyeolcoffee.recipe.query.domain.repository.RecipeQueryRepository;

@Service
public class RecipeQueryService {
	private final RecipeQueryRepository recipeQueryRepository;
	ModelMapper modelMapper = new ModelMapper();

	/* 이미지 저장 할 위치 및 응답 할 이미지 주소 */
	@Value("${image.image-dir}")
	private String IMAGE_DIR;

	@Value("${image.image-url}")
	private String IMAGE_URL;

	@Autowired
	public RecipeQueryService(RecipeQueryRepository recipeQueryRepository) {
		this.recipeQueryRepository = recipeQueryRepository;
	}

	/* 모든 레시피 정보 불러오기 */
	public List<RecipeDto> getAllRecipes() {
		List<Recipe> savedRecipes = recipeQueryRepository.findAll();
		return savedRecipes.stream()
			.map(recipe -> modelMapper.map(recipe, RecipeDto.class))
			.collect(Collectors.toList());
	}

	/* {recipeId}를 가진 레시피 정보 불러오기 */
	public RecipeDto getRecipeById(Long recipeId) {
		Recipe savedRecipe = recipeQueryRepository.findById(recipeId)
			.orElseThrow(() -> new RuntimeException("레시피를 찾을 수 없습니다. ID: " + recipeId));
		savedRecipe.incrementViewsCount();
		savedRecipe = recipeQueryRepository.save(savedRecipe);
		return modelMapper.map(savedRecipe, RecipeDto.class);
	}
}