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
import org.springframework.stereotype.Repository;

import com.ta.entity.Booking;
import com.ta.entity.Booking;
import com.ta.entity.model.BookingModel;
import com.ta.entity.model.BookingModel;
import com.ta.repository.BookingRepository;

@Repository
@Transactional
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
		bookingModel.setId(id);
		Booking booking = modelMapper.map(bookingModel, Booking.class);
		bookingRepository.save(booking);
		return booking;
	}

	public List<Booking> getAllBookings() {
		return bookingRepository.findAll();
	}

}
