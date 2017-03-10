package com.xh.ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.xh.bean.User;
import com.xh.tool.GetService;
/**
 * 增加用户
 * @author XH
 *
 */
public class AddUserFrame extends JFrame implements GetService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Box box = Box.createVerticalBox();
	private Box boxName = Box.createHorizontalBox();
	private Box boxPwd = Box.createHorizontalBox();
	private Box boxRePwd = Box.createHorizontalBox();
	private Box boxRights = Box.createHorizontalBox();
	private Box boxBut = Box.createHorizontalBox();
	
	private JLabel jlName = new JLabel("用户名");
	private JLabel jlPwd = new JLabel("密码");
	private JLabel jlRePwd = new JLabel("重复密码");
	private JLabel jlRights = new JLabel("权限");
	private JLabel jlInfo;				//提示信息
	private JButton jbAdd = new JButton("添加");
	
	private JTextField jtfName = new JTextField();
	private JPasswordField jpfPwd = new JPasswordField();
	private JPasswordField jpfRePwd = new JPasswordField();
	private JTextField jtfRights = new JTextField();
	
	public AddUserFrame(String title)
	{
		super(title);
		initFrame();
		this.setVisible(true);
	}
	
	public void initFrame()
	{
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
		boxPwd.add(jpfPwd);
		boxPwd.add(Box.createHorizontalStrut(20));
		
		boxRePwd.add(Box.createHorizontalStrut(20));
		boxRePwd.add(jlRePwd);
		boxRePwd.add(Box.createHorizontalStrut(17));
		boxRePwd.add(jpfRePwd);
		boxRePwd.add(Box.createHorizontalStrut(20));
		
		boxRights.add(Box.createHorizontalStrut(20));
		boxRights.add(jlRights);
		boxRights.add(Box.createHorizontalStrut(43));
		boxRights.add(jtfRights);
		boxRights.add(Box.createHorizontalStrut(20));
		
		boxBut.add(Box.createHorizontalStrut(150));
		boxBut.add(jbAdd);
		
		box.add(Box.createVerticalStrut(40));
		box.add(boxName);
		box.add(Box.createVerticalStrut(20));
		box.add(boxPwd);
		box.add(Box.createVerticalStrut(20));
		box.add(boxRePwd);
		box.add(Box.createVerticalStrut(20));
		box.add(boxRights);
		box.add(Box.createVerticalStrut(10));
		box.add(jlInfo);
		box.add(Box.createVerticalStrut(20));
		box.add(boxBut);
		box.add(Box.createVerticalStrut(40));
		this.add(box);
		this.setSize(300, 350);
		this.setLocation(400, 200);
		this.setDefaultCloseOperation(AddUserFrame.DISPOSE_ON_CLOSE);
		jbAdd.addActionListener(new ActionListener() {	
				//为添加按钮添加时间监听器
			@Override
			public void actionPerformed(ActionEvent e) {
				String username = jtfName.getText();
				String pwd = new String(jpfPwd.getPassword());
				String rePwd = new String(jpfRePwd.getPassword());
				int rights = Integer.valueOf(jtfRights.getText());
				if(pwd.equals(rePwd))
				{
					User user = new User(username, pwd, rights);
					boolean flag = asi.addUser(user);
					if(flag)
					{
						AddUserFrame.this.dispose();
						new ShowUserFrame("显示用户信息界面", 1, 5);
					}
				}
			}
		});
	}
	
	public static void main(String[] args) {
		new AddUserFrame("增加用户");
	}
}
