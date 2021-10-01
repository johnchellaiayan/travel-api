package com.ta.entity.model;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Column;

import com.ta.entity.audit.UserDateAudit;

import lombok.Data;

@Data
public class UserModel {

	private Long id;

	private String firstName;

	private String lastName;

	private LocalDate dob;

	private String mobileNumber;

	@Column(unique = true)
	private String email;

	private String address;

	private String password;

	private Set<RoleModel> roles;

}