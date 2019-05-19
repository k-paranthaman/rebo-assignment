/*
 * Copyright 2002-2019 the original author or authors.
 */
package com.cts.rebo.enums;

/**
 * @author Paranthaman K
 *
 */
public enum FileType {
	
	XML("xml"),CSV("csv"),XLSX("xlsx"),XLS("xls");
	
	private String type;
	
	private FileType(String  type) {
		this. type =  type;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	
	
	

}
