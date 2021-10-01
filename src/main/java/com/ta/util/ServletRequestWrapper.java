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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class ServletRequestWrapper extends HttpServletRequestWrapper {

	private Map headerMap;

	public void addHeader(String name, String value) {
		headerMap.put(name, value);
	}

	public ServletRequestWrapper(HttpServletRequest request) {
		super(request);
		headerMap = new HashMap();
	}

	public Enumeration getHeaderNames() {
		HttpServletRequest request = (HttpServletRequest) getRequest();
		List list = new ArrayList();
		for (Enumeration e = request.getHeaderNames(); e.hasMoreElements();)
			list.add(e.nextElement().toString());
		for (Iterator i = headerMap.keySet().iterator(); i.hasNext();) {
			list.add(i.next());
		}
		return Collections.enumeration(list);
	}

	public String getHeader(String name) {
		Object value;
		if ((value = headerMap.get("" + name)) != null)
			return value.toString();
		else
			return ((HttpServletRequest) getRequest()).getHeader(name);
	}
}
