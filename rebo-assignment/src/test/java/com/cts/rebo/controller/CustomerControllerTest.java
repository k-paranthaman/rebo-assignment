/*
 * Copyright 2002-2019 the original author or authors.
 */
package com.cts.rebo.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.InputStream;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cts.rebo.testbase.AbstractTest;

/**
 * @author Paranthaman K
 *
 */

public class CustomerControllerTest extends AbstractTest{

	private static final String URL = "/customer/statement/get";
	private static final String PARAM_NAME = "file";
	private static final String TYPE = "text/plain";

	@Before
	@Override
	public void setUp() {
		super.setUp();
	}

	@Test
	public void generateXMLStatementTest() throws Exception {
		// given
		InputStream fis = CustomerControllerTest.class.getResourceAsStream("/records.xml");
		MockMultipartFile firstFile = new MockMultipartFile(PARAM_NAME, "records.xml", TYPE, fis);
		mvc.perform(MockMvcRequestBuilders.multipart(URL).file(firstFile))
				.andExpect(status().is(200));

	}

	@Test
	public void generateCSVStatementTest() throws Exception {
		// given
		InputStream fis = this.getClass().getResourceAsStream("/records.csv");
		MockMultipartFile firstFile = new MockMultipartFile(PARAM_NAME, "records.csv", TYPE, fis);
		mvc.perform(MockMvcRequestBuilders.multipart(URL).file(firstFile))
				.andExpect(status().is(200));

	}

	@Test
	public void notSupportedFileTypeTest() throws Exception {
		// given
		InputStream fis = null;
		MockMultipartFile firstFile = new MockMultipartFile(PARAM_NAME, "records.xlsx", TYPE, fis);
		mvc.perform(MockMvcRequestBuilders.multipart(URL).file(firstFile))
				.andExpect(status().is(200)).andExpect(content().string("{\"status\":false,\"message\":\"FAILURE\",\"data\":\" Application not support this option\"}"));

	}
	
	@Test
	public void notUnkownFileTest() throws Exception {
		// given
		InputStream fis = null;
		MockMultipartFile firstFile = new MockMultipartFile(PARAM_NAME, "records.pdf", TYPE, fis);
		mvc.perform(MockMvcRequestBuilders.multipart(URL).file(firstFile))
				.andExpect(status().is(200)).andExpect(content().string("{\"status\":false,\"message\":\"FAILURE\",\"data\":\"Unknow server error please contact application support team\"}"));

	}
	
	@Test
	public void xmlParseErrorTest() throws Exception {
		// given
		InputStream fis =  this.getClass().getResourceAsStream("/ErrorRecords.xml");
		MockMultipartFile firstFile = new MockMultipartFile(PARAM_NAME, "ErrorRecords.xml", TYPE, fis);
		mvc.perform(MockMvcRequestBuilders.multipart(URL).file(firstFile))
				.andExpect(status().is(200)).andExpect(content().string("{\"status\":false,\"message\":\"FAILURE\",\"data\":\"Error Parsing XML\"}"));

	}
}
