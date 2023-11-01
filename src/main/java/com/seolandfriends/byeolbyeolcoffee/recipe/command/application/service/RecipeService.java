package com.seolandfriends.byeolbyeolcoffee.recipe.command.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seolandfriends.byeolbyeolcoffee.recipe.command.application.dto.RecipeDto;
import com.seolandfriends.byeolbyeolcoffee.recipe.command.domain.aggregate.entity.Recipe;
import com.seolandfriends.byeolbyeolcoffee.recipe.command.domain.repository.RecipeRepository;

@Service
public class RecipeService {

	private final RecipeRepository recipeRepository;

	@Autowired
	public RecipeService(RecipeRepository recipeRepository) {
		this.recipeRepository = recipeRepository;
	}

	/*
	 * 새로운 레시피 생성 메소드
	 * */
	public Recipe createRecipe(RecipeDto recipeDto) {
		Recipe newRecipe = Recipe.builder()
			.recipeName(recipeDto.getRecipeName())
			.recipePhoto(recipeDto.getRecipePhoto())
			.description(recipeDto.getDescription())
			.franchiseCafe(recipeDto.getFranchiseCafe())
			.baseBeverage(recipeDto.getBaseBeverage())
			.customOptions(recipeDto.getCustomOptions())
			.author(recipeDto.getAuthor())
			.build();

		return recipeRepository.save(newRecipe);
	}

	/*
	 * {recipeid}를 가진 레시피 정보 수정하기
	 * */
	public Recipe updateRecipe(Long recipeId, RecipeDto recipeDto) {
		Recipe recipe = recipeRepository.findById(recipeId)
			.orElseThrow(() -> new RuntimeException("레시피를 찾을 수 없습니다. ID: " + recipeId));

		recipe.updateRecipe(
			recipeDto.getRecipeName(),
			recipeDto.getRecipePhoto(),
			recipeDto.getDescription(),
			recipeDto.getFranchiseCafe(),
			recipeDto.getBaseBeverage(),
			recipeDto.getCustomOptions(),
			recipeDto.getAuthor()
		);

		return recipeRepository.save(recipe);
	}

	/*
	 모든 레시피 정보 불러오기
	**/
	public List<Recipe> getAllRecipes() {
		return recipeRepository.findAll();
	}

	/*
	 * {recipeId}를 가진 레시피 정보 불러오기
	 * */
	public Recipe getRecipeById(Long recipeId) {
		return recipeRepository.findById(recipeId)
			.orElseThrow(() -> new RuntimeException("레시피를 찾을 수 없습니다. ID: " + recipeId));
	}

}