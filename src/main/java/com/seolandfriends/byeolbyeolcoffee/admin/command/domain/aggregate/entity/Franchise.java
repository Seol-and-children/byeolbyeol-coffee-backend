package com.seolandfriends.byeolbyeolcoffee.admin.command.domain.aggregate.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name="franchise")
@NoArgsConstructor
public class Franchise {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long franchiseId;
	@Column(name="name")
	private String franchiseName;
	@Column(name="bg_color")
	private String franchiseBackColor;
	@Column(name="font_color")
	private String franchiseFontColor;
	@Column(name="image")
	private String franchiseImage;
	@Column(name="process")
	private boolean processing;

	@Builder
	public Franchise(String franchiseName,String franchiseFontColor,String franchiseBackColor, String franchiseImage, boolean processing){
		this.franchiseName = franchiseName;
		this.franchiseBackColor = franchiseBackColor;
		this.franchiseFontColor = franchiseFontColor;
		this.franchiseImage = franchiseImage;
		this.processing = processing;
	}

	//프렌차이즈 정보 수정 메소드
	public void modifyFranchise(String franchiseName,String franchiseFontColor,String franchiseBackColor, String franchiseImage){
		this.franchiseName = franchiseName;
		this.franchiseBackColor = franchiseBackColor;
		this.franchiseFontColor = franchiseFontColor;
		this.franchiseImage = franchiseImage;
	}

	//처리 여부 변경 메소드
	public void processingCompleted(){ this.processing = false;}
	public void processingBefore(){ this.processing = true;}
}
