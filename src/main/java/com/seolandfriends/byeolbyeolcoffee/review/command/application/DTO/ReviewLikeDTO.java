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
public class ReviewLikeDTO implements Serializable {
	private Long recipeId;
	private String userId;
}