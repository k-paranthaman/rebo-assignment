/*
 * Copyright 2002-2019 the original author or authors.
 */
package com.cts.rebo.scanner;

/**
 * It is a Anti virus scanner.
 * 
 * @author Paranthaman K
 *
 */
public interface IFileScanner {
	
	/**
	 * This provides antivirus scanner.
	 * 
	 * @param fileBytes
	 */
	public Boolean scanFile(byte[] fileBytes);

}
