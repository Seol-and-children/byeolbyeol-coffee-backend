package com.seolandfriends.byeolbyeolcoffee.review.command.application.DTO;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewCommentDTO implements Serializable {
	private Long commentId;
	private String userId;
	private String content;
	private Long parentId;
	private int depth;
	private Long recipeId;
}