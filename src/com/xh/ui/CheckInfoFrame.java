package com.xh.ui;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.xh.bean.Info;
import com.xh.bean.User;
import com.xh.tool.DesktopTool;
import com.xh.tool.GetService;

/**
 * 审核当天工作情况，是否完成了最初的安排内容
 * @author XH
 *
 */
public class CheckInfoFrame extends JFrame implements GetService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Date date;
	private User user;
	private int num;
	private Box box = Box.createVerticalBox();
	private JButton jbBack = new JButton("返回主页");
	
	private Image image;
	
	public CheckInfoFrame(String title, Date date, 
			User user, int num)
	{
		super(title);
		this.date = date;
		this.user = user;
		this.num = num;
		initFrame();
	}
	
	private void initFrame()
	{
		List<Info> list = new ArrayList<Info>();
		list = usi.getInfoByPdByDay(user.getUsername(), 1, 5, date).getList();
		for(final Info info:list)
		{//显示该用户在这一天的所有的工作安排，并且可以进行工作安排的审核操作
			String str = "从 "+info.getStartTime()+" 点到 "+
			info.getEndTime()+" 点您要完成的工作:"+info.getContent();
			JLabel jlInfo = new JLabel(str);
			jlInfo.setFont(new Font("宋体", Font.BOLD, 15));
			final JButton jb = new JButton("审核");
			if(info.getIsChecked()==1)
			{//如果该信息已审核则显示已审核状态，并不允许继续审核操作
				jb.setText("已审核");
				jb.setEnabled(false);
			}
			JPanel jp = new JPanel();
			jp.add(jlInfo);
			jp.add(jb);
			box.add(jp);
			jb.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					boolean flag = usi.checkInfo(info.getId());
					if(flag)
					{
						jb.setText("已审核");
						jb.setEnabled(false);
					}
				}
			});
		}
		box.add(jbBack);
		jbBack.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				CheckInfoFrame.this.dispose();
				DesktopTool.closeDesktop();
				new MainFrame("工作提醒软件", user, num);
			}
		});
		this.add(box);
		image = this.getToolkit().getImage("src/com/xh/res/icon.jpg");
		this.setIconImage(image);
		this.setSize(600, 300);
		this.setLocation(200, 100);
		this.setVisible(true);
	}
}
