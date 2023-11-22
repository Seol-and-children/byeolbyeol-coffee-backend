package com.seolandfriends.byeolbyeolcoffee.recipe.command.application.dto;

import java.util.List;

import com.seolandfriends.byeolbyeolcoffee.recipe.command.domain.aggregate.vo.BaseBeverage;
import com.seolandfriends.byeolbyeolcoffee.recipe.command.domain.aggregate.vo.FranchiseCafe;
import com.seolandfriends.byeolbyeolcoffee.recipe.command.domain.aggregate.vo.RecipeUser;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RecipeDto {
	private Long id;
	private String recipeName;
	private String recipePhoto;
	private String description;
	private FranchiseCafe franchiseCafe;
	private BaseBeverage baseBeverage;
	private List<String> customOptions;
	private RecipeUser author;
	private Integer likesCount;
	private Integer viewsCount;
}