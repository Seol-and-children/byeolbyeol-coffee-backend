package com.seolandfriends.byeolbyeolcoffee.admin.command.application.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RecipeIngredientDTO {
	private Long ingredientId;

	private String ingredientName;

	private String ingredientUnit;

	private boolean processing;
}
