package com.xh.dao;

import java.util.List;

import com.xh.bean.User;
import com.xh.page.PageDiv;

/**
 * 该程序中对于用户所能够进行的操作
 * @author XH
 *
 */
public interface UserDao {
	public boolean addUser(User user);
	
	public boolean deleteUser(int id);
	
	public boolean deleteUser(String username);
	
	/**
	 * 通过用户名来修改用户信息
	 * @param user	要修改的用户信息
	 * @return	修改成功返回true否则返回false
	 */
	public boolean updateUser(User user);
	
	public User getUser(int id);
	
	public User getUser(String username);
	
	public PageDiv<User> getUserByPd(int pageNo, int pageSize);
	
	public List<User> getAllUser();
}
