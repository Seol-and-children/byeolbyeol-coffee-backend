package com.seolandfriends.byeolbyeolcoffee.common.entity;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity {

	// 엔터티가 생성될 때 자동으로 생성일자를 저장하는 필드
	@CreatedDate
	@Column(name = "created_at", updatable = false, columnDefinition = "TIMESTAMP")
	private LocalDateTime createdAt;

	// 엔터티가 수정될 때 자동으로 수정일자를 저장하는 필드
	@LastModifiedDate
	@Column(name = "updated_at", columnDefinition = "TIMESTAMP")
	private LocalDateTime updatedAt;

	// 엔터티가 저장되기 전(pre-persist)에 호출되는 메서드
	// 생성일자를 현재 시간으로 설정함
	@PrePersist
	public void prePersist() {
		LocalDateTime now = LocalDateTime.now();
		createdAt = now;
		updatedAt = now; // 업데이트 시에도 수정일자를 업데이트
	}

	// 엔터티가 수정되기 전(pre-update)에 호출되는 메서드
	// 수정일자를 현재 시간으로 업데이트함
	@PreUpdate
	public void preUpdate() {
		updatedAt = LocalDateTime.now();
	}

}