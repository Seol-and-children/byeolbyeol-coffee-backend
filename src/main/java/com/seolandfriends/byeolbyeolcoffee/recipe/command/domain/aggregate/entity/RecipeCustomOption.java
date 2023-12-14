package com.seolandfriends.byeolbyeolcoffee.recipe.command.domain.aggregate.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "recipe_custom_option")
public class RecipeCustomOption {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "custom_option_name")
	private String customOptionName;

	@Column(name = "quantity")
	private String quantity;

	@Column(name = "recipe_id", nullable = false)
	private Long recipeId;

	@Builder
	public RecipeCustomOption(String customOptionName, String quantity, Long recipeId) {
		this.customOptionName = customOptionName;
		this.quantity = quantity;
		this.recipeId = recipeId;
	}
}