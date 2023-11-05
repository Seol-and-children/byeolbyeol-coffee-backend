package com.seolandfriends.byeolbyeolcoffee.recipe.command.application.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seolandfriends.byeolbyeolcoffee.recipe.command.application.dto.RecipeDto;
import com.seolandfriends.byeolbyeolcoffee.recipe.command.domain.aggregate.entity.Recipe;
import com.seolandfriends.byeolbyeolcoffee.recipe.command.domain.repository.RecipeRepository;

@Service
public class RecipeService {
	private final RecipeRepository recipeRepository;
	ModelMapper modelMapper = new ModelMapper();

	@Autowired
	public RecipeService(RecipeRepository recipeRepository) {
		this.recipeRepository = recipeRepository;
	}

	/* 새로운 레시피 생성 메소드 */
	public RecipeDto createRecipe(RecipeDto recipeDto) {
		Recipe newRecipe = Recipe.builder()
			.recipeName(recipeDto.getRecipeName())
			.recipePhoto(recipeDto.getRecipePhoto())
			.description(recipeDto.getDescription())
			.franchiseCafe(recipeDto.getFranchiseCafe())
			.baseBeverage(recipeDto.getBaseBeverage())
			.customOptions(recipeDto.getCustomOptions())
			.author(recipeDto.getAuthor())
			.build();

		Recipe savedRecipe = recipeRepository.save(newRecipe);
		return modelMapper.map(savedRecipe, RecipeDto.class);
	}

	/* {recipeid}를 가진 레시피 정보 수정하기 */
	public RecipeDto updateRecipe(Long recipeId, RecipeDto recipeDto) {
		Recipe recipe = recipeRepository.findById(recipeId)
			.orElseThrow(() -> new RuntimeException("레시피를 찾을 수 없습니다. ID: " + recipeId));

		recipe.updateRecipe(
			recipeDto.getRecipeName(),
			recipeDto.getRecipePhoto(),
			recipeDto.getDescription(),
			recipeDto.getFranchiseCafe(),
			recipeDto.getBaseBeverage(),
			recipeDto.getCustomOptions()
		);

		Recipe savedRecipe = recipeRepository.save(recipe);
		return modelMapper.map(savedRecipe, RecipeDto.class);
	}

	/* 모든 레시피 정보 불러오기 */
	public List<RecipeDto> getAllRecipes() {
		List<Recipe> savedRecipes = recipeRepository.findAll();
		return savedRecipes.stream()
			.map(recipe -> modelMapper.map(recipe, RecipeDto.class))
			.collect(Collectors.toList());
	}

	/* {recipeId}를 가진 레시피 정보 불러오기 */
	public RecipeDto getRecipeById(Long recipeId) {
		Recipe savedRecipe = recipeRepository.findById(recipeId)
			.orElseThrow(() -> new RuntimeException("레시피를 찾을 수 없습니다. ID: " + recipeId));
		savedRecipe.incrementViewsCount();
		savedRecipe = recipeRepository.save(savedRecipe);
		return modelMapper.map(savedRecipe, RecipeDto.class);
	}

	/* {recipeId}를 가진 레시피 삭제하기 */
	public void deleteRecipe(Long recipeId) {
		if (!recipeRepository.existsById(recipeId)) {
			throw new RuntimeException("레시피를 찾을 수 없습니다. ID: " + recipeId);
		}
		recipeRepository.deleteById(recipeId);
	}

}