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
package com.ta.exception;

import java.util.List;

import lombok.Data;
 
@Data
public class ErrorResponse {
	
	public ErrorResponse()
	{
		
	}

	private String errorCode;
    //General error message about nature of error
    private String message=null;
     //Specific errors in API request processing
    private List<String> errorList=null;  
    

	public ErrorResponse(String errorCode, String message, List<String> errorList) {
		super();
		this.errorCode = errorCode;
		this.message = message;
		this.errorList = errorList;
	}
	public ErrorResponse(String errorCode, String message) {
		super();
		this.errorCode = errorCode;
		this.message = message;
	}	
}
