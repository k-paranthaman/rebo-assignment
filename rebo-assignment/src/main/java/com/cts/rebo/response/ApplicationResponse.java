/*
 * Copyright 2002-2019 the original author or authors.
 */
package com.cts.rebo.response;

/**
 * It is Base Response.
 * 
 * @author Paranthaman K
 * @param <T>
 *
 */
public class ApplicationResponse<T> {
	
	
	
	
	private Boolean status;
	private String message;
	private T data;
	/**
	 * @return the status
	 */
	public Boolean getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(Boolean status) {
		this.status = status;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	/**
	 * @return the data
	 */
	public T getData() {
		return data;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(T data) {
		this.data = data;
	}
	
	

}
