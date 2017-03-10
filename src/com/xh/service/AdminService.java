package com.xh.service;

import com.xh.bean.User;
import com.xh.page.PageDiv;

/**
 * 对于管理员来说所能够进行的操作内容
 * @author XH
 *
 */
public interface AdminService {
	/**
	 * 管理员登陆
	 * @param user 要登陆的用户对象
	 * @return 登陆成功返回1，用户名不存在返回2，用户名存在但密码错误返回0
	 */
	public int loginAdmin(User user);
	
	/**
	 * 添加一个用户
	 * @param user 要添加的用户对象
	 * @return	添加成功返回true否则返回false
	 */
	public boolean addUser(User user);
	
	/**
	 * 通过用户名删除一个用户
	 * @param username 要删除用户信息的用户名
	 * @return	要删除成功返回true否则返回false
	 */
	public boolean deleteUser(String username);
	
	/**
	 * 更新用户信息
	 * @param user	要更新的用户对象
	 * @return	更新成功返回true否则返回false
	 */
	public boolean updateUser(User user);
	
	/**
	 * 通过用户名来获取当前用户对象
	 * @param username	要获取用户对象的用户名
	 * @return	获取成功返回User对象否则返回null
	 */
	public User getUser(String username);
	
	/**
	 * 显示所有的用户信息
	 * @param pageNo	显示用户信息的页码
	 * @param pageSize	每页显示信息的条数
	 * @return PageDiv<User>集合对象
	 */
	public PageDiv<User> showUser(int pageNo, int pageSize);
}
