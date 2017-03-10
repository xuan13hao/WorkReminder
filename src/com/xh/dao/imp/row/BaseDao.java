package com.xh.dao.imp.row;

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
	public Object [] params;
	
	public static boolean isTrue(int no)
	{
		if(no>1)
			return true;
		else 
			return false;
	}
}
