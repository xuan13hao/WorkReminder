package com.xh.bean;

import java.io.Serializable;

/**
 * 用户信息所对应的基类
 * @author XH
 *
 */
public class User implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;				//唯一标示id
	
	private String username;	//用户名
	
	private String password;	//密码
	
	private int rights;			//用户的权限，1表示管理员，0表示一般用户
	
	public User()
	{
		
	}

	public User(String username, String password, int rights) {
		super();
		this.username = username;
		this.password = password;
		this.rights = rights;
	}

	public User(int id, String username, String password, int rights) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.rights = rights;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getRights() {
		return rights;
	}

	public void setRights(int rights) {
		this.rights = rights;
	}
}
