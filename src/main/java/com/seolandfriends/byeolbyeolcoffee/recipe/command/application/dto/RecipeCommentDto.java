package com.seolandfriends.byeolbyeolcoffee.recipe.command.application.dto;

import com.seolandfriends.byeolbyeolcoffee.recipe.command.domain.aggregate.entity.RecipeComment;
import com.seolandfriends.byeolbyeolcoffee.recipe.command.domain.aggregate.vo.CommentUser;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RecipeCommentDto {
	private CommentUser commentUser;
	private String content;
	private RecipeComment parent;
	private int depth;
}
