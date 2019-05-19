/*
 * Copyright 2002-2019 the original author or authors.
 */
package com.cts.rebo.testbase;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Paranthaman K
 *
 */
@Configuration
@ComponentScan(
    basePackages="com.cts.*")
public class TestConfig {

}
