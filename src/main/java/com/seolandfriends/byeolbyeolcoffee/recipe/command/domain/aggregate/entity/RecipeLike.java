package com.seolandfriends.byeolbyeolcoffee.recipe.command.domain.aggregate.entity;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.seolandfriends.byeolbyeolcoffee.recipe.command.domain.aggregate.vo.LikeUser;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
public class RecipeLike {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn
	private Recipe recipe;

	@Embedded
	private LikeUser likeUser;

	@Builder
	public RecipeLike(Recipe recipe, LikeUser likeUser) {
		this.recipe = recipe;
		this.likeUser = likeUser;
	}
}