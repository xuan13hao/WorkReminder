package com.xh.tool;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class TestJMenu {
	private JMenuBar jmb = new JMenuBar();
	private JMenu jm1 = new JMenu("menu1");
	private JMenu jm2 = new JMenu("menu2");
	private JMenuItem jmi1 = new JMenuItem("jmi11");
	private JMenuItem jmi2 = new JMenuItem("jmi1222");
	private JMenuItem jmi3 = new JMenuItem("jmi1222");
	public TestJMenu(String title)
	{
		JFrame jf = new JFrame(title);
		jf.setSize(200, 200);
		jf.setLocation(200, 200);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jm2.add(jmi1);
		jm2.add(jmi2);
		jm2.add(jmi3);
		jm2.add(jmi2);
		jm1.add(jm2);
		jmb.add(jm1);
		jf.add(jmb);
	}
	public static void main(String[] args) {
		new TestJMenu("");
	}
}
