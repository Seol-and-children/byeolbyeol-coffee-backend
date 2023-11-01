package com.seolandfriends.byeolbyeolcoffee.recipe.command.domain.aggregate.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;

import com.seolandfriends.byeolbyeolcoffee.recipe.command.domain.aggregate.vo.BaseBeverage;
import com.seolandfriends.byeolbyeolcoffee.recipe.command.domain.aggregate.vo.FranchiseCafe;
import com.seolandfriends.byeolbyeolcoffee.recipe.command.domain.aggregate.vo.RecipeUser;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Recipe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String recipeName;
	private String recipePhoto;
	private String description;

	@ManyToOne(cascade = CascadeType.ALL)
	private FranchiseCafe franchiseCafe;

	@Embedded
	private BaseBeverage baseBeverage;

	@ElementCollection
	private List<String> customOptions;

	private LocalDateTime registerTime;

	@ManyToOne(cascade = CascadeType.ALL)
	private RecipeUser author;

	@PrePersist
	protected void onCreate() {
		registerTime = LocalDateTime.now();
	}

	@Builder
	public Recipe(String recipeName, String recipePhoto, String description,
		FranchiseCafe franchiseCafe, BaseBeverage baseBeverage,
		List<String> customOptions, RecipeUser author) {
		this.recipeName = recipeName;
		this.recipePhoto = recipePhoto;
		this.description = description;
		this.franchiseCafe = franchiseCafe;
		this.baseBeverage = baseBeverage;
		this.customOptions = customOptions;
		this.author = author;
	}

	/*
	* 레시피 정보 업데이트 메소드
	* */
	public void updateRecipe(String recipeName, String recipePhoto, String description,
		FranchiseCafe franchiseCafe, BaseBeverage baseBeverage,
		List<String> customOptions, RecipeUser author) {
		this.recipeName = recipeName;
		this.recipePhoto = recipePhoto;
		this.description = description;
		this.franchiseCafe = franchiseCafe;
		this.baseBeverage = baseBeverage;
		this.customOptions = customOptions;
		this.author = author;
	}
}
