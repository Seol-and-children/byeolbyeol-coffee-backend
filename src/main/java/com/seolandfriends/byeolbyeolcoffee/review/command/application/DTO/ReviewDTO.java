package com.seolandfriends.byeolbyeolcoffee.review.command.application.DTO;

import java.time.LocalDateTime;

import com.seolandfriends.byeolbyeolcoffee.review.command.domain.aggregate.vo.ReviewUser;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {
	private Long reviewId;
	private String reviewName;
	private String content;
	private String photoUrl;
	private ReviewUser author;
	private LocalDateTime registerTime;
	private Integer likesCount;
	private Integer viewsCount;
}
