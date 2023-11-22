package com.seolandfriends.byeolbyeolcoffee.user.command.application.dto;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserRoleDTO {

	private UUID userId;

	private int roleCode;

	private RoleDTO role;

	public UserRoleDTO() {
	}

	public UserRoleDTO(UUID userId, int roleCode) {
		this.userId = userId;
		this.roleCode = roleCode;
	}

	public UserRoleDTO(UUID userId, int roleCode, RoleDTO role) {
		this.userId = userId;
		this.roleCode = roleCode;
		this.role = role;
	}

}