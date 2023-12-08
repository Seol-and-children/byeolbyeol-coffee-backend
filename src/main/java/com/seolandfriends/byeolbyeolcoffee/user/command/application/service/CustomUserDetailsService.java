package com.seolandfriends.byeolbyeolcoffee.user.command.application.service;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

import com.seolandfriends.byeolbyeolcoffee.user.command.application.dto.UserDTO;
import com.seolandfriends.byeolbyeolcoffee.user.command.domain.aggregate.entity.User;
import com.seolandfriends.byeolbyeolcoffee.user.command.domain.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	private final UserRepository userRepository;

	private final ModelMapper modelMapper;

	public CustomUserDetailsService(UserRepository userRepository,
		ModelMapper modelMapper){
		this.userRepository = userRepository;
		this.modelMapper = modelMapper;
	}

	@Transactional
	@Override
	public UserDetails loadUserByUsername(String userAccount) throws UsernameNotFoundException {
		User user = userRepository.findByUserAccount(userAccount);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + userAccount);
		}
		UserDTO userDTO = modelMapper.map(user, UserDTO.class);

		List<GrantedAuthority> authorities = new ArrayList<>();

		// userRole이 2인 경우 'ROLE_USER' 권한 부여
		if (user.getUserRole() == 2) {
			authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		}
		// userRole이 3인 경우 'ROLE_ADMIN' 권한 부여
		else if (user.getUserRole() == 3) {
			authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		}
		// 추가적인 역할 조건을 여기에 추가할 수 있습니다.

		userDTO.setRoles(authorities);

		return userDTO;
	}
}