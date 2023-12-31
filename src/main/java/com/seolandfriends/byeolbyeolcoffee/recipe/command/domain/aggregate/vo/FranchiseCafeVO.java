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
@Table(name = "franchise")
public class FranchiseCafeVO {
	@Id
	@Column(name = "id")
	private Long franchiseId;

	@Column(name = "name")
	private String franchiseName;

	public FranchiseCafeVO(Long franchiseId) {
		this.franchiseId = franchiseId;
	}
}