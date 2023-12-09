package com.seolandfriends.byeolbyeolcoffee.recipe.command.domain.aggregate.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;

import com.seolandfriends.byeolbyeolcoffee.recipe.command.domain.aggregate.vo.BaseBeverageVO;
import com.seolandfriends.byeolbyeolcoffee.recipe.command.domain.aggregate.vo.CustomOptionVO;
import com.seolandfriends.byeolbyeolcoffee.recipe.command.domain.aggregate.vo.FranchiseCafeVO;
import com.seolandfriends.byeolbyeolcoffee.recipe.command.domain.aggregate.vo.RecipeUserVO;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "recipe")
public class Recipe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long recipeId;

	@Column(name = "name", nullable = false, length = 128)
	private String recipeName;

	@Column(name = "photo")
	private String photoUrl;

	@Column(name = "description", length = 255)
	private String description;

	@ManyToOne
	@JoinColumn(name = "franchise_id", nullable = false)
	private FranchiseCafeVO franchiseCafeVO;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private RecipeUserVO author;

	@Embedded
	private BaseBeverageVO baseBeverageVO;

	@ManyToMany
	@JoinTable(
		name = "recipe_custom_option",
		joinColumns = @JoinColumn(name = "recipe_id"),
		inverseJoinColumns = @JoinColumn(name = "custom_option_id")
	)
	private List<CustomOptionVO> customOptions;

	@Column(name = "register_time")
	private LocalDateTime registerTime;

	@Column(name = "likes_count", nullable = false)
	private Integer likesCount = 0;

	@Column(name = "views_count", nullable = false)
	private Integer viewsCount = 0;

	@PrePersist
	protected void onCreate() {
		registerTime = LocalDateTime.now();
	}

	@Builder(toBuilder = true)
	public Recipe(String recipeName, String photoUrl, String description,
		FranchiseCafeVO franchiseCafeVO, BaseBeverageVO baseBeverageVO,
		List<CustomOptionVO> customOptions, RecipeUserVO author, Integer likesCount, Integer viewsCount) {
		this.recipeName = recipeName;
		this.photoUrl = photoUrl;
		this.description = description;
		this.franchiseCafeVO = franchiseCafeVO;
		this.baseBeverageVO = baseBeverageVO;
		this.customOptions = customOptions;
		this.author = author;
		this.likesCount = likesCount != null ? likesCount : this.likesCount;
		this.viewsCount = viewsCount != null ? viewsCount : this.viewsCount;
	}

	/* 좋아요 수 증가 메소드 */
	public void incrementLikesCount() {
		this.likesCount++;
	}

	/* 조회수 증가 메소드 */
	public void incrementViewsCount() {
		this.viewsCount++;
	}

	/* 좋아요 수 감소 메소드 */
	public void decrementLikesCount() {
		if (this.likesCount > 0) {
			this.likesCount--;
		}
	}

}
