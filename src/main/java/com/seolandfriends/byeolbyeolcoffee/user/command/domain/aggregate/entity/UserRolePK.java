package com.seolandfriends.byeolbyeolcoffee.user.command.domain.aggregate.entity;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserRolePK implements Serializable {

	private int userId;
	private int roleCode;


}