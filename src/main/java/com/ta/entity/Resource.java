package com.ta.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ta.entity.audit.UserDateAudit;
import com.ta.entity.lookup.ResourceType;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@NoArgsConstructor
public class Resource extends UserDateAudit {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(length = 20, unique = true)
	private String code;

	@Column(length = 50)
	private String name;

	@Column(length = 50)
	private String target;

	@Column(length = 100)
	private String page;

	private String icon;
	private String link;
	private String url;

	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean checkUrl;
	private Long sequence;

	private String iconName;

	@Column(length = 100)
	private String description;

	@Column(length = 100)
	private String version;

	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean permitAll;

	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean status;

	@JsonIgnore
	@JoinColumn(name = "parent_id", referencedColumnName = "id")
	@ManyToOne(fetch = FetchType.LAZY)
	private Resource parent;

	@JsonIgnore
	@JoinColumn(name = "resource_type_id", referencedColumnName = "id", nullable = false)
	@ManyToOne(fetch = FetchType.LAZY)
	private ResourceType resourceType;

	@JsonIgnore
	@OneToMany(mappedBy = "resource")
	private List<ResourceRoleMap> resourceRoleMaps;

	public Resource(Long id) {
		this.id = id;
	}

	public Resource(String code2) {
		this.code = code2;
	}
}
