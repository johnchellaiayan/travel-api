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
package com.ta.entity.audit;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.PostLoad;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@MappedSuperclass
/*
 * @JsonIgnoreProperties( value = {"createdBy", "modifiedBy"}, allowGetters =
 * true )
 */
@NoArgsConstructor
@Data
@EntityListeners(AuditingEntityListener.class)
public abstract class UserDateAudit implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(hidden = true)
	@CreatedBy
	private Long createdBy;

	@ApiModelProperty(hidden = true)
	@LastModifiedBy
	private Long modifiedBy;

	@ApiModelProperty(hidden = true)
	@CreatedDate
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createdOn;

	@ApiModelProperty(hidden = true)
	@LastModifiedDate
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date modifiedOn;

	public UserDateAudit(Date createdOn, Date modifiedOn, Number createdBy, Number modifiedBy) {
		this.createdOn = createdOn;
		this.modifiedOn = modifiedOn;
		this.createdBy = createdBy.longValue();
		this.modifiedBy = modifiedBy.longValue();
	}
	
	public UserDateAudit(Date createdOn, Date modifiedOn, Long createdBy, Long modifiedBy) {
		this.createdOn = createdOn;
		this.modifiedOn = modifiedOn;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
	}
	
	@PostLoad
	public void load()
	{
		//LocalDateTime.now(Clock.systemUTC())
	}

}
