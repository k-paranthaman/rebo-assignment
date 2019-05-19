/*
 * Copyright 2002-2019 the original author or authors.
 */
package com.cts.rebo.fileprocessor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.cts.rebo.exception.ApplicationException;
import com.cts.rebo.exception.ErrorType;
import com.cts.rebo.jaxb.Record;
import com.cts.rebo.jaxb.Records;

/**
 * {@link IFileProcessor} implementation for IFileProcessor. It provides implementation for CSV file format.
 * 
 * @author Paranthaman K
 *
 */
@Component(value = "csvProcessor")
public class CSVProcessor implements IFileProcessor {
	
	private static final Logger logger = LoggerFactory.getLogger(CSVProcessor.class);
	
	@Value("${statment.csv.delimiter}")
    private String delimiter;

	
	/* (non-Javadoc)
	 * @see com.cts.rebo.fileprocessor.IFileProcessor#readFile(java.lang.String)
	 */
	@Override
	public Records processFile(String inputString) {
		Reader csvReader = new StringReader(inputString);
		return readRecordsFromCSV(csvReader);
	}

	/**
	 * Reads the file and generates Records object and it returns {@link Records}.
	 * 
	 * @param csvReader
	 * @return
	 */
	private Records readRecordsFromCSV(Reader csvReader) {
		logger.info("Started the CSVProcessor.readRecordsFromCSV(Reader) method");
		Records records = new Records();

		// create an instance of BufferedReader
		// using try with resource, Java 7 feature to close resources
		try (BufferedReader br = new BufferedReader(csvReader);) {

			// read the first line from the text file
			String line = br.readLine();

			// loop until all lines are read
			Boolean flag = Boolean.FALSE;
			while (line != null) {
				
				if(flag){ // Ignore Header
					// use string.split to load a string array with the values from
					// each line of
					// the file, using delimiter
					String[] attributes = line.split(delimiter);

					Record record = createRecord(attributes);

					// adding Record into ArrayList
					records.addRecord(record);

				}

				// read next line before looping
				// if end of file reached, line would be null
				line = br.readLine();
				flag = Boolean.TRUE;
			}

		} catch (IOException e) {
			throw new ApplicationException(ErrorType.FILE_PROCESSING_ERROR,e);
		}
		logger.info("End of CSVProcessor.readRecordsFromCSV(Reader) method");
		return records;
	}

	/**
	 * @param metadata
	 * @return
	 */
	private static Record createRecord(String[] metadata) {
		Integer reference = Integer.valueOf(metadata[0]);
		String accountNum = metadata[1];
		String description = metadata[2];
		BigDecimal startBlance = new BigDecimal(metadata[3]);
		BigDecimal mutation = new BigDecimal(metadata[4]);
		BigDecimal endBlance = new BigDecimal(metadata[5]);
		
		return new Record(accountNum, description, startBlance,mutation,endBlance,reference);
	}

}
