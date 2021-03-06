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

import com.ta.entity.Booking;
import com.ta.entity.model.BookingModel;
import com.ta.repository.BookingRepository;
import org.hibernate.Criteria;
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
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	@Transactional
	public Booking updateBooking(BookingModel bookingModel, Long id) {
		bookingModel.setId(id);
		Booking booking = modelMapper.map(bookingModel, Booking.class);
		bookingRepository.save(booking);
		return booking;
	}

	public List<Booking> getAllBookings() {
		return bookingRepository.findAll();
	}

	@SuppressWarnings("deprecation")
	@Transactional
	public Map<String, Object> getAllBookingsPage(String bookingname, int page, int size) {

		Pageable paging = PageRequest.of(page, size);
		Page<Booking> pageTuts;
		if (bookingname == null)
			pageTuts = bookingRepository.findAll(paging);
		else
			pageTuts = bookingRepository.findByBookingnoContaining(bookingname, paging);
		List<Booking>  bookings = pageTuts.getContent();
		Map<String, Object> response = new HashMap<>();
		response.put("bookings", bookings);
		response.put("currentPage", pageTuts.getNumber());
		response.put("totalItems", pageTuts.getTotalElements());
		response.put("totalPages", pageTuts.getTotalPages());

		return response;
	}

	@Transactional
	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<BookingModel> getDatebasedBookings(String inFindValue) {
		List<BookingModel> bookingModels = new ArrayList<>();
		LocalDateTime currDateTime = LocalDateTime.now().minusHours(2);
		LocalDate localDate = currDateTime.toLocalDate();
		Session session = em.unwrap(Session.class);
		Criteria cr = session.createCriteria(Booking.class);
		cr.add(Restrictions.ilike("reportDate", inFindValue + "%", MatchMode.START));
		cr.add(Restrictions.ne("bookStatus", "Completed"));
		List<Booking> list = (List<Booking>) cr.list();
		if (list != null && list.size() > 0) {
			list.forEach(l -> {
				BookingModel bookingModel = new BookingModel();
				bookingModel = modelMapper.map(l, BookingModel.class);
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				LocalDateTime dateTime = LocalDateTime.parse(l.getReportDate(), formatter);
				LocalDate reportDate = dateTime.toLocalDate();
				if ((dateTime.isAfter(currDateTime) || dateTime.isBefore(currDateTime)) && localDate.equals(reportDate)
						&& (l.getDriverName() == null || l.getDriverName().isEmpty())) {
					bookingModel.setDriverAssigningdTimeExceeded(true);
				}
				bookingModels.add(bookingModel);
			});
			return bookingModels;

		}
		return null;
	}

	@Transactional
	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<BookingModel> searchBookingInfo(String inFindField, String inFindValue) {
		List<BookingModel> bookingModels = new ArrayList<>();
		LocalDateTime currDateTime = LocalDateTime.now().minusHours(2);
		LocalDate localDate = currDateTime.toLocalDate();
		Session session = em.unwrap(Session.class);
		Criteria cr = session.createCriteria(Booking.class);
		cr.add(Restrictions.ilike(inFindField, "%" + inFindValue + "%", MatchMode.ANYWHERE));
		List<Booking> list = (List<Booking>) cr.list();
		if (list != null && list.size() > 0) {
			list.forEach(l -> {
				BookingModel bookingModel = new BookingModel();
				bookingModel = modelMapper.map(l, BookingModel.class);
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				LocalDateTime dateTime = LocalDateTime.parse(l.getReportDate(), formatter);
				LocalDate reportDate = dateTime.toLocalDate();
				if ((dateTime.isAfter(currDateTime) || dateTime.isBefore(currDateTime)) && localDate.equals(reportDate)
						&& (l.getDriverName() == null || l.getDriverName().isEmpty())) {
					bookingModel.setDriverAssigningdTimeExceeded(true);
				}
				bookingModels.add(bookingModel);
			});
			return bookingModels;
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
	public List<BookingModel> getActiveBookings(int limit, int offset) {
		List<BookingModel> bookingModels = new ArrayList<>();
		LocalDateTime currDateTime = LocalDateTime.now().minusHours(2);
		LocalDate localDate = currDateTime.toLocalDate();
		Session session = em.unwrap(Session.class);
		String status = "Completed";
		List<Booking> bookings = session.createNativeQuery(
				"SELECT * FROM booking WHERE DATE(report_date) >= CURDATE() AND book_status not like'%" + status
						+ "%' order by DATE(report_date) ASC",
				Booking.class).setFirstResult(offset).setMaxResults(limit).list();
		bookings.forEach(l -> {
			BookingModel bookingModel = new BookingModel();
			bookingModel = modelMapper.map(l, BookingModel.class);
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			LocalDateTime dateTime = LocalDateTime.parse(l.getReportDate(), formatter);
			LocalDate reportDate = dateTime.toLocalDate();
			if ((dateTime.isAfter(currDateTime) || dateTime.isBefore(currDateTime)) && localDate.equals(reportDate)
					&& (l.getDriverName() == null || l.getDriverName().isEmpty())) {
				bookingModel.setDriverAssigningdTimeExceeded(true);
			}
			bookingModels.add(bookingModel);
		});
		return bookingModels;

	}
}
