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
package com.ta.config;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.DriverManagerConnectionFactory;
import org.apache.commons.dbcp.PoolableConnection;
import org.apache.commons.dbcp.PoolableConnectionFactory;
import org.apache.commons.dbcp.PoolingDataSource;
import org.apache.commons.pool.impl.GenericObjectPool;

import com.ta.dto.ErrorLogDto;
import com.ta.enumeration.LogOperation;
import com.ta.exception.ForbiddenException;
import com.ta.util.LogWrapper;

public class MysqlLogDBConfig {
	private String driver = null;
	private String username = null;
	private String password = null;
	private String connectionUrl = null;
	
	private static class Singleton {
		public static final MysqlLogDBConfig INSTANCE = new MysqlLogDBConfig();
	}

	private DataSource dataSource;

	private MysqlLogDBConfig() {

		clickHouseConfiguration();
	}

	public static Connection getDatabaseConnection() throws SQLException {
		return Singleton.INSTANCE.dataSource.getConnection();
	}

	public void clickHouseConfiguration() {
		try {
			Properties prop = new Properties();
			String profileName = "application.properties";
			InputStream is = getClass().getClassLoader().getResourceAsStream(profileName);
			if (is != null) {
				prop.load(is);
				driver = prop.getProperty("spring.datasource.driver-class-name");
				username = prop.getProperty("spring.datasource.username");
				password = prop.getProperty("spring.datasource.password");
				connectionUrl = prop.getProperty("spring.datasource.url");
				connection();

			} else {
				throw new ForbiddenException("ClickHouse file read exception");
			}

			connection();
		} catch (Exception e) {
			/*
			 * LogWrapper.logErrorDetails(ErrorLogDto.builder().operation(LogOperation.
			 * SELECT).errorMessage(e.getMessage()) .exception(e).build());
			 */
		}
	}

	public void connection() throws ClassNotFoundException {
		Class.forName(driver);
		Properties props = new Properties();
		props.setProperty("user", username);
		props.setProperty("password", password);
		GenericObjectPool<PoolableConnection> pool = new GenericObjectPool<>();
		pool.setMaxActive(60);
		pool.setMaxIdle(30);
		pool.setMaxWait(30000);
		DriverManagerConnectionFactory connectionFactory = new DriverManagerConnectionFactory(connectionUrl, props);
		new PoolableConnectionFactory(connectionFactory, pool, null, "SELECT 1", 3, false, false,
				Connection.TRANSACTION_READ_COMMITTED);
		this.dataSource = new PoolingDataSource(pool);
		}
}
