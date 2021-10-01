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
import java.util.Optional;

import javax.persistence.Tuple;
import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ta.dto.IBasicModel;
import com.ta.entity.User;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {
	@Query("select u.password as password,role.name as roleName from User u left join u.roles role where u.email=:email ")
	List<Tuple> checkByEmail(@Param("email") String email);

	Optional<User> findByEmail(String email);	
	
	@Query("select r.id as id,r.name as name from User usr join usr.roles r where usr.id =:userId")
	public List<IBasicModel> getAllUserRoles(@Param("userId") Long userId);
}
