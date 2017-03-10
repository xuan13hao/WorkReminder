package com.xh.dao.imp.row;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.xh.bean.Info;
import com.xh.dao.InfoDao;
import com.xh.dao.imp.BaseDao;
import com.xh.page.PageDiv;

/**
 * 通过数据库对象进行封装实现的InfoDao
 * @author XH
 *
 */
public class InfoDaoImp extends BaseDao implements InfoDao {

	JDBCTemplate jt = new JDBCTemplate();
	@Override
	public boolean addInfo(Info info) {
		sql = "insert into info(userId, startTime, endTime, content, dayTime, isChecked)" +
				" values('" + info.getUserId() + "','" + info.getStartTime() + "','" +
				info.getEndTime() + "','" + info.getContent() + "','" + info.getDayTime() + "','" + 
				info.getIsChecked() + "')";
		try {
			stat = con.createStatement();
			i = stat.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("Info连接数据库失败");
			e.printStackTrace();
		}
		return BaseDao.isTrue(i);
	}

	@Override
	public boolean deleteInfo(int id) {
		sql = "delete from info where id = " + id;
		try {
			stat = con.createStatement();
			i = stat.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("Info连接数据库失败");
			e.printStackTrace();
		}
		return BaseDao.isTrue(i);
	}

	@Override
	public boolean deleteInfoByUserId(int userId) {
		sql = "delete from info where userId = " + userId;
		try {
			stat = con.createStatement();
			i = stat.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("Info连接数据库失败");
			e.printStackTrace();
		}
		return BaseDao.isTrue(i);
	}

	@Override
	public boolean checkInfo(int id) {
		sql = "update info set isChecked = 1 where id = " + id;
		try {
			stat = con.createStatement();
			i = stat.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("Info连接数据库失败");
			e.printStackTrace();
		}
		return BaseDao.isTrue(i);
	}
	

	@Override
	public boolean updateInfo(Info info) {
		sql = "update info set userId = " +info.getUserId() + ", startTime = '" + info.getStartTime() + "', " +
				"endTime = '" + info.getEndTime() + "', content = '" + info.getContent() + "', " +
				"dayTime = '" +info.getDayTime() + "', isChecked = '" + info.getIsChecked() + "'";
		try {
			stat = con.createStatement();
			i = stat.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("Info连接数据库失败");
			e.printStackTrace();
		}
		return BaseDao.isTrue(i);
	}
	
	@Override
	public boolean hasInfo(Date date, int userId)
	{
		sql = "select * from info where dayTime = '" + date + "' and userId = " + userId;
		try {
			stat = con.createStatement();
			rs = stat.executeQuery(sql);
			if(rs.next())
				return true;
		} catch (SQLException e) {
			System.out.println("一般封装检测是否有Info安排出现异常");
			e.printStackTrace();
		}
			return false;
	}

	@Override
	public Info getInfo(int id) {
		Info info = new Info();
		sql = "select * from info where id =" + id;
		try {
			stat = con.createStatement();
			rs = stat.executeQuery(sql);
			while(rs.next())
			{
				info.setId(rs.getInt("id"));
				info.setUserId(rs.getInt("userId"));
				info.setStartTime(rs.getString("startTime"));
				info.setEndTime(rs.getString("endTime"));
				info.setContent(rs.getString("content"));
				info.setDayTime(rs.getDate("dayTime"));
				info.setIsChecked(rs.getInt("isChecked"));
			}
		} catch (SQLException e) 
		{
			System.out.println("Info获取失败");
			e.printStackTrace();
		}
		return info;
	}

	@Override
	public Info getInfoByUserId(int userId) {
		Info info = new Info();
		sql = "select * from info where userId =" + userId;
		try {
			stat = con.createStatement();
			rs = stat.executeQuery(sql);
			while(rs.next())
			{
				info.setId(rs.getInt("id"));
				info.setUserId(rs.getInt("userId"));
				info.setStartTime(rs.getString("startTime"));
				info.setEndTime(rs.getString("endTime"));
				info.setContent(rs.getString("content"));
				info.setDayTime(rs.getDate("dayTime"));
				info.setIsChecked(rs.getInt("isChecked"));
			}
		} catch (SQLException e) 
		{
			System.out.println("Info获取失败");
			e.printStackTrace();
		}
		return info;
	}

	@Override
	public PageDiv<Info> getInfoByPd(int userId, int pageNo, int pageSize) {
		List<Info> list = new ArrayList<Info>();
		sql = "select * from info limit "+(pageNo-1)*pageSize+","+pageSize;
		sqlCount = "select count(id) from info";
		count = jt.getCount(sqlCount);
		try {
			stat = con.createStatement();
			rs = stat.executeQuery(sql);
			while(rs.next())
			{
				Info info = new Info();
				info.setId(rs.getInt("id"));
				info.setUserId(rs.getInt("userId"));
				info.setStartTime(rs.getString("startTime"));
				info.setEndTime(rs.getString("endTime"));
				info.setDayTime(rs.getDate("dayTime"));
				info.setContent(rs.getString("content"));
				info.setIsChecked(rs.getInt("isChecked"));
				list.add(info);
			}
		} catch (SQLException e) {
			System.out.println("通过DbUtil获取User分页出现异常");
			e.printStackTrace();
		}
		return new PageDiv<Info>(pageNo, pageSize, count, list);
	}
	
	@Override
	public PageDiv<Info> getInfoByPdByDay(int userId, int pageNo, int pageSize, Date day) {
		List<Info> list = new ArrayList<Info>();
		sql = "select * from info where userId = " + userId + " and dayTime = '" + day + "'" +
				"limit "+(pageNo-1)*pageSize+","+pageSize;
		sqlCount = "select count(id) from info";
		count = jt.getCount(sqlCount);
		try {
			stat = con.createStatement();
			rs = stat.executeQuery(sql);
			while(rs.next())
			{
				Info info = new Info();
				info.setId(rs.getInt("id"));
				info.setUserId(rs.getInt("userId"));
				info.setStartTime(rs.getString("startTime"));
				info.setEndTime(rs.getString("endTime"));
				info.setDayTime(rs.getDate("dayTime"));
				info.setContent(rs.getString("content"));
				info.setIsChecked(rs.getInt("isChecked"));
				list.add(info);
			}
		} catch (SQLException e) {
			System.out.println("通过DbUtil获取User分页出现异常");
			e.printStackTrace();
		}
		return new PageDiv<Info>(pageNo, pageSize, count, list);
	}
	
	@Override
	public List<Info> getAllInfo() {
		List<Info> list = new ArrayList<Info>();
		sql = "select * from info";
		try {
			stat = con.createStatement();
			rs = stat.executeQuery(sql);
			while(rs.next())
			{
				Info info = new Info();
				info.setId(rs.getInt("id"));
				info.setUserId(rs.getInt("userId"));
				info.setStartTime(rs.getString("startTime"));
				info.setEndTime(rs.getString("endTime"));
				info.setContent(rs.getString("content"));
				info.setDayTime(rs.getDate("dayTime"));
				info.setIsChecked(rs.getInt("isChecked"));
				list.add(info);
			}
		} catch (SQLException e) 
		{
			System.out.println("Info获取失败");
			e.printStackTrace();
		}
		return list;
	}
}
