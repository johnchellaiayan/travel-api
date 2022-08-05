package com.ta.entity;

import com.ta.entity.audit.UserDateAudit;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Booking extends UserDateAudit {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	/*
	 * @JsonIgnore
	 * 
	 * @Version private int version;
	 */

	@Column(length = 30)
	private String bookingno;
	
	@Column(length = 150)
	private String loggedby;	
	
	@Column(length = 150)
	private String bookedby;

	@Column(length = 150)
	private String driverName;

	@Column(length = 150)
	private String customerID;

	@Column(length = 150)
	private String driverID;

	@Column(length = 100)
	private String reportDate;

	@Column(length = 100)
	private String reportTime;

	@Column(length = 150)
	private String customerName;

	@Column(length = 100)
	private String pickupArea;

	@Column(length = 100)
	private String dropArea;

	@Column(length = 30)
	private String custPhone1;

	@Column(length = 30)
	private String custPhone2;

	@Column(length = 30)
	private String carName;

	@Column(length = 30)
	private String smsTo;

	@Column(columnDefinition = "TEXT")
	private String fromAddress;

	@Column(columnDefinition = "TEXT")
	private String toAddress;

	@Column(columnDefinition = "TEXT")
	private String remark;

	@Column(columnDefinition = "TEXT")
	private String complaints;

	@Column(columnDefinition = "TEXT")
	private String customerRequest;
	
	@Column(length = 30)
	private String bookStatus;

}
