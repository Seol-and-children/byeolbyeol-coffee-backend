package com.seolandfriends.byeolbyeolcoffee.recipe.command.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RecipeCommentDto {
	private Long commentId;
	private String userId;
	private String content;
	private Long parentId;
	private int depth;
	private Long recipeId;
}
