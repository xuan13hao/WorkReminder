package com.xh.db;

import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3P0Connection extends DbManager {
	private Connection con = null;
	@Override
	public Connection getRealConnection(){
		try {
			con =  new ComboPooledDataSource().getConnection();
		} catch (SQLException e) {
			System.out.println("C3P0连接数据库异常");
			e.printStackTrace();
		}
		return con;
	}
}
