package com.xh.bean;

import java.io.Serializable;

/**
 * 该类用于获取用户注册时的验证码，即只有正确获得的验证码才能够完成注册
 * 验证码的生成是根据用户名随机产生的，但一个用户名只能够完成一次获取验证码操作
 * @author XH
 *
 */
public class Register implements Serializable
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;				//唯一标示id
	
	private String username;	//要获取验证码的用户名
	
	private String coder;		//产生的验证码
	
	private int isRegister;		//区分该用户是否已经获取过验证码

	public Register()
	{
		
	}
	
	public Register(String username, String coder, int isRegister) {
		super();
		this.username = username;
		this.coder = coder;
		this.isRegister = isRegister;
	}
	
	public Register(int id, String username, String coder, int isRegister) {
		super();
		this.id = id;
		this.username = username;
		this.coder = coder;
		this.isRegister = isRegister;
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
	public String getCoder() {
		return coder;
	}
	public void setCoder(String coder) {
		this.coder = coder;
	}
	public int getIsRegister() {
		return isRegister;
	}
	public void setIsRegister(int isRegister) {
		this.isRegister = isRegister;
	}
}
