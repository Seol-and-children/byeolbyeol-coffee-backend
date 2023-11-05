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
	private Long recipeId;

	@Column
	private String recipeName;

	@Column
	private String recipePhoto;

	@Column
	private String description;

	@Embedded
	private FranchiseCafe franchiseCafe;

	@Embedded
	private BaseBeverage baseBeverage;

	@ElementCollection
	private List<String> customOptions;

	@Column
	private LocalDateTime registerTime;

	@Embedded
	private RecipeUser author;

	@Column(nullable = false)
	private Integer likesCount = 0;

	@Column(nullable = false)
	private Integer viewsCount = 0;

	@PrePersist
	protected void onCreate() {
		registerTime = LocalDateTime.now();
	}

	@Builder
	public Recipe(String recipeName, String recipePhoto, String description,
		FranchiseCafe franchiseCafe, BaseBeverage baseBeverage,
		List<String> customOptions, RecipeUser author, Integer likesCount, Integer viewsCount) {
		this.recipeName = recipeName;
		this.recipePhoto = recipePhoto;
		this.description = description;
		this.franchiseCafe = franchiseCafe;
		this.baseBeverage = baseBeverage;
		this.customOptions = customOptions;
		this.author = author;
		this.likesCount = likesCount != null ? likesCount : this.likesCount;
		this.viewsCount = viewsCount != null ? viewsCount : this.viewsCount;
	}

	/* 레시피 업데이트 메소드 */
	public void updateRecipe(String recipeName, String recipePhoto, String description,
		FranchiseCafe franchiseCafe, BaseBeverage baseBeverage,
		List<String> customOptions) {
		this.recipeName = recipeName;
		this.recipePhoto = recipePhoto;
		this.description = description;
		this.franchiseCafe = franchiseCafe;
		this.baseBeverage = baseBeverage;
		this.customOptions = customOptions;
	}

	/* 좋아요 수 증가 메소드 */
	public void incrementLikesCount() {
		this.likesCount++;
	}

	/* 조회수 증가 메소드 */
	public void incrementViewsCount() {
		this.viewsCount++;
	}

	/* 좋아요 수 감소 메소드 */
	public void decrementLikesCount() {
		if (this.likesCount > 0) {
			this.likesCount--;
		}
	}

}
