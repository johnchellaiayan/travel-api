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

import com.ta.entity.Customer;
import com.ta.entity.User;
import com.ta.entity.model.UserModel;
import com.ta.repository.UserRepository;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.concurrent.Future;

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

	@Transactional
	public User updateUser(UserModel userModel, Long id) {
		userModel.setId(id);
		User user = modelMapper.map(userModel, User.class);
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		String password = user.getPassword();
		if (password != null)
			user.setPassword(encoder.encode(password));
		Session session = em.unwrap(Session.class);
		session.update(user);
		return user;
	}

	@Transactional
	public User deleteUser(UserModel userDto) {
		User user = modelMapper.map(userDto, User.class);
		userRepository.delete(user);
		return user;
	}

	public List<User> getUserDetail(Long id) {
		Session session = em.unwrap(Session.class);
		Criteria cr = session.createCriteria(User.class);
		cr.add(Restrictions.eq("id", id));
		List<User> list = (List<User>) cr.list();
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}
}
