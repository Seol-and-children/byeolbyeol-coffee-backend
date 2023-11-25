package com.seolandfriends.byeolbyeolcoffee.recipe.command.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.seolandfriends.byeolbyeolcoffee.recipe.command.application.dto.RecipeDto;
import com.seolandfriends.byeolbyeolcoffee.recipe.command.application.service.RecipeService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/recipes")
@Slf4j
public class RecipeController {

	private final RecipeService recipeService;

	@Autowired
	public RecipeController(RecipeService recipeService) {
		this.recipeService = recipeService;
	}

	/* 새로운 레시피 생성 */
	@PostMapping
	public ResponseEntity<RecipeDto> createRecipe(@RequestPart MultipartFile recipeImage, @RequestPart RecipeDto recipeDto) {
		RecipeDto newRecipe = recipeService.createRecipe(recipeDto, recipeImage);
		return ResponseEntity.ok(newRecipe);
	}

	/* 레시피 수정 */
	@PutMapping("/{recipeId}")
	public ResponseEntity<RecipeDto> updateRecipe(@PathVariable Long recipeId, @RequestBody RecipeDto recipeDto, @RequestParam(name = "recipeImage", required = false) MultipartFile recipeImage) {
		RecipeDto updatedRecipe = recipeService.updateRecipe(recipeId, recipeDto, recipeImage);
		return ResponseEntity.ok(updatedRecipe);
	}

	/* 모든 레시피 정보 가져오기 */
	@GetMapping
	public ResponseEntity<List<RecipeDto>> getAllRecipes() {
		List<RecipeDto> recipes = recipeService.getAllRecipes();
		return ResponseEntity.ok(recipes);
	}

	/* Id로 레시피 가져오기 */
	@GetMapping("/{recipeId}")
	public ResponseEntity<RecipeDto> getRecipeById(@PathVariable Long recipeId) {
		RecipeDto recipe = recipeService.getRecipeById(recipeId);
		return ResponseEntity.ok(recipe);
	}

	/* Id로 레시피 삭제하기 */
	@DeleteMapping("/{recipeId}")
	public ResponseEntity<?> deleteRecipe(@PathVariable Long recipeId) {
		recipeService.deleteRecipe(recipeId);
		return ResponseEntity.ok( "삭제 완료");
	}

}
