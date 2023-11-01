package com.seolandfriends.byeolbyeolcoffee.recipe.command.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.seolandfriends.byeolbyeolcoffee.recipe.command.application.dto.RecipeDto;
import com.seolandfriends.byeolbyeolcoffee.recipe.command.application.service.RecipeService;
import com.seolandfriends.byeolbyeolcoffee.recipe.command.domain.aggregate.entity.Recipe;

@RestController
@RequestMapping("/recipes")
public class RecipeController {

	private final RecipeService recipeService;

	@Autowired
	public RecipeController(RecipeService recipeService) {
		this.recipeService = recipeService;
	}

	// 새 레시피 생성
	@PostMapping
	public ResponseEntity<Recipe> createRecipe(@RequestBody RecipeDto recipeDto) {
		Recipe newRecipe = recipeService.createRecipe(recipeDto);
		return ResponseEntity.ok(newRecipe);
	}

	// 레시피 업데이트
	@PutMapping("/{recipeId}")
	public ResponseEntity<Recipe> updateRecipe(@PathVariable Long recipeId, @RequestBody RecipeDto recipeDto) {
		Recipe updatedRecipe = recipeService.updateRecipe(recipeId, recipeDto);
		return ResponseEntity.ok(updatedRecipe);
	}

	// 모든 레시피 가져오기
	@GetMapping
	public ResponseEntity<List<Recipe>> getAllRecipes() {
		List<Recipe> recipes = recipeService.getAllRecipes();
		return ResponseEntity.ok(recipes);
	}

	// ID로 레시피 가져오기
	@GetMapping("/{recipeId}")
	public ResponseEntity<Recipe> getRecipeById(@PathVariable Long recipeId) {
		Recipe recipe = recipeService.getRecipeById(recipeId);
		return ResponseEntity.ok(recipe);
	}
}
