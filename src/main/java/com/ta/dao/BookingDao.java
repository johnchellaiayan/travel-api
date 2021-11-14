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

import com.ta.entity.Booking;
import com.ta.entity.model.BookingModel;
import com.ta.repository.BookingRepository;

@Repository
public class BookingDao {

	@Autowired
	private EntityManager em;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private BookingRepository bookingRepository;

	public Booking saveBooking(BookingModel bookingModel) {
		Booking booking = modelMapper.map(bookingModel, Booking.class);
		bookingRepository.save(booking);
		return booking;
	}

	
	public Booking updateBooking(BookingModel bookingModel, Long id) {
		Session session = em.unwrap(Session.class);
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>" + id);
		bookingModel.setId(id);
		Booking booking = modelMapper.map(bookingModel, Booking.class);
		session.update(booking);
		return booking;
	}

	public List<Booking> getAllBookings() {
		return bookingRepository.findAll();
	}

	@Transactional
	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<Booking> getDatebasedBookings(String inFindValue) {
		Session session = em.unwrap(Session.class);
		Criteria cr = session.createCriteria(Booking.class);
		cr.add(Restrictions.ilike("reportDate", inFindValue + "%", MatchMode.START));
		cr.add(Restrictions.ne("bookStatus", "Completed"));
		List<Booking> list = (List<Booking>) cr.list();
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	@Transactional
	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<Booking> searchBookingInfo(String inFindField, String inFindValue) {
		Session session = em.unwrap(Session.class);
		Criteria cr = session.createCriteria(Booking.class);
		cr.add(Restrictions.ilike(inFindField, "%" + inFindValue + "%", MatchMode.ANYWHERE));
		List<Booking> list = (List<Booking>) cr.list();
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	@Transactional
	@SuppressWarnings({ "deprecation", "unchecked" })
	public Booking getBookingDetailsById(Long id) {
		Session session = em.unwrap(Session.class);
		Criteria cr = session.createCriteria(Booking.class);
		cr.add(Restrictions.eq("id", id));
		List<Booking> list = (List<Booking>) cr.list();
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	
	@Transactional
	public List<Booking> getActiveBookings() {
		Session session = em.unwrap(Session.class);
		String status="Completed";
		List<Booking> bookings = session.createNativeQuery("SELECT * FROM Booking WHERE STR_TO_DATE(report_date, '%m/%d/%Y') >= CURDATE() AND book_status not like'%"+status+"%'", Booking.class)
		.list();
      return bookings;

}
}
