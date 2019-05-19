/*
 * Copyright 2002-2019 the original author or authors.
 */
package com.cts.rebo.reboassignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 
 * The <code>ReboAssignmentApplication</code> 
 * Class used to launch the Spring Web application. 
 * 
 * @author Paranthaman K
 *
 */

@SpringBootApplication
@ComponentScan(basePackages="com.cts.*")
public class ReboAssignmentApplication {

	public static void main(String[] args) {
		System.setProperty("server.servlet.context-path", "/rebo-assignment");
		SpringApplication.run(ReboAssignmentApplication.class, args);
	}

}
