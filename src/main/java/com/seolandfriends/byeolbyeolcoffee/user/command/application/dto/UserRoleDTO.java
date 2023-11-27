package com.seolandfriends.byeolbyeolcoffee.user.command.application.dto;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserRoleDTO {

	private UUID userCode;

	private int roleCode;

	private RoleDTO role;

	public UserRoleDTO() {
	}

	public UserRoleDTO(UUID userCode, int roleCode) {
		this.userCode = userCode;
		this.roleCode = roleCode;
	}

	public UserRoleDTO(UUID userCode, int roleCode, RoleDTO role) {
		this.userCode = userCode;
		this.roleCode = roleCode;
		this.role = role;
	}

}