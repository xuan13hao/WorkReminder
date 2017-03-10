package com.xh.bean;

import java.io.Serializable;
import java.sql.Date;

/**
 * 用户所对应的工作安排基类
 * @author XH
 *
 */
public class Info implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;				//唯一标示id
	
	private int userId;			//所对应的用户
	
	private String startTime;	//安排开始时间
	
	private String endTime;		//安排结束时间
	
	private String content;		//安排具体内容
	
	private Date dayTime;		//安排工作日期
	
	private int isChecked;		//是否完成审核，该字段的作用用于对所安排的工作的一种确认
								//如果当前的任务项完成则记录为1，否则记录为0

	public Info()
	{
		
	}
	
	
	public Info(int userId, String startTime,
			String endTime, String content,
			Date dayTime, int isChecked) {
		super();
		this.userId = userId;
		this.startTime = startTime;
		this.endTime = endTime;
		this.content = content;
		this.dayTime = dayTime;
		this.isChecked = isChecked;
	}
	
	public Info(int id, int userId, String startTime, String endTime,
			String content, Date dayTime, int isChecked) {
		super();
		this.id = id;
		this.userId = userId;
		this.startTime = startTime;
		this.endTime = endTime;
		this.content = content;
		this.dayTime = dayTime;
		this.isChecked = isChecked;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDayTime() {
		return dayTime;
	}

	public void setDayTime(Date dayTime) {
		this.dayTime = dayTime;
	}

	public int getIsChecked() {
		return isChecked;
	}

	public void setIsChecked(int isChecked) {
		this.isChecked = isChecked;
	}
}
