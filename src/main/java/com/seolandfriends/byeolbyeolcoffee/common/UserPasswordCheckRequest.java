package com.seolandfriends.byeolbyeolcoffee.common;

import javax.persistence.criteria.CriteriaBuilder;

public class UserPasswordCheckRequest {
	private Integer userId;
	private String userPassword;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
}
