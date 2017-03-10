package com.xh.service.imp;

import com.xh.bean.User;
import com.xh.page.PageDiv;
import com.xh.service.AdminService;

public class AdminServiceImp extends BaseService implements AdminService {

	@Override
	public int loginAdmin(User user) {
		int state = 2;
		User us = udi.getUser(user.getUsername());
		if(us != null)
		{
			if(us.getPassword().equals(user.getPassword()))
			{	
				if(us.getRights() == 1)
					state = 1;
			}
			else
				state = 0;
		}
		return state;
	}

	@Override
	public boolean addUser(User user) {
		return udi.addUser(user);
	}

	@Override
	public boolean deleteUser(String username) {
		return udi.deleteUser(username);
	}

	@Override
	public boolean updateUser(User user) {
		return udi.updateUser(user);
	}
	
	@Override
	public User getUser(String username) {
		return udi.getUser(username);
	}

	@Override
	public PageDiv<User> showUser(int pageNo, int pageSize) {
		return udi.getUserByPd(pageNo, pageSize);
	}
}
