package com.xh.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.xh.bean.User;
import com.xh.tool.GetService;
import com.xh.tool.MyLink;

/**
 * 注册页面的实现
 * @author XH
 *
 */
public class RegisterFrame extends JFrame implements GetService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Toolkit tk = Toolkit.getDefaultToolkit();
	private Box box = Box.createHorizontalBox();		//整体box实现
	private Box boxImg = Box.createHorizontalBox();		//左侧图片显示
	private Box boxRight = Box.createVerticalBox();		//右侧内容显示
	private Box boxName = Box.createHorizontalBox();	//用户名
	private Box boxCoder = Box.createHorizontalBox();	//验证码
	private Box boxPwd = Box.createHorizontalBox();		//密码
	private Box boxRePwd = Box.createHorizontalBox();	//重复密码
	private Box boxBut = Box.createHorizontalBox();		//提交按钮
	
	private JLabel jlImg;								//左侧图片内容
	private JLabel jlName = new JLabel("用户名");			//用户名标签
	private JLabel jlCoder = new JLabel("验证码");		//验证码标签
	private JLabel jlPwd = new JLabel("密码");			//密码标签
	private JLabel jlRePwd = new JLabel("重复密码");		//重复密码标签
	private JTextField jtfName = new JTextField();		//用户名文本框
	private JTextField jtfCoder = new JTextField();		//验证码文本框
	private JPasswordField jpfPwd = new JPasswordField();//密码文本框
	private JPasswordField jpfRePwd = new JPasswordField();//重复密码文本框
	private MyLink mlCoder;								//获取验证码
	private JButton jbSubmit = new JButton("注册");		//注册按钮
	private JButton jbBack = new JButton("返回");		//返回按钮
	
	private Image image;								//图片对象
	private Dimension screenSize = tk.getScreenSize();  //屏幕大小
	private int width = 400;						    //窗口宽度
	private int height = 300;						    //窗口高度
	private int x = 0;
	private int y = 0;
	
	public RegisterFrame(String title)
	{
		super(title);
		initFrame();
		this.setVisible(true);
	}
	
	public void initFrame()
	{
		this.setSize(width, height);
		x = (int)(screenSize.getWidth()-width)/2;
		y = (int)(screenSize.getHeight()-height)/2;
		this.setLocation(x, y);
		jlImg = new JLabel(new ImageIcon("src/com/xh/res/register.png"));
		mlCoder = new MyLink("获取验证码");
		mlCoder.setForeground(Color.GRAY);
		jpfPwd.setEchoChar('●');
		jpfRePwd.setEchoChar('●');
		boxImg.add(jlImg);
		boxName.add(Box.createHorizontalStrut(20));
		boxName.add(jlName);
		boxName.add(Box.createHorizontalStrut(20));
		boxName.add(jtfName);
		boxName.add(Box.createHorizontalStrut(10));
		
		boxCoder.add(Box.createHorizontalStrut(20));
		boxCoder.add(jlCoder);
		boxCoder.add(Box.createHorizontalStrut(20));
		boxCoder.add(jtfCoder);
		boxCoder.add(Box.createHorizontalStrut(10));
		boxCoder.add(mlCoder);
		boxCoder.add(Box.createHorizontalStrut(10));
		
		boxPwd.add(Box.createHorizontalStrut(23));
		boxPwd.add(jlPwd);
		boxPwd.add(Box.createHorizontalStrut(30));
		boxPwd.add(jpfPwd);
		boxPwd.add(Box.createHorizontalStrut(10));
		
		boxRePwd.add(Box.createHorizontalStrut(10));
		boxRePwd.add(jlRePwd);
		boxRePwd.add(Box.createHorizontalStrut(18));
		boxRePwd.add(jpfRePwd);
		boxRePwd.add(Box.createHorizontalStrut(10));
		
		boxBut.add(jbSubmit);
		boxBut.add(Box.createHorizontalStrut(20));
		boxBut.add(jbBack);
		
		boxRight.add(Box.createVerticalStrut(40));
		boxRight.add(boxName);
		boxRight.add(Box.createVerticalStrut(20));
		boxRight.add(boxCoder);
		boxRight.add(Box.createVerticalStrut(20));
		boxRight.add(boxPwd);
		boxRight.add(Box.createVerticalStrut(20));
		boxRight.add(boxRePwd);
		boxRight.add(Box.createVerticalStrut(20));
		boxRight.add(boxBut);
		boxRight.add(Box.createVerticalStrut(40));
		
		box.add(boxImg);
		box.add(boxRight);
		this.add(box);
		this.getContentPane().setBackground(new Color(0X4781D5));
		this.setResizable(false);//设置大小不可变
		image = tk.getImage(RegisterFrame.class.getResource("/com/xh/res/icon.jpg"));
		this.setIconImage(image);
		addActionListener();
	}
	
	public void addActionListener()
	{
		RegisterAction rs = new RegisterAction();
		jbBack.addActionListener(rs);
		jbSubmit.addActionListener(rs);
		mlCoder.addMouseListener(new CoderAction());
		jpfRePwd.addActionListener(rs);
	}
	
	class RegisterAction implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			Object obj = e.getSource();
			if(jbBack.equals(obj))
			{//返回到登陆界面
				RegisterFrame.this.dispose();
				new LoginFrame("登陆界面");
			}
			else if(jbSubmit.equals(obj)||jpfRePwd.equals(obj))
			{//完成提交
				String username = jtfName.getText();
				String pwd = new String(jpfPwd.getPassword());
				String repwd = new String(jpfRePwd.getPassword());
				String coder = jtfCoder.getText();
				if(pwd.equals(repwd)&&coder.equals(usi.getCoder(username)))
				{
					User user = new User(username, pwd, 0);
					boolean flag = usi.registerUser(user);
					if(flag)
					{//添加到数据库成功
						if(usi.hasRegisted(username))
						{//更改用户注册状态成功
							JOptionPane.showMessageDialog(RegisterFrame.this, "恭喜您，注册成功！！！", "注册成功", JOptionPane.INFORMATION_MESSAGE);
							RegisterFrame.this.dispose();
							new LoginFrame("登陆界面");
						}
					}
				}
			}	
		}	
	}
	
	class CoderAction extends MouseAdapter
	{
		@Override
		public void mouseClicked(MouseEvent e) {
		//获取验证码
			String username = jtfName.getText();
			String coder = usi.getCoder(username);
			if(coder==null)
				JOptionPane.showMessageDialog(RegisterFrame.this, "对不起，您没有权限注册", "验证码", JOptionPane.ERROR_MESSAGE);
			else
				JOptionPane.showMessageDialog(RegisterFrame.this, "您此次注册的验证码是：\t" + coder, "验证码", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	public static void main(String[] args) {
		new RegisterFrame("注册");
	}
}
