package com.ta.dto;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;

@Data
public class ResourceDto {
	private Long id;
	private String code;
	private String name;
	private String page;
	private String icon;
	private String link;
	private String url;
	private Boolean checkUrl;
	private Long sequence;
	private String iconName;
	private String description;
	private Boolean permitAll;
	private Boolean status;
	private Long parentId;
	
	private Set<ResourceDto> subMenus=new HashSet<>();
	public ResourceDto(Long id, String code, String name, String page, String icon, String link,
			String url, Boolean checkUrl, Long sequence, String iconName, String description, Boolean permitAll,
			Boolean status, Long parentId) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.page = page;
		this.icon = icon;
		this.link = link;
		this.url = url;
		this.checkUrl = checkUrl;
		this.sequence = sequence;
		this.iconName = iconName;
		this.description = description;
		this.permitAll = permitAll;
		this.status = status;
		this.parentId = parentId;
	}
}
