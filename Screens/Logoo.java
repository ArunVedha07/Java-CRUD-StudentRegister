package LogIn;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Common.AllComponent;

	public  class Logoo extends JFrame implements ActionListener  {
	private static final long serialVersionUID=1L;
	JLabel adminlb1,userlb1,passwordlb1;
	JTextField userfield;
	JPasswordField passwordField;
	JButton loginBtn,resetBtn;
	JPanel loginPanel;
	public Logoo() {
		AllComponent ac=new AllComponent();
		setTitle("Login Screen");
		setSize(1500,1000);
		setVisible(true);
		setLocation(0,0);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		 loginPanel=new JPanel();
		 loginPanel.setBounds(450, 80,430, 460);
		 loginPanel.setBackground(Color.pink);
		 
		 adminlb1=ac.createLabel("Admin Login", 550, 120, 200, 40, Color.black,Font.BOLD, 30);
		 userlb1=ac.createLabel("User ID", 350, 200, 230, 40, Color.black,Font.BOLD, 20);
		 passwordlb1=ac.createLabel("Password", 350, 300, 230, 40, Color.black,Font.BOLD, 20);
		 
		 JLabel nameRequired =ac.createLabel("*", 590, 200,60 , 40, Color.RED, Font.BOLD, 25);
		 JLabel passwordRequired =ac.createLabel("*", 590, 300,60 , 40, Color.RED, Font.PLAIN, 25);
		 
		 adminlb1.setHorizontalAlignment(SwingConstants.RIGHT);
		 userlb1.setHorizontalAlignment(SwingConstants.RIGHT);
		 passwordlb1.setHorizontalAlignment(SwingConstants.RIGHT);
				 
	userfield=ac.createTextField("", 630, 200,180, 30, Color.white, Font.PLAIN, 15);
	passwordField=ac.createPasswordField("", 630,300, 180, 30, Color.WHITE, Font.PLAIN, 15);
	
	
	loginBtn=ac.createButton(" Reset", Color.GRAY, 690, 375, 110,40, Color.WHITE, Font.BOLD,15);
	resetBtn=ac.createButton(" Login", Color.BLUE, 530, 375, 110,40, Color.WHITE, Font.BOLD,15);
	
	loginBtn.setActionCommand("Login");
	resetBtn.setActionCommand("Reset");
	
	loginBtn.addActionListener(this);
	resetBtn.addActionListener(this);
	
	add(adminlb1);
	add(userlb1);
	add(passwordlb1);
	add(nameRequired);
	add(passwordRequired);
	add(userfield);
	add(passwordField);
	add(loginBtn);
	add(resetBtn);
	add(loginPanel);
	}
	public static void main(String[] args) {
		Logoo l=new Logoo();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	}

