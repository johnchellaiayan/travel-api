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

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/all/")
public class TestController {

	@PersistenceContext
	private EntityManager em;

	@GetMapping(value = "encrypt-password")
	public String sendMail(@RequestParam String password) {
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		return "Password is "+password;
	}
	
	@GetMapping(value = "test-password")
	public Map test(@RequestParam(defaultValue = "Sam",required = false) String name ) {
		Map<String,Object> map=new HashMap<>();
		map.put("name", name);
		return map;
	}
}
