/*
 * Copyright 2002-2019 the original author or authors.
 */
package com.cts.rebo.scanner;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * {@link IFileScanner} implementation for IFileScanner. It provides implementation for AVG Scanner.
 * 
 * @author Paranthaman K
 *
 */
@Component(value = "avgScanner")
public class AVGFileScanner implements IFileScanner {
	
	@Value(value="${rebo.antivirus.server.name}")
	private String serverName;
	
	@Value(value="${rebo.antivirus.server.port}")
	private Integer serverPort;

	/* (non-Javadoc)
	 * @see com.cts.rebo.scanner.IFileScanner#scanFile(byte[])
	 */
	public Boolean scanFile(byte[] fileBytes) {
		// No Anti virus Server setup available
			
		return true;
		
	}

}
