/*
 * Copyright 2002-2019 the original author or authors.
 */
package com.cts.rebo.statement.processor;

import com.cts.rebo.jaxb.Records;

/**
 * @author Paranthaman K
 *
 */
public interface IStatementProcessor {
	
	/**
	 * @param records
	 */
	public void processStatement(Records records);	

}
