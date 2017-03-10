package com.xh.db;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * 该接口整合程序中所能够用到的对于数据库的各种操作
 * 包括更新数据库、查询等等
 * @author XH
 *
 */
public interface BaseDb<T extends Serializable>{
	
	/**
	 * 获得连接数据库的对象
	 * @return 连接数据库的对象Connection
	 */
	public Connection getConnection();
	
	/**
     * 完成对数据的更新操作，包括insert、delete、update
     * @param sql 要执行的sql语句
     * @return  返回影响数据表的条数
     */
    public int executeUpdate(String sql) throws SQLException;
	
    /**
     * 通过可变形参更新数据表inser update delete
     * 例如:insert t_name(aa,aa,ss) values(?,?,?) 
     * @param sql 要执行的sql语句
     * @param params  加入的参数列表
     * @return 返回影响数据表的条数
     */
    public int executeUpdate(String sql,Object...params)throws SQLException;
	
    /**
	 * 查句指定sql所查找的记录总数  sql语数一定要以selec count(*)开始
	 * @param sql  sql语句select count(id) from user;
	 * @return   返回记录总条数
	 */
    public Integer executeQueryForCount(String sql)throws SQLException;
   
    /**
     * 查询一条记录，如果返回多条记录，则返回第一条
     * @param sql  sql语句select * from user where id=12
     * @return  返回一条记录的对象封装
     */
    public T executeQueryForBean(String sql, Class<T> clazz)throws SQLException;
    
    /**
     * 查询一条记录，如果返回多条记录，则返回第一条
     * @param sql  sql语句select * from user where id=?
     * @param params  用于替代?的参数
     * @return  返回一条记录的对象封装
     */
    public T executeQueryForBean(String sql, Class<T> clazz,Object...params)throws SQLException;

    /**
     * 查询数据表，将结果转为一个List
     * String sql=select * from user;
     * List<User> list=executeQuery(sql,User.class);
     * @param sql   要执行的sql语够大
     * @param clazz  返回list中元素的类型
     * @return   返回数据表结果的list封装
     */
    public List<T> executeQuery(String sql,Class<T> clazz)throws SQLException; 
    
    /**
     * 查询数据表，将结果转为一个List
     * String sql=select * from user where id=?;
     * List<User> list=executeQuery(sql,User.class);
     * @param sql   要执行的sql语够大
     * @param clazz  返回list中元素的类型
     * @param param  为传入的参数用来封替代?
     * @return   返回数据表结果的list封装
     */
    public List<T> executeQuery(String sql,Class<T> clazz,Object...params)throws SQLException;
}
