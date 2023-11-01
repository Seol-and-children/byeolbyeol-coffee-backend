package com.seolandfriends.byeolbyeolcoffee.recipe.command.domain.aggregate.vo;

import javax.persistence.Embeddable;

@Embeddable
public class BaseBeverage {
	private String name;
	private String size;
	private String type; // Hot or Ice
}
