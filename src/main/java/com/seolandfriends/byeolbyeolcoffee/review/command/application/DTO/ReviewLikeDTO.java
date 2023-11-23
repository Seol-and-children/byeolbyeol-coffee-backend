package com.seolandfriends.byeolbyeolcoffee.review.command.application.DTO;

import com.seolandfriends.byeolbyeolcoffee.review.command.domain.aggregate.vo.LikeUser;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewLikeDTO {
	private LikeUser likeUser;
}