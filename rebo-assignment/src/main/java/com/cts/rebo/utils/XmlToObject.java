/*
 * Copyright 2002-2019 the original author or authors.
 */
package com.cts.rebo.utils;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.cts.rebo.exception.ApplicationException;
import com.cts.rebo.exception.ErrorType;
import com.cts.rebo.jaxb.Records;

/**
 * This is a Utility class to help convert XML to java object and object to XML using JAXB.
 * 
 * @author Paranthaman K
 *
 */
public class XmlToObject {


	/**
	 * 
	 */
	private XmlToObject() {
	}
	/**
	 * This method is to convert XML string to java object.
	 * 
	 * @param xmlStr
	 * @param classType
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T>T toObject(String xmlStr, Class<T> classType) {

		try {

			StringReader reader = new StringReader(xmlStr);
			JAXBContext jaxbContext = JAXBContext.newInstance(classType);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Records records = (Records) jaxbUnmarshaller.unmarshal(reader);
			return (T) records;
		} catch (JAXBException e) {
			throw new ApplicationException(ErrorType.XML_PARSE_ERROR,e);
		}

	}

}
