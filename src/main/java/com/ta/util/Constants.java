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

public class Constants {
	public static final String CACHE_CONTROL_AGE = "public, max-age=31536000";

	public final class HeaderValue {
		private HeaderValue() {
		}

		public static final String AUTHORIZATION = "Authorization";
		public static final String REFRESH_TOKEN = "refresh_token";
		public static final String GROUP = "group";
		public static final String CONTENT_TYPE = "Content-Type";

	}

	public final class BooleanValue {
		private BooleanValue() {
		}

		public static final String IS_OTP_VALID = "isOtpValid";
		public static final String IS_ERROR_HANDLED = "isErrorHandled";
		public static final String IS_API_KEY_INVALID = "isApiKeyInvalid";
	}
}