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
public class RecipeCommentDto implements Serializable {
	private Long commentId;
	private int userId;
	private String content;
	private Long parentId;
	private int depth;
	private Long recipeId;
}
