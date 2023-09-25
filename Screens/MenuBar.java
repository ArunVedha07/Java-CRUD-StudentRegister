package LogIn;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;

import Common.AllComponent;

public class MenuBar extends JFrame implements ActionListener{
private static final long serialversionUID=1L;
public JTextField hiddenIdM;

public MenuBar() {
	AllComponent ac=new AllComponent();
	setTitle("Menu Screen");
	setSize(1500,1000);
	setVisible(true);
	setLocation(0,0);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setLayout(null);
	
	JMenuBar menubar=new JMenuBar();
	JMenu mAdmin=new JMenu("Student");
	JMenu mTeach=new JMenu("Test");
	JMenu mProfile=new JMenu("Profile");
	
	JMenuItem miProfile=new JMenuItem("Profile");
	JMenuItem miChange=new JMenuItem("Change");
    JMenuItem miList=new JMenuItem("List");    
    hiddenIdM=ac.createTextField("", 25, 80, 180, 30, Color.BLACK, Font.PLAIN, 15);

    mProfile.add(miProfile);
    mProfile.add(miChange);
    mAdmin.add(miList);
	
    menubar.add(mAdmin);
    menubar.add(mTeach);
    menubar.add(mProfile);
    setJMenuBar(menubar); add(hiddenIdM);

    miProfile.addActionListener(new ActionListener() {
    	@Override
public void actionPerformed(ActionEvent avt) {
    		if(avt.getSource()==miProfile) {
    			setVisible(false);
	
}
    }
    });
    miChange.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent avt) {
    		if(avt.getSource()==miChange) {
    			setVisible(false);
}
    }
    });
    miList.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent avt) {
    		if(avt.getSource()==miList) {
    			setVisible(false);
	
}
    }
    });
}
public static void main(String[] args) {
	MenuBar p=new MenuBar();
}
@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	
}
}
 


