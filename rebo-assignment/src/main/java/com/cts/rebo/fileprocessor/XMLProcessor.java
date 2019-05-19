/*
 * Copyright 2002-2019 the original author or authors.
 */
package com.cts.rebo.fileprocessor;

import org.springframework.stereotype.Component;

import com.cts.rebo.jaxb.Records;
import com.cts.rebo.utils.XmlToObject;

/**
 * {@link IFileProcessor} implementation for IFileProcessor. It provides implementation for XML file format.
 * 
 * @author Paranthaman K
 *
 */
@Component(value="xmlProcessor")
public class XMLProcessor implements IFileProcessor {

	/* (non-Javadoc)
	 * @see com.cts.rebo.fileprocessor.IFileProcessor#readFile(java.lang.String)
	 */
	@Override
	public Records processFile(String inputString) {
		return XmlToObject.toObject(inputString, Records.class);
	}

}
