package com.xh.dao;

import com.xh.bean.Register;
/**
 * 对于用户的注册码所进行的操作
 * @author XH
 *
 */
public interface RegisterDao {
	public boolean addRegister(Register register);
	
	public String getCoder(String username);
	
	public boolean changeStates(String username);
}
