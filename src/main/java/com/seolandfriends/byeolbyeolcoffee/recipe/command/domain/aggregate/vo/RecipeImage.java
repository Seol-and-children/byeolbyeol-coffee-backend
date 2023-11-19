package com.seolandfriends.byeolbyeolcoffee.review.command.domain.aggregate.vo;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
public class ReviewImage implements Serializable {
	private Long ReviewImageId;

	public ReviewImage(Long reviewImageId) {
		ReviewImageId = reviewImageId;
	}
}
