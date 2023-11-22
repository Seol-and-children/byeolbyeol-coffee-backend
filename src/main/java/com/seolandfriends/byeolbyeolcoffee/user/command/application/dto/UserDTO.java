package com.seolandfriends.byeolbyeolcoffee.user.command.application.dto;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import com.seolandfriends.byeolbyeolcoffee.user.command.domain.aggregate.entity.UserRole;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserDTO implements UserDetails {
	private UUID userId;
	private String userAccount;
	private String userPassword;
	private String userNickName;
	private String userEmail;
	private String kakaoId;
	private String kakaoName;
	private boolean status;
	private List<UserRole> userRole;
	private Collection<GrantedAuthority> roles;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		if(userRole != null) {
			userRole.forEach(role -> {
				roles.add(() -> role.getRole().getRoleName());
			});
			return authorities;
		}
		return new ArrayList<>();
	}

	@Override
	public String getPassword() {
		return this.userPassword;
	}

	@Override
	public String getUsername() {
		return this.userNickName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}

	@Override
	public boolean isEnabled() {
		return false;
	}
}
