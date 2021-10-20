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

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResponseMessage <T> {
    private int statusCode;  
    private T results ;
    private String message; 
    
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long pageStart;
    
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long totalRecords;
    
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer totalPage;
    
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer pageSize;
    
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer currentPage;

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public T getResults() {
		return results;
	}

	public void setResults(T results) {
		this.results = results;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Long getPageStart() {
		return pageStart;
	}

	public void setPageStart(Long pageStart) {
		this.pageStart = pageStart;
	}

	public Long getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(Long totalRecords) {
		this.totalRecords = totalRecords;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
    
    
}
