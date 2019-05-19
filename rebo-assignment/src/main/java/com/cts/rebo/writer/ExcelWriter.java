/*
 * Copyright 2002-2019 the original author or authors.
 */
package com.cts.rebo.writer;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.cts.rebo.constants.ApplicationConstants;
import com.cts.rebo.exception.ApplicationException;
import com.cts.rebo.exception.ErrorType;
import com.cts.rebo.jaxb.Record;

/**
 * The <code>ExcelWriter</code> Class generate excel and writes to desire
 * output stream.
 * 
 * @author Paranthaman K
 *
 */
@Component
public class ExcelWriter {

	@Value("${statment.output.header}")
	private String[] headers;

	@Value("${statment.output.data.map}")
	private String[] data;

	/**
	 * This method will generate the excel file and write the excel into out stream.
	 * 
	 * @param recordList
	 * @param outputStream
	 */
	public void generateExcel(List<Record> recordList, OutputStream outputStream) {

		try (Workbook workbook = new XSSFWorkbook();) {

			Sheet sheet = workbook.createSheet(ApplicationConstants.SUMMARY_VALUE_STR);

			// Create header Row
			createHeader(sheet, workbook);

			// Create Other rows and cells with Status data
			createData(recordList, sheet);

			// Resize all columns to fit the content size
			for (int i = 0; i < headers.length; i++) {
				sheet.autoSizeColumn(i);
			}

			workbook.write(outputStream);
		} catch (IOException e) {
			throw new ApplicationException(ErrorType.OUTPUT_ERROR, e);
		}

	}
	
	/**
	 * <p>This method is to generate header row</p>
	 * 
	 * @param sheet
	 * @param headerCellStyle
	 */
	private void createHeader(Sheet sheet, Workbook workbook) {

		// Modify Header Style
		Font headerFont = workbook.createFont();
		headerFont.setBold(true);
		headerFont.setFontHeightInPoints((short) 14);
		headerFont.setColor(IndexedColors.BLUE.getIndex());

		CellStyle headerCellStyle = workbook.createCellStyle();

		headerCellStyle.setFont(headerFont);
		Row headerRow = sheet.createRow(0);

		for (int i = 0; i < headers.length; i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(headers[i]);
			cell.setCellStyle(headerCellStyle);
		}
	}

	/**
	 * <p>This method is to generate data rows</p>
	 * 
	 * @param recordList
	 * @param sheet
	 * @param rowNum
	 * @return
	 */
	private void createData(List<Record> recordList, Sheet sheet) {
		int rowNum = 1;
		for (Record record : recordList) {
			Row row = sheet.createRow(rowNum++);
			createDataRow(row, record);
		}
	}

	/**
	 * <p>This method is to generate data row. Object and Property is mapped from property file, that will enable dynamic data generation</p>
	 * 
	 * @param sheet
	 * @param rowNum
	 * @param record
	 * @return
	 */
	private void createDataRow(Row dataRow, Record record) {

		try {
			for (int i = 0; i < data.length; i++) {
				Cell cell = dataRow.createCell(i);
				cell.setCellValue(BeanUtils.getProperty(record, data[i]));
			}
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			throw new ApplicationException(ErrorType.OUTPUT_ERROR);
		}

	}



}
