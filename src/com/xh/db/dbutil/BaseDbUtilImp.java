package com.xh.db.dbutil;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.xh.db.BaseDb;
import com.xh.db.DbManager;

/**
 * 利用dbutils包下提供的方式来完成对于数据库的一些基本操作
 * @author XH
 *
 * @param <T>
 */
public class BaseDbUtilImp<T extends Serializable> implements BaseDb<T> {

	private QueryRunner qr = new QueryRunner();
	private Connection con = DbManager.getInstance().getConnection();
	@Override
	public Connection getConnection() {
		return con;
	}

	@Override
	public int executeUpdate(String sql) throws SQLException {
		return qr.update(con, sql);
	}

	@Override
	public int executeUpdate(String sql, Object... params) throws SQLException {
		return qr.update(con, sql, params);
	}

	@Override
	public Integer executeQueryForCount(String sql) throws SQLException {
		 Integer re=0;
		 if(sql.matches("^select\\s+count.+"))
		 {
			 Object[] tem = qr.query(con, sql, new ArrayHandler());
			 if(null!=tem&&tem.length>0)
			 {
				if(tem[0].toString().matches("\\d+"))
				{
		          re=Integer.parseInt(tem[0].toString());
				}
			 }
		 }
		 return re;
	}

	@Override
	public T executeQueryForBean(String sql, Class<T> clazz)
			throws SQLException {
		return qr.query(con, sql, new BeanHandler<T>(clazz));
	}

	@Override
	public T executeQueryForBean(String sql, Class<T> clazz, Object... params)
			throws SQLException {
		return qr.query(con, sql, new BeanHandler<T>(clazz), params);
	}

	@Override
	public List<T> executeQuery(String sql, Class<T> clazz) throws SQLException {
		return qr.query(con, sql, new BeanListHandler<T>(clazz));
	}

	@Override
	public List<T> executeQuery(String sql, Class<T> clazz, Object... params)
			throws SQLException {
		return qr.query(con, sql, new BeanListHandler<T>(clazz), params);
	}
}
