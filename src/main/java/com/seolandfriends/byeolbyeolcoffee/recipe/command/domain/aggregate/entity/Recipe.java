package com.seolandfriends.byeolbyeolcoffee.recipe.command.domain.aggregate.entity;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.seolandfriends.byeolbyeolcoffee.recipe.command.domain.aggregate.vo.CustomOption;
import com.seolandfriends.byeolbyeolcoffee.recipe.command.domain.aggregate.vo.Franchise;
import com.seolandfriends.byeolbyeolcoffee.recipe.command.domain.aggregate.vo.RecipeImage;
import com.seolandfriends.byeolbyeolcoffee.recipe.command.domain.aggregate.vo.RecipeUser;

@Entity
public class Recipe {
	@Id
	private Long recipeId;
	private String recipeName;
	@Embedded
	private RecipeUser userName;
	private String registerTime;
	@Embedded
	private RecipeImage recipeImage;
	@Embedded
	private Franchise franchise;
	@Embedded
	private CustomOption customOption;
	private String description;
}