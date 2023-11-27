package com.seolandfriends.byeolbyeolcoffee.user.command.domain.aggregate.entity;


import java.util.UUID;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "USER_ROLE")
@IdClass(UserRolePK.class)
@Getter
@ToString
public class UserRole {

	@Id
	@Column(name = "USER_ID", nullable = false)
	private UUID userId;

	@Id
	@Column(name = "ROLE_CODE", nullable = false)
	private int roleCode;

	@ManyToOne
	@JoinColumn(name = "ROLE_CODE", insertable = false, updatable = false)
	private Role role;

	protected UserRole() {
	}

	public UserRole(UUID userId, int roleCode) {
		this.userId = userId;
		this.roleCode = roleCode;
	}

	public UserRole(UUID userId, int roleCode, Role role) {
		this.userId = userId;
		this.roleCode = roleCode;
		this.role = role;
	}

	public UserRole userId(UUID userId) {
		this.userId = userId;
		return this;
	}

	public UserRole roleCode(int roleCode) {
		this.roleCode = roleCode;
		return this;
	}

	public UserRole role(Role role) {
		this.role = role;
		return this;
	}

	public UserRole build() {
		return new UserRole(userId, roleCode, role);
	}

}