package com.seolandfriends.byeolbyeolcoffee.recipeOption.query.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seolandfriends.byeolbyeolcoffee.recipeOption.command.application.dto.CustomOptionDTO;
import com.seolandfriends.byeolbyeolcoffee.recipeOption.command.application.dto.FranchiseDTO;
import com.seolandfriends.byeolbyeolcoffee.recipeOption.query.application.service.OptionQueryService;

@RestController
@RequestMapping("/option")
public class OptionQueryController {
	private final OptionQueryService optionQueryService;

	@Autowired
	public OptionQueryController(OptionQueryService optionQueryService){
		this.optionQueryService = optionQueryService;
	}

	/*기본 재공 재료 관련 메소드*/
	//모든 기본 재료 정보 불러오기
	@GetMapping("/ingredients")
	public ResponseEntity<List<CustomOptionDTO>> getAllIngredients(){
		List<CustomOptionDTO> customOptionDTO1 = optionQueryService.getAllRecipeIngredient();
		return ResponseEntity.ok(customOptionDTO1);
	}

	//특정 기본 재료 정보 불러오기
	@GetMapping("/ingredients/{ingredientId}")
	public ResponseEntity<CustomOptionDTO> getIngredients(@PathVariable Long ingredientId) {
		CustomOptionDTO customOptionDTO = optionQueryService.getRecipeIngredient(ingredientId);
		return ResponseEntity.ok(customOptionDTO);
	}


	/*프렌차이즈 관련 메소드*/
	//모든 프렌차이즈 정보 불러오기
	@GetMapping("/franchises")
	public ResponseEntity<List<FranchiseDTO>> getAllFranchise(){
		List<FranchiseDTO> AllFranchise = optionQueryService.getAllFranchise();
		return ResponseEntity.ok(AllFranchise);
	}

	//특정 프렌차이즈 정보 불러오기
	@GetMapping("/franchises/{franchiseId}")
	public ResponseEntity<FranchiseDTO> getFranchise(@PathVariable Long franchiseId) {
		FranchiseDTO franchise = optionQueryService.getFranchise(franchiseId);
		return ResponseEntity.ok(franchise);
	}

}
