package com.seolandfriends.byeolbyeolcoffee.search.command.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seolandfriends.byeolbyeolcoffee.recipe.command.domain.aggregate.entity.Recipe;
import com.seolandfriends.byeolbyeolcoffee.search.command.application.service.SearchService;

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
	@GetMapping("/recipe/{recipeName}")
	public List<Recipe> search(@PathVariable String recipeName) {
		log.info("query: {}",recipeName);
		return searchService.search(recipeName);
	}
}
