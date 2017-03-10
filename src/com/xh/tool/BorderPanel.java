package com.xh.tool;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 * 重写JPanel来进行一个边框的实现
 * @author XH
 *
 */
public class BorderPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public BorderPanel()
	{
		super();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		int width = this.getSize().width;
		int height = this.getSize().height;
		g.setFont(new Font("宋体", Font.BOLD, 30));
		g.drawString("各用户信息", 100, 50);
		g.setColor(Color.GRAY);
		g.drawRect(10, 10, width - 20, height - 20);
		g.drawLine(10, 70, width - 10, 70);
	}
}
