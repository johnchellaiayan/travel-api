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
package com.ta.repository;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ta.dto.ResourceDto;
import com.ta.entity.Resource;

@Repository
@Transactional
public interface ResourceRepository extends JpaRepository<Resource, Long> {
	
	@Query("select new com.ta.dto.ResourceDto(res.id,res.code,res.name,res.page,res.icon,res.link,res.url,res.checkUrl,res.sequence,res.iconName,res.description,res.permitAll,res.status,par.id) from Resource res "
			+ "left join res.parent par "			
			+ "inner join res.resourceRoleMaps rm "
			+ "left join rm.user usr "
			+ "left join rm.role rol "
			+ "where (usr.id =:userId or rol.id IN :roleIds)")
	public Set<ResourceDto> getUserResources(@Param("userId") Long userId,@Param("roleIds") List<Long> roleIds);
}
