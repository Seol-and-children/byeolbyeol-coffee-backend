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
import com.seolandfriends.byeolbyeolcoffee.user.command.domain.aggregate.entity.UserRole;
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
		UserDTO userDTO = modelMapper.map(user, UserDTO.class);

		List<GrantedAuthority> authorities = new ArrayList<>();
		for(UserRole memberRole : user.getUserRole()){
			String authorityName = memberRole.getRole().getRoleName();
			authorities.add(new SimpleGrantedAuthority(authorityName));
		}

		userDTO.setRoles(authorities);

		return userDTO;
	}

}