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

import com.ta.entity.Customer;
import com.ta.entity.Customer;
import com.ta.entity.model.CustomerModel;
import com.ta.repository.CustomerRepository;

@Repository
public class CustomerDao {

	@Autowired
	private EntityManager em;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private CustomerRepository customerRepository;

	public Customer saveCustomer(CustomerModel customerModel) {
		Customer customer = modelMapper.map(customerModel, Customer.class);
		customerRepository.save(customer);
		return customer;
	}

	@Transactional
	public Customer updateCustomer(CustomerModel customerModel, Long id) {
		customerModel.setId(id.toString());
		Customer customer = modelMapper.map(customerModel, Customer.class);
		Session session = em.unwrap(Session.class);
		session.update(customer);
		return customer;
	}

	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}
	@Transactional
	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<Customer> getCustomerDetail(Long id) {
		Session session = em.unwrap(Session.class);
		Criteria cr = session.createCriteria(Customer.class);
		cr.add(Restrictions.eq("id", id));
		List<Customer> list = (List<Customer>) cr.list();
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}
	@Transactional
	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<Customer> searchCustomerInfo(String inFindField, String inFindValue) {
		Session session = em.unwrap(Session.class);
		Criteria cr = session.createCriteria(Customer.class);
		cr.add(Restrictions.ilike(inFindField, "%" + inFindValue + "%", MatchMode.ANYWHERE));
		/// or condition
		List<Customer> list = (List<Customer>) cr.list();
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}
}
