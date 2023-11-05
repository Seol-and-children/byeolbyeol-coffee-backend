package com.seolandfriends.byeolbyeolcoffee.review.command.domain.aggregate.entity;


import com.seolandfriends.byeolbyeolcoffee.review.command.domain.aggregate.vo.ReviewImage;
import com.seolandfriends.byeolbyeolcoffee.review.command.domain.aggregate.vo.ReviewScript;
import com.seolandfriends.byeolbyeolcoffee.review.command.domain.aggregate.vo.ReviewUser;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Review {
	@Id
	@Column
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long reviewID;
	@Column
	private String reviewName;
	@Column
	private String reviewNumber;

	@Embedded
	@joinColumn
	private ReviewUser userName;
	@Column
	private String registerTime;
	@Embedded
	private ReviewImage reviewImage;
	@Embedded
	private ReviewScript reviewScript;

}