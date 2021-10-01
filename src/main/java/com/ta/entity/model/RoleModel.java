package com.ta.entity.model;

import lombok.Data;

@Data
public class RoleModel {
	private Long id;
	private String name;
	private String description;
	@Override
	public String toString() {
		return "RoleEntityDto [id=" + id + ", name=" + name + ", description=" + description + "]";
	}
	
	
}