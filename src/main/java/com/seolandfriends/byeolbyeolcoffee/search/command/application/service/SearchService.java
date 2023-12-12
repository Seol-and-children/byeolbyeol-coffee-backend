package com.seolandfriends.byeolbyeolcoffee.search.command.application.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seolandfriends.byeolbyeolcoffee.recipe.command.domain.aggregate.entity.Recipe;
import com.seolandfriends.byeolbyeolcoffee.search.command.domain.repository.SearchRecipeRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SearchService {
	private final SearchRecipeRepository searchRecipeRepository;
	private final ModelMapper modelMapper;

	@Autowired
	public SearchService(SearchRecipeRepository searchRecipeRepository, ModelMapper modelMapper) {
		this.searchRecipeRepository = searchRecipeRepository;
		this.modelMapper = modelMapper;
	}

	public List<Recipe> search(String query) {
		List<Recipe> recipe = searchRecipeRepository.findByRecipeNameContaining(query);
		log.info("result: {}",recipe);
		return recipe;
	}
}
