package com.ta.entity.model;

import java.util.Date;

import lombok.Data;

@Data
public class BookingModel {

	private Long id;
	private String bookedby;
	private String bookingno;
	private String driverName;
	private Date reportDate;
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
}
