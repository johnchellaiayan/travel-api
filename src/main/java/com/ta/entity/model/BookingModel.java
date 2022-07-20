package com.ta.entity.model;

import lombok.Data;

@Data
public class BookingModel {

	private Long id;
	private String bookedby;
	private String bookingno;
	private String driverName;
	private String reportDate;
	private String reportTime;
	private String customerName;
	private String pickupArea;
	private String dropArea;
	private String custPhone1;
	private String custPhone2;
	private String carName;
	private String fromAddress;
	private String toAddress;
	private String remark;
	private String complaints;	
	private String smsTo;
	private String customerRequest;
	private String bookStatus;
	private String loggedby;
	private boolean isDriverAssigningdTimeExceeded;
}
