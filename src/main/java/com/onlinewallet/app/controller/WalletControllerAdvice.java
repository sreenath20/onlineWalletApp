package com.onlinewallet.app.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.onlinewallet.app.exceptions.LoginException;
import com.onlinewallet.app.exceptions.WalletException;

@RestControllerAdvice
public class WalletControllerAdvice {
	
	
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ExceptionHandler(WalletException.class)
	public String exceptionHandler(Exception e) {
		return e.getMessage();
	}

	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ExceptionHandler(LoginException.class)
	public String loginExceptionHandler(Exception e) {
		return e.getMessage();
	}

	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException e) {

		Map<String, String> errorMap = new HashMap<>();

		e.getBindingResult().getAllErrors().forEach( erorObject -> {
			String fieldName = ((FieldError) erorObject).getField();
			String errorMsg = erorObject.getDefaultMessage();
			errorMap.put(fieldName, errorMsg);

		});

		return errorMap;

	}
	
}
