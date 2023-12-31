package com.seolandfriends.byeolbyeolcoffee.recipe.query.application.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.seolandfriends.byeolbyeolcoffee.recipe.command.application.dto.RecipeCustomOptionDto;
import com.seolandfriends.byeolbyeolcoffee.recipe.command.application.dto.RecipeDto;
import com.seolandfriends.byeolbyeolcoffee.recipe.command.domain.aggregate.entity.Recipe;
import com.seolandfriends.byeolbyeolcoffee.recipe.command.domain.aggregate.entity.RecipeCustomOption;
import com.seolandfriends.byeolbyeolcoffee.recipe.query.domain.repository.RecipeCustomOptionQueryRepository;
import com.seolandfriends.byeolbyeolcoffee.recipe.query.domain.repository.RecipeQueryRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RecipeQueryService {
	private final RecipeQueryRepository recipeQueryRepository;
	private final RecipeCustomOptionQueryRepository customOptionRepository;
	ModelMapper modelMapper = new ModelMapper();

	/* 이미지 저장 할 위치 및 응답 할 이미지 주소 */
	@Value("${image.image-dir}")
	private String IMAGE_DIR;

	@Value("${image.image-url}")
	private String IMAGE_URL;

	@Autowired
	public RecipeQueryService(RecipeQueryRepository recipeQueryRepository,
		RecipeCustomOptionQueryRepository customOptionRepository) {
		this.recipeQueryRepository = recipeQueryRepository;
		this.customOptionRepository = customOptionRepository;
	}

	/* 레시피 아이디로 작성자 닉네임 가져오기 */
	public String getUserNicknameByRecipeId(Long recipeId) {
		return recipeQueryRepository.findUserNicknameByRecipeId(recipeId);
	}

	public String getFranchiseNameByRecipeId(Long recipeId) {
		return recipeQueryRepository.findFranchiseNameByRecipeId(recipeId);
	}

	/* 모든 레시피 정보 불러오기 */
	public List<RecipeDto> getAllRecipes() {
		List<Recipe> savedRecipesWithUser = recipeQueryRepository.findAllRecipesWithUser();
		return savedRecipesWithUser.stream()
			.map(recipe -> {
				RecipeDto recipeDto = modelMapper.map(recipe, RecipeDto.class);
				recipeDto.setUserNickname(recipe.getAuthor().getUserNickname());
				recipeDto.setFranchiseName(recipe.getFranchiseCafeVO().getFranchiseName());
				return recipeDto;
			})
			.collect(Collectors.toList());
	}

	/* {recipeId}를 가진 레시피 정보 불러오기 */
	public RecipeDto getRecipeById(Long recipeId) {
		Recipe savedRecipe = recipeQueryRepository.findById(recipeId)
			.orElseThrow(() -> new RuntimeException("레시피를 찾을 수 없습니다. ID: " + recipeId));
		savedRecipe.incrementViewsCount();
		savedRecipe = recipeQueryRepository.save(savedRecipe);

		RecipeDto recipeDto = modelMapper.map(savedRecipe, RecipeDto.class);
		recipeDto.setUserNickname(getUserNicknameByRecipeId(savedRecipe.getRecipeId()));
		recipeDto.setFranchiseName(getFranchiseNameByRecipeId(savedRecipe.getRecipeId()));

		List<RecipeCustomOption> customOptions = customOptionRepository.findByRecipeId(recipeId);
		List<RecipeCustomOptionDto> customOptionDtos = customOptions.stream()
			.map(option -> {
				RecipeCustomOptionDto dto = modelMapper.map(option, RecipeCustomOptionDto.class);
				dto.setRecipeId(recipeId);
				return dto;
			})
			.collect(Collectors.toList());

		recipeDto.setCustomOptions(customOptionDtos);

		return recipeDto;
	}
}