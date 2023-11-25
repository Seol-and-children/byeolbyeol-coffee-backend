package com.seolandfriends.byeolbyeolcoffee.recipe.command.domain.aggregate.vo;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BaseBeverage {
	@Column(name = "base_beverage_name")
	private String name;

	@Column(name = "base_beverage_size")
	private String size;

	@Column(name = "base_beverage_temperature")
	private String temperature;
}