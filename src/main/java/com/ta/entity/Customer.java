package com.ta.entity;

import com.ta.entity.audit.UserDateAudit;
import lombok.Data;

import javax.persistence.*;

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
