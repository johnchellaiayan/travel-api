/*******************************************************************************
 * ======================================================================================================
 *                                     Copyright (C) 2019 Trackerwave Pvt Ltd.
 *                                             All rights reserved
 * ======================================================================================================
 * Notice:  All Rights Reserved.
 * This material contains the trade secrets and confidential business information of Trackerwave Pvt Ltd,
 * which embody substantial creative effort, design, ideas and expressions.  No part of this material may
 * be reproduced or transmitted in any form or by any means, electronic, mechanical, optical or otherwise
 * ,including photocopying and recording, or in connection with any information storage or retrieval
 * system, without written permission.
 *        
 * www.trackerwave.com, Traceability and Change log maintained in Source Code Control System
 * =====================================================================================================
 ******************************************************************************/
package com.ta.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.collect.Lists;
import com.ta.util.Constants;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ApiKeyVehicle;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	private ApiInfo apiInfo() {
		return new ApiInfo("Ovitag Api List", "Your REST API for web app", "1.0", "Terms of service",
				new Contact("Pradeep", "http://techie-mixture.blogspot.com/", ""), "", "");
		
	}

	@Bean
	public SecurityConfiguration securityInfo() {
		return new SecurityConfiguration(null, null, null, null, "", ApiKeyVehicle.HEADER, Constants.HeaderValue.AUTHORIZATION, "");
	}

	private ApiKey apiKey() {
		return new ApiKey(Constants.HeaderValue.AUTHORIZATION, Constants.HeaderValue.AUTHORIZATION, "header"); 
	}

	private ApiInfo metadata() {
		return new ApiInfoBuilder()//
				.title("Covid 19 api list documentation - 06-08-2020 - 07:30 AM")//
				.description("Api Documentation")//
				.version("1.0.0")//
				.license("MIT License").licenseUrl("http://opensource.org/licenses/MIT")//
				.contact(new Contact(null, null, "trackerwave@gmail.com"))//
				.build();
	}
	
	@Bean
	public Docket apiKeyAPI() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("2.API_KEY_GROUP")//
				.select()//
				.apis(RequestHandlerSelectors.basePackage("com.ta.controller")).build().apiInfo(apiInfo())
				.securitySchemes(Lists.newArrayList(apiKey())).apiInfo(metadata())//
				.useDefaultResponseMessages(false)//
				.securitySchemes(new ArrayList<>(Arrays.asList(new ApiKey("API_KEY", "API_KEY", "Header"))))
				.genericModelSubstitutes(Optional.class);
	}
	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("1.JWT_GROUP")//
				.select()//
				.apis(RequestHandlerSelectors.basePackage("com.ta.controller")).build().apiInfo(apiInfo())
				.securitySchemes(Lists.newArrayList(apiKey())).apiInfo(metadata())//
				.useDefaultResponseMessages(false)//
				// .securitySchemes(new ArrayList<>(Arrays.asList(new ApiKey("Bearer %token",
				// "Authorization", "Header"))))//
				// .tags(new Tag("users", "Operations about users"))//
				// .tags(new Tag("ping", "Just a ping"))//
				.genericModelSubstitutes(Optional.class);
	}

}
