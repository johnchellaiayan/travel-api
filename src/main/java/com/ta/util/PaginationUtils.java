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
package com.ta.util;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ta.dto.ResponseMessage;

import lombok.Data;
import lombok.NoArgsConstructor;

@Service
@Data
@NoArgsConstructor
public class PaginationUtils<T> {
	public ResponseMessage<List<T>> getPagingResults(Page<T> page,String entityName) {
		ResponseMessage<List<T>> message = new ResponseMessage<>();
		Pageable pageInfo = page.getPageable();
		message.setPageSize(pageInfo.getPageSize());
		message.setPageStart(pageInfo.getOffset());
		message.setCurrentPage(pageInfo.getPageNumber());
		message.setTotalPage(page.getTotalPages());
		message.setTotalRecords(page.getTotalElements());		
		List<T> list=page.getContent();
		message.setStatusCode((list!=null && !list.isEmpty())?1:0);
		message.setResults(page.getContent());
		message.setMessage((list!=null && !list.isEmpty())?entityName+" lists are available":entityName+" lists are empty");
		return message;
	}

}
