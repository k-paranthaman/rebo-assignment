/*
 * Copyright 2002-2019 the original author or authors.
 */
package com.cts.rebo.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.cts.rebo.enums.FileType;
import com.cts.rebo.exception.ApplicationException;
import com.cts.rebo.exception.ErrorType;
import com.cts.rebo.fileprocessor.IFileProcessor;

/**
 * @author Paranthaman K
 *
 */
@Component
public class FileProcessorFactory {
	
	@Autowired
	@Qualifier(value="csvProcessor")
	private IFileProcessor csvProcessor;
	
	@Autowired
	@Qualifier(value="xmlProcessor")
	private IFileProcessor xmlProcessor;
	
	/**
	 * 
	 * It is a factory method. It returns {@link IFileProcessor} type. 
	 * 
	 * @see com.cts.rebo.fileprocessor.CSVProcessor
	 * @see com.cts.rebo.fileprocessor.XMLProcessor
	 * 
	 * @param fileType
	 * @return
	 */
	public IFileProcessor getProcessor(FileType fileType ){
		
		switch (fileType) {
		case XML:
			return xmlProcessor;
		case CSV:
			return csvProcessor;
		default:
			throw new ApplicationException(ErrorType.NOT_SUPPORTED_ERROR);
		}
	}

}
