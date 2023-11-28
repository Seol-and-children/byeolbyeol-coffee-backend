package com.seolandfriends.byeolbyeolcoffee.review.command.domain.aggregate.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_user")
public class ReviewUserVO {
    @Id
    @Column(name = "USER_ID")
    private String userId;
}