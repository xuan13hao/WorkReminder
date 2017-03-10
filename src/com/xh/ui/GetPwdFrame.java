package com.xh.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.xh.bean.User;
import com.xh.tool.GetService;

public class GetPwdFrame extends JFrame implements GetService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Box box = Box.createVerticalBox();
	private Box boxName = Box.createHorizontalBox();
	private Box boxPwd = Box.createHorizontalBox();
	private Box boxRePwd = Box.createHorizontalBox();
	private Box boxBut = Box.createHorizontalBox();
	
	private JLabel jlName = new JLabel("用户名");
	private JLabel jlPwd = new JLabel("新密码");
	private JLabel jlRePwd = new JLabel("重复密码");
	
	private JTextField jtfName = new JTextField();
	private JPasswordField jpfPwd = new JPasswordField();
	private JPasswordField jpfRePwd = new JPasswordField();
	private JButton jbChange = new JButton("修改密码");
	
	public GetPwdFrame(String title)
	{
		super(title);
		initFrame();
		this.setVisible(true);
	}
	
	public void initFrame()
	{
		boxName.add(Box.createHorizontalStrut(20));
		boxName.add(jlName);
		boxName.add(Box.createHorizontalStrut(40));
		boxName.add(jtfName);
		boxName.add(Box.createHorizontalStrut(20));
		
		boxPwd.add(Box.createHorizontalStrut(20));
		boxPwd.add(jlPwd);
		boxPwd.add(Box.createHorizontalStrut(40));
		boxPwd.add(jpfPwd);
		boxPwd.add(Box.createHorizontalStrut(20));
		
		boxRePwd.add(Box.createHorizontalStrut(20));
		boxRePwd.add(jlRePwd);
		boxRePwd.add(Box.createHorizontalStrut(28));
		boxRePwd.add(jpfRePwd);
		boxRePwd.add(Box.createHorizontalStrut(20));
		boxBut.add(jbChange);
		
		box.add(Box.createVerticalStrut(20));
		box.add(boxName);
		box.add(Box.createVerticalStrut(10));
		box.add(boxPwd);
		box.add(Box.createVerticalStrut(10));
		box.add(boxRePwd);
		box.add(Box.createVerticalStrut(10));
		box.add(boxBut);
		box.add(Box.createVerticalStrut(20));
		
		this.add(box);
		this.setLocation(300, 300);
		this.setSize(300, 200);
		jbChange.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				String username = jtfName.getText();
				String pwd = new String(jpfPwd.getPassword());
				String repwd = new String(jpfRePwd.getPassword());
				if(pwd.equals(repwd))
				{
					User user = new User(username, pwd, 0);
					boolean flag = usi.updateUser(user);
					if(flag)
					{
						JOptionPane.showMessageDialog(GetPwdFrame.this, "更改密码成功", "更改密码", JOptionPane.INFORMATION_MESSAGE);
						GetPwdFrame.this.dispose();
						new LoginFrame("登录界面");
					}
				}
			}	
		});
	}
	
	public static void main(String[] args) {
		new GetPwdFrame("获取密码");
	}
}
