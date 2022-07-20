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

import com.ta.entity.Driver;
import com.ta.entity.model.DriverModel;
import com.ta.repository.DriverRepository;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	@SuppressWarnings("deprecation")
	@Transactional
	public List<Driver> getAllDrivers(int limit,int offset) {
		Session session = em.unwrap(Session.class);
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Driver> criteriaQuery = builder.createQuery(Driver.class);
		Root<Driver> root = criteriaQuery.from(Driver.class);
		criteriaQuery.select(root);
		Query<Driver> query = session.createQuery(criteriaQuery);
		List<Driver> drivers = query.setFirstResult(offset).setMaxResults(limit).getResultList();
		return drivers;
	}

	@SuppressWarnings("deprecation")
	@Transactional
	public Map<String, Object> getAllDriversPage(String drivername, int page, int size) {

		Pageable paging = PageRequest.of(page, size);
		Page<Driver> pageTuts;
		if (drivername == null)
			pageTuts = driverRepository.findAll(paging);
		else
			pageTuts = driverRepository.findByNameContaining(drivername, paging);
		List<Driver>  drivers = pageTuts.getContent();
		Map<String, Object> response = new HashMap<>();
		response.put("drivers", drivers);
		response.put("currentPage", pageTuts.getNumber());
		response.put("totalItems", pageTuts.getTotalElements());
		response.put("totalPages", pageTuts.getTotalPages());

		return response;
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
	public List<Driver> getActiveDrivers(String value) {
		Session session = em.unwrap(Session.class);
		Criteria cr = session.createCriteria(Driver.class);
		cr.add(Restrictions.ilike("name", "%" + value + "%", MatchMode.ANYWHERE));
		cr.add(Restrictions.eqOrIsNull("isResigned", "true"));
		List<Driver> list = (List<Driver>) cr.list();
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}
	
	@Transactional
	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<Driver> getLiscenseExpiredDrivers() {
		Session session = em.unwrap(Session.class);
		Date currentDate = new Date();
		Date thirtyDaysFromCurrentDate = new Date(currentDate.getTime() + Duration.ofDays(30).toMillis());
		Criteria cr = session.createCriteria(Driver.class).add(Restrictions.disjunction().
		add(Restrictions.le("licenseExpiryDate", thirtyDaysFromCurrentDate)).
		add(Restrictions.le("licenseExpiryDate", new Date())));
		List<Driver> list = (List<Driver>) cr.list();
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	public Driver deleteDriver(DriverModel driverModel) {
		Driver driver = modelMapper.map(driverModel, Driver.class);
		driverRepository.delete(driver);
		return driver;
	}
}
