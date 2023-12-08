package com.seolandfriends.byeolbyeolcoffee.recipe.command.application.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RecipeLikeDto implements Serializable {
	private Long recipeId;
	private int userId;
}