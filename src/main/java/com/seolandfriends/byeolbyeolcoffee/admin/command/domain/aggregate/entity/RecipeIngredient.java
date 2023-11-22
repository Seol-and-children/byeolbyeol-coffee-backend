package com.seolandfriends.byeolbyeolcoffee.admin.command.domain.aggregate.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class RecipeIngredient {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long ingredientId;
	@Column
	private String ingredientName;
	@Column
	private String ingredientUnit;
	@Column
	private boolean processing;

	@Builder
	public RecipeIngredient(String ingredientName,String ingredientUnit,boolean processing){
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
