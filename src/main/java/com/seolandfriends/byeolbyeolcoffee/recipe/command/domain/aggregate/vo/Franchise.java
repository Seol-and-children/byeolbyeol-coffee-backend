package com.seolandfriends.byeolbyeolcoffee.review.command.domain.aggregate.vo;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
public class Franchise implements Serializable {
	private Long franchiseId;

	public Franchise(Long franchiseId) {
		this.franchiseId = franchiseId;
	}
}
