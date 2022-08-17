package com.ta.entity;

import com.ta.entity.audit.UserDateAudit;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

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
	
	@Column(length = 30)
    private String identifierNo;

	@Column(length = 30)
	private String bloodGroup;

	@Column(length = 30)
	private String aadharcard;

	@Column(length = 30)
	private String vaccination;

	/*@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id", referencedColumnName = "id")
	private FileDB fileDB;*/
}
