/*
 * Copyright 2002-2019 the original author or authors.
 */
package com.cts.rebo.advisor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cts.rebo.enums.StatusEnum;
import com.cts.rebo.exception.ApplicationException;
import com.cts.rebo.exception.ErrorType;
import com.cts.rebo.utils.ResponseGenerator;

/**
 * This is a application exception handler. If any exception occurs, this class will handle and send appropriate error message.
 * 
 * @author Paranthaman K
 *
 */

@ControllerAdvice
public class ApplicationExceptionAdvisor {
	
	private static final Logger logger = LoggerFactory.getLogger(ApplicationExceptionAdvisor.class);

	/**
	 * This is a {@link ApplicationException} handler. If any exception occurs, this class will handle and send appropriate error message.
	 * 
	 * @param exception
	 * @return
	 */
	@ExceptionHandler({ ApplicationException.class})
	public ResponseEntity<String> handleException(final ApplicationException exception) {
		
		logger.error("Error found", exception);
		return ResponseGenerator.toResponse(exception.getErrorCode().getErrorMessage(),StatusEnum.FAILURE.toString(),Boolean.FALSE);
	}
	
	/**
	 * 
	 * This is a {@link Throwable} handler. If any exception occurs, this class will handle and send appropriate error message.
	 * 
	 * @param exception
	 * @return
	 */
	@ExceptionHandler({ Throwable.class})
	public ResponseEntity<String>  handleThrowable(final Throwable exception) {
		logger.error("",exception);
		return ResponseGenerator.toResponse(ErrorType.SERVER_ERROR.getErrorMessage(),StatusEnum.FAILURE.toString(),Boolean.FALSE);
	}

}
