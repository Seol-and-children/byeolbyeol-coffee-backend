package com.seolandfriends.byeolbyeolcoffee.review.command.domain.aggregate.vo;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
public class ReviewUser implements Serializable {
    private Long reviewUserId;

    public ReviewUser(Long reviewUserId) {
        this.reviewUserId = reviewUserId;
    }
}