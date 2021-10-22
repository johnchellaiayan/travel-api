package com.ta.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.ta.entity.audit.UserDateAudit;

import lombok.Data;

@Entity
@Data
public class Driver extends UserDateAudit {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;	
	
	/*@JsonIgnore
	@Version
	private int version;*/

	@Column(columnDefinition = "TEXT")
	private String name;

	@Column(columnDefinition = "TEXT")
	private String address;

	@Column(length = 30)
	private String area;

	@Column(length = 30)
	private String phoneNo1;

	@Column(length = 30)
	private String phoneNo2;

	@Column(length = 30)
	private String mobileNo1;

	@Column(length = 30)
	private String mobileNo2;

	@Column(length = 30)
	private String licenseNo;
	private Date licenseDate;
	private Date licenseExpiryDate;

	@Column(columnDefinition = "TEXT")
	private String complaints;
	
	@Column(length = 30)
	private String isResigned;
}
