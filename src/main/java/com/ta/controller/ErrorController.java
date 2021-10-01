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

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ta.dto.ErrorLogDto;
import com.ta.dto.ResponseMessage;
import com.ta.entity.ApplicationError;
import com.ta.enumeration.LogOperation;
import com.ta.repository.ErrorLogRepository;
import com.ta.util.LogWrapper;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("api/public/errors")
public class ErrorController {

	@Autowired
	private ErrorLogRepository repo;

	@Transactional
	@PostMapping(value = "")
	@ApiOperation(value = "save  error logs")
	public ResponseEntity<ResponseMessage<String>> saveError(@RequestBody ApplicationError applicationError) {
		ResponseMessage<String> message = new ResponseMessage<>();

		try {
			applicationError.setCreatedAt(new Date());
			applicationError = repo.save(applicationError);
			if (applicationError != null) {
				message.setMessage("error logs saved successfully");
				message.setResults(null);
				message.setStatusCode(1);

			} else {
				message.setMessage("error log not saved");
				message.setResults(null);
				message.setStatusCode(0);

			}
		} catch (Exception e) {
			LogWrapper.logErrorDetails(ErrorLogDto.builder()
					.operation(LogOperation.SELECT).errorMessage(e.getMessage()).exception(e).build());
			throw e;
		}
		return new ResponseEntity<>(message, HttpStatus.OK);
	}

}
