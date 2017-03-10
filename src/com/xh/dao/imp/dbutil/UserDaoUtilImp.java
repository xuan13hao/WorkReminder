package com.xh.dao.imp.dbutil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.xh.bean.User;
import com.xh.dao.UserDao;
import com.xh.dao.imp.BaseDao;
import com.xh.db.dbutil.BaseDbUtilImp;
import com.xh.page.PageDiv;
/**
 * 通过dbUtil方式来实现的UserDao
 * @author XH
 *
 */
public class UserDaoUtilImp extends BaseDao implements UserDao {
	BaseDbUtilImp<User> bdui = new BaseDbUtilImp<User>();
	@Override
	public boolean addUser(User user) {
		sql = "insert into user(username, password, rights) values(?,?,?)";
		params = new Object[]{user.getUsername(), user.getPassword(), user.getRights()};
		try {
			i = bdui.executeUpdate(sql, params);
		} catch (SQLException e) {
			System.out.println("利用DbUtil机制添加User失败");
			e.printStackTrace();
		}
		return BaseDao.isTrue(i);
	}

	@Override
	public boolean deleteUser(int id) {
		sql = "delete from user where id = ?";
		try {
			i = bdui.executeUpdate(sql, id);
		} catch (SQLException e) {
			System.out.println("通过DbUtil删除User出现异常");
			e.printStackTrace();
		}
		return BaseDao.isTrue(i);
	}

	@Override
	public boolean deleteUser(String username) {
		sql = "delete from user where username = ?";
		try {
			i = bdui.executeUpdate(sql, username);
		} catch (SQLException e) {
			System.out.println("通过DbUtil删除User出现异常");
			e.printStackTrace();
		}
		return BaseDao.isTrue(i);
	}

	@Override
	public boolean updateUser(User user) {
		sql = "update user set password = ?, rights = ? where username = ?";
		params = new Object[]{user.getPassword(), user.getRights(), user.getUsername()};
		try {
			i = bdui.executeUpdate(sql, params);
		} catch (SQLException e) {
			System.out.println("通过DbUtil修改User出现异常");
			e.printStackTrace();
		}
		return BaseDao.isTrue(i);
	}

	@Override
	public User getUser(int id) {
		User user = new User();
		sql = "select * from user where id = ?";
		try {
			user = bdui.executeQueryForBean(sql, User.class, id);
		} catch (SQLException e) {
			System.out.println("通过DbUtil获取User出现异常");
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public User getUser(String username) {
		User user = new User();
		sql = "select * from user where username = ?";
		try {
			user = bdui.executeQueryForBean(sql, User.class, username);
		} catch (SQLException e) {
			System.out.println("通过DbUtil获取User出现异常");
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public PageDiv<User> getUserByPd(int pageNo, int pageSize)
	{
		List<User> list = new ArrayList<User>();
		sql = "select * from user limit ?,?";
		sqlCount = "select count(id) from user";
		params = new Object[]{(pageNo-1)*pageSize, pageSize};
		try {
			list = bdui.executeQuery(sql, User.class, params);
			count = bdui.executeQueryForCount(sqlCount);
		} catch (SQLException e) {
			System.out.println("通过DbUtil获取User分页出现异常");
			e.printStackTrace();
		}
		return new PageDiv<User>(pageNo, pageSize, count, list);
	}
	
	@Override
	public List<User> getAllUser() {
		List<User> list = new ArrayList<User>();
		sql = "select * from user";
		try {
			list = bdui.executeQuery(sql, User.class);
		} catch (SQLException e) {
			System.out.println("通过DbUtil获取所有的User出现异常");
			e.printStackTrace();
		}
		return list;
	}
}
