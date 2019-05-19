/*
 * Copyright 2002-2019 the original author or authors.
 */
package com.cts.rebo.testbase;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author Paranthaman K
 *
 */

@RunWith(SpringRunner.class)
@WebMvcTest
@ContextConfiguration(classes={TestConfig.class})
public abstract class AbstractTest {

	protected MockMvc mvc;

	@Autowired
	WebApplicationContext webApplicationContext;

	protected void setUp() {
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

}
