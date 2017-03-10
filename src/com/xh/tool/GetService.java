package com.xh.tool;

import com.xh.service.AdminService;
import com.xh.service.UserService;
import com.xh.service.imp.ServiceFactory;

/**
 * 该接口的目的在于提供一个统一的接口，来初始化操作的对象
 * 产生所有的操作serviceDao的对象
 * 通过该接口，其实现类都可以完成对serviceDao的所有操作
 * @author XH
 *
 */
public interface GetService {
	UserService usi = (UserService) ServiceFactory.getService("UserService");
	AdminService asi = (AdminService) ServiceFactory.getService("AdminService");
}
