package com.seolandfriends.byeolbyeolcoffee.recipe.command.application.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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

	@NotBlank
	private String content;

	private Long parentId;

	@NotNull
	private int depth;

	@NotNull
	private Long recipeId;

	private String userNickname;
}
