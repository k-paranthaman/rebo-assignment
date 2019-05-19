/*
 * Copyright 2002-2019 the original author or authors.
 */
package com.cts.rebo.statement.processor;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.cts.rebo.enums.StatusEnum;
import com.cts.rebo.jaxb.Record;
import com.cts.rebo.jaxb.Records;

/**
 * {@link IStatementProcessor} implementation for IStatementProcessor. 
 * 
 * @author Paranthaman K
 *
 */
@Component
public class StatementProcessorImpl implements IStatementProcessor {
	
	private static final Logger logger = LoggerFactory.getLogger(StatementProcessorImpl.class);
	
	@Value("${statement.pricision}")
	private Integer pricision;

	
	/* (non-Javadoc)
	 * @see com.cts.rebo.statement.processor.IStatementProcessor#processStatement(com.cts.rebo.jaxb.Records)
	 */
	@Override
	public void processStatement(Records records) {
		logger.info("Started the StatementProcessorImpl.processStatement(Records) method");
		List<Record> recordList = records.getRecord();

		List<Integer> duplicateReference = recordList.stream()
				.collect(Collectors.groupingBy(Record::getReference, Collectors.counting())).entrySet().stream()
				.filter(e -> e.getValue() > 1L).map(e -> e.getKey()).collect(Collectors.toList());
		
		logger.debug("Duplicate reference ids : {} ",duplicateReference); 

		recordList.stream().forEach(e -> 
			setStatus(duplicateReference, e)
		);
		logger.info("End of StatementProcessorImpl.processStatement(Records) method");
	}

	/**
	 * @param duplicateReference
	 * @param record
	 */
	private void setStatus(List<Integer> duplicateReference, Record record) {
		if (duplicateReference.contains(record.getReference())) {
			record.setStatus(StatusEnum.DUPLICATE);
		} else if (tallyBalance(record.getStartBalance(), record.getMutation(), record.getEndBalance())) {
			record.setStatus(StatusEnum.SUCCESS);
		} else {
			record.setStatus(StatusEnum.FAILURE);
		}
	}

	/**
	 * @param startBalance
	 * @param mutation
	 * @param endBalance
	 * @return
	 */
	private Boolean tallyBalance(BigDecimal startBalance, BigDecimal mutation, BigDecimal endBalance) {
		MathContext mc = new MathContext(pricision);
		BigDecimal sumValue = startBalance.round(mc).add(mutation.round(mc), mc);
		return sumValue.compareTo(endBalance.round(mc)) == 0 ? Boolean.TRUE : Boolean.FALSE;
	}
}
