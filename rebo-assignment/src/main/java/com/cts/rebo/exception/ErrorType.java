/*
 * Copyright 2002-2019 the original author or authors.
 */
package com.cts.rebo.exception;

/**
 * @author Paranthaman K
 *
 */
public enum ErrorType {

	BUSINESS_ERROR(100,""), XML_PARSE_ERROR(200,"Error Parsing XML"), FILE_PROCESSING_ERROR(300," Error Processing file"), 
	NOT_SUPPORTED_ERROR(400," Application not support this option"), SERVER_ERROR(500,"Unknow server error please contact application support team"),
	OUTPUT_ERROR(600,"Error while Generating file"), 
	FILE_SCAN_ERROR(700,"Error while scan uploaded file"),
	FILE_CORRUPTED_ERROR(701,"Upload file found with virus please check"), 
	NETWORK_ERROR(1000,"Error Conect ton external system please try after some time");

	private int errorCode;
	private String errorMessage;

	private ErrorType(int errorCode,String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public int getErrorCode() {
		return errorCode;
	}

	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}


	
}
