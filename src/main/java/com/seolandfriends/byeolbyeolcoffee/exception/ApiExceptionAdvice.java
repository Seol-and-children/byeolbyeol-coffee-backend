package com.seolandfriends.byeolbyeolcoffee.exception;


import com.seolandfriends.byeolbyeolcoffee.exception.dto.ApiExceptionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionAdvice {

	@ExceptionHandler(LoginFailedException.class)
	public ResponseEntity<ApiExceptionDTO> exceptionHandler(LoginFailedException e){
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
			.body(new ApiExceptionDTO(HttpStatus.BAD_REQUEST, e.getMessage()));
	}

	@ExceptionHandler(TokenException.class)
	public ResponseEntity<ApiExceptionDTO> exceptionHandler(TokenException e){
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
			.body(new ApiExceptionDTO(HttpStatus.UNAUTHORIZED, e.getMessage()));
	}

	@ExceptionHandler(DuplicatedUserEmailException.class)
	public ResponseEntity<ApiExceptionDTO> exceptionHandler(DuplicatedUserEmailException e){
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
			.body(new ApiExceptionDTO(HttpStatus.BAD_REQUEST, e.getMessage()));
	}

}