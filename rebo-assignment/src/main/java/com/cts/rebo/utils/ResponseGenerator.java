/*
 * Copyright 2002-2019 the original author or authors.
 */
package com.cts.rebo.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.cts.rebo.response.ApplicationResponse;

/**
 * It is a common class to generate HTTP response.
 * 
 * @author Paranthaman K
 *
 */
public class ResponseGenerator {


	/**
	 * 
	 */
	private ResponseGenerator() {
	}
	/**
	 * @param data
	 * @param message
	 * @param status
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> ResponseEntity<T> toResponse(T data, String message, Boolean status) {

		ApplicationResponse<Object> response = new ApplicationResponse<>();
		response.setData(data);
		response.setMessage(message);
		response.setStatus(status);
		return (ResponseEntity<T>) new ResponseEntity<>(response, HttpStatus.OK);

	}
}
