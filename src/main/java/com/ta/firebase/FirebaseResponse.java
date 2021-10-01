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
package com.ta.firebase;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FirebaseResponse {

	/* Fields */
	@JsonProperty(value ="multicast_id")
    private long multicastId;
    private Integer success;
    private Integer failure;
    @JsonProperty(value ="canonical_ids")
    private Object canonicalIds;
    private Object results;

    @Override
    public String toString() {
        return "FirebaseResponse{" +
                "multicast_id=" + multicastId +
                ", success=" + success +
                ", failure=" + failure +
                ", canonical_ids=" + canonicalIds +
                ", results=" + results +
                '}';
    }

    /* getters and setters */
	public long getMulticastId() {
		return multicastId;
	}

	public void setMulticastId(long multicastId) {
		this.multicastId = multicastId;
	}

	public Integer getSuccess() {
		return success;
	}

	public void setSuccess(Integer success) {
		this.success = success;
	}

	public Integer getFailure() {
		return failure;
	}

	public void setFailure(Integer failure) {
		this.failure = failure;
	}

	public Object getCanonicalIds() {
		return canonicalIds;
	}

	public void setCanonicalIds(Object canonicalIds) {
		this.canonicalIds = canonicalIds;
	}

	public Object getResults() {
		return results;
	}

	public void setResults(Object results) {
		this.results = results;
	}
}
