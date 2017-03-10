package com.xh.service.imp;

import com.xh.dao.InfoDao;
import com.xh.dao.RegisterDao;
import com.xh.dao.UserDao;
import com.xh.dao.imp.DaoFactory;

/**
 * 该类提供所有操作底层的实现类对象
 * 便于在SERVICE实现类中的使用
 * @author XH
 *
 */
public class BaseService {
	public UserDao udi = (UserDao)DaoFactory.getDao("UserDao");
	public InfoDao idi = (InfoDao)DaoFactory.getDao("InfoDao");
	public RegisterDao rdi = (RegisterDao)DaoFactory.getDao("RegisterDao");
}
