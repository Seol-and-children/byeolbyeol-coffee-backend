package com.seolandfriends.byeolbyeolcoffee.recipe.command.domain.aggregate.vo;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
public class RecipeUser implements Serializable {
	private Long recipeUserId;

	public RecipeUser(Long recipeUserId) {
		this.recipeUserId = recipeUserId;
	}
}