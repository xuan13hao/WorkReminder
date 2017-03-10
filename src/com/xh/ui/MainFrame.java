package com.xh.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;

import com.xh.bean.Info;
import com.xh.bean.User;
import com.xh.tool.DayJButton;
import com.xh.tool.DesktopTool;
import com.xh.tool.GetService;
/**
 * 主界面的实现
 * @author XH
 *
 */
public class MainFrame extends JFrame implements GetService {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*区分MenuBar Menu MenuItem和JMenuBar JMenu JMenuItem之间的区别*/
	private MenuBar mb = new MenuBar();			//菜单栏
	private Menu m1 = new Menu("系统");			//系统菜单
	private Menu m2 = new Menu("编辑");			//编辑菜单
	private Menu m3 = new Menu("查看");			//查看菜单
	private Menu m4 = new Menu("工具");			//工具菜单
	private Menu m5 = new Menu("帮助");			//帮助菜单
	private MenuItem mi11 = new MenuItem("新建");//系统菜单新建菜单项
	private MenuItem mi12 = new MenuItem("删除");//系统菜单删除菜单项
	private MenuItem mi13 = new MenuItem("修改");//系统菜单修改菜单项
	private MenuItem mi14 = new MenuItem("保存");//系统菜单保存菜单项
	private MenuItem mi15 = new MenuItem("退出");//系统菜单退出菜单项
	private MenuItem mi21 = new MenuItem("撤销");//编辑菜单撤销菜单项
	private MenuItem mi22 = new MenuItem("剪切");//编辑菜单剪切菜单项
	private MenuItem mi23 = new MenuItem("复制");//编辑菜单复制菜单项
	private MenuItem mi24 = new MenuItem("粘贴");//编辑菜单粘贴菜单项
	private MenuItem mi25 = new MenuItem("全选");//编辑菜单全选菜单项
	private MenuItem mi26 = new MenuItem("反选");//编辑菜单反选菜单项
	
	private JToolBar jtb = new JToolBar();		//工具栏
	private Action atShow;						//查看工具
	private Action atEntry;						//录入工具
	private Action atUpd;						//修改工具
	private Action atCheck;						//审核工具
	
	private Box boxLeft = Box.createVerticalBox();
	private JLabel jlPlan = new JLabel("今日工作安排");
												//分屏左侧显示内容
	
	private JSplitPane jsp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,true);
	 											//横向分屏模板
	private JTabbedPane jtp = new JTabbedPane(JTabbedPane.TOP);
												//置顶选项卡		
	private ResourceBundle res = ResourceBundle.getBundle("days");
												//读取配置文件
	
	private Toolkit tk;							//获取工具包
	private Dimension screenSize;	 			//屏幕大小
	private Image image;						//图片对象
	
	private User user;
	private int num;
	public MainFrame(String title, User user, int num)
	{
		super(title);
		this.user = user;			//记录当前登录的用户
		this.num = num;				//设置当前选项卡选中的模块
		initMenu();					//初始化菜单
		initTool();					//初始化工具栏
		initMain();					//初始化主界面
		initFrame();				//初始化窗口
		DesktopTool.setUser(user);
		DesktopTool.setDesktop();	//设置托盘显示
	}
	
	/**
	 * 初始化菜单，并增加各菜单项的事件监听
	 */
	private void initMenu()
	{
		m1.add(mi11);
		m1.add(mi12);
		m1.add(mi13);
		m1.addSeparator();		//分割线
		m1.add(mi14);
		m1.addSeparator();
		m1.add(mi15);	
		m2.add(mi21);
		m2.addSeparator();
		m2.add(mi22);
		m2.add(mi23);
		m2.add(mi24);
		m2.add(m1);
		m2.addSeparator();
		m2.add(mi25);
		m2.add(mi26);
		mb.add(m1);
		mb.add(m2);
		mb.add(m3);
		mb.add(m4);
		mb.add(m5);
		this.setMenuBar(mb);
		mi21.setShortcut(new MenuShortcut(KeyEvent.VK_Z));
		mi22.setShortcut(new MenuShortcut(KeyEvent.VK_X));
		mi23.setShortcut(new MenuShortcut(KeyEvent.VK_C));
		mi24.setShortcut(new MenuShortcut(KeyEvent.VK_V));
		mi25.setShortcut(new MenuShortcut(KeyEvent.VK_A));
		//设置快捷键
		mi11.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("新建");
			}
		});
		mi15.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				MainFrame.this.dispose();
			}
		});
		mi21.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("撤销");
			}
		});
		mi22.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("剪切");
			}
		});
	}
	
	/**
	 * 初始化工具栏
	 */
	@SuppressWarnings("serial")
	public void initTool()
	{
		atShow = new AbstractAction("查看", new ImageIcon("src/com/xh/res/tool_show.png")) {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainFrame.this.dispose();
				new CheckInfoFrame("审核工作安排", new Date(Calendar.getInstance().getTimeInMillis()), user, jtp.getSelectedIndex());
			}
		};
		atEntry = new AbstractAction("录入", new ImageIcon("src/com/xh/res/tool_entry.png")) {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainFrame.this.dispose();
				new EntryInfoFrame("录入工作安排", new Date(Calendar.getInstance().getTimeInMillis()), user, jtp.getSelectedIndex());
			}
		};
		atUpd = new AbstractAction("修改", new ImageIcon("src/com/xh/res/tool_upd.png")) {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("修改");
			}
		};
		atCheck = new AbstractAction("审核", new ImageIcon("src/com/xh/res/tool_check.png")) {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainFrame.this.dispose();
				new CheckInfoFrame("审核工作安排", new Date(Calendar.getInstance().getTimeInMillis()), user, jtp.getSelectedIndex());
			}
		};
		jtb.setFloatable(false);	//设置工具栏不可浮动
		jtb.add(atShow);
		jtb.addSeparator();
		jtb.add(atEntry);
		jtb.addSeparator();
		jtb.add(atUpd);
		jtb.addSeparator();
		jtb.add(atCheck);
		this.add(jtb, "North");
	}
	
	/**
	 * 初始化主窗口，
	 * 其中包括分屏模板，通过选项卡的方式来显示操作界面
	 */
	public void initMain()
	{
		jlPlan.setForeground(Color.GREEN);
		jlPlan.setFont(new Font("宋体", Font.BOLD, 20));
		boxLeft.add(Box.createVerticalStrut(10));
		boxLeft.add(jlPlan);
		boxLeft.add(Box.createVerticalStrut(10));
		List<Info> list = new ArrayList<Info>();
		list = usi.getInfoByPdByDay(user.getUsername(), 1, 10, new Date(new java.util.Date().getTime())).getList();
		for(Info info:list)
		{
			String showInfo = 
					"  ■  " + info.getStartTime() +
					"  ~  " + info.getEndTime() + 
					"    " + info.getContent() + "    ";
			//构造要显示的工作安排字符串形式
			JLabel jlInfo = new JLabel(showInfo);
			jlInfo.setForeground(Color.RED);
			jlInfo.setFont(new Font("宋体", Font.ITALIC, 15));
			boxLeft.add(jlInfo);
			boxLeft.add(Box.createVerticalStrut(5));
		}
		for(int i=1; i<=12; i++)
		{							
			JPanel jp = new JPanel();
			jp.setLayout(null);
			int days = Integer.valueOf(res.getString("month" + i));
											//通过配置文件来获取当前月份的天数
			for(int j=0; j<days; j++)
			{
				Date date = Date.valueOf("2012-"+i+"-"+(j+1));//构造当前日期
				JButton jb = new DayJButton(usi.hasInfo(date, user), j+1);
				jb.setBounds(80*(j%7)+100, 80*(j/7)+100, 80, 80);
				//设置按钮显示的位置
				jp.add(jb);
				jb.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						int months = jtp.getSelectedIndex()+1;
						DayJButton mb = (DayJButton)e.getSource();
						Date curDate = Date.valueOf("2012-"+months+"-"+mb.getDays());
						if(mb.isFlag())
							new CheckInfoFrame("审核工作安排", curDate, user, jtp.getSelectedIndex());
						else
							new EntryInfoFrame("录入工作安排", curDate, user, jtp.getSelectedIndex());
						MainFrame.this.dispose();
					}
				});
			}											//动态构建JPanel
			JScrollPane jslp = new JScrollPane(jp);		//利用JPanel来构建JSCrollPane
			jtp.add(getMonth(i), jslp);					//将各个JScrollPane添加到选项卡中
		}		
		jtp.setSelectedIndex(num);			//设置选项卡显示的页面
		jsp.setLeftComponent(boxLeft);		//左侧显示当天工作安排具体信息
		jsp.setRightComponent(jtp);
		jsp.setOneTouchExpandable(true);
	}
	
	/**
	 * 通过数字来返回对应月份的字符串表示
	 * @param i	要显示的月份
	 * @return	String数据
	 */
	public String getMonth(int i)
	{
		String re = null;
		switch(i)
		{
		case 1: re = "壹月"; break;
		case 2: re = "贰月"; break;
		case 3: re = "叁月"; break;
		case 4: re = "肆月"; break;
		case 5: re = "伍月"; break;
		case 6: re = "陆月"; break;
		case 7: re = "柒月"; break;
		case 8: re = "捌月"; break;
		case 9: re = "玖月"; break;
		case 10: re = "拾月"; break;
		case 11: re = "拾壹月"; break;
		case 12: re = "拾贰月"; break;
		}
		return re;
	}
	
	/**
	 * 初始化窗口
	 */
	private void initFrame()
	{
		tk = Toolkit.getDefaultToolkit();
		screenSize = tk.getScreenSize();
		this.setSize(screenSize);
		image = tk.getImage(RegisterFrame.class.getResource("/com/xh/res/icon.jpg"));
		this.setIconImage(image);
		this.add(jsp, "Center");
		this.setVisible(true);
		this.setDefaultCloseOperation(MainFrame.DISPOSE_ON_CLOSE);
	}
}
