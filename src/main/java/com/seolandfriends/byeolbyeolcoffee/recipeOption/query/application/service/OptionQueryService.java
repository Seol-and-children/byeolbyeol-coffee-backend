package com.seolandfriends.byeolbyeolcoffee.recipeOption.query.application.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seolandfriends.byeolbyeolcoffee.recipeOption.command.application.dto.CustomOptionDTO;
import com.seolandfriends.byeolbyeolcoffee.recipeOption.command.application.dto.FranchiseDTO;
import com.seolandfriends.byeolbyeolcoffee.recipeOption.command.domain.aggregate.entity.CustomOption;
import com.seolandfriends.byeolbyeolcoffee.recipeOption.command.domain.aggregate.entity.Franchise;
import com.seolandfriends.byeolbyeolcoffee.recipeOption.command.domain.repository.CustomOptionRepository;
import com.seolandfriends.byeolbyeolcoffee.recipeOption.command.domain.repository.FranchiseRepository;
@Service
public class OptionQueryService {
	private final CustomOptionRepository customOptionRepository;
	private final FranchiseRepository franchiseRepository;
	private final ModelMapper modelMapper;
	@Autowired
	public OptionQueryService(CustomOptionRepository customOptionRepository, ModelMapper modelMapper, FranchiseRepository franchiseRepository){
		this.customOptionRepository = customOptionRepository;
		this.franchiseRepository = franchiseRepository;
		this.modelMapper = modelMapper;
	}

	/*기본 재공 관련 메소드*/
	//모든 기본 재료들 불러오기
	public List<CustomOptionDTO> getAllRecipeIngredient(){
		List<CustomOption> customOptionList = customOptionRepository.findAll();
		return customOptionList.stream().map(customOption -> modelMapper.map(customOption, CustomOptionDTO.class)).collect(
			Collectors.toList());
	}

	//특정 기본 재료 불러오기
	public CustomOptionDTO getRecipeIngredient(Long recipeIngredientId){
		CustomOption customOption = customOptionRepository.findById(recipeIngredientId).orElseThrow(() -> new RuntimeException("기본 재료 정보를 찾을 수 없습니다.. ID: " + recipeIngredientId));
		return modelMapper.map(customOption, CustomOptionDTO.class);
	}


	/*프렌차이즈 관련 메소드*/
	//모든 기본 재료들 불러오기
	public List<FranchiseDTO> getAllFranchise(){
		List<Franchise> franchiseList = franchiseRepository.findAll();
		return franchiseList.stream().map(franchise -> modelMapper.map(franchise, FranchiseDTO.class)).collect(Collectors.toList());
	}

	//특정 기본 재료 불러오기
	public FranchiseDTO getFranchise(Long franchiseId){
		Franchise franchise = franchiseRepository.findById(franchiseId).orElseThrow(() -> new RuntimeException("프렌차이즈 정보를찾을 수 없습니다.. ID: " + franchiseId));
		return modelMapper.map(franchise, FranchiseDTO.class);
	}

}
