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

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ta.entity.Driver;
import com.ta.entity.model.DriverModel;
import com.ta.repository.DriverRepository;

@Repository
public class DriverDao {

	@Autowired
	private EntityManager em;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private DriverRepository driverRepository;

	public Driver saveDriver(DriverModel driverModel) {
		Driver driver = modelMapper.map(driverModel, Driver.class);
		driverRepository.save(driver);
		return driver;
	}

	@Transactional
	public Driver updateDriver(DriverModel driverModel, Long id) {
		driverModel.setId(id);
		Driver driver = modelMapper.map(driverModel, Driver.class);
		Session session = em.unwrap(Session.class);
		session.update(driver);
		return driver;
	}

	public List<Driver> getAllDrivers() {
		return driverRepository.findAll();
	}

	@Transactional
	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<Driver> getDriverDetail(Long id) {
		Session session = em.unwrap(Session.class);
		Criteria cr = session.createCriteria(Driver.class);
		cr.add(Restrictions.eq("id", id));
		List<Driver> list = (List<Driver>) cr.list();
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	@Transactional
	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<Driver> searchDriverInfo(String inFindField, String inFindValue) {
		Session session = em.unwrap(Session.class);
		Criteria cr = session.createCriteria(Driver.class);
		cr.add(Restrictions.ilike(inFindField, "%" + inFindValue + "%", MatchMode.ANYWHERE));
		List<Driver> list = (List<Driver>) cr.list();
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}
	
	@Transactional
	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<Driver> getActiveDrivers() {
		Session session = em.unwrap(Session.class);
		Criteria cr = session.createCriteria(Driver.class);
		cr.add(Restrictions.eqOrIsNull("isResigned", "false"));
		List<Driver> list = (List<Driver>) cr.list();
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

}
