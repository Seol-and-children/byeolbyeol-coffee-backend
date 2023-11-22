package com.seolandfriends.byeolbyeolcoffee.recipe.command.application.dto;

import com.seolandfriends.byeolbyeolcoffee.recipe.command.domain.aggregate.vo.LikeUser;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RecipeLikeDto {
	private LikeUser likeUser;
}