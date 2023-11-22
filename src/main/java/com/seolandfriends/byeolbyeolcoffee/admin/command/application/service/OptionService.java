package com.seolandfriends.byeolbyeolcoffee.admin.command.application.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seolandfriends.byeolbyeolcoffee.admin.command.application.dto.FranchiseDTO;
import com.seolandfriends.byeolbyeolcoffee.admin.command.application.dto.RecipeIngredientDTO;
import com.seolandfriends.byeolbyeolcoffee.admin.command.domain.aggregate.entity.Franchise;
import com.seolandfriends.byeolbyeolcoffee.admin.command.domain.aggregate.entity.RecipeIngredient;
import com.seolandfriends.byeolbyeolcoffee.admin.command.domain.repository.FranchiseRepository;
import com.seolandfriends.byeolbyeolcoffee.admin.command.domain.repository.RecipeIngredientRepository;

@Service
public class OptionService {
	private final RecipeIngredientRepository recipeIngredientRepository;
	private final FranchiseRepository franchiseRepository;
	private final ModelMapper modelMapper;
	@Autowired
	public OptionService(RecipeIngredientRepository recipeIngredientRepository, ModelMapper modelMapper, FranchiseRepository franchiseRepository){
		this.recipeIngredientRepository = recipeIngredientRepository;
		this.franchiseRepository = franchiseRepository;
		this.modelMapper = modelMapper;
	}

	/*기본 재공 관련 메소드*/
	// 새로운 기본 재료 생성
	@Transactional
	public RecipeIngredientDTO createIngredient(RecipeIngredientDTO recipeIngredientDTO) {
		RecipeIngredient newIngredient = RecipeIngredient.builder()
			.ingredientName(recipeIngredientDTO.getIngredientName())
			.ingredientUnit(recipeIngredientDTO.getIngredientUnit())
			.processing(recipeIngredientDTO.isProcessing())
			.build();
		RecipeIngredient ingredient = recipeIngredientRepository.save(newIngredient);
		return modelMapper.map(ingredient, RecipeIngredientDTO.class);
	}

	//모든 기본 재료들 불러오기
	public List<RecipeIngredientDTO> getAllRecipeIngredient(){
		List<RecipeIngredient> recipeIngredientList = recipeIngredientRepository.findAll();
		return recipeIngredientList.stream().map(recipeIngredient -> modelMapper.map(recipeIngredient, RecipeIngredientDTO.class)).collect(Collectors.toList());
	}

	//등록된 기본 재료 수정하기
	@Transactional
	public RecipeIngredientDTO modifyRecipeIngredient(Long recipeIngredientId, String ingredientName ,String ingredientUnit) {
		RecipeIngredient recipeIngredient = recipeIngredientRepository.findById(recipeIngredientId).orElseThrow(() -> new RuntimeException("기본 재료 정보를 찾을 수 없습니다.. ID: " + recipeIngredientId));
		recipeIngredient.modifyRecipeIngredient(ingredientName, ingredientUnit);
		return modelMapper.map(recipeIngredient, RecipeIngredientDTO.class);
	}
	//특정 기본 재료 사용 현황 변경
	@Transactional
	public RecipeIngredientDTO processRecipeIngredient(Long recipeIngredientId, RecipeIngredientDTO recipeIngredientDTO) {
		RecipeIngredient recipeIngredient = recipeIngredientRepository.findById(recipeIngredientId).orElseThrow(() -> new RuntimeException("기본 재료 정보를 찾을 수 없습니다.. ID: " + recipeIngredientId));
		if (recipeIngredient.isProcessing()) {
			recipeIngredient.processingCompleted();
		} else {
			recipeIngredient.processingBefore();
		}

		return modelMapper.map(recipeIngredient, RecipeIngredientDTO.class);
	}

	//특정 기본 재료 불러오기
	public RecipeIngredientDTO getRecipeIngredient(Long recipeIngredientId){
		RecipeIngredient recipeIngredient = recipeIngredientRepository.findById(recipeIngredientId).orElseThrow(() -> new RuntimeException("기본 재료 정보를 찾을 수 없습니다.. ID: " + recipeIngredientId));
		return modelMapper.map(recipeIngredient, RecipeIngredientDTO.class);
	}


	/*프렌차이즈 관련 메소드*/
	//새로운 프렌차이즈 정보 생성
	@Transactional
	public FranchiseDTO createFranchise(FranchiseDTO franchiseDTO) {
		Franchise newFranchise = Franchise.builder()
			.franchiseName(franchiseDTO.getFranchiseName())
			.franchiseBackColor(franchiseDTO.getFranchiseBackColor())
			.franchiseFontColor(franchiseDTO.getFranchiseFontColor())
			.franchiseImage(franchiseDTO.getFranchiseImage())
			.processing(franchiseDTO.isProcessing())
			.build();
		Franchise franchise = franchiseRepository.save(newFranchise);
		return modelMapper.map(franchise, FranchiseDTO.class);
	}

	//모든 기본 재료들 불러오기
	public List<FranchiseDTO> getAllFranchise(){
		List<Franchise> franchiseList = franchiseRepository.findAll();
		return franchiseList.stream().map(franchise -> modelMapper.map(franchise, FranchiseDTO.class)).collect(Collectors.toList());
	}

	//등록된 기본 재료 수정하기
	@Transactional
	public FranchiseDTO modifyFranchise(Long franchiseId, String franchiseName,String franchiseFontColor,String franchiseBackColor, String franchiseImage) {
		Franchise franchise = franchiseRepository.findById(franchiseId).orElseThrow(() -> new RuntimeException("프렌차이즈 정보를 찾을 수 없습니다.. ID: " + franchiseId));
		franchise.modifyFranchise(franchiseName, franchiseFontColor, franchiseBackColor, franchiseImage);
		return modelMapper.map(franchise, FranchiseDTO.class);
	}
	//특정 기본 재료 사용 현황 변경
	@Transactional
	public FranchiseDTO processFranchise(Long franchiseId, FranchiseDTO franchiseDTO) {
		Franchise franchise = franchiseRepository.findById(franchiseId).orElseThrow(() -> new RuntimeException("프렌차이즈 정보를 찾을 수 없습니다.. ID: " + franchiseId));
		if (franchise.isProcessing()) {
			franchise.processingCompleted();
		} else {
			franchise.processingBefore();
		}

		return modelMapper.map(franchise, FranchiseDTO.class);
	}

	//특정 기본 재료 불러오기
	public FranchiseDTO getFranchise(Long franchiseId){
		Franchise franchise = franchiseRepository.findById(franchiseId).orElseThrow(() -> new RuntimeException("프렌차이즈 정보를찾을 수 없습니다.. ID: " + franchiseId));
		return modelMapper.map(franchise, FranchiseDTO.class);
	}
}
