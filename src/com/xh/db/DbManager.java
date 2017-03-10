package com.xh.db;

import java.sql.Connection;
import java.util.ResourceBundle;

/**
 * 利用该类完成对数据库的访问
 * 这里提供两种访问数据库的方式，一是JDBC的方式，二是C3P0的方式
 * 至于采用什么样的方式，需要程序员在配置文件中完成定义，
 * 该类生命为抽象的，目的在于提供一个抽象方法，使得不同连接数据库方式的不同实现
 * 即通过继承该类，对于其抽象方法的不同实现就注定了用不同的方式访问数据库
 * @author XH
 *
 */
public abstract class DbManager {
	
	private static String CONNECTIONMANAGER = "connectionManager";
	//指定读取配置文件的Key
	private static ResourceBundle jdbc=ResourceBundle.getBundle("jdbc");
	//指定当前读取的配置文件
	public Connection getConnection()
	{
		return this.getRealConnection();
	}
	
	/**
	 * 提供一个抽象方法，该方法是真正完成对于数据连接的方法
	 * 在该方法中不同的实现就产生了不同的连接方式
	 * @return 连接数据库的对象Connection
	 */
	public abstract Connection getRealConnection();
	
	/**
     * 该方法通过读取配置文件，进而决定采用什么样的方式来连接数据库
     * 也就是说，该方法决定哪一个子类来完成对该类的重写
     * @return 当前类DbManager对象
     */
    public static DbManager getInstance()
    {
    	DbManager db = null;
    	String connectionManager=jdbc.getString(CONNECTIONMANAGER);
    	if(null == connectionManager)
    	{
    		System.out.println("找不到connectionManager的配置，请检查jdbc.properties中connectionManager");
    	}
    	else
    	{
    		try 
    		{
				db = (DbManager)Class.forName(connectionManager).newInstance();
			} catch (Exception e)
			{
				System.out.println("找不到connectionManager的配置，请检查jdbc.properties中connectionManager");
				e.printStackTrace();
			}	
    	}
    	return db;
    }
}
