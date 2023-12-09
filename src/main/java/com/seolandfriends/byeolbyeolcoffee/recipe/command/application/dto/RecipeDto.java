package com.seolandfriends.byeolbyeolcoffee.recipe.command.application.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.seolandfriends.byeolbyeolcoffee.recipe.command.domain.aggregate.vo.BaseBeverageVO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RecipeDto implements Serializable {
	@NotNull
	private Long recipeId;

	@NotBlank
	private String recipeName;

	private String photoUrl;

	@NotBlank
	private String description;

	@NotNull
	private Long franchiseId;

	private String franchiseName;

	@NotBlank
	private BaseBeverageVO baseBeverageVO;

	private List<Long> customOptionId;
  
	private int authorId;

	private String userNickname;

	private LocalDateTime registerTime;

	private Integer likesCount;

	private Integer viewsCount;
}