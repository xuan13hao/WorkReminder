package com.xh.dao.imp.row;

import java.sql.SQLException;

import com.xh.dao.imp.BaseDao;

/**
 * 该类的作用用于完成对于数据库的操作，其中包括获取数据条数的方法
 * @author XH
 *
 */
public class JDBCTemplate extends BaseDao{
	
	/**
	 * 获取到记录条数
	 * @param sql	获取记录的SQL语句
	 * @return	返回记录条数
	 */
	public int getCount(String sql)
	{
		try {
			stat = con.createStatement();
			rs = stat.executeQuery(sql);
			while(rs.next())
			{
				i = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("在JDBCTemplate中获取Statement出现异常");
			e.printStackTrace();
		}
		return i;
	}
}
