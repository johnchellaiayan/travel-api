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

import com.ta.dao.UserDao;
import com.ta.dto.ErrorLogDto;
import com.ta.dto.ResponseMessage;
import com.ta.entity.User;
import com.ta.entity.model.UserModel;
import com.ta.enumeration.LogOperation;
import com.ta.util.LogWrapper;
import com.ta.util.PaginationUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/users")
public class UserController {

	@Autowired
	private UserDao userDao;

	@SuppressWarnings({ "unused", "rawtypes" })
	@Autowired
	private PaginationUtils pageUtil;

	@PostMapping(value = { "new-user-registeration" })
	@ApiOperation(value = "register new user")
	public ResponseEntity<ResponseMessage<User>> saveUser(@RequestBody UserModel userDto) {
		ResponseMessage<User> rm = new ResponseMessage<>();

		try {
			User user = userDao.saveUser(userDto);
			if (user != null) {
				rm.setMessage("User details save successfully");
				rm.setResults(user);
				rm.setStatusCode(1);
			} else {
				rm.setMessage("Record not saved");
				rm.setResults(user);
				rm.setStatusCode(0);
			}
		} catch (Exception e) {
			LogWrapper.logErrorDetails(ErrorLogDto.builder().operation(LogOperation.DELETE).errorMessage(e.getMessage())
					.exception(e).build());
			throw e;
		}
		return new ResponseEntity<>(rm, HttpStatus.OK);
	}

	@GetMapping("users")
	public ResponseEntity<ResponseMessage<List<User>>> getUsers() {
		ResponseMessage<List<User>> rm = new ResponseMessage<>();

		try {
			List<User> users = userDao.getUsers();
			if (users != null) {
				rm.setMessage("Users are available");
				rm.setResults(users);
				rm.setStatusCode(1);
			} else {
				rm.setMessage("Users are not available.");
				rm.setResults(users);
				rm.setStatusCode(0);
			}
		} catch (Exception e) {
			/*
			 * LogWrapper.logErrorDetails(ErrorLogDto.builder().operation(LogOperation.
			 * DELETE).errorMessage(e.getMessage()) .exception(e).build());
			 */
			throw e;
		}
		return new ResponseEntity<>(rm, HttpStatus.OK);
	}

	@PutMapping("update/{id}")
	public ResponseEntity<ResponseMessage<User>> updateUser(@RequestBody UserModel userDto,
																  @PathVariable Long id) {
		ResponseMessage<User> rm = new ResponseMessage<>();

		try {
			User users = userDao.updateUser(userDto, id );
			if (users != null) {
				rm.setMessage("Users are available");
				rm.setResults(users);
				rm.setStatusCode(1);
			} else {
				rm.setMessage("Users are not available.");
				rm.setResults(users);
				rm.setStatusCode(0);
			}
		} catch (Exception e) {
			throw e;
		}
		return new ResponseEntity<>(rm, HttpStatus.OK);
	}

	@GetMapping("user/{id}")
	public ResponseEntity<ResponseMessage<User>> getUserDetail(@PathVariable Long id) {
		ResponseMessage<User> rm = new ResponseMessage<>();

		try {
			User users = userDao.getUserDetail(id).get(0);
			if (users != null) {
				rm.setMessage("User details are available");
				rm.setResults(users);
				rm.setStatusCode(1);
			} else {
				rm.setMessage("User details are not available.");
				rm.setResults(users);
				rm.setStatusCode(0);
			}
		} catch (Exception e) {
			/*
			 * LogWrapper.logErrorDetails(ErrorLogDto.builder().operation(LogOperation.
			 * DELETE).errorMessage(e.getMessage()) .exception(e).build());
			 */
			throw e;
		}
		return new ResponseEntity<>(rm, HttpStatus.OK);
	}

	@DeleteMapping
	public ResponseEntity<ResponseMessage<User>> deleteUser(@RequestBody UserModel userDto) {
		ResponseMessage<User> rm = new ResponseMessage<>();

		try {
			User user = userDao.deleteUser(userDto);
			if (user != null) {
				rm.setMessage("User details deleted successfully");
				rm.setResults(user);
				rm.setStatusCode(1);
			} else {
				rm.setMessage("Record not deleted");
				rm.setResults(user);
				rm.setStatusCode(0);
			}
		} catch (Exception e) {
			LogWrapper.logErrorDetails(ErrorLogDto.builder().operation(LogOperation.DELETE).errorMessage(e.getMessage())
					.exception(e).build());
			throw e;
		}
		return new ResponseEntity<>(rm, HttpStatus.OK);
	}
}
