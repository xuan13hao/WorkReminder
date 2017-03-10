package com.xh.tool;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import com.xh.bean.User;
import com.xh.ui.MainFrame;

/**
 * 将程序设置到任务栏中，其中参数flag代表当前登录的程度
 * 即flag=0表示正在登录，flag=1表示已登录成功，flag=-1表示离线
 * User表示当前登录用户的信息，仅当flag=1时才有用户存在
 * @author XH
 *
 */
public class DesktopTool
{
	static SystemTray st = SystemTray.getSystemTray();		//获取桌面的系统托盘类
	static TrayIcon ti;
	
	private static User user;
	public static void setUser(User user)
	{
		DesktopTool.user = user;
	}
	
	public static void setDesktop()
	{
		Image image = null;
		PopupMenu pm = new PopupMenu();					//构建动态弹出菜单
		String str = new String();						//构建提示信息
		image = Toolkit.getDefaultToolkit().getImage(DesktopTool.class.getResource("/com./xh/res/icon.jpg"));
		MenuItem miOpen = new MenuItem("打开主界面");
		MenuItem miClose = new MenuItem("退出");
	
		pm.add(miOpen);
		pm.addSeparator();		//横线
		pm.add(miClose);
		if(SystemTray.isSupported())
		{//判断当前是支持系统托盘操作
			ti = new TrayIcon(image, str, pm);		//构建系统托盘的托盘图标
			try
			{
				st.add(ti);
			} catch (AWTException e)
			{
				e.printStackTrace();
			}
		}

		miClose.addActionListener(new ActionListener()
		{//关闭当前窗口
			@Override
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
			}
		});
		
		miOpen.addActionListener(new ActionListener()
		{//打开主界面
			@Override
			public void actionPerformed(ActionEvent e)
			{
				new MainFrame("工作提醒软件", user, Calendar.getInstance().get(Calendar.MONTH));
			}
		});
	}
	
	public static void closeDesktop()
	{
		st.remove(ti);
	}
}