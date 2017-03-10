package com.xh.ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.xh.bean.User;
import com.xh.tool.GetService;
/**
 * 修改用户信息
 * @author XH
 *
 */
public class UpdateUserFrame extends JFrame implements GetService{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Box box = Box.createVerticalBox();
	private Box boxName = Box.createHorizontalBox();
	private Box boxPwd = Box.createHorizontalBox();
	private Box boxRights = Box.createHorizontalBox();
	private Box boxBut = Box.createHorizontalBox();
	
	private JLabel jlName = new JLabel("用户名");
	private JLabel jlPwd = new JLabel("密码");
	private JLabel jlRights = new JLabel("权限");
	private JLabel jlInfo;				//提示信息
	private JButton jbUpd = new JButton("修改");
	
	private JTextField jtfName = new JTextField();
	private JTextField jtfPwd = new JTextField();
	private JTextField jtfRights = new JTextField();
	
	private String username;		//接收用户的用户名信息
	
	public UpdateUserFrame(String title, String username)
	{
		super(title);
		this.username = username;
		initFrame();
		this.setVisible(true);
	}
	
	public void initFrame()
	{
		User user = asi.getUser(username);
		jtfName.setText(username);
		jtfPwd.setText(user.getPassword());
		jtfRights.setText(String.valueOf(user.getRights()));
		jlInfo = new JLabel("1为管理员，0为一般用户");
		jlInfo.setForeground(Color.RED);
		boxName.add(Box.createHorizontalStrut(20));
		boxName.add(jlName);
		boxName.add(Box.createHorizontalStrut(30));
		boxName.add(jtfName);
		boxName.add(Box.createHorizontalStrut(20));
		
		boxPwd.add(Box.createHorizontalStrut(20));
		boxPwd.add(jlPwd);
		boxPwd.add(Box.createHorizontalStrut(43));
		boxPwd.add(jtfPwd);
		boxPwd.add(Box.createHorizontalStrut(20));
		
		boxRights.add(Box.createHorizontalStrut(20));
		boxRights.add(jlRights);
		boxRights.add(Box.createHorizontalStrut(43));
		boxRights.add(jtfRights);
		boxRights.add(Box.createHorizontalStrut(20));
		
		boxBut.add(Box.createHorizontalStrut(150));
		boxBut.add(jbUpd);
		
		box.add(Box.createVerticalStrut(40));
		box.add(boxName);
		box.add(Box.createVerticalStrut(20));
		box.add(boxPwd);
		box.add(Box.createVerticalStrut(20));
		box.add(boxRights);
		box.add(Box.createVerticalStrut(10));
		box.add(jlInfo);
		box.add(Box.createVerticalStrut(20));
		box.add(boxBut);
		box.add(Box.createVerticalStrut(40));
		this.add(box);
		this.setSize(300, 300);
		this.setLocation(400, 200);
		this.setResizable(false);
		this.setDefaultCloseOperation(UpdateUserFrame.DISPOSE_ON_CLOSE);
		jbUpd.addActionListener(new ActionListener() {	
			//为修改按钮添加时间监听器
			@Override
			public void actionPerformed(ActionEvent e) {
				String username = jtfName.getText();
				String pwd = jtfPwd.getText();
				int rights = Integer.valueOf(jtfRights.getText());
				User user = new User(username, pwd, rights);
				boolean flag = asi.updateUser(user);
				if(flag)
				{
					UpdateUserFrame.this.dispose();
					new ShowUserFrame("显示用户信息界面", 1, 5);
				}	
			}
		});
	}
}
