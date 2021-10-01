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
package com.ta.dao;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.ta.dto.ErrorLogDto;
import com.ta.util.LogWrapper;

@Repository
@Transactional
public class AuthDao {

	

	public String forgetPassword(String username) {
		username = username.replaceFirst("^0+(?!$)", "");
		String phoneNumber = null;
		Integer loginId = null;

		String message = "OTP for Trackerwave Covid is Confidential.Please Do Not Share it with anyone.Your OTP is ";
		try {
		
		} catch (Exception e) {
			LogWrapper.logErrorDetails(ErrorLogDto.builder().errorMessage("Sms not send").exception(e).build());
		}

		if (phoneNumber != null) {
			Integer index = 8;
			if (phoneNumber.contains("91") || phoneNumber.contains("+91")) {
				index = 10;
			}
			phoneNumber = "XXXXXXX" + phoneNumber.substring(index, phoneNumber.length());
		}

		return "Your otp is sent to your registered mobile number." + phoneNumber;
	}

	public String resetPassword(String username, String otp, String password) {
		username = username.replaceFirst("^0+(?!$)", "");
		return "Password reset successfully";
	}

}
