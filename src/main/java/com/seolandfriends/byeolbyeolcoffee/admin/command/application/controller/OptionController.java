package com.seolandfriends.byeolbyeolcoffee.admin.command.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seolandfriends.byeolbyeolcoffee.admin.command.application.dto.FranchiseDTO;
import com.seolandfriends.byeolbyeolcoffee.admin.command.application.dto.RecipeIngredientDTO;
import com.seolandfriends.byeolbyeolcoffee.admin.command.application.service.OptionService;

@RestController
@RequestMapping("/option")
public class OptionController {
	private final OptionService optionService;

	@Autowired
	public OptionController(OptionService optionService){
		this.optionService = optionService;
	}

	/*기본 재공 재료 관련 메소드*/
	//새로운 기본 재료 생성
	@PostMapping("/ingredients")
	public ResponseEntity<RecipeIngredientDTO> createIngredients(@RequestBody RecipeIngredientDTO recipeIngredientDTO) {
		RecipeIngredientDTO recipeIngredientDTO1 = optionService.createIngredient(recipeIngredientDTO);
		return ResponseEntity.ok(recipeIngredientDTO1);
	}

	//기본 재료 사용 여부 수정(true/false)
	@PutMapping("/ingredients/process/{ingredientId}")
	public ResponseEntity<RecipeIngredientDTO> modifyProcessIngredients(@PathVariable Long ingredientId, @RequestBody RecipeIngredientDTO recipeIngredientDTO){
		RecipeIngredientDTO modifyRecipeIngredient = optionService.processRecipeIngredient(ingredientId, recipeIngredientDTO);
		return ResponseEntity.ok(modifyRecipeIngredient);
	}

	//기본 재료 정보 수정
	@PutMapping("/ingredients/info/{ingredientId}")
	public ResponseEntity<RecipeIngredientDTO> modifyIngredients(@PathVariable Long ingredientId, @RequestBody RecipeIngredientDTO recipeIngredientDTO){
		RecipeIngredientDTO modifyRecipeIngredient = optionService.modifyRecipeIngredient(ingredientId, recipeIngredientDTO.getIngredientName(),recipeIngredientDTO.getIngredientUnit());
		return ResponseEntity.ok(modifyRecipeIngredient);
	}

	//모든 기본 재료 정보 불러오기
	@GetMapping("/ingredients")
	public ResponseEntity<List<RecipeIngredientDTO>> getAllIngredients(){
		List<RecipeIngredientDTO> recipeIngredientDTO1 = optionService.getAllRecipeIngredient();
		return ResponseEntity.ok(recipeIngredientDTO1);
	}

	//특정 기본 재료 정보 불러오기
	@GetMapping("/ingredients/{ingredientId}")
	public ResponseEntity<RecipeIngredientDTO> getIngredients(@PathVariable Long ingredientId) {
		RecipeIngredientDTO recipeIngredientDTO = optionService.getRecipeIngredient(ingredientId);
		return ResponseEntity.ok(recipeIngredientDTO);
	}

	/*프렌차이즈 관련 메소드*/
	//프렌차이즈 정보 생성
	@PostMapping("/franchises")
	public ResponseEntity<FranchiseDTO> createFranchise(@RequestBody FranchiseDTO franchiseDTO) {
		FranchiseDTO createFranchise = optionService.createFranchise(franchiseDTO);
		return ResponseEntity.ok(createFranchise);
	}

	//프렌차이즈 사용 여부 수정(true/false)
	@PutMapping("/franchises/process/{franchiseId}")
	public ResponseEntity<FranchiseDTO> modifyProcessFranchise(@PathVariable Long franchiseId, @RequestBody FranchiseDTO franchiseDTO){
		FranchiseDTO modifyFranchise = optionService.processFranchise(franchiseId, franchiseDTO);
		return ResponseEntity.ok(modifyFranchise);
	}

	//프렌차이즈 정보 수정
	@PutMapping("/franchises/info/{franchiseId}")
	public ResponseEntity<FranchiseDTO> modifyFranchise(@PathVariable Long franchiseId, @RequestBody FranchiseDTO franchiseDTO){
		FranchiseDTO modifyfranchise = optionService.modifyFranchise(franchiseId, franchiseDTO.getFranchiseName(),franchiseDTO.getFranchiseFontColor(), franchiseDTO.getFranchiseBackColor(), franchiseDTO.getFranchiseImage());
		return ResponseEntity.ok(modifyfranchise);
	}

	//모든 프렌차이즈 정보 불러오기
	@GetMapping("/franchises")
	public ResponseEntity<List<FranchiseDTO>> getAllFranchise(){
		List<FranchiseDTO> AllFranchise = optionService.getAllFranchise();
		return ResponseEntity.ok(AllFranchise);
	}

	//특정 프렌차이즈 정보 불러오기
	@GetMapping("/franchises/{franchiseId}")
	public ResponseEntity<FranchiseDTO> getFranchise(@PathVariable Long franchiseId) {
		FranchiseDTO franchise = optionService.getFranchise(franchiseId);
		return ResponseEntity.ok(franchise);
	}
}
