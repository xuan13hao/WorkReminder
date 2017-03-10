package com.xh.dao;

import java.sql.Date;
import java.util.List;

import com.xh.bean.Info;
import com.xh.page.PageDiv;

/**
 * 该程序中对于工作信息所能够进行的操作
 * @author XH
 *
 */
public interface InfoDao {
	/**
	 * 增加工作信息
	 * @param info 要增加的工作信息对象
	 * @return	增加成功返回true否则返回false
	 */
	public boolean addInfo(Info info);
	
	/**
	 * 通过id来删除工作信息
	 * @param id	要删除的工作信息id
	 * @return	删除成功返回true否则返回false
	 */
	public boolean deleteInfo(int id);
	
	/**
	 * 通过用户id来删除一条工作信息
	 * @param userId	要删除工作信息的用户id
	 * @return	删除成功返回true否则返回false
	 */
	public boolean deleteInfoByUserId(int userId);
	
	/**
	 * 修改当前用户工作信息
	 * @param info	要修改的用户工作信息
	 * @return	修改成功返回true否则返回false
	 */
	public boolean updateInfo(Info info);
	
	/**
	 * 通过id来审核当前工作安排信息
	 * @param id 要审核的信息id
	 * @return	审核成功返回true否则返回false
	 */
	public boolean checkInfo(int id);
	
	/**
	 * 判断当日是否有工作安排信息
	 * @param date		要判断工作安排信息的日期数
	 * @param userId 	要获得当前用户的工作安排信息
	 * @return	如果当天有工作安排返回true否则返回false
	 */
	public boolean hasInfo(Date date, int userId);
	
	/**
	 * 通过id获取用户信息
	 * @param id	要获取的用户信息的id
	 * @return	用户信息Info对象
	 */
	public Info getInfo(int id);
	
	/**
	 * 通过用户id来获取用户信息
	 * @param userId	要获取用户信息的用户id
	 * @return	用户信息Info对象
	 */
	public Info getInfoByUserId(int userId);
	
	/**
	 * 获取某一用户的工作信息，并完成分页
	 * @param userId	要获取信息的用户id
	 * @param pageNo	当前显示内容的页码
	 * @param pageSize	每页显示的数据条数
	 * @return 分页集合对象
	 */
	public PageDiv<Info> getInfoByPd(int userId, int pageNo, int pageSize);
	
	/**
	 * 获取某一用户在某一天的工作信息，并完成分页
	 * @param userId	要获取信息的用户id
	 * @param pageNo	当前显示内容的页码
	 * @param pageSize	每页显示信息的条数
	 * @param day		要获取工作信息的日期
	 * @return	PageDiv<Info>集合对象
	 */
	public PageDiv<Info> getInfoByPdByDay(int userId, int pageNo, int pageSize, Date day);
	
	/**
	 * 获取到所有的工作信息
	 * @return	用户信息的集合对象
	 */
	public List<Info> getAllInfo();
}
