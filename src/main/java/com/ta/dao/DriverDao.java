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

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ta.entity.Driver;
import com.ta.entity.model.DriverModel;
import com.ta.repository.DriverRepository;

@Repository
@Transactional
public class DriverDao {

	@Autowired
	private EntityManager em;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private DriverRepository driverRepository;

	public Driver saveDriver(DriverModel driverModel) {
		Driver driver = modelMapper.map(driverModel, Driver.class);		
		driverRepository.save(driver);
		return driver;
	}
	
	public Driver updateDriver(DriverModel driverModel,Long id) {
		driverModel.setId(id);
		Driver driver = modelMapper.map(driverModel, Driver.class);		
		driverRepository.save(driver);
		return driver;
	}
	
	public List<Driver> getAllDrivers()
	{
		return driverRepository.findAll();
	}
	
	

}
