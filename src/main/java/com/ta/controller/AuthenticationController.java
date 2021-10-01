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
package com.ta.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ta.dao.AuthDao;
import com.ta.dao.UserDao;
import com.ta.dto.ErrorLogDto;
import com.ta.dto.LoginDto;
import com.ta.dto.RefreshTokenDto;
import com.ta.dto.ResetPasswordDto;
import com.ta.dto.ResponseMessage;
import com.ta.dto.TokenDto;
import com.ta.enumeration.LogOperation;
import com.ta.service.OauthTokenService;
import com.ta.util.LogWrapper;

import io.swagger.annotations.ApiOperation;

@CrossOrigin
@RestController
@RequestMapping("api/all/auth")
public class AuthenticationController {

	@Autowired
	private UserDao dao;

	@Autowired
	private OauthTokenService oauth;

	@Autowired
	private AuthDao authDao;

	@PostMapping(value = "/login")
	@ApiOperation(value = "user login")
	public ResponseEntity<ResponseMessage<TokenDto>> login(@RequestBody LoginDto loginDto) {
		ResponseMessage<TokenDto> rm = new ResponseMessage<>();
		try {
			TokenDto dto = oauth.login(loginDto);
			if (dto != null) {
				rm.setMessage("Login Successfully.");
				rm.setStatusCode(1);
				rm.setResults(dto);
			} else {
				rm.setMessage("Login Failed Try again.");
				rm.setStatusCode(1);
				rm.setResults(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
			LogWrapper.logErrorDetails(ErrorLogDto.builder().operation(LogOperation.SELECT).errorMessage(e.getMessage())
					.exception(e).build());
		}

		return new ResponseEntity<>(rm, HttpStatus.OK);
	}

	@PostMapping(value = "renew-token")
	@ApiOperation(value = "refresh token")
	public ResponseEntity<ResponseMessage<TokenDto>> refreshNewToken(@RequestBody RefreshTokenDto dto,
			@RequestHeader(required = false, value = "Authorization") String basicToken, HttpServletRequest request) {
		ResponseMessage<TokenDto> rm = new ResponseMessage<>();

		try {
			TokenDto tokenDto = oauth.refreshNewToken(dto.getRefreshToken(), request);
			if (tokenDto != null) {
				rm.setMessage("New Access Token Generated");
				rm.setStatusCode(0);
				rm.setResults(tokenDto);
				rm.setStatusCode(1);

			} else {
				rm.setMessage("New Access Token Not Generated");
				rm.setStatusCode(0);
				rm.setResults(tokenDto);
				rm.setStatusCode(0);
			}

		} catch (Exception e) {
			LogWrapper.logErrorDetails(ErrorLogDto.builder().operation(LogOperation.CREATE).errorMessage(e.getMessage())
					.exception(e).build());
		}

		return new ResponseEntity<>(rm, HttpStatus.OK);
	}

	@PostMapping(value = "/reset-password")
	@ApiOperation(value = "Updation of new password entered by the user")
	public ResponseEntity<ResponseMessage<String>> resetPassword(@Valid @RequestBody ResetPasswordDto dto) {
		ResponseMessage<String> rm = new ResponseMessage<>();
		try {
			authDao.resetPassword(dto.getUsername(), dto.getCode(), dto.getNewPassword());
			rm.setMessage("Password reset successfully");
			rm.setStatusCode(1);
			rm.setResults(null);
		} catch (Exception e) {
			LogWrapper.logErrorDetails(ErrorLogDto.builder().operation(LogOperation.CREATE).errorMessage(e.getMessage())
					.exception(e).build());
		}
		return new ResponseEntity<>(rm, HttpStatus.OK);
	}
}
