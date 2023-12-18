package com.seolandfriends.byeolbyeolcoffee.search.command.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seolandfriends.byeolbyeolcoffee.recipe.command.domain.aggregate.entity.Recipe;
import com.seolandfriends.byeolbyeolcoffee.review.command.domain.aggregate.entity.Review;
import com.seolandfriends.byeolbyeolcoffee.search.command.application.service.SearchService;
import com.seolandfriends.byeolbyeolcoffee.user.command.domain.aggregate.entity.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/search")
public class SearchController {
	private final SearchService searchService;

	@Autowired
	public SearchController(SearchService searchService) { this.searchService = searchService;	}

	/*데이터 베이스가 없으므로 임시로 Report로 설정
	  검색 기능*/
	@GetMapping("/recipe/recipename/{recipeName}")
	public List<Recipe> searchRecipe(@PathVariable String recipeName) {
		log.info("query: {}",recipeName);
		return searchService.searchRecipe(recipeName);
	}

	@GetMapping("/recipe/nickname/{nickName}")
	public List<Recipe> searchRecipeNickname(@PathVariable String nickName) {
		log.info("query: {}",nickName);
		return searchService.searchRecipeNickname(nickName);
	}

	@GetMapping("/review/reviewname/{reviewName}")
	public List<Review> searchReview(@PathVariable String reviewName) {
		log.info("query: {}",reviewName);
		return searchService.searchReview(reviewName);
	}

	@GetMapping("/review/nickname/{nickName}")
	public List<Review> searchReviewNickname(@PathVariable String nickName) {
		log.info("query: {}",nickName);
		return searchService.searchReviewNickname(nickName);
	}

	@GetMapping("/user/{userName}")
	public List<User> searchUser(@PathVariable String userName) {
		log.info("query: {}",userName);
		return searchService.searchUser(userName);
	}
}
