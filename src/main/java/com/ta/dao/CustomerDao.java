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
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
		session.saveOrUpdate(customer);
		return customer;
	}

	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}

	public Optional<Customer> getCustomerDetail(Long id) {
		return customerRepository.findById(id.toString());
	}

}
