package com.seolandfriends.byeolbyeolcoffee.review.command.domain.aggregate.vo;


import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class LikeUser {
	private Long userId;
}