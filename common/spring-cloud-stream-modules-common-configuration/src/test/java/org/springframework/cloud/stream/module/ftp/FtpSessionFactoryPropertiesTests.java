/*
 * Copyright 2015-2016 the original author or authors.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.cloud.stream.module.ftp;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.EnvironmentTestUtils;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

/**
 * @author David Turanski
 * @author Gary Russell
 */
public class FtpSessionFactoryPropertiesTests {

	@Test
	public void hostCanBeCustomized() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		EnvironmentTestUtils.addEnvironment(context, "username:user", "host:myHost");
		context.register(Conf.class);
		context.refresh();
		FtpSessionFactoryProperties properties = context.getBean(FtpSessionFactoryProperties.class);
		assertThat(properties.getHost(), equalTo("myHost"));
	}

	@Test
	public void portCanBeCustomized() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		EnvironmentTestUtils.addEnvironment(context, "username:user", "host:myHost", "port:12");
		context.register(Conf.class);
		context.refresh();
		FtpSessionFactoryProperties properties = context.getBean(FtpSessionFactoryProperties.class);
		assertThat(properties.getPort(), equalTo(12));
	}

	@Test
	public void usernameCanBeCustomized() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		EnvironmentTestUtils.addEnvironment(context, "username:user", "host:myHost");
		context.register(Conf.class);
		context.refresh();
		FtpSessionFactoryProperties properties = context.getBean(FtpSessionFactoryProperties.class);
		assertThat(properties.getUsername(), equalTo("user"));
	}

	@Test
	public void clientModeCanBeCustomized() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		EnvironmentTestUtils.addEnvironment(context, "username:user", "host:myHost", "clientMode:PASSIVE");
		context.register(Conf.class);
		context.refresh();
		FtpSessionFactoryProperties properties = context.getBean(FtpSessionFactoryProperties.class);
		assertThat(properties.getClientMode(), equalTo(FtpSessionFactoryProperties.ClientMode.PASSIVE));
	}

	@Test
	public void passwordCanBeCustomized() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		EnvironmentTestUtils.addEnvironment(context, "username:user", "host:myHost", "password:pass");
		context.register(Conf.class);
		context.refresh();
		FtpSessionFactoryProperties properties = context.getBean(FtpSessionFactoryProperties.class);
		assertThat(properties.getPassword(), equalTo("pass"));
	}

	@Configuration
	@EnableConfigurationProperties(FtpSessionFactoryProperties.class)
	static class Conf {
	}

}
