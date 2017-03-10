package com.xh.dao.imp.reflection;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.xh.bean.Info;
import com.xh.dao.InfoDao;
import com.xh.dao.imp.BaseDao;
import com.xh.db.reflection.BaseDbReflectionImp;
import com.xh.page.PageDiv;
/**
 * 通过反射机制来实现InfoDao
 * @author XH
 *
 */
public class InfoDaoRefImp extends BaseDao implements InfoDao {
	BaseDbReflectionImp<Info> brei = new BaseDbReflectionImp<Info>();
	@Override
	public boolean addInfo(Info info) {
		sql = "insert into info(userId, startTime, endTime, content, " +
				"dayTime, isChecked) values(?,?,?,?,?,?)";
		params = new Object[]{info.getUserId(), info.getStartTime(), info.getEndTime(),
				info.getContent(), info.getDayTime(), info.getIsChecked()};
		try {
			i = brei.executeUpdate(sql, params);
		} catch (SQLException e) {
			System.out.println("通过反射添加Info出现异常");
			e.printStackTrace();
		}
		return BaseDao.isTrue(i);
	}

	@Override
	public boolean deleteInfo(int id) {
		sql = "delete from info where id = ?";
		try {
			i = brei.executeUpdate(sql, id);
		} catch (SQLException e) {
			System.out.println("通过反射删除Info出现异常");
			e.printStackTrace();
		}
		return BaseDao.isTrue(i);
	}

	@Override
	public boolean deleteInfoByUserId(int userId) {
		sql = "delete from info where userId = ?";
		try {
			i = brei.executeUpdate(sql, userId);
		} catch (SQLException e) {
			System.out.println("通过反射删除Info出现异常");
			e.printStackTrace();
		}
		return BaseDao.isTrue(i);
	}

	@Override
	public boolean updateInfo(Info info) {
		sql = "update info set userId = ?, startTime = ?, endTime = ?, " +
				"content = ?, dayTime = ?, isChecked = ? where id = ?";
		params = new Object[]{info.getUserId(), info.getStartTime(), info.getEndTime(),
				info.getContent(), info.getDayTime(), info.getIsChecked(), info.getId()};
		try {
			i = brei.executeUpdate(sql, params);
		} catch (SQLException e) {
			System.out.println("通过反射机制来修改Info出现异常");
			e.printStackTrace();
		}
		return BaseDao.isTrue(i);
	}
	
	@Override
	public boolean checkInfo(int id) {
		sql = "update info set isChecked = ? where id = ?";
		params = new Object[]{1, id};
		try {
			i = brei.executeUpdate(sql, params);
		} catch (SQLException e) {
			System.out.println("通过反射机制来审核Info出现异常");
			e.printStackTrace();
		}
		return BaseDao.isTrue(i);
	}
	
	@Override
	public boolean hasInfo(Date date, int userId)
	{
		List<Info> list = new ArrayList<Info>();
		sql = "select * from info where dayTime = ? and userId = ?";
		params = new Object[]{date, userId};
		try {
			list = brei.executeQuery(sql, Info.class, params);
		} catch (SQLException e) {
			System.out.println("通过反射机制来检测是否有Info安排出现异常");
			e.printStackTrace();
		}
		if(list.size()>0)
			return true;
		else 
			return false;
	}

	@Override
	public Info getInfo(int id) {
		Info info = new Info();
		sql = "select * from info where id = ?";
		try {
			info = brei.executeQueryForBean(sql, Info.class, id);
		} catch (SQLException e) {
			System.out.println("通过反射机制来获取Info出现异常");
			e.printStackTrace();
		}
		return info;
	}

	@Override
	public Info getInfoByUserId(int userId) {
		Info info = new Info();
		sql = "select * from info where userId = ?";
		try {
			info = brei.executeQueryForBean(sql, Info.class, userId);
		} catch (SQLException e) {
			System.out.println("通过反射机制来获取Info出现异常");
			e.printStackTrace();
		}
		return info;
	}

	@Override
	public PageDiv<Info> getInfoByPd(int userId, int pageNo, int pageSize)
	{
		List<Info> list = new ArrayList<Info>();
		sql = "select * from info where userId = ? limit ?,?";
		sqlCount = "select count(id) from info";
		params = new Object[]{userId, (pageNo-1)*pageSize, pageSize};
		try {
			list = brei.executeQuery(sql, Info.class, params);
			count = brei.executeQueryForCount(sqlCount);
		} catch (SQLException e) {
			System.out.println("通过DbUtil获取Info分页出现异常");
			e.printStackTrace();
		}
		return new PageDiv<Info>(pageNo, pageSize, count, list);
	}
	
	@Override
	public PageDiv<Info> getInfoByPdByDay(int userId, int pageNo, int pageSize, Date day)
	{
		List<Info> list = new ArrayList<Info>();
		sql = "select * from info where userId = ? and dayTime = ? limit ?,?";
		sqlCount = "select count(id) from info";
		params = new Object[]{userId, day, (pageNo-1)*pageSize, pageSize};
		try {
			list = brei.executeQuery(sql, Info.class, params);
			count = brei.executeQueryForCount(sqlCount);
		} catch (SQLException e) {
			System.out.println("通过DbUtil获取Info分页出现异常");
			e.printStackTrace();
		}
		return new PageDiv<Info>(pageNo, pageSize, count, list);
	}
	
	@Override
	public List<Info> getAllInfo() {
		List<Info> list = new ArrayList<Info>();
		sql = "select * from info";
		try {
			list = brei.executeQuery(sql, Info.class);
		} catch (SQLException e) {
			System.out.println("通过反射机制来获取所有的Info出现异常");
			e.printStackTrace();
		}
		return list;
	}
}
