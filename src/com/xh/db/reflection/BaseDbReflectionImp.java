package com.xh.db.reflection;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.xh.db.BaseDb;
import com.xh.db.DbManager;
/**
 * 利用反射机制，自动完成所有的类对象的整合
 * @author XH
 * @param <T>
 */
public class BaseDbReflectionImp<T extends Serializable> implements BaseDb<T> {

	private Connection con = DbManager.getInstance().getConnection();
	private Statement stat = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	public BaseDbReflectionImp()
	{
		try {
			stat = con.createStatement();
		} catch (SQLException e)
		{
			System.out.println("获取statement失败");
			e.printStackTrace();
		}
	}
	
	@Override
	public Connection getConnection() {
		return con;
	}

	@Override
	public int executeUpdate(String sql) throws SQLException {
		return stat.executeUpdate(sql);
	}

	@Override
	public int executeUpdate(String sql, Object... params) throws SQLException {
		ps = con.prepareStatement(sql);
		for(int i = 1; i <= params.length; i++)
			ps.setObject(i, params[i-1]);
		return ps.executeUpdate();
	}

	@Override
	public Integer executeQueryForCount(String sql) throws SQLException {
		rs = stat.executeQuery(sql);
		while(rs.next())
			return rs.getInt(1);
		return 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T executeQueryForBean(String sql, Class<T> clazz)
			throws SQLException {
		rs = stat.executeQuery(sql);
		return (T) ResultSetParse.parseResultSet(rs, clazz).get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T executeQueryForBean(String sql, Class<T> clazz, Object... params)
			throws SQLException {
		ps = con.prepareStatement(sql);
		for(int i = 1; i <= params.length; i++)
			ps.setObject(i, params[i-1]);
		rs = ps.executeQuery();
		return (T) ResultSetParse.parseResultSet(rs, clazz).get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> executeQuery(String sql, Class<T> clazz) throws SQLException {
		rs = stat.executeQuery(sql);
		return (List<T>) ResultSetParse.parseResultSet(rs, clazz);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> executeQuery(String sql, Class<T> clazz, Object... params)
			throws SQLException {
		ps = con.prepareStatement(sql);
		for(int i = 1; i <= params.length; i++)
			ps.setObject(i, params[i-1]);
		rs = ps.executeQuery();
		return (List<T>) ResultSetParse.parseResultSet(rs, clazz);
	}
}
