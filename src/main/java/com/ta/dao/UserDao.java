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
package com.ta.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.ta.entity.User;
import com.ta.entity.model.UserModel;
import com.ta.repository.UserRepository;

@Repository
@Transactional
public class UserDao {

	@Autowired
	private EntityManager em;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserRepository userRepository;

	public User saveUser(UserModel userDto) {
		User user = modelMapper.map(userDto, User.class);
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		String password = user.getPassword();
		if (password != null)
			user.setPassword(encoder.encode(password));
		userRepository.save(user);
		return user;
	}

	public List<User> getUsers() {
		return userRepository.findAll();	
	}

}
