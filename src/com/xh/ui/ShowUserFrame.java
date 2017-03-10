package com.xh.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.xh.bean.User;
import com.xh.tool.GetService;

/**
 * 显示所有的用户信息界面
 * @author XH
 *
 */
public class ShowUserFrame extends JFrame implements GetService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JScrollPane jsp;
	private Box box = Box.createVerticalBox();
	private Box boxTop = Box.createHorizontalBox();
	private Box boxCenter = Box.createHorizontalBox();
	private Box boxBottom = Box.createHorizontalBox();
	private JTable jt;			//用来存放数据的Table
	private JButton jbPre = new JButton("<上一页");
	private JButton jbNext = new JButton("下一页>");
	private JButton jbAdd = new JButton("增加");
	private JButton jbDel = new JButton("删除");
	private JButton jbUpd = new JButton("修改");
	private JButton jbCan = new JButton("返回");
	private JButton jbExit = new JButton("退出");
	private JButton jbLog = new JButton("注销");
	
	private Vector<String> title = new Vector<String>();
	private Vector<Vector<String>> data = new Vector<Vector<String>>();
	private List<User> list = new ArrayList<User>();//用来存储数据
	private int count = 1;		//计数
	
	private int pageNo;
	private int pageSize;
	
	public ShowUserFrame(String title, int pageNo, int pageSize)
	{
		super(title);
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		initFrame();
		this.setVisible(true);
	}
	
	public void initFrame()
	{
		this.setSize(600, 300);
		this.setLocation(200, 200);
		this.setResizable(false);
		initData();
		jt = new JTable(data, title);
		jsp = new JScrollPane(jt);
		boxTop.add(jsp);
		
		boxCenter.add(jbPre);
		boxCenter.add(Box.createHorizontalStrut(300));
		boxCenter.add(jbNext);
		
		boxBottom.add(jbAdd);
		boxBottom.add(Box.createHorizontalStrut(20));
		boxBottom.add(jbDel);
		boxBottom.add(Box.createHorizontalStrut(20));
		boxBottom.add(jbUpd);
		boxBottom.add(Box.createHorizontalStrut(20));
		boxBottom.add(jbCan);
		boxBottom.add(Box.createHorizontalStrut(20));
		boxBottom.add(jbExit);
		boxBottom.add(Box.createHorizontalStrut(20));
		boxBottom.add(jbLog);
		
		box.add(boxTop);
		box.add(Box.createVerticalStrut(20));
		box.add(boxCenter);
		box.add(Box.createVerticalStrut(20));
		box.add(boxBottom);
		box.add(Box.createVerticalStrut(20));
		this.add(box);
		addAction();
	}
	
	public void initData()
	{
		title.add("序号");
		title.add("用户名");
		title.add("密码");
		title.add("权限");
		list = asi.showUser(1, 5).getList();
		for(User tem:list)
		{
			Vector<String> value = new Vector<String>();
			value.add(String.valueOf(count++));
			value.add(tem.getUsername());
			value.add(tem.getPassword());
			if(tem.getRights()==1)
				value.add("管理员");
			else
				value.add("一般用户");
			data.add(value);
		}
	}
	
	public void addAction()
	{
		jbAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ShowUserFrame.this.dispose();
				new AddUserFrame("添加用户信息界面");
			}
		});
		
		jbDel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				asi.deleteUser((String)jt.getValueAt(jt.getSelectedRow(), 1));
				ShowUserFrame.this.dispose();
				new ShowUserFrame("显示用户信息界面", pageNo, pageSize);
			}
		});
		
		jbUpd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ShowUserFrame.this.dispose();
				String username = (String)jt.getValueAt(jt.getSelectedRow(), 1);
				new UpdateUserFrame("修改用户信息界面", username);
			}
		});
		
		jbPre.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pageNo++;
				ShowUserFrame.this.dispose();
				new ShowUserFrame("显示用户信息界面", pageNo, pageSize);
			}
		});
		
		jbNext.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pageNo--;
				ShowUserFrame.this.dispose();
				new ShowUserFrame("显示用户信息界面", pageNo, pageSize);
			}
		});
	}
	
	public static void main(String[] args) {
		new ShowUserFrame("显示用户信息界面", 1, 5);
	}
}
