package com.seolandfriends.byeolbyeolcoffee.user.command.domain.aggregate.entity;

import lombok.*;
import javax.persistence.*;


@Entity
@Table(name = "role")
@AllArgsConstructor
@Getter
@ToString
public class Role {

	@Id
	@Column(name = "role_code", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int roleCode;

	@Column(name = "role_name", length = 255, nullable = false)
	private String roleName;

	@Column(name = "role_desc", length = 4000, nullable = false)
	private String roleDesc;

	protected Role() {}

	public Role RoleCode(int authorityCode) {
		this.roleCode = authorityCode;
		return this;
	}

	public Role authorityName(String authorityName) {
		this.roleName = authorityName;
		return this;
	}

	public Role authorityDesc(String authorityDesc) {
		this.roleDesc = authorityDesc;
		return this;
	}

	public Role build() {
		return new Role(roleCode, roleName, roleDesc);
	}

}