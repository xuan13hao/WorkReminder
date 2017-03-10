package com.xh.service.imp;

import java.util.ResourceBundle;

/**
 * 生成SERVICE的一个工厂类
 * @author XH
 * @version 1.3
 */
public class ServiceFactory 
{
   private static ResourceBundle rs = ResourceBundle.getBundle("service");
  /**
   * 通过SERVICE的名字来生成SERVICE的实现类对象
   * @param serviceName	SERVICE的名字
   * @return	SERVICE实现类的一个对象
   */
   public static Object getService(String serviceName)
   {
	   Object obj = null;
	   String impclass = rs.getString(serviceName);
	   if(null != impclass)
	   {
		   try {
			obj = (Object)Class.forName(impclass).newInstance();
		  } catch (Exception e) {
			System.out.println("获取service出现异常");
			e.printStackTrace();
		 } 
	   }
	   return obj;
   }
}
