package com.xh.db.reflection;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
/**
 * 完成将结果集和类对象转化成相对应的List<Object>集合
 * @author XH
 *
 */
public class ResultSetParse 
{
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public static List<Object> parseResultSet(ResultSet rs, Class clazz) 
    {
    	List<Object> list=new ArrayList<Object>(); 
    	try {	
			if(null!=rs)
			{
				ResultSetMetaData rsmd = rs.getMetaData();
				int columCount = rsmd.getColumnCount();
				String []colums = new String[columCount];
				for(int i=0;i<colums.length;i++)
				{
					colums[i] = rsmd.getColumnName(i+1);
				}
				while(rs.next())
				{
					Object obj = clazz.newInstance();//实例化对象	
					for(int i=0;i<colums.length;i++)
					{
						int type = rsmd.getColumnType(i+1);				
						Method settermethod = clazz.getDeclaredMethod(parseProperty(colums[i]), parseTypes(type));
						//采用反射机制来得到该Class中的属性以及方法
				        switch(type)
				        {
				        case Types.CHAR:
				        case Types.VARCHAR:
				        case Types.LONGVARCHAR:
				        	 settermethod.invoke(obj, rs.getString(colums[i]));
				        	 break;
				        case Types.BIT:
				        	 settermethod.invoke(obj,rs.getBoolean(colums[i]));
				        	 break;
				        case Types.TINYINT:
				        case Types.SMALLINT:
				        case Types.INTEGER:
				        	 settermethod.invoke(obj, rs.getInt(colums[i]));
				        break;
				        case Types.BIGINT:
				        	 settermethod.invoke(obj, rs.getLong(colums[i]));
				        	 break;
				        case Types.DECIMAL:
				        	 settermethod.invoke(obj, rs.getBigDecimal(colums[i]));
				        	 break;
				        case Types.FLOAT:
				        case Types.DOUBLE:
				        	 settermethod.invoke(obj, rs.getDouble(colums[i]));
				        	 break;
				        case Types.TIMESTAMP:
				        	 settermethod.invoke(obj, rs.getTimestamp(colums[i]));
				        	 break;
				        case Types.DATE:
				        	 settermethod.invoke(obj, rs.getDate(colums[i]));
				        	 break;
				        default:   
				        }                         
					}
					 list.add(obj);
				}//while循环结束
			}	
		} catch (Exception e) 
		{
			e.printStackTrace();
		} 
    	return list;
    }
    
    /**
     * 完成对数据库中各种字段的重命名
     * @param field
     * user_id 转化成 setUserId
     * id 转化成 setId
     * @return String
     */
    public static String parseProperty(String field)
    {
    	String result = null;
    	int index = field.indexOf("_");
    	if(-1 == index)//表示该Field中不包含“_”
    	{
    	   result = "set"+parseFirstchar(field);
    	}
    	else
    	{
    		String []towstr = field.split("_");//利用"_"来完成对Field的分割
    		result = "set"+parseFirstchar(towstr[0])+parseFirstchar(towstr[1]);
    		//结果转化成set+首字母大写+首字母大写
    	}
    	return result;
    }
    
    /**
     * 完成将传入的参数首字母大写,其他字母保持不变
     * @param orgstr
     * name 转化成 Name
     * @return String 
     */
    public static String parseFirstchar(String orgstr)
    {
    	return orgstr.substring(0,1).toUpperCase()+orgstr.substring(1);	
    }
    
    /**
     * 数据的类型
     * @param type
     * @return Class
     */
    @SuppressWarnings("rawtypes")
	public static Class parseTypes(int type)
    {
        switch(type)
        {
        case Types.CHAR:
        case Types.VARCHAR:
        case Types.LONGVARCHAR:
             return String.class;
        case Types.BIT:
             return Boolean.class;
        case Types.TINYINT:
        case Types.SMALLINT:
        case Types.INTEGER:
             return Integer.class;
        case Types.BIGINT:
             return Long.class;
        case Types.DECIMAL:
        	 return BigDecimal.class;
        case Types.FLOAT:
        case Types.DOUBLE:
             return Double.class;
        case Types.DATE:
             return java.sql.Date.class;
        case Types.TIMESTAMP:
        	 return java.sql.Timestamp.class;
        default:
             return Object.class;
        }
    }
}
