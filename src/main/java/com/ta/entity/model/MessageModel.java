package com.ta.entity.model;

import lombok.Data;

@Data
public class MessageModel {

	private Long id;
	private String custphone;
	private String driverphone;
	private String smsto;
	private String whatsappto;
	private String comments;
}
