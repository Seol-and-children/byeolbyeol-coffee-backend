package com.seolandfriends.byeolbyeolcoffee.review.command.application.DTO;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO implements Serializable{
	private Long reviewId;
	private String reviewName;
	private String authorId;
	private String content;
	private String photoUrl;
	private LocalDateTime registerTime;
	private Integer likesCount;
	private Integer viewsCount;
}
