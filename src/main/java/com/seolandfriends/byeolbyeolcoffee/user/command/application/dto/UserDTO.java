package com.seolandfriends.byeolbyeolcoffee.user.command.application.dto;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserDTO implements UserDetails {
	private Integer userId;
	private String userAccount;
	private String userPassword;
	private String userNickName;
	private String userEmail;
	private String kakaoId;
	private String kakaoName;
	private Boolean status = true;
	private Integer userRole = 2;
	private Collection<GrantedAuthority> roles;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> authorities = new ArrayList<>();

		// userRole이 2인 경우 'ROLE_USER' 권한 부여
		if (this.userRole != null) {
			if (this.userRole == 2) {
				authorities.add(() -> "ROLE_USER");
			}
			// userRole이 3인 경우 'ROLE_ADMIN' 권한 부여
			else if (this.userRole == 3) {
				authorities.add(() -> "ROLE_ADMIN");
			}
			// 추가적인 역할 조건을 여기에 추가할 수 있습니다.
		}
		return authorities;
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
