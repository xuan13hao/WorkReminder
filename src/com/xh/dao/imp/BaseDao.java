package com.xh.dao.imp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.xh.db.DbManager;

public class BaseDao {
	public int i = 0;
	public boolean flag = false;
	public Connection con = DbManager.getInstance().getConnection();
	public Statement stat = null;
	public ResultSet rs = null;
	public String sql = null;
	public String sqlCount = null;
	public int count = 0;
	public Object [] params;
	
	public static boolean isTrue(int no)
	{
		if(no > 0)
			return true;
		else 
			return false;
	}
}
