package com.seolandfriends.byeolbyeolcoffee.recipe.query.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seolandfriends.byeolbyeolcoffee.recipe.command.application.dto.RecipeDto;
import com.seolandfriends.byeolbyeolcoffee.recipe.query.application.service.RecipeQueryService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/recipes")
@Slf4j
public class RecipeQueryController {

	private final RecipeQueryService recipeQueryService;

	@Autowired
	public RecipeQueryController(RecipeQueryService recipeQueryService) {
		this.recipeQueryService = recipeQueryService;
	}

	/* 모든 레시피 정보 가져오기 */
	@GetMapping
	public ResponseEntity<List<RecipeDto>> getAllRecipes() {
		List<RecipeDto> recipes = recipeQueryService.getAllRecipes();
		return ResponseEntity.ok(recipes);
	}

	/* Id로 레시피 가져오기 */
	@GetMapping("/{recipeId}")
	public ResponseEntity<RecipeDto> getRecipeById(@PathVariable Long recipeId) {
		RecipeDto recipe = recipeQueryService.getRecipeById(recipeId);
		log.info("query: {}",recipeId);
		return ResponseEntity.ok(recipe);
	}
}
