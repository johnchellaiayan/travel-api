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
package com.ta.filter;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.ta.util.Constants;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

	private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationEntryPoint.class);

	@Override 
	public void commence(HttpServletRequest req, HttpServletResponse res, AuthenticationException e)
			throws IOException, ServletException {
		
		String isApiKeyInvalid = (String) req.getAttribute("isApiKeyInvalid");
		if (isApiKeyInvalid != null && req.getAttribute(Constants.BooleanValue.IS_ERROR_HANDLED)==null) {
			req.setAttribute(Constants.BooleanValue.IS_ERROR_HANDLED,"true");
			res.sendError(HttpServletResponse.SC_FORBIDDEN, "Your API is invalid");			
		}		

		String expired = (String) req.getAttribute("expired"); 
		if (expired != null && req.getAttribute(Constants.BooleanValue.IS_ERROR_HANDLED)==null) {
			req.setAttribute(Constants.BooleanValue.IS_ERROR_HANDLED,"true");
			res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "JWT is Expired.please renew the token");			
		}
		
		String jwtInvalid = (String) req.getAttribute("jwt_invalid"); 
		if (jwtInvalid != null && req.getAttribute(Constants.BooleanValue.IS_ERROR_HANDLED)==null) {
			req.setAttribute(Constants.BooleanValue.IS_ERROR_HANDLED,"true");
			res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid JWT Format."+" - "+req.getAttribute("msg"));			
		}
		
		
		
		if(req.getAttribute("general_error")!=null)
		{
			req.setAttribute(Constants.BooleanValue.IS_ERROR_HANDLED,"true");
			res.sendError(HttpServletResponse.SC_FORBIDDEN,	"General Error " + e.getMessage()+" - "+req.getAttribute("msg"));
		}
       
		if(req.getAttribute(Constants.BooleanValue.IS_ERROR_HANDLED)==null)
		{
			req.setAttribute(Constants.BooleanValue.IS_ERROR_HANDLED,"true");
			res.sendError(HttpServletResponse.SC_FORBIDDEN,	"You're not authorized to access this resource.." + e.getMessage()+" - "+req.getAttribute("msg"));
		}
	}
}
