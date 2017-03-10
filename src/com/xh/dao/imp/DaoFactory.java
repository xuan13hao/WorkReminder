package com.xh.dao.imp;

import java.util.ResourceBundle;

/**
 * 生成DAO的一个工厂类
 * @author XH
 * @version 1.3
 */
public class DaoFactory 
{
   private static ResourceBundle rs = ResourceBundle.getBundle("dao");
  /**
   * 通过DAO的名字来生成DAO的实现类对象
   * @param daoName	DAO名字
   * @return	DAO实现类的一个对象
   */
   public static Object getDao(String daoName)
   {
	   Object obj = null;
	   String impclass = rs.getString(daoName);
	   if(null != impclass)
	   {
		   try {
			obj = (Object)Class.forName(impclass).newInstance();
		  } catch (Exception e) {
			System.out.println("获取dao出现异常");
			e.printStackTrace();
		 } 
	   }
	   return obj;
   }
}
