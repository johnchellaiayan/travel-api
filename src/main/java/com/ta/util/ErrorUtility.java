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

import com.ta.enumeration.LogOperation;
import com.ta.exception.BadRequestException;
import com.ta.exception.ForbiddenException;
import com.ta.exception.RecordNotFoundException;
import com.ta.exception.UnHandledException;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorUtility {

	public static Exception utilityError(Exception e) throws UnHandledException {
		if (e instanceof ForbiddenException)
			throw (ForbiddenException) e;
		else if (e instanceof RecordNotFoundException)
			throw (RecordNotFoundException) e;
		else if (e instanceof BadRequestException)
			throw (BadRequestException) e;
		else
			throw new UnHandledException(e);
	}

	public static Exception utilityError(Exception e, String additioanlInfo) throws UnHandledException {
		if (e instanceof ForbiddenException)
			throw (ForbiddenException) e;
		else if (e instanceof RecordNotFoundException)
			throw (RecordNotFoundException) e;
		else if (e instanceof BadRequestException)
			throw (BadRequestException) e;
		else
			throw new UnHandledException(e, additioanlInfo);
	}

	public static Exception utilityError(Exception e, LogOperation ope) throws UnHandledException {
		if (e instanceof ForbiddenException)
			throw (ForbiddenException) e;
		else if (e instanceof RecordNotFoundException)
			throw (RecordNotFoundException) e;
		else if (e instanceof BadRequestException)
			throw (BadRequestException) e;
		else
			throw new UnHandledException(e, e.getMessage(), ope);
	}

	public static Exception utilityError(Exception e, LogOperation ope, String additionalInfo)
			throws UnHandledException {
		if (e instanceof ForbiddenException)
			throw (ForbiddenException) e;
		else if (e instanceof RecordNotFoundException)
			throw (RecordNotFoundException) e;
		else if (e instanceof BadRequestException)
			throw (BadRequestException) e;
		else
			throw new UnHandledException(e, e.getMessage(), ope, additionalInfo);
	}

}
