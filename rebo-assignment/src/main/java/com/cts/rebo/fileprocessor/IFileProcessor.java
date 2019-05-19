/*
 * Copyright 2002-2019 the original author or authors.
 */
package com.cts.rebo.fileprocessor;

import com.cts.rebo.jaxb.Records;

/**
 * 
 * It is a file processor.
 * 
 * @author Paranthaman K
 *
 */
public interface IFileProcessor {
	
	/**
	 * 
	 * It process the file and returns the {@link Records} object.
	 * @param <T>
	 * 
	 * @see com.cts.rebo.fileprocessor.CSVProcessor
	 * @see com.cts.rebo.fileprocessor.XMLProcessor
	 * 
	 * @param inputString
	 * @return
	 */
	public Records processFile(String inputString);

}
