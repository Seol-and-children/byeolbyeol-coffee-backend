package com.seolandfriends.byeolbyeolcoffee.exception;


import java.util.HashMap;
import java.util.Map;

import javax.validation.ValidationException;

import com.seolandfriends.byeolbyeolcoffee.exception.dto.ApiExceptionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<Map<String, String>> dtoValidation(final MethodArgumentNotValidException e) {
		Map<String, String> errors = new HashMap<>();
		e.getBindingResult().getAllErrors().forEach((error)-> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
			.body(errors);
	}

}