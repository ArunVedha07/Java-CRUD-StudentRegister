package Screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Common.AllComponent;
import Common.CommonFunction;
import Common.Database;
import Common.ValidationProcess;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class EditScreen extends JFrame implements ActionListener{
		private static final long serialVersionUID = 1L;
		JLabel stu,idlbl,namel,genderl,dobl,maill,contactl,iddb;
		JLabel req1,req2,req3,req4,req5,req6;
		JTextField namef,mailf,contactf;
		JRadioButton male_r,female_r;
		JButton update_btn,cancel_btn;
		UtilDateModel model=new UtilDateModel();
		JDatePanelImpl datePanel=new JDatePanelImpl(model);
		JDatePickerImpl datePicker;
		java.sql.Date selectedDate=null;
		boolean emailFlag=false,contactFlag=false,datePickerFlag=false;
		ValidationProcess vp=new ValidationProcess();
		CommonFunction cf=new CommonFunction();
		String dbid;
		public EditScreen(String userId) {
			System.out.println(userId);
			AllComponent ac=new AllComponent();
			setTitle("Student update");
			stu=ac.CreateLabel("Student Edit", 30, 30, 300, 30, Color.MAGENTA,Font.BOLD , 25);
			idlbl=ac.CreateLabel("StudentID", 475, 85, 150, 30, Color.BLACK, Font.BOLD, 17);
			iddb=ac.CreateLabel("",  655, 85, 200,30, Color.BLACK, Font.BOLD, 17);
			namel=ac.CreateLabel("Name", 475, 135, 150, 30, Color.BLACK,Font.BOLD , 17);
			genderl=ac.CreateLabel("Gender", 475, 185, 150, 30, Color.BLACK,Font.BOLD , 17);
			dobl=ac.CreateLabel("DOB",  475, 235, 150, 30, Color.BLACK,Font.BOLD , 17);
			maill=ac.CreateLabel("Email",  475, 285, 150, 30, Color.BLACK,Font.BOLD , 17);
			contactl=ac.CreateLabel("Contact",  475, 335, 150, 30, Color.BLACK,Font.BOLD , 17);
			req1=ac.CreateLabel(": ", 630, 93, 10, 15, Color.BLACK, Font.BOLD, 17);
			req2=ac.CreateLabel("*", 630, 135, 10, 15, Color.RED, Font.BOLD, 17);
			req3=ac.CreateLabel("*", 630, 185, 10, 15, Color.RED, Font.BOLD, 17);
			req4=ac.CreateLabel("*", 630, 235, 10, 15, Color.RED, Font.BOLD, 17);
			req5=ac.CreateLabel("*", 630, 285, 10, 15, Color.RED, Font.BOLD, 17);
			req6=ac.CreateLabel("*", 630, 335, 10, 15, Color.RED, Font.BOLD, 17);
			namef=ac.CreateTextField( 655, 135, 200,30, Color.WHITE, Font.PLAIN,17);
			mailf=ac.CreateTextField( 655, 285, 200,30, Color.WHITE, Font.PLAIN,17);
			contactf=ac.CreateTextField( 655,335, 200,30, Color.WHITE, Font.PLAIN,17);
			male_r=ac.CreateRadioButton("Male",655,  190, 65, 20);
			female_r=ac.CreateRadioButton("FeMale", 715, 185,80, 30);
			ButtonGroup bg=new ButtonGroup();
			bg.add(male_r);
			bg.add(female_r);
			update_btn=ac.CreateButton("Update",Color.GREEN,555, 470, 100, 30, Color.BLACK,Font.BOLD , 17);
			cancel_btn=ac.CreateButton("Cancel",Color.RED,725, 470, 100, 30, Color.WHITE,Font.BOLD , 17);
			update_btn.setActionCommand("Update");
			cancel_btn.setActionCommand("Cancel");
			update_btn.addActionListener(this);
			cancel_btn.addActionListener(this);
			idlbl.setHorizontalAlignment(SwingConstants.RIGHT);
			namel.setHorizontalAlignment(SwingConstants.RIGHT);
			genderl.setHorizontalAlignment(SwingConstants.RIGHT);
			dobl.setHorizontalAlignment(SwingConstants.RIGHT);
			maill.setHorizontalAlignment(SwingConstants.RIGHT);
			contactl.setHorizontalAlignment(SwingConstants.RIGHT);
			datePicker=ac.CreateDatePicker(datePanel, 655, 235, 200, 30, Color.WHITE, Font.PLAIN, 17);
			add(stu);
			add(idlbl);
			add(iddb);
			add(namel);
			add(genderl);
			add(dobl);
			add(maill);
			add(contactl);
			add(namef);
			add(mailf);
			add(contactf);
			add(datePicker);
			add(update_btn);
			add(cancel_btn);
			add(req1);
			add(req2);
			add(req3);
			add(req4);
			add(req5);
			add(req6);
			add(male_r);
			add(female_r);
			setSize(1920,1080);
			setLayout(null);
			setVisible(true);
			try {
				Connection con=Database.getConnection();
				String query="select studentId,StudentName,gender,dob,mail,contact from t_student where studentId=?";
				PreparedStatement ps=con.prepareStatement(query);
				ps.setString(1,userId);
				ResultSet rs=ps.executeQuery();
				while(rs.next()) {
					if(rs.getInt("gender")==1) {
						male_r.setSelected(true);
					}
					else {
						female_r.setSelected(true);
					}
					String datePicker=rs.getString("dob");
					String[] d=datePicker.split("-");
					int year=Integer.parseInt(d[0]);
					int month=Integer.parseInt(d[1]);
					int day=Integer.parseInt(d[2]);
					model.setDate(year, month+1, day);
					model.setSelected(true);
					dbid=rs.getString("studentId");
					iddb.setText(rs.getString("studentId"));
					namef.setText(rs.getString("StudentName"));
					mailf.setText(rs.getString("mail"));
					contactf.setText(rs.getString("contact"));
					
				}
				rs.close();
				ps.close();
			}
			catch(Exception e) { }
			
		}
		
		public static void main(String[] args) {
			new EditScreen("?");
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			String cmd=e.getActionCommand();
			if(!mailf.getText().isEmpty()) {
				emailFlag=vp.mailValidation(mailf.getText());
			}
			if(!contactf.getText().isEmpty()) {
				contactFlag=vp.onlyTenDigitNumber(contactf.getText());
			}
			if(model.isSelected()) {
				java.util.Date datePickerValue=(Date)datePicker.getModel().getValue();
				 selectedDate=new java.sql.Date(datePickerValue.getTime());
				datePickerFlag=vp.isLessThan(18, selectedDate);
			}
			if(e.getSource()==cancel_btn) {
				SingleView sv=new SingleView(dbid);
				sv.setVisible(true);
				setVisible(false);
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
			else if(cmd.equals("Update")) {
				if(namef.getText().isEmpty()) {
					JOptionPane.showMessageDialog(this, "Please Enter Name");
				}
				else if((male_r.isSelected()==false)&&(female_r.isSelected()==false)) {
					JOptionPane.showMessageDialog(this, "Please Select Gender");
				}
				else if(model.isSelected()==false) {
					JOptionPane.showMessageDialog(this, "Please Select DOB");
				}
				else if(datePickerFlag) {
					JOptionPane.showMessageDialog(this, "You're Not 18 Years Old");
				}
				else if(mailf.getText().isEmpty()) {
					JOptionPane.showMessageDialog(this, "Please Enter Mail");
				}
				else if(!emailFlag) {
					JOptionPane.showMessageDialog(this, "Invalid Mail");
				}
				else if(contactf.getText().isEmpty()) {
					JOptionPane.showMessageDialog(this, "Please Enter Contact");
				}
				else if(!contactFlag) {
					JOptionPane.showMessageDialog(this, "Invalid Contact");
				}
				else {
					try {
						String updateSql="update t_student set studentName=?,gender=?,dob=?,mail=?,contact=? where studentId=?";
						Connection z=Database.getConnection();
						String getIdSql="select id from t_student";
						PreparedStatement ps=z.prepareStatement(updateSql);
						int gender=0;
						if(male_r.isSelected()) {
							gender=1;
						}
						if(female_r.isSelected()) {
							gender=2;
						}
						ps.setString(1, namef.getText());
						ps.setInt(2, gender);
						ps.setDate(3, selectedDate);
						ps.setString(4, mailf.getText());
						ps.setString(5, contactf.getText());
						ps.setString(6, dbid);
						int result=ps.executeUpdate();
						if(result==1) {
							
							JOptionPane.showMessageDialog(this, "Updated Successfully");
							ListScreen reg=new ListScreen();
							setVisible(false);
							setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
							reg.setVisible(true);
						}
						else {
							System.out.println("Update Failed");
						}
					}
					catch(Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		}
}

