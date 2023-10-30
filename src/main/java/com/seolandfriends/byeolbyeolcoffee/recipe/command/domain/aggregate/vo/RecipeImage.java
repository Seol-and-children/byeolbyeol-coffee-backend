package com.seolandfriends.byeolbyeolcoffee.recipe.command.domain.aggregate.vo;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
public class RecipeImage implements Serializable {
	private Long RecipeImageId;

	public RecipeImage(Long recipeImageId) {
		RecipeImageId = recipeImageId;
	}
}
