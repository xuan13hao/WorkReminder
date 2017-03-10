package com.xh.service;

import java.sql.Date;
import java.util.List;

import com.xh.bean.Info;
import com.xh.bean.User;
import com.xh.page.PageDiv;

/**
 * 该接口中提供了一些用户可以完成的基本操作
 * 对于所有的用户来说，都可以完成的操作内容
 * @author XH
 *
 */
public interface UserService {
	/**
	 * 注册用户
	 * @param user 要注册的用户对象
	 * @return	注册成功返回true否则返回false
	 */
	public boolean registerUser(User user);
	
	/**
	 * 用户登录
	 * @param user 	要登录的用户对象
	 * @return	登录成功返回1,用户名不存在返回2，用户名存在但密码错误返回0
	 */
	public int loginUser(User user);
	
	/**
	 * 修改用户的个人基本信息
	 * @param user	要修改信息的用户对象
	 * @return	修改成功返回true否则返回false
	 */
	public boolean updateUser(User user);
	
	/**
	 * 通过用户名来获取User对象
	 * @param username	要获取用户对象的用户名
	 * @return 获取成功返回User对象否则返回null
	 */
	public User getUser(String username);
	/* 用户对于User类完成的操作内容 */
	
	/**
	 * 增加工作信息
	 * @param info 要增加的工作安排对象
	 * @return	添加成功返回true否则返回false
	 */
	public boolean addInfo(Info info);
	
	/**
	 * 检测某天是否有工作安排
	 * @param date	要检测工作安排的日期
	 * @param user	要获取信息的用户
	 * @return	有工作安排返回true否则返回false
	 */
	public boolean hasInfo(Date date, User user);
	
	/**
	 * 通过id来审核当前工作安排信息
	 * @param id 要审核的信息id
	 * @return	审核成功返回true否则返回false
	 */
	public boolean checkInfo(int id);
	
	/**
	 * 获取当天所有的工作安排
	 * @return List<Info>集合
	 */
	public List<Info> getAllInfo();
	
	/**
	 * 通过分页形式来显示用户的工作安排情况
	 * 没有指定日期就表明是当前的工作安排
	 * @param username	要显示信息的用户名
	 * @param pageNo	要显示信息的页码
	 * @param pageSize	每页显示信息的条数
	 * @return	PageDiv<Info>集合对象
	 */
	public PageDiv<Info> getInfoByPd(String usrename, int pageNo, int pageSize);
	
	/**
	 * 获取某一天的工作安排信息
	 * @param username 	要显示信息的用户名
	 * @param pageNo	要显示信息的页码
	 * @param pageSize	每页显示信息的条数
	 * @param day		要获取的信息日期
	 * @return PageDiv<Info>集合对象
	 */
	public PageDiv<Info> getInfoByPdByDay(String usrename, int pageNo, int pageSize, Date day);
	/* 用户对于Info类完成的操作内容*/
	
	/**
	 * 获取注册码
	 * @param username 要获取验证码的用户名
	 * @return	获取成功返回一个验证码字符串序列否则返回null
	 */
	public String getCoder(String username);
	
	/**
	 * 某用户已经完成注册，修改注册状态
	 * @param username	要修改状态的用户名
	 * @return	修改成功返回true否则返回false
	 */
	public boolean hasRegisted(String username);
	/* 用户对于注册信息的操作内容*/
}
