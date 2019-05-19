/*
 * Copyright 2002-2019 the original author or authors.
 */
package com.cts.rebo.service.impl;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cts.rebo.constants.ApplicationConstants;
import com.cts.rebo.enums.FileType;
import com.cts.rebo.exception.ApplicationException;
import com.cts.rebo.exception.ErrorType;
import com.cts.rebo.factory.FileProcessorFactory;
import com.cts.rebo.fileprocessor.IFileProcessor;
import com.cts.rebo.jaxb.Records;
import com.cts.rebo.scanner.IFileScanner;
import com.cts.rebo.service.ICustomerService;
import com.cts.rebo.statement.processor.IStatementProcessor;

/**
 * 
 * {@link ICustomerService} implementation for IStatementProcessorService interface.
 * 
 * @author Paranthaman K
 *
 */
@Service
public class CustomerServiceImpl implements ICustomerService {

	private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);
	
	@Autowired
	private FileProcessorFactory processFactory;
	
	@Autowired
	private IStatementProcessor stmtProcessor;
	
	@Autowired
	private BeanFactory beanFactory;
	
	@Value("${cts.robo.encoding}")
	private String encoding;
	
	@Value("${rebo.antivisrus.impl}")
	private String antiVirusScanner;
	

	
	/* (non-Javadoc)
	 * @see com.cts.rebo.service.IStatementProcessorService#statementProcessor(org.springframework.web.multipart.MultipartFile)
	 */
	@Override
	public Records generateStatement(MultipartFile uploadFile) {
		
		logger.info("Started the CustomerServiceImpl.generateStatement(MultipartFile) method");
		try {
			IFileScanner fileScanner = (IFileScanner) beanFactory.getBean(antiVirusScanner);
			Boolean fileScanStatus = fileScanner.scanFile(uploadFile.getBytes());
			if(fileScanStatus){
				
				String extension = uploadFile.getOriginalFilename().split(ApplicationConstants.DOT_VALUE_STR)[1];
				
				logger.debug("Uploaded file type as : {}" , extension);
				
				FileType fileType = FileType.valueOf(StringUtils.upperCase(extension));
				IFileProcessor processor = processFactory.getProcessor(fileType);
				String inputString = new String(uploadFile.getBytes(), encoding);
				
				Records records = processor.processFile(inputString);
				
				stmtProcessor.processStatement(records);
				
				logger.info("End of CustomerServiceImpl.generateStatement(MultipartFile) method");
				return records;
			}else{
				throw new ApplicationException(ErrorType.FILE_CORRUPTED_ERROR);
			}
			
		} catch (IOException e) {
			throw new ApplicationException(ErrorType.FILE_PROCESSING_ERROR, e);
		}

	}


}
