package com.ta.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ta.entity.audit.UserDateAudit;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@NoArgsConstructor
public class ResourceRoleMap extends UserDateAudit {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@JsonIgnore
	@JoinColumn(name = "resource_id", referencedColumnName = "id")
	@ManyToOne(fetch = FetchType.LAZY)
	private Resource resource;

	@JsonIgnore
	@JoinColumn(name = "role_id", referencedColumnName = "id")
	@ManyToOne(fetch = FetchType.LAZY)
	private Role role;

	@JsonIgnore
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	@ManyToOne(fetch = FetchType.LAZY)
	private User user;
}
