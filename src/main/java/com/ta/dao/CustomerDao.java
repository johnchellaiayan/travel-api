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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
		session.update(customer);
		return customer;
	}

	@SuppressWarnings("deprecation")
	@Transactional
	public Map<String, Object> getAllCustomersPage(String customername, int page, int size) {

		Pageable paging = PageRequest.of(page, size);
		Page<Customer> pageTuts;
		if (customername == null)
			pageTuts = customerRepository.findAll(paging);
		else
			pageTuts = customerRepository.findByNameContaining(customername, paging);
		List<Customer>  customers = pageTuts.getContent();
		Map<String, Object> response = new HashMap<>();
		response.put("customers", customers);
		response.put("currentPage", pageTuts.getNumber());
		response.put("totalItems", pageTuts.getTotalElements());
		response.put("totalPages", pageTuts.getTotalPages());

		return response;
	}

	@SuppressWarnings("deprecation")
	@Transactional
	public List<Customer> getAllCustomers(int limit, int offset) {
		Session session = em.unwrap(Session.class);
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Customer> criteriaQuery = builder.createQuery(Customer.class);
		Root<Customer> root = criteriaQuery.from(Customer.class);
		criteriaQuery.select(root);
		Query<Customer> query = session.createQuery(criteriaQuery);
		List<Customer> customers = query.setFirstResult(offset).setMaxResults(limit).getResultList();
		return customers;
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
	@SuppressWarnings({ "deprecation" })
	public List<Customer> searchCustomerInfo(String inFindValue) {
		Session session = em.unwrap(Session.class);
		CriteriaBuilder crBuilder = session.getCriteriaBuilder();
		CriteriaQuery<Customer> crq = crBuilder.createQuery(Customer.class);
		Root<Customer> root = crq.from(Customer.class);
		crq.select(root)
				.where(crBuilder.or(crBuilder.like(root.<String>get("name"), "" + inFindValue + "%"),
						crBuilder.like(root.get("mobileNo1"), "" + inFindValue + "%"),
						crBuilder.like(root.<String>get("area"), "" + inFindValue + "%"),
						crBuilder.like(root.<String>get("mobileNo2"), "" + inFindValue + "%"),
						crBuilder.like(root.<String>get("phoneNo1"), "" + inFindValue + "%"),
						crBuilder.like(root.<String>get("phoneNo2"), "" + inFindValue + "%")))
				.orderBy(crBuilder.asc(root.get("name")));

		Query<Customer> q = session.createQuery(crq);
		List<Customer> list = q.getResultList();
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}
}
