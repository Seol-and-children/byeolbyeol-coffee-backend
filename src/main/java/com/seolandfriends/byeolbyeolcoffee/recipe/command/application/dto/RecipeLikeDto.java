package com.seolandfriends.byeolbyeolcoffee.recipe.command.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RecipeLikeDto {
	private Long recipeId;
	private String userId;
}