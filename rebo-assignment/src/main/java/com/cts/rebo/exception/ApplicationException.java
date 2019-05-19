/*
 * Copyright 2002-2019 the original author or authors.
 */
package com.cts.rebo.exception;

/**
 * @author Paranthaman K
 *
 */
public class ApplicationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final ErrorType errorType;
	
	/**
	 * @param errorCode
	 */
	public ApplicationException(ErrorType errorCode){
		this.errorType = errorCode;
	}
	
	/**
	 * @param errorCode
	 * @param e
	 */
	public ApplicationException(ErrorType errorCode, Throwable e){
		super(e);
		this.errorType = errorCode;
	}


	/**
	 * @return the errorCode
	 */
	public ErrorType getErrorCode() {
		return errorType;
	}
	
	
}
