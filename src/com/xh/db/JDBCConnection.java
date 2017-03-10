package com.xh.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
/**
 * 采用JDBC的方式完成对数据库的连接操作
 * @author XH
 *
 */
public class JDBCConnection extends DbManager {
	private ResourceBundle res = ResourceBundle.getBundle("jdbc");
	private Connection con = null;
	
	@Override
	public Connection getRealConnection() {
		String driverClass = res.getString("driverClass");
		String jdbcUrl = res.getString("jdbcUrl");
		String dbName = res.getString("dbName");
		String dbPwd = res.getString("dbPwd");
		try {
			Class.forName(driverClass);
			con = DriverManager.getConnection(jdbcUrl, dbName, dbPwd);
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC加载驱动失败");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("JDBC连接数据库失败");
			e.printStackTrace();
		}
		return con;
	}
}
