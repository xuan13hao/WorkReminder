package com.xh.service.imp;

import java.sql.Date;
import java.util.List;

import com.xh.bean.Info;
import com.xh.bean.User;
import com.xh.page.PageDiv;
import com.xh.service.UserService;

public class UserServiceImp extends BaseService implements UserService{

	@Override
	public boolean registerUser(User user) {
		return udi.addUser(user);
	}
	
	@Override
	public int loginUser(User user) {
		int state = 2;
		User us = udi.getUser(user.getUsername());
		if(us != null)
		{
			if(us.getPassword().equals(user.getPassword()))
				state = 1;
			else 
				state = 0;
		}
		return state;
	}


	@Override
	public boolean updateUser(User user) {
		return udi.updateUser(user);
	}
	
	@Override
	public User getUser(String username)
	{
		return udi.getUser(username);
	}

	@Override
	public boolean addInfo(Info info) {
		return idi.addInfo(info);
	}
	
	@Override
	public boolean checkInfo(int id)
	{
		return idi.checkInfo(id);
	}
	
	@Override
	public boolean hasInfo(Date date, User user)
	{
		return idi.hasInfo(date, user.getId());
	}

	@Override
	public List<Info> getAllInfo() {
		return idi.getAllInfo();
	}

	@Override
	public PageDiv<Info> getInfoByPd(String username, int pageNo, int pageSize) {
		int userId = udi.getUser(username).getId();
		return idi.getInfoByPd(userId, pageNo, pageSize);
	}

	@Override
	public PageDiv<Info> getInfoByPdByDay(String username, int pageNo, int pageSize,
			Date day) {
		int userId = udi.getUser(username).getId();
		return idi.getInfoByPdByDay(userId, pageNo, pageSize, day);
	}

	@Override
	public String getCoder(String username) {
		return rdi.getCoder(username);
	}
	
	@Override
	public boolean hasRegisted(String username) {
		return rdi.changeStates(username);
	}
}
