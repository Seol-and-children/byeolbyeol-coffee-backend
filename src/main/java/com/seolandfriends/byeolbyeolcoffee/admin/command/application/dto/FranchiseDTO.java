package com.seolandfriends.byeolbyeolcoffee.admin.command.application.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FranchiseDTO {
	private Long franchiseId;

	private String franchiseName;

	private String franchiseBackColor;

	private String franchiseFontColor;

	private String franchiseImage;

	private boolean processing;
}
