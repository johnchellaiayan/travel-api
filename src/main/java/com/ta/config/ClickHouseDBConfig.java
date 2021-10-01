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

/*This is commented for quatar client*/
/*
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

import com.tw.util.ErrorUtility;

public class ClickHouseDBConfig {
	private String driver = null;
	private String username = null;
	private String password = null;
	private String connectionUrl = null;

	private static interface Singleton {
		final ClickHouseDBConfig INSTANCE = new ClickHouseDBConfig();
	}

	private DataSource dataSource;

	private ClickHouseDBConfig() {

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
				profileName = prop.getProperty("spring.profiles.active");
				String fileName = "application-" + profileName + ".properties";
				is = getClass().getClassLoader().getResourceAsStream(fileName);
				prop.load(is);
				driver = prop.getProperty("clickhouse.driver");
				username = prop.getProperty("clickhouse.username");
				password = prop.getProperty("clickhouse.password");
				connectionUrl = prop.getProperty("clickhouse.connectionurl");
				connection();

			} else {
				throw new Exception("ClickHouse file read exception");
			}
		} catch (Exception e) {
			try {
				ErrorUtility.utilityError(e);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
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
}*/
