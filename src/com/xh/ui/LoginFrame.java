package com.xh.ui;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.xh.bean.User;
import com.xh.tool.GetService;
import com.xh.tool.MyJButton;
import com.xh.tool.MyJPanel;
import com.xh.tool.MyLink;
/**
 * 系统登录界面的实现
 * @author XH
 *
 */
public class LoginFrame extends JFrame implements GetService {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Box box = Box.createVerticalBox();			//整体容器利用纵向排列
	private Box boxTitle = Box.createHorizontalBox();	//完成界面的标题显示
	private Box boxTop = Box.createHorizontalBox();		//完成界面的宣传图片显示
	private Box boxCenter = Box.createHorizontalBox();	//完成主要内容显示
	private Box boxCenImg = Box.createVerticalBox();	//完成用户头像显示
	private Box boxCenInput = Box.createVerticalBox();	//完成用户输入信息的显示
	private Box boxCenName = Box.createHorizontalBox();	//完成用户名信息的显示
	private Box boxCenPwd = Box.createHorizontalBox();	//完成密码框信息的显示
	private Box boxCh = Box.createHorizontalBox();		//完成复选框内容的显示
	private Box boxBut = Box.createHorizontalBox();		//完成按钮的显示

	private JLabel jlTitle;				//存放图标图像
	private JLabel jlTop;				//存放宣传图像
	private JLabel jlImg;				//存放人物头像
	private JLabel jlName;				//存放用户名
	private JLabel jlPwd;				//存放密码
	private MyLink mlRegist;			//注册账号
	private MyLink mlGet;				//忘记密码
	
	private JTextField jtfName;			//用户名文本框
	private JPasswordField jpfPwd;	 	//密码文本框
	
	private MyJButton jbMin;			//最小化按钮
	private MyJButton jbClose;			//关闭按钮
	private Choice chMethod;			//登陆方法，后台还是一般用户
	private JCheckBox jcbMake;			//记住密码
	private JCheckBox jcbAuto;			//自动登陆
	private JButton jbLogin;			//登陆按钮
	private JButton jbCancel;			//取消按钮
	
	private MyJPanel mpBottom;			//底层按钮存放区域
	
	private Image image;				//图片对象
	
	private User user = new User();		//用户对象
	public LoginFrame(String title)
	{
		super(title);
		initFrame();
		this.setVisible(true);
	}
	
	public void initFrame()
	{
		this.setUndecorated(true);		//设置窗体不加修饰
		this.setSize(350, 266);			//设置窗体的大小
		this.setLocation(200, 200);		//设置窗体的显示位置
		jlTitle = new JLabel(new ImageIcon("src/com/xh/res/icon_title.jpg"));
		jlTop = new JLabel(new ImageIcon("src/com/xh/res/title.jpg"));
		jbMin = new MyJButton(0);		
		jbClose = new MyJButton(1);		//构建登陆界面上的最小化以及关闭操作按钮
		
		jlImg = new JLabel(new ImageIcon("src/com/xh/img/res_img.png"));
		
		jlName = new JLabel("用户名");
		jlPwd = new JLabel("密码");
		jtfName = new JTextField();
		jpfPwd = new JPasswordField();
		jpfPwd.setEchoChar('●');
		mlRegist = new MyLink("注册账号", LoginFrame.this);
		mlGet = new MyLink("忘记密码", LoginFrame.this);
		mlRegist.setForeground(Color.gray);
		mlGet.setForeground(Color.gray);	//设置超链接默认的颜色为灰色
		chMethod = new Choice();
		chMethod.add("一般用户");
		chMethod.add("管理员");
		jcbMake = new JCheckBox("记住密码");
		jcbAuto = new JCheckBox("自动登录");
		mpBottom = new MyJPanel();
		jbLogin = new JButton("登陆");
		jbCancel = new JButton("取消");
		
		boxTitle.add(jlTitle);
		boxTitle.add(jbMin);
		boxTitle.add(jbClose);
		boxTop.add(jlTop);
		
		boxCenImg.add(Box.createVerticalStrut(20));
		boxCenImg.add(jlImg);
		
		boxCenName.add(jlName);
		boxCenName.add(Box.createHorizontalStrut(6));
		boxCenName.add(jtfName);
		boxCenName.add(Box.createHorizontalStrut(6));
		boxCenName.add(mlRegist);
		boxCenName.add(Box.createHorizontalStrut(6));
		boxCenPwd.add(jlPwd);
		boxCenPwd.add(Box.createHorizontalStrut(18));
		boxCenPwd.add(jpfPwd);
		boxCenPwd.add(Box.createHorizontalStrut(6));
		boxCenPwd.add(mlGet);
		boxCenPwd.add(Box.createHorizontalStrut(6));
		
		boxCenInput.add(Box.createVerticalStrut(10));
		boxCenInput.add(boxCenName);
		boxCenInput.add(Box.createVerticalStrut(10));
		boxCenInput.add(boxCenPwd);
		boxCenInput.add(Box.createVerticalStrut(10));
		
		boxCenter.add(Box.createHorizontalStrut(40));
		boxCenter.add(boxCenImg);
		boxCenter.add(Box.createHorizontalStrut(20));
		boxCenter.add(boxCenInput);
		
		boxCh.add(Box.createHorizontalStrut(100));
		boxCh.add(chMethod);
		boxCh.add(Box.createHorizontalStrut(6));
		boxCh.add(jcbMake);
		boxCh.add(Box.createHorizontalStrut(6));
		boxCh.add(jcbAuto);
		boxCh.add(Box.createHorizontalStrut(6));
		
		mpBottom.add(jbCancel);
		mpBottom.add(Box.createHorizontalStrut(150));
		mpBottom.add(jbLogin);
		boxBut.add(mpBottom);
		
		box.add(boxTitle);
		box.add(boxTop);
		box.add(boxCenter);
		box.add(boxCh);
		box.add(Box.createVerticalStrut(2));
		box.add(boxBut);
		this.add(box);
		image = this.getToolkit().getImage(LoginFrame.class.getResource("/com/xh/res/icon.jpg"));
		this.setIconImage(image);
		addListener();			//添加事件监听器
	}
	
	/**
	 * 完成对所有的事件的监听器设计
	 */
	public void addListener()
	{
		FrameAction fa = new FrameAction();
		jbMin.addActionListener(fa);
		jbClose.addActionListener(fa);	//对窗口按钮增加事件监听器
		
		LoginAction la = new LoginAction();
		jbCancel.addActionListener(la);
		jbLogin.addActionListener(la);	//用户登陆所进行的事件监听
		jpfPwd.addActionListener(la);	//在密码文本框上完成对于回车的监听
	}
	
	class FrameAction implements ActionListener
	{//最小化以及关闭按钮的事件处理
		@Override
		public void actionPerformed(ActionEvent e) {
			String str = e.getActionCommand();
			if("0".equals(str))
				LoginFrame.this.setState(1);	//使窗口最小化
			else
				System.exit(0);					//关闭当前界面
		}	
	}
	
	class LoginAction implements ActionListener
	{//页面登陆时的事件监听及处理
		@Override
		public void actionPerformed(ActionEvent e) {
			String str = e.getActionCommand();
			if("取消".equals(str))
			{//当点击取消按钮时，将文本框的内容清空
				jtfName.setText("");
				jpfPwd.setText("");
				jtfName.requestFocus(true);
				//用户名文本框获取到光标显示
			}
			else
			{//当点击登陆按钮时，验证用户信息,或者在密码框上输入回车
				String username = jtfName.getText();
				String password = new String(jpfPwd.getPassword());
				int method = chMethod.getSelectedIndex();
				//String method = chMethod.getSelectedItem();
				boolean flag1 = jcbAuto.isSelected();
				boolean flag2 = jcbMake.isSelected();
				//获取各个组件当前选中的状态，获取其值
				int loginState = 2;	//用来记录用户登陆的状态
				boolean isAdmin = false;	//用来记录当前用户是否为管理员登陆
				//1表示登陆成功，2表示用户不存在，1表示密码错误
				user.setId(usi.getUser(username).getId());
				user.setUsername(username);
				user.setPassword(password);
				user.setRights(method);
				//封装用户对象
				if(flag1)
					System.out.println("自动登陆");
				if(flag2)
					System.out.println("记住密码");
				if(method == 1)//检测管理员登陆
				{
					loginState = asi.loginAdmin(user);
					isAdmin = true;
				}
				else//检测用户登陆
					loginState = usi.loginUser(user);
				switch(loginState){
				case 0:
					JOptionPane.showMessageDialog(LoginFrame.this, "密码错误", "登陆出错", JOptionPane.ERROR_MESSAGE);
					jtfName.setText(username);
					jpfPwd.setText("");
					jpfPwd.requestFocus(true);
					break;
				case 1:
					LoginFrame.this.dispose();
					if(isAdmin)
						new ShowUserFrame("显示用户信息", 1, 5);
					else 
						new MainFrame("工作提醒软件", user, Calendar.getInstance().get(Calendar.MONTH));
					break;
				case 2:
					JOptionPane.showMessageDialog(LoginFrame.this, "用户不存在", "登陆出错", JOptionPane.ERROR_MESSAGE);
					jtfName.setText("");
					jpfPwd.setText("");
					jtfName.requestFocus(true);
					break;
				}
			}
		}	
	}
	
	public static void main(String[] args) {
		new LoginFrame("登录界面");
	}
}