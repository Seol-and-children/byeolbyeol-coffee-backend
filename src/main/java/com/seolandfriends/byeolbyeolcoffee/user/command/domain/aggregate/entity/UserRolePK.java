package com.seolandfriends.byeolbyeolcoffee.user.command.domain.aggregate.entity;

import lombok.*;

import java.io.Serializable;

/* 복합키 타입을 정의할 때는 Serializable을 반드시 구현 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserRolePK implements Serializable {

	private int userid;
	private int roleCode;


}