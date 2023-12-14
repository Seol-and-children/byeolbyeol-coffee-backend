package com.seolandfriends.byeolbyeolcoffee.recipe.command.domain.aggregate.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "custom_option")
public class CustomOptionVO {
	@Id
	@Column(name = "id")
	private Long customOptionId;

	@Column(name = "name")
	private String ingredientName;

	@Column(name = "ingredient_unit")
	private String ingredientUnit;

	public CustomOptionVO(Long customOptionId) {
		this.customOptionId = customOptionId;
	}
}
