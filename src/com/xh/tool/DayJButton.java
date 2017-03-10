package com.xh.tool;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;

public class DayJButton extends JButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private boolean flag;		//用来标示当前日期时候有安排信息
	private int days;			//要显示的日期数
	/**
	 * 该构造方法用来完成日期的绘制，即每一天用一个按钮来表示
	 * @param flag 判断当前日期中是否有安排信息
	 * @param i	   要显示的日期数
	 */
	public DayJButton(boolean flag, int days)
	{
		this.flag = flag;	
		this.days = days;
	}
	
	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		BufferedImage bi = null;
		try {
			if(flag)
				bi = ImageIO.read(MyJButton.class.getResource("/com/xh/res/bt_show.png"));
			else
				bi = ImageIO.read(MyJButton.class.getResource("/com/xh/res/bt_entry.png"));
			} catch (IOException e) {
				System.out.println("DayJButton获取图片出现异常");
				e.printStackTrace();
			}
		g.drawImage(bi, 0, 0, null);
		g.drawString(String.valueOf(days), 40, 40);
	}
}
