/*
 * Copyright 2002-2019 the original author or authors.
 */
package com.cts.rebo.service;

import org.springframework.web.multipart.MultipartFile;

import com.cts.rebo.jaxb.Records;

/**
 * 
 * @author Paranthaman K
 *
 */
public interface ICustomerService {

	/**
	 * It reads the file upload by client and generate the final statement.
	 * 
	 * @param uploadFile
	 * @return Records
	 */

	public Records generateStatement(MultipartFile uploadFile);

}
