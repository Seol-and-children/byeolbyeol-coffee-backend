package com.seolandfriends.byeolbyeolcoffee.review.command.domain.aggregate.vo;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
public class ReviewScript implements Serializable {
    private Long reviewScriptId;

    public ReviewScript(Long reviewScriptId) {
        this.reviewScriptId = reviewScriptId;
    }
}