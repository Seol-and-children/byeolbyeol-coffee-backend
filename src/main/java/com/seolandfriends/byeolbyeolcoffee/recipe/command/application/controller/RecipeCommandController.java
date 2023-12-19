package com.seolandfriends.byeolbyeolcoffee.recipe.command.application.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.seolandfriends.byeolbyeolcoffee.recipe.command.application.dto.RecipeDto;
import com.seolandfriends.byeolbyeolcoffee.recipe.command.application.service.RecipeCommandService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/recipes")
@Slf4j
public class RecipeCommandController {

	private final RecipeCommandService recipeCommandService;

	@Autowired
	public RecipeCommandController(RecipeCommandService recipeCommandService) {
		this.recipeCommandService = recipeCommandService;
	}

	/* 새로운 레시피 생성 (create) */
	@PostMapping
	public ResponseEntity<RecipeDto> createRecipe(@RequestPart MultipartFile recipeImage, @RequestPart @Valid RecipeDto recipeDto) {
		RecipeDto newRecipe = recipeCommandService.createRecipe(recipeDto, recipeImage);
		return ResponseEntity.ok(newRecipe);
	}

	/* 레시피 수정 (update) */
	@PutMapping("/{recipeId}")
	public ResponseEntity<RecipeDto> updateRecipe(@PathVariable Long recipeId,
		@RequestPart(required = false) MultipartFile recipeImage,
		@RequestPart @Valid RecipeDto recipeDto) {
		RecipeDto updatedRecipe = recipeCommandService.updateRecipe(recipeId, recipeDto, recipeImage);
		return ResponseEntity.ok(updatedRecipe);
	}

	/* Id로 레시피 삭제하기 (delete) */
	@DeleteMapping("/{recipeId}")
	public ResponseEntity<?> deleteRecipe(@PathVariable Long recipeId) {
		recipeCommandService.deleteRecipe(recipeId);
		return ResponseEntity.ok( "삭제 완료");
	}

}
