package com.ta.entity.model;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class DriverModel {
	@ApiModelProperty(hidden = true)
	private Long id;
	private String name;
	private String address;
	private String area;
	private String phoneNo1;
	private String phoneNo2;
	private String mobileNo1;
	private String mobileNo2;
	private String licenseNo;
	private Date licenseDate;
	private Date licenseExpiryDate;
	private String complaints;
	private String isResigned;
}