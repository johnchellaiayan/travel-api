package com.ta.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ta.entity.audit.UserDateAudit;
import com.ta.service.StringPrefixedSequenceIdGenerator;

import lombok.Data;

@Entity
@Data
public class Customer extends UserDateAudit {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	/*
	 * @JsonIgnore
	 * 
	 * @Version private int version;
	 */

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
	private String carName;

	@Column(columnDefinition = "TEXT")
	private String complaints;

	@Column(columnDefinition = "TEXT")
	private String customerRequest;

}
