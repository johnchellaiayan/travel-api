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
package com.ta.util;

import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
public class URLUtil {

	@Autowired
	private Environment environment;

	@Autowired
	private ServletContext context;

	@Value("${api.baseurl.protocol}")
	private String protocol;

	@Value("${api.base-url}")
	private String baseUrl;

	public String getBaseUrl(HttpServletRequest request) throws MalformedURLException {
		URL requestURL = new URL(request.getRequestURL().toString());
		String port = requestURL.getPort() == -1 ? "" : ":" + requestURL.getPort();
		return requestURL.getProtocol() + "://" + requestURL.getHost() + port;
	}

	public String getApiBaseUrl(HttpServletRequest request) throws MalformedURLException {
		String apiBaseUrl = null;
		String contextPath = context.getContextPath();
		apiBaseUrl = this.getBaseUrl(request) + "/" + contextPath;
		return apiBaseUrl;
	}

	public String getOauthUrl(HttpServletRequest request) throws MalformedURLException {
		String oauthBaseUrl = null;
		if (protocol != null && protocol.equals("http")) {
			String contextPath = context.getContextPath();
			oauthBaseUrl = this.getBaseUrl(request) + contextPath + "/oauth/token";
		}
	
		return oauthBaseUrl;
	}

}
