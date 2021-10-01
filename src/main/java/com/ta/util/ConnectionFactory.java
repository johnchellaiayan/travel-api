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

/*THis is commented for Qatar client*/
/*
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.DriverManagerConnectionFactory;
import org.apache.commons.dbcp.PoolableConnectionFactory;
import org.apache.commons.dbcp.PoolingDataSource;
import org.apache.commons.pool.impl.GenericObjectPool;

public class ConnectionFactory {
	 private static interface Singleton {
	  final ConnectionFactory INSTANCE = new ConnectionFactory();
	 }

	 private final DataSource dataSource;

	 private ConnectionFactory() {
	  Properties properties = new Properties();
	  properties.setProperty("user", "dwuser");
	  properties.setProperty("password", "password");

	  GenericObjectPool pool = new GenericObjectPool();
	  DriverManagerConnectionFactory connectionFactory = new DriverManagerConnectionFactory(
	    "jdbc:clickhouse://ec2-13-127-64-13.ap-south-1.compute.amazonaws.com:8123/covid", properties);
	  new PoolableConnectionFactory(connectionFactory, pool, null,
	    "SELECT 1", 3, false, false,
	    Connection.TRANSACTION_READ_COMMITTED);

	  this.dataSource = new PoolingDataSource(pool);
	  System.out.println("connected");
	 }

	 public static Connection getDatabaseConnection() throws SQLException {
		 System.out.println("connected"); 
	  return Singleton.INSTANCE.dataSource.getConnection();
	 }
	}*/
