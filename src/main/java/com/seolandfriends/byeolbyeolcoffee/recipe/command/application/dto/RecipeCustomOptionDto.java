package com.seolandfriends.byeolbyeolcoffee.recipe.command.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RecipeCustomOptionDto {

	private Long customOptionId;

	private Integer quantity;

	private String ingredientName;

	private String ingredientUnit;
}
