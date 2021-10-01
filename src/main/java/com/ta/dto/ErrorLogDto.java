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

}
