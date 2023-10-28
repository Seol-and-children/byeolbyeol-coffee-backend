package com.seolandfriends.byeolbyeolcoffee.common.entity;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity extends BaseTimeEntity {

	// 엔터티를 생성한 사용자의 정보를 저장하는 필드
	@CreatedBy
	@Column(name = "created_by", updatable = false)
	private String createdBy;

	// 엔터티를 마지막으로 수정한 사용자의 정보를 저장하는 필드
	@LastModifiedBy
	@Column(name = "update_by")
	private String updatedBy;

}
