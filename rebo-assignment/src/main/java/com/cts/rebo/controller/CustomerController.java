/*
 * Copyright 2002-2019 the original author or authors.
 */
package com.cts.rebo.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cts.rebo.constants.ApplicationConstants;
import com.cts.rebo.exception.ApplicationException;
import com.cts.rebo.exception.ErrorType;
import com.cts.rebo.jaxb.Records;
import com.cts.rebo.service.ICustomerService;
import com.cts.rebo.writer.ExcelWriter;

/**
 * The <code>CustomerController</code> 
 * is a rest controller class. 
 * 
 * 
 * @author Paranthaman K
 *
 */
@RestController
@RequestMapping(value=ApplicationConstants.CUSTOMER_API)
public class CustomerController {
	
	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
	
	@Autowired
	private ICustomerService custService;
	
	@Autowired
	private ExcelWriter excelWriter;
	
	@Value("${statment.output.filename}")
	private String fileName;
	
	/**
	 * This method is a statement processor API. It receives either CSV or XML file from client and sends the 
	 * statement excel over HTTP
	 * 
	 * @param uploadFile
	 * @param response
	 */
	@PostMapping(path=ApplicationConstants.STATEMENT_GENERATE_URI,headers=("content-type=multipart/*"))
	public void generateStatement(@RequestParam("file") MultipartFile uploadFile,HttpServletResponse response){
		
		logger.info("Started the CustomerController.generateStatement() method");
		try {
			Records records =  custService.generateStatement(uploadFile);
			
			// Write the file to HTTP response
			response.setContentType(ApplicationConstants.CONTENT_TYPE_EXCEL);

			response.setHeader(ApplicationConstants.CONTENT_DISPOSITION, fileName);
			excelWriter.generateExcel(records.getRecord(), response.getOutputStream());
			
		} catch (IOException e) {
			throw new ApplicationException(ErrorType.OUTPUT_ERROR);
		}
		
		logger.info("End of CustomerController.generateStatement() method");

	}

}
