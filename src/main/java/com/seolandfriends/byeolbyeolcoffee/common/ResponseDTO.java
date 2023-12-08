package com.seolandfriends.byeolbyeolcoffee.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@ToString
public class ResponseDTO {


	private int status;
	private String message;
	private Object data;
	private boolean success;

	public ResponseDTO() {
	}

	public ResponseDTO(HttpStatus status, String message, boolean success, Object data) {
		this.status = status.value();
		this.message = message;
		this.success = success;
		this.data = data;
	}
}