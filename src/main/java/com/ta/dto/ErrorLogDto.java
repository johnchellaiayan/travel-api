/*******************************************************************************
 * ======================================================================================================
 *                                     Copyright (C) 2019 Trackerwave Pvt Ltd.
 *                                             All rights reserved
 * ======================================================================================================
 * Notice:  All Rights Reserved.
 * This material contains the trade secrets and confidential business information of Trackerwave Pvt Ltd,
 * which embody substantial creative effort, design, ideas and expressions.  No part of this material may
 * be reproduced or transmitted in any form or by any means, electronic, mechanical, optical or otherwise
 * ,including photocopying and recording, or in connection with any information storage or retrieval
 * system, without written permission.
 *        
 * www.trackerwave.com, Traceability and Change log maintained in Source Code Control System
 * =====================================================================================================
 ******************************************************************************/
package com.ta.dto;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ta.enumeration.LogOperation;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ErrorLogDto {

	private Long userId;
	private Long loginId;
	private String tagId;
	private Date errorTimeStamp;
	private LogOperation operation;
	private String applicationScreen;
	private String componentName;
	private String errorType;
	private String errorSeverity;
	private String errorCode;
	private Integer lineNumber;
	private String errorMessage;
	private String stacktrace;
	private Long createdBy;
	private Date createdAt;
	private String additionalInfo;
	@JsonIgnore
	private Exception exception;

	@JsonIgnore
	private String requestUrl;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getLoginId() {
		return loginId;
	}

	public void setLoginId(Long loginId) {
		this.loginId = loginId;
	}

	public String getTagId() {
		return tagId;
	}

	public void setTagId(String tagId) {
		this.tagId = tagId;
	}

	public Date getErrorTimeStamp() {
		return errorTimeStamp;
	}

	public void setErrorTimeStamp(Date errorTimeStamp) {
		this.errorTimeStamp = errorTimeStamp;
	}

	public LogOperation getOperation() {
		return operation;
	}

	public void setOperation(LogOperation operation) {
		this.operation = operation;
	}

	public String getApplicationScreen() {
		return applicationScreen;
	}

	public void setApplicationScreen(String applicationScreen) {
		this.applicationScreen = applicationScreen;
	}

	public String getComponentName() {
		return componentName;
	}

	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}

	public String getErrorType() {
		return errorType;
	}

	public void setErrorType(String errorType) {
		this.errorType = errorType;
	}

	public String getErrorSeverity() {
		return errorSeverity;
	}

	public void setErrorSeverity(String errorSeverity) {
		this.errorSeverity = errorSeverity;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public Integer getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(Integer lineNumber) {
		this.lineNumber = lineNumber;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getStacktrace() {
		return stacktrace;
	}

	public void setStacktrace(String stacktrace) {
		this.stacktrace = stacktrace;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getAdditionalInfo() {
		return additionalInfo;
	}

	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}

	public Exception getException() {
		return exception;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}

	public String getRequestUrl() {
		return requestUrl;
	}

	public void setRequestUrl(String requestUrl) {
		this.requestUrl = requestUrl;
	}

}
