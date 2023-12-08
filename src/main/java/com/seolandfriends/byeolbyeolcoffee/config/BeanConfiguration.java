package com.seolandfriends.byeolbyeolcoffee.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
@Configuration
public class BeanConfiguration {

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		// UUID 컨버터 등록
		// Converter<String, UUID> toUUID = ctx -> ctx.getSource() == null ? null : UUID.fromString(ctx.getSource());
		// Converter<UUID, String> fromUUID = ctx -> ctx.getSource() == null ? null : ctx.getSource().toString();
		//
		// modelMapper.addConverter(toUUID);
		// modelMapper.addConverter(fromUUID);

		modelMapper.getConfiguration()
			.setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
			.setFieldMatchingEnabled(true);

		return modelMapper;
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}