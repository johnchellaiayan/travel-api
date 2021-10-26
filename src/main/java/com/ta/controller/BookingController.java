package com.ta.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ta.dao.BookingDao;
import com.ta.dto.ResponseMessage;
import com.ta.entity.Booking;
import com.ta.entity.model.BookingModel;

@RestController
@RequestMapping("api/booking/")
public class BookingController {

	@Autowired
	private BookingDao bookingDao;

	@PostMapping("bookings")
	public ResponseEntity<ResponseMessage<Booking>> saveBooking(@RequestBody BookingModel bookingModel) {
		ResponseMessage<Booking> rm = new ResponseMessage<>();

		try {
			Booking booking = bookingDao.saveBooking(bookingModel);
			if (booking != null) {
				rm.setMessage("Booking Information saved successfully");
				rm.setResults(booking);
				rm.setStatusCode(1);
			} else {
				rm.setMessage("Record not saved");
				rm.setResults(booking);
				rm.setStatusCode(0);
			}
		} catch (Exception e) {
			/*
			 * LogWrapper.logErrorDetails(ErrorLogDto.builder().operation(LogOperation.
			 * DELETE).errorMessage(e.getMessage()) .exception(e).build());
			 */
			throw e;
		}
		return new ResponseEntity<>(rm, HttpStatus.OK);
	}
	
	@CrossOrigin
	@PutMapping("bookings/{id}")
	public ResponseEntity<ResponseMessage<Booking>> updateBooking(@RequestBody BookingModel bookingModel,
			@PathVariable Long id) {
		ResponseMessage<Booking> rm = new ResponseMessage<>();

		try {
			Booking booking = bookingDao.updateBooking(bookingModel,id);
			if (booking != null) {
				rm.setMessage("Booking Information saved successfully");
				rm.setResults(booking);
				rm.setStatusCode(1);
			} else {
				rm.setMessage("Record not saved");
				rm.setResults(booking);
				rm.setStatusCode(0);
			}
		} catch (Exception e) {
			/*
			 * LogWrapper.logErrorDetails(ErrorLogDto.builder().operation(LogOperation.
			 * DELETE).errorMessage(e.getMessage()) .exception(e).build());
			 */
			throw e;
		}
		return new ResponseEntity<>(rm, HttpStatus.OK);
	}
	@GetMapping("bookings/{id}")
	public ResponseEntity<ResponseMessage<Booking>> getBookingDetailsById(@PathVariable Long id) {
		ResponseMessage<Booking> rm = new ResponseMessage<>();

		try {
			Booking booking = bookingDao.getBookingDetailsById(id);
			if (booking != null) {
				rm.setMessage("Booking deatils are available");
				rm.setResults(booking);
				rm.setStatusCode(1);
			} else {
				rm.setMessage("Record not found");
				rm.setResults(booking);
				rm.setStatusCode(0);
			}
		} catch (Exception e) {
			/*
			 * LogWrapper.logErrorDetails(ErrorLogDto.builder().operation(LogOperation.
			 * DELETE).errorMessage(e.getMessage()) .exception(e).build());
			 */
			throw e;
		}
		return new ResponseEntity<>(rm, HttpStatus.OK);
	}
	
	@GetMapping("bookings")
	public ResponseEntity<ResponseMessage<List<Booking>>> getBookings() {
		ResponseMessage<List<Booking>> rm = new ResponseMessage<>();

		try {
			List<Booking> bookings = bookingDao.getAllBookings();
			if (bookings != null) {
				rm.setMessage("Bookings are available");
				rm.setResults(bookings);
				rm.setStatusCode(1);
			} else {
				rm.setMessage("Bookings are not available.");
				rm.setResults(bookings);
				rm.setStatusCode(0);
			}
		} catch (Exception e) {
			/*
			 * LogWrapper.logErrorDetails(ErrorLogDto.builder().operation(LogOperation.
			 * DELETE).errorMessage(e.getMessage()) .exception(e).build());
			 */
			throw e;
		}
		return new ResponseEntity<>(rm, HttpStatus.OK);
	}
	
	@GetMapping("bookings/{datefield}")
	public ResponseEntity<ResponseMessage<List<Booking>>> getBookings(@PathVariable String datefield) {
		ResponseMessage<List<Booking>> rm = new ResponseMessage<>();

		try {
			List<Booking> bookings = bookingDao.getDatebasedBookings(datefield);
			if (bookings != null) {
				rm.setMessage("Bookings are available");
				rm.setResults(bookings);
				rm.setStatusCode(1);
			} else {
				rm.setMessage("Bookings are not available.");
				rm.setResults(bookings);
				rm.setStatusCode(0);
			}
		} catch (Exception e) {
			/*
			 * LogWrapper.logErrorDetails(ErrorLogDto.builder().operation(LogOperation.
			 * DELETE).errorMessage(e.getMessage()) .exception(e).build());
			 */
			throw e;
		}
		return new ResponseEntity<>(rm, HttpStatus.OK);
	}

	@GetMapping("search/{field}/{value}")
	public ResponseEntity<ResponseMessage<List<Booking>>> searchBookingDetail(@PathVariable String field, 
			@PathVariable String value) {
		ResponseMessage<List<Booking>> rm = new ResponseMessage<>();

		try {
			List<Booking> bookings = bookingDao.searchBookingInfo(field, value);
			if (bookings != null) {
				rm.setMessage("Bookings are available");
				rm.setResults(bookings);
				rm.setStatusCode(1);
			} else {
				rm.setMessage("Bookings are not available.");
				rm.setResults(bookings);
				rm.setStatusCode(0);
			}
		} catch (Exception e) {
			throw e;
		}
		return new ResponseEntity<>(rm, HttpStatus.OK);
	}
}
