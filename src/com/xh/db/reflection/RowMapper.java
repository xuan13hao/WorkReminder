package com.xh.db.reflection;

import java.sql.ResultSet;
/**
 * 提供一个借口，方法实现将结果集转化成相对应的类型
 * 对于不同的类对象，结果集都不尽相同，所以此借口必须被每一个基类完成实现
 * @author XH
 *
 * @param <T>
 */
public interface RowMapper<T>
{   
	public T parse(ResultSet rs);
}
