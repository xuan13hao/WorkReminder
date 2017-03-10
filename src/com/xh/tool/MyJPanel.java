package com.xh.tool;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class MyJPanel extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MyJPanel()
	{
		super();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		BufferedImage bi = null;
		try{
				bi = ImageIO.read(MyJButton.class.getResource("/com/xh/res/login_bottom.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		g.drawImage(bi, 0, 0, null);
	}
}
