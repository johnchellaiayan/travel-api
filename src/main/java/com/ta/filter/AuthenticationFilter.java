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
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.jwk.source.RemoteJWKSet;
import com.nimbusds.jose.proc.BadJOSEException;
import com.nimbusds.jose.proc.JWSKeySelector;
import com.nimbusds.jose.proc.JWSVerificationKeySelector;
import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.JWTParser;
import com.nimbusds.jwt.proc.ConfigurableJWTProcessor;
import com.nimbusds.jwt.proc.DefaultJWTProcessor;
import com.ta.dto.ApiKeyDto;
import com.ta.dto.ErrorLogDto;
import com.ta.enumeration.LogOperation;
import com.ta.util.AES;
import com.ta.util.Constants;
import com.ta.util.LogWrapper;
import com.ta.util.URLUtil;

public class AuthenticationFilter extends OncePerRequestFilter {

	@Value("${api.base-url}")
	private String issuerUrl;
	private static final String AUTH_HEADER_STRING = "Authorization";
	private static final String AUTH_BEARER_STRING = "Bearer";
	private static final String API_KEY_HEADER = "API_KEY";

	@Value("${oauth.key-store-path:/.well-known/jwks.json}")
	private String keyStorePath;

	// should cache keys
	private RemoteJWKSet remoteJWKSet;

	@Autowired
	private URLUtil urlUtil;

	@Bean
	public FilterRegistrationBean registerFilter() {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(this);

		return registration;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {

		if (remoteJWKSet == null) {

			if (urlUtil.getApiBaseUrl(req) != null) {
				issuerUrl = urlUtil.getApiBaseUrl(req);
			}

			URL jWKUrl = new URL(issuerUrl + keyStorePath);
			remoteJWKSet = new RemoteJWKSet(jWKUrl);
		}

		Boolean isError = false;
		String header = req.getHeader(AUTH_HEADER_STRING);
		String authKeyHeader = req.getHeader(API_KEY_HEADER);
		Boolean isLoginPageRequest = false;

		if (header != null) {
			header = req.getHeader(AUTH_HEADER_STRING).replace(AUTH_BEARER_STRING, "");
		}

		String url = req.getRequestURL().toString();

		if ((url != null) && (url.contains("api/all/auth/login") || url.contains("api/all/login"))) {
			isLoginPageRequest = true;
		}

		if (header == null && authKeyHeader != null && url != null) {
			String decrptedString = null;
			try {
				decrptedString = AES.decrypt(authKeyHeader);
			} catch (Exception e) {

				isError = true;
				req.setAttribute(Constants.BooleanValue.IS_API_KEY_INVALID, "true");
				if (req.getAttribute(Constants.BooleanValue.IS_ERROR_HANDLED) == null) {
					req.setAttribute(Constants.BooleanValue.IS_ERROR_HANDLED, "true");
					res.sendError(HttpServletResponse.SC_FORBIDDEN, "Your API Key is invalid..");
				}

			}

			if (!Boolean.TRUE.equals(isError)) {
				if (decrptedString != null) {
					try {
						ObjectMapper mapper = new ObjectMapper();
						ApiKeyDto keyDto = mapper.readValue(decrptedString, ApiKeyDto.class);
						String domain = keyDto.getDomain();
						if (domain != null && domain.equalsIgnoreCase("covid")) {
							List<GrantedAuthority> authorities = new ArrayList<>();
							authorities.add(new SimpleGrantedAuthority("CUSTOMER"));

							UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
									null, null, null);
							SecurityContextHolder.getContext().setAuthentication(authToken);
						}
					} catch (Exception e) {
						LogWrapper.logErrorDetails(ErrorLogDto.builder().operation(LogOperation.CREATE)
								.errorMessage(e.getMessage()).exception(e).build());
						req.setAttribute(Constants.BooleanValue.IS_API_KEY_INVALID, "true");
					}
				} else {
					req.setAttribute(Constants.BooleanValue.IS_API_KEY_INVALID, "true");
					if (req.getAttribute(Constants.BooleanValue.IS_ERROR_HANDLED) == null) {
						req.setAttribute(Constants.BooleanValue.IS_ERROR_HANDLED, "true");
						req.setAttribute("text", "b");
						res.sendError(HttpServletResponse.SC_FORBIDDEN, "Your API Key is invalid..");
					}
				}
			}
		}

		if (header != null && !isLoginPageRequest) {
			req.setAttribute("text", "c");
			JWT jwt = null;
			try {
				jwt = JWTParser.parse(header);

				// check if issuer is our user pool

				JWSKeySelector keySelector = new JWSVerificationKeySelector(JWSAlgorithm.RS256, remoteJWKSet);

				ConfigurableJWTProcessor jwtProcessor = new DefaultJWTProcessor();
				jwtProcessor.setJWSKeySelector(keySelector);

				JWTClaimsSet claimsSet = jwtProcessor.process(jwt, null);
				// process roles (gropus in cognito)

				/**
				 * List<String> groups = (List<String>) claimsSet.getClaim("cognito:groups");
				 * List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
				 * System.out.println("3"); for (String group : groups) {
				 * System.out.println("4"); logger.info("Authorities " + group);
				 * System.out.println("Authorities " + group); authorities.add(new
				 * SimpleGrantedAuthority(group)); }
				 * 
				 */

				UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
						claimsSet, null, null);

				SecurityContextHolder.getContext().setAuthentication(authenticationToken);

			} catch (ParseException e) {
				req.setAttribute("jwt_invalid", "true");

			} catch (JOSEException e) {

				req.setAttribute("msg", "two " + e + "- " + header + " -" + jwt);
			} catch (BadJOSEException e) {
				req.setAttribute("expired", "true");
				req.setAttribute("msg", "three " + e + "- " + header + " -" + jwt);
			} catch (NullPointerException e) {
				// in case that header is null
				req.setAttribute("msg", "four " + e + "- " + header + " -" + jwt);
			} catch (Exception e) {
				req.setAttribute("general_error", "true");
				req.setAttribute("msg", "five " + e + "- " + header + " -" + jwt);
			}
		}

		chain.doFilter(req, res);

	}
}
