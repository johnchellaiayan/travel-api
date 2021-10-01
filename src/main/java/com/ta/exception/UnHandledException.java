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

import com.ta.dto.ErrorLogDto;
import com.ta.enumeration.LogOperation;
import com.ta.util.LogWrapper;

public class UnHandledException extends Exception {

	private static final String CLASSNAME = "com.tw";

	public UnHandledException(Exception e) {
		StackTraceElement[] st = e.getStackTrace();
		for (StackTraceElement element : st) {
			if (element.getClassName().contains(CLASSNAME) && !element.getClassName().contains("$")
					&& !element.getMethodName().contains("$")) {
				LogWrapper.logErrorDetails(ErrorLogDto.builder()
						.componentName(element.getClassName() + "." + element.getMethodName() + "()")
						.lineNumber(element.getLineNumber()).exception(e).build());
				break;
			}
		}

	}

	public UnHandledException(Exception e, String additionalInfo) {
		StackTraceElement[] st = e.getStackTrace();
		for (StackTraceElement element : st) {
			if (element.getClassName().contains(CLASSNAME) && !element.getClassName().contains("$")
					&& !element.getMethodName().contains("$")) {
				LogWrapper.logErrorDetails(ErrorLogDto.builder()
						.componentName(element.getClassName() + "." + element.getMethodName() + "()")
						.lineNumber(element.getLineNumber()).exception(e).additionalInfo(additionalInfo).build());
				break;
			}
		}
	}

	public UnHandledException(Exception e, String message, LogOperation operation) {
		StackTraceElement[] st = e.getStackTrace();
		for (StackTraceElement element : st) {
			if (element.getClassName().contains(CLASSNAME) && !element.getClassName().contains("$")
					&& !element.getMethodName().contains("$")) {
				LogWrapper.logErrorDetails(ErrorLogDto.builder()
						.componentName(element.getClassName() + "." + element.getMethodName() + "()")
						.lineNumber(element.getLineNumber()).exception(e).operation(operation).errorMessage(message)
						.build());
				break;
			}
		}
	}

	public UnHandledException(Exception e, String message, LogOperation operation, String additionalInfo) {
		StackTraceElement[] st = e.getStackTrace();
		for (StackTraceElement element : st) {
			if (element.getClassName().contains(CLASSNAME) && !element.getClassName().contains("$")
					&& !element.getMethodName().contains("$")) {
				LogWrapper.logErrorDetails(ErrorLogDto.builder()
						.componentName(element.getClassName() + "." + element.getMethodName() + "()")
						.lineNumber(element.getLineNumber()).exception(e).additionalInfo(additionalInfo)
						.operation(operation).errorMessage(message).build());
				break;
			}
		}

	}

}
