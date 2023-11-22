package com.seolandfriends.byeolbyeolcoffee.user.command.domain.aggregate.entity;


import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "TBL_USER_ROLE")
@IdClass(UserRolePK.class)
@Getter
@ToString
public class UserRole {

	@Id
	@Column(name = "USER_CODE", nullable = false)
	private UUID userid;

	@Id
	@Column(name = "ROLE_CODE", nullable = false)
	private int roleCode;

	@ManyToOne
	@JoinColumn(name = "ROLE_CODE", insertable = false, updatable = false)
	private Role role;

	protected UserRole() {
	}

	public UserRole(UUID userid, int roleCode) {
		this.userid = userid;
		this.roleCode = roleCode;
	}

	public UserRole(UUID userid, int roleCode, Role role) {
		this.userid = userid;
		this.roleCode = roleCode;
		this.role = role;
	}

	public UserRole userid(UUID userid) {
		this.userid = userid;
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
		return new UserRole(userid, roleCode, role);
	}
}