package com.seolandfriends.byeolbyeolcoffee.recipeOption.command.application.controller;

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

import com.seolandfriends.byeolbyeolcoffee.recipeOption.command.application.dto.FranchiseDTO;
import com.seolandfriends.byeolbyeolcoffee.recipeOption.command.application.dto.CustomOptionDTO;
import com.seolandfriends.byeolbyeolcoffee.recipeOption.command.application.service.OptionService;

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
	public ResponseEntity<CustomOptionDTO> createIngredients(@RequestBody CustomOptionDTO customOptionDTO) {
		CustomOptionDTO customOptionDTO1 = optionService.createIngredient(customOptionDTO);
		return ResponseEntity.ok(customOptionDTO1);
	}

	//기본 재료 사용 여부 수정(true/false)
	@PutMapping("/ingredients/process/{ingredientId}")
	public ResponseEntity<CustomOptionDTO> modifyProcessIngredients(@PathVariable Long ingredientId, @RequestBody CustomOptionDTO customOptionDTO){
		CustomOptionDTO modifyRecipeIngredient = optionService.processRecipeIngredient(ingredientId, customOptionDTO);
		return ResponseEntity.ok(modifyRecipeIngredient);
	}

	//기본 재료 정보 수정
	@PutMapping("/ingredients/info/{ingredientId}")
	public ResponseEntity<CustomOptionDTO> modifyIngredients(@PathVariable Long ingredientId, @RequestBody CustomOptionDTO customOptionDTO){
		CustomOptionDTO modifyRecipeIngredient = optionService.modifyRecipeIngredient(ingredientId, customOptionDTO.getIngredientName(),
			customOptionDTO.getIngredientUnit());
		return ResponseEntity.ok(modifyRecipeIngredient);
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
		FranchiseDTO modifyfranchise = optionService.modifyFranchise(franchiseId, franchiseDTO.getFranchiseName(),franchiseDTO.getFranchiseFontColor(), franchiseDTO.getFranchiseBackColor());
		return ResponseEntity.ok(modifyfranchise);
	}

}
