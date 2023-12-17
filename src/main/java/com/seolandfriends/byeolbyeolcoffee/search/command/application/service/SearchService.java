package com.seolandfriends.byeolbyeolcoffee.search.command.application.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seolandfriends.byeolbyeolcoffee.recipe.command.domain.aggregate.entity.Recipe;
import com.seolandfriends.byeolbyeolcoffee.review.command.domain.aggregate.entity.Review;
import com.seolandfriends.byeolbyeolcoffee.search.command.domain.repository.SearchNicknameRepository;
import com.seolandfriends.byeolbyeolcoffee.search.command.domain.repository.SearchRecipeRepository;
import com.seolandfriends.byeolbyeolcoffee.search.command.domain.repository.SearchReviewRepository;
import com.seolandfriends.byeolbyeolcoffee.user.command.domain.aggregate.entity.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SearchService {
	private final SearchRecipeRepository searchRecipeRepository;

	private final SearchReviewRepository searchReviewRepository;
	private final SearchNicknameRepository searchNicknameRepository;
	private final ModelMapper modelMapper;

	@Autowired
	public SearchService(SearchRecipeRepository searchRecipeRepository, SearchReviewRepository searchReviewRepository, SearchNicknameRepository searchNicknameRepository, ModelMapper modelMapper) {
		this.searchRecipeRepository = searchRecipeRepository;
		this.searchNicknameRepository = searchNicknameRepository;
		this.searchReviewRepository = searchReviewRepository;
		this.modelMapper = modelMapper;
	}

	public List<Recipe> searchRecipe(String query) {
		List<Recipe> recipe = searchRecipeRepository.findByRecipeNameContaining(query);
		log.info("result: {}",recipe);
		return recipe;
	}

	public List<Recipe> searchRecipeNickname(String query) {
		List<Recipe> recipe = searchRecipeRepository.findByAuthorUserNicknameContaining(query);
		log.info("result: {}",recipe);
		return recipe;
	}

	public List<Review> searchReview(String query) {
		List<Review> review = searchReviewRepository.findByReviewNameContaining(query);
		log.info("result: {}",review);
		return review;
	}

	public List<Review> searchReviewNickname(String query) {
		List<Review> review = searchReviewRepository.findByAuthorUserNicknameContaining(query);
		log.info("result: {}",review);
		return review;
	}

	// public List<User> searchUser(String query) {
	// 	List<User> user = searchNicknameRepository.findByUserNickNameContaining(query);
	// 	log.info("result: {}",user);
	// 	return user;
	// }
}
