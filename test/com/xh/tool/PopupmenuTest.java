package com.xh.tool;

import java.awt.event.*; 
import javax.swing.*;
 class PopupmenuTest extends MouseAdapter implements MouseListener 
{ 
JFrame f; 
JPopupMenu pm; 
public static void main(String argv[]) 
{ 
new PopupmenuTest(); 
} 
public PopupmenuTest() 
{ 
f=new JFrame("PopupmenuTest"); 
f.addMouseListener(this); 
JMenuItem popup1=new JMenuItem(); 
JMenuItem popup2=new JMenuItem(); 
pm=new JPopupMenu(); 
pm.add(popup1); 
pm.add(popup2); 
f.setSize(100,100); 
f.setVisible(true); 
} 
public void mouseReleased(MouseEvent e)
   {
    if(e.isPopupTrigger())
    {
pm.show(f,e.getX(),e.getY()); 
} 
}
}