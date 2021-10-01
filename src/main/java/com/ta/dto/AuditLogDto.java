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

@Getter
@Builder
public class AuditLogDto {

	private Long userId;
	private Long loginId;
	private Integer roleId;
	private String tagId;
	private LogOperation operation;
	private String applicationScreen;
	private String message;
	private String componentName;
	private String tableName;
	private String stackTrace;
	private Long createdBy;
	private Date createdAt;
	private String additionalInfo; 
	private String inputJson; 
	
	@JsonIgnore
	private String httpMethod; 
	
	@JsonIgnore
	private String requestUrl;

}
