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
	private String reviewName; // 리뷰의 제목 또는 이름
	private String content; // 리뷰의 내용
	private String photoUrl; // 리뷰에 첨부된 사진의 URL
	private ReviewUser author; // 리뷰의 작성자 정보를 나타내는 ReviewUser 타입의 객체
	private LocalDateTime registerTime; // 리뷰 등록 시간
	private int recommendations; // 리뷰 추천 수
	private int views; // 리뷰 조회 수
}
