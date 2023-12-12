package com.seolandfriends.byeolbyeolcoffee.recipeOption.command.domain.aggregate.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name="custom_option")
@NoArgsConstructor
public class CustomOption {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long ingredientId;
	@Column(name = "name")
	private String ingredientName;
	@Column(name = "ingredient_unit")
	private String ingredientUnit;
	@Column(name = "process")
	private boolean processing;

	@Builder
	public CustomOption(String ingredientName,String ingredientUnit,boolean processing){
		this.ingredientName = ingredientName;
		this.ingredientUnit = ingredientUnit;
		this.processing = processing;
	}

	//기본 제공 재료 정보 수정 메소드
	public void modifyRecipeIngredient(String ingredientName,String ingredientUnit){
		this.ingredientName = ingredientName;
		this.ingredientUnit = ingredientUnit;
	}

	//처리 여부 변경 메소드
	public void processingCompleted(){ this.processing = false;}
	public void processingBefore(){ this.processing = true;}

}
