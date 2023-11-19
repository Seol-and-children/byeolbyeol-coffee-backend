package com.seolandfriends.byeolbyeolcoffee.review.command.domain.aggregate.vo;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
public class CustomOption implements Serializable {
	private Long customOptionId;

	public CustomOption(Long customOptionId) {
		this.customOptionId = customOptionId;
	}
}
