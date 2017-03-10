package com.xh.tool;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JLabel;

import com.xh.ui.GetPwdFrame;
import com.xh.ui.RegisterFrame;

/**
 * 重绘JLabel来完成超链接的实现
 * @author XH
 *
 */
public class MyLink extends JLabel implements MouseListener, GetService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean isEntered = false; 
	private String title;
	private JFrame jf;

	 public MyLink(String title)   
    { 
    	super(title); 
    	this.title = title;
        this.addMouseListener(this); 
    } 

    public MyLink(String title, JFrame jf)   
    { 
    	super(title); 
    	this.title = title;
    	this.jf = jf;
        this.addMouseListener(this); 
    } 

    protected void paintBorder(Graphics g)   
    {
    	int w = this.getSize().width; 
        int h = this.getSize().height; 
        if(isEntered) 
        	g.drawLine(0, h - 1, w - 1, h - 1); 
        //当鼠标移动上时，显示下划线
    } 

    public void mouseClicked(MouseEvent mouseEvent){ 
    	//监听鼠标点击时所完成的效果
    	if("注册账号".equals(title))
    	{
    		new RegisterFrame(title);	//进入注册页面
    		jf.dispose();				//关闭登陆页面
    	}
    	else if("忘记密码".equals(title))
    	{
    		new GetPwdFrame(title);		//进入获取密码页面
    		jf.dispose();
    	}
    } 

    public void mousePressed(MouseEvent mouseEvent) { 
    } 

    public void mouseReleased(MouseEvent mouseEvent) { 
    } 

    public void mouseEntered(MouseEvent mouseEvent) { 
    	//监听鼠标进入时所完成的效果
            isEntered = true; 
            this.repaint(); 
            MyLink.this.setCursor(new Cursor(Cursor.HAND_CURSOR));
            //设置当鼠标移动到当前组件上时显示手型
            MyLink.this.setForeground(Color.cyan);
            //设置鼠标进入时显示的颜色
    } 

    public void mouseExited(MouseEvent mouseEvent) { 
    	//监听鼠标移出时所产生的效果
            isEntered = false; 
            MyLink.this.setForeground(Color.gray);
            //设置鼠标离开时显示的颜色
            this.repaint(); 
    } 
}
