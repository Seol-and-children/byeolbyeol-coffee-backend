package com.seolandfriends.byeolbyeolcoffee.admin.command.application.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seolandfriends.byeolbyeolcoffee.admin.command.application.dto.FranchiseDTO;
import com.seolandfriends.byeolbyeolcoffee.admin.command.application.dto.CustomOptionDTO;
import com.seolandfriends.byeolbyeolcoffee.admin.command.domain.aggregate.entity.Franchise;
import com.seolandfriends.byeolbyeolcoffee.admin.command.domain.aggregate.entity.CustomOption;
import com.seolandfriends.byeolbyeolcoffee.admin.command.domain.repository.FranchiseRepository;
import com.seolandfriends.byeolbyeolcoffee.admin.command.domain.repository.CustomOptionRepository;

@Service
public class OptionService {
	private final CustomOptionRepository customOptionRepository;
	private final FranchiseRepository franchiseRepository;
	private final ModelMapper modelMapper;
	@Autowired
	public OptionService(CustomOptionRepository customOptionRepository, ModelMapper modelMapper, FranchiseRepository franchiseRepository){
		this.customOptionRepository = customOptionRepository;
		this.franchiseRepository = franchiseRepository;
		this.modelMapper = modelMapper;
	}

	/*기본 재공 관련 메소드*/
	// 새로운 기본 재료 생성
	@Transactional
	public CustomOptionDTO createIngredient(CustomOptionDTO customOptionDTO) {
		CustomOption newIngredient = CustomOption.builder()
			.ingredientName(customOptionDTO.getIngredientName())
			.ingredientUnit(customOptionDTO.getIngredientUnit())
			.processing(customOptionDTO.isProcessing())
			.build();
		CustomOption ingredient = customOptionRepository.save(newIngredient);
		return modelMapper.map(ingredient, CustomOptionDTO.class);
	}

	//모든 기본 재료들 불러오기
	public List<CustomOptionDTO> getAllRecipeIngredient(){
		List<CustomOption> customOptionList = customOptionRepository.findAll();
		return customOptionList.stream().map(customOption -> modelMapper.map(customOption, CustomOptionDTO.class)).collect(Collectors.toList());
	}

	//등록된 기본 재료 수정하기
	@Transactional
	public CustomOptionDTO modifyRecipeIngredient(Long recipeIngredientId, String ingredientName ,String ingredientUnit) {
		CustomOption customOption = customOptionRepository.findById(recipeIngredientId).orElseThrow(() -> new RuntimeException("기본 재료 정보를 찾을 수 없습니다.. ID: " + recipeIngredientId));
		customOption.modifyRecipeIngredient(ingredientName, ingredientUnit);
		return modelMapper.map(customOption, CustomOptionDTO.class);
	}
	//특정 기본 재료 사용 현황 변경
	@Transactional
	public CustomOptionDTO processRecipeIngredient(Long recipeIngredientId, CustomOptionDTO customOptionDTO) {
		CustomOption customOption = customOptionRepository.findById(recipeIngredientId).orElseThrow(() -> new RuntimeException("기본 재료 정보를 찾을 수 없습니다.. ID: " + recipeIngredientId));
		if (customOption.isProcessing()) {
			customOption.processingCompleted();
		} else {
			customOption.processingBefore();
		}

		return modelMapper.map(customOption, CustomOptionDTO.class);
	}

	//특정 기본 재료 불러오기
	public CustomOptionDTO getRecipeIngredient(Long recipeIngredientId){
		CustomOption customOption = customOptionRepository.findById(recipeIngredientId).orElseThrow(() -> new RuntimeException("기본 재료 정보를 찾을 수 없습니다.. ID: " + recipeIngredientId));
		return modelMapper.map(customOption, CustomOptionDTO.class);
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
