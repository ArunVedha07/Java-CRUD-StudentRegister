package LogIn;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


import Common.AllComponent;
import Common.CommonFunction;
import Common.Database;
import Common.ValidationProcess;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;
public class StudentRegister extends JFrame implements ActionListener {
	private static  final long serialVersionUID=1L;

JLabel stu,namelbl,pwlbl,genderlbl,doblbl,deg_lb,dep_lbl,address_lbl,pin_lbl,mail_lbl,cont_lbl,image_lbl,s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11;
JTextField name_field,n3,n4,n5;JPasswordField n2;
JRadioButton male_r,female_r;
JTextArea a1;JButton b1,b2,b3;
JComboBox<String> c1,c2;
UtilDateModel model=new UtilDateModel();
JDatePanelImpl datePanel=new JDatePanelImpl(model);
JDatePickerImpl datePicker;
java.sql.Date selectedDate=null;
boolean emailFlag=false,contactFlag=false,datePickerFlag=false,pinFlag=false;
ValidationProcess vp=new ValidationProcess();
File uploadFile;
CommonFunction cf=new CommonFunction();
public StudentRegister()
{
AllComponent ac=new AllComponent();
JFrame p=new JFrame("login page");


 stu=ac.createLabel("Student Register",30,30,300,30,Color.MAGENTA,Font.BOLD,25);
 namelbl=ac.createLabel("Name",475,75,100,30,Color.BLACK,Font.BOLD,17);
 pwlbl=ac.createLabel("Password",475,115,100,30,Color.BLACK,Font.BOLD,17);
 genderlbl=ac.createLabel("Gender",475,155,100,30,Color.BLACK,Font.BOLD,17);
 doblbl=ac.createLabel("Date Of Birth",475,190,100,30,Color.BLACK,Font.BOLD,17);
 deg_lb=ac.createLabel("Degree",475,235,100,30,Color.BLACK,Font.BOLD,17);
dep_lbl=ac.createLabel("Department",475,275,100,30,Color.BLACK,Font.BOLD,17);
 address_lbl=ac.createLabel("Address",475,315,100,30,Color.BLACK,Font.BOLD,17);
 pin_lbl=ac.createLabel("Pincode",475,410,100,30,Color.BLACK,Font.BOLD,17);
 mail_lbl=ac.createLabel("Mail",475,450,100,30,Color.BLACK,Font.BOLD,17);
 cont_lbl=ac.createLabel("Contact",475,490,100,30,Color.BLACK,Font.BOLD,17);
 image_lbl=ac.createLabel("Image",475,530,100,30,Color.BLACK,Font.BOLD,17);

 s1=ac.createLabel("*",585,75,10,15,Color.RED,Font.BOLD,17);
 s2=ac.createLabel("*",585,115,10,15,Color.RED,Font.BOLD,17);
 s3=ac.createLabel("*",585,155,10,15,Color.RED,Font.BOLD,17);
 s4=ac.createLabel("*",585,190,10,15,Color.RED,Font.BOLD,17);
 s5=ac.createLabel("*",585,235,10,15,Color.RED,Font.BOLD,17);
 s6=ac.createLabel("*",585,275,10,15,Color.RED,Font.BOLD,17);
 s7=ac.createLabel("*",585,315,10,15,Color.RED,Font.BOLD,17);
 s8=ac.createLabel("*",585,410,10,15,Color.RED,Font.BOLD,17);
 s9=ac.createLabel("*",585,450,10,15,Color.RED,Font.BOLD,17);
 s10=ac.createLabel("*",585,490,10,15,Color.RED,Font.BOLD,17);
 s11=ac.createLabel("*",585,530,10,15,Color.RED,Font.BOLD,17);


 name_field=ac.createTextField("",625, 75, 200,30,Color.white,Font.PLAIN,17);
 n2=ac.createPasswordField("",625, 115, 200,30,Color.white,Font.PLAIN,17);
 n3=ac.createTextField("",625,410,200,30,Color.white,Font.PLAIN,17);
 n4=ac.createTextField("",625, 450,200,30,Color.white,Font.PLAIN,17);
 n5=ac.createTextField("",625, 490,200,30,Color.white,Font.PLAIN,17);




 male_r=ac.createRadioButton("Male",625,160,65,20);
 female_r=ac.createRadioButton("Female",690,155,80,30);
ButtonGroup Bg=new ButtonGroup();
Bg.add(male_r);
Bg.add(female_r);

 a1=ac.createTextArea("",625,315,200,85,Color.white,Font.PLAIN,17);



 b1=ac.createButton("Browse",Color.GRAY,625,530,100,30,Color.WHITE,Font.BOLD,17);
 b2=ac.createButton("Register",Color.GREEN,555,600,100,30,Color.BLACK,Font.BOLD,17);
 b3=ac.createButton("Cancel",Color.RED,725,600,100,30,Color.WHITE,Font.BOLD,17);

b1.setActionCommand("Browse");
b2.setActionCommand("Register");
b3.setActionCommand("Cancel");

b1.addActionListener(this);
b2.addActionListener(this);
b3.addActionListener(this);


 c1=ac.createComboBox(625,235,200,30,Color.BLACK,Font.BOLD,17);
try {
	String degSql="select degreeId,degreeName from m_degree";
	c1.addItem("Select Degree");
	Connection con=Database.getConnection();
	PreparedStatement psDeg=con.prepareStatement(degSql);
ResultSet rsDeg=psDeg.executeQuery();
while(rsDeg.next()) {
	c1.addItem(rsDeg.getString("degreeId")+" - "+rsDeg.getString("degreeName"));
}
con.close();
}
catch(Exception degSqlException) {
	
}



 c2=ac.createComboBox(625,275,200,30,Color.BLACK,Font.BOLD,17);

c1.addActionListener(new ActionListener() {

	@Override
	public void actionPerformed(ActionEvent e) {
	String degree=c1.getSelectedItem().toString();
	String[] degreeArray=degree.split("-");
	int len=c2.getItemCount();
	if(len!=0) {c2.removeAllItems();}
	try {
		String degSql="select departmentId,deparmentName from m_department where degreeId=?";
c2.addItem("select Department");
Connection cn=Database.getConnection();
PreparedStatement pst=cn.prepareStatement(degSql);
pst.setString(1, degreeArray[0]);
ResultSet rs=pst.executeQuery();
while(rs.next()) {
	c2.addItem(rs.getString("departmentId")+" - "+rs.getString("deparmentName"));
}
cn.close();
	}catch(Exception ee) {
		
	}
		
	}
	
});

namelbl.setHorizontalAlignment(SwingConstants.RIGHT);
pwlbl.setHorizontalAlignment(SwingConstants.RIGHT);
genderlbl.setHorizontalAlignment(SwingConstants.RIGHT);
doblbl.setHorizontalAlignment(SwingConstants.RIGHT);
deg_lb.setHorizontalAlignment(SwingConstants.RIGHT);
dep_lbl.setHorizontalAlignment(SwingConstants.RIGHT);
address_lbl.setHorizontalAlignment(SwingConstants.RIGHT);
pin_lbl.setHorizontalAlignment(SwingConstants.RIGHT);
mail_lbl.setHorizontalAlignment(SwingConstants.RIGHT);
cont_lbl.setHorizontalAlignment(SwingConstants.RIGHT);
image_lbl.setHorizontalAlignment(SwingConstants.RIGHT);


datePicker=ac.createDatePicker(datePanel,"",625,190,200,30,Color.white,Font.PLAIN,17);






p.add(stu);
p.add(namelbl);
p.add(pwlbl);
p.add(genderlbl);
p.add(doblbl);
p.add(deg_lb);
p.add(dep_lbl);
p.add(address_lbl);
p.add(pin_lbl);
p.add(mail_lbl);
p.add(cont_lbl);
p.add(image_lbl);
p.add(name_field);
p.add(n2);
p.add(n3);
p.add(n4);
p.add(n5);
p.add(male_r);
p.add(female_r);
p.add(a1);
p.add(b1);
p.add(b2);
p.add(b3);
p.add(c1);
p.add(c2);
p.add(s1);
p.add(s2);
p.add(s3);
p.add(s4);
p.add(s5);
p.add(s6);
p.add(s7);
p.add(s8);
p.add(s9);
p.add(s10);
p.add(s11);
p.add(datePicker);
p.setSize(1920,1080);
p.setLayout(null);
p.setVisible(true);

}






public static void main(String[] args) {
new StudentRegister();
}






@Override
public void actionPerformed(ActionEvent e) {
	String cmd=e.getActionCommand();
	if(!n3.getText().isEmpty()) {
		pinFlag=vp.onlySixDigitNumber(n3.getText());
	
	}if(!n4.getText().isEmpty()) {
		emailFlag=vp.mailValidation(n4.getText());
	}
	if(!n5.getText().isEmpty()) {
		contactFlag=vp.onlyTenDigitNumber(n5.getText());
		}

	if(model.isSelected()) {
		java.util.Date datePickerValue=(Date) datePicker.getModel().getValue();
		 selectedDate=new java.sql.Date(datePickerValue.getTime());
		 datePickerFlag=vp.isLessThan(18, selectedDate);
		 
	}
	
	if (cmd.equals("Browse")) {
		uploadFile=cf.fileBrowseButton();
	}
		else if (cmd.equals("Register")){
			if(name_field.getText().isEmpty()) {
				JOptionPane.showMessageDialog(this,"Please Enter Name");
			}
			else  if(n2.getText().isEmpty()) {
				JOptionPane.showMessageDialog(this, "Please Enter Password" );
			}
			else  if((male_r.isSelected()== false) && (female_r.isSelected()== false)) {
				JOptionPane.showMessageDialog(this, "Please Select Gender");
			}
			else  if(model.isSelected()==false) {
				JOptionPane.showMessageDialog(this, "Please Enter DOB");
			}
			else  if(datePickerFlag) {
				JOptionPane.showMessageDialog(this, "You're  not 18 Years Old");
			}
			else  if(c1.getSelectedIndex()<1) {
				JOptionPane.showMessageDialog(this, "Please select Degree");
			}
			else  if(c2.getSelectedIndex()<1) {
				JOptionPane.showMessageDialog(this, "Please select Department");
			}
			else  if(a1.getText().isEmpty()) {
				JOptionPane.showMessageDialog(this, "Please Enter Address");
			}
			else  if(n3.getText().isEmpty()) {
				JOptionPane.showMessageDialog(this, "Please Enter Pincode");
			}else  if(!pinFlag) {
				JOptionPane.showMessageDialog(this, "Invalid PinCode");
			}
			else  if(n4.getText().isEmpty()) {
				JOptionPane.showMessageDialog(this, "Please Enter Mail");
			}
			else  if(!emailFlag) {
				JOptionPane.showMessageDialog(this, "Invalid Mail Address");
			}
			else  if(n5.getText().isEmpty()) {
				JOptionPane.showMessageDialog(this, "Please Enter Contact Number");
			}else  if(!contactFlag) {
				JOptionPane.showMessageDialog(this, "Invalid  Contact Number");
			}
			else {
				try {
		
					String insertSQL="insert into t_student(studentName,studentId,password,gender,dob,degree,department,address,pincode,mail,contact,image,createdBy) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
					String getIdSql="select id from t_student";
					Connection z=Database.getConnection();
					String userId=cf.userIdCreation("STU",getIdSql,z);
					String md5Password=cf.generateMd5(n2.getText());
					PreparedStatement ps=z.prepareStatement(insertSQL);
					int gender=0;
					if(male_r.isSelected()) {
						gender=1;
					}
					else  if(female_r.isSelected()) {
						gender=2;
					}
					FileInputStream fin=new FileInputStream(uploadFile);
					ps.setBinaryStream(12, fin,fin.available());
					String degree=c1.getSelectedItem().toString();
					String[] degreeArray=degree.split("-");
					String dept=c2.getSelectedItem().toString();
					String[] deptArray=dept.split("-");
					ps.setString(1, name_field.getText());
					ps.setString(2, userId);
					ps.setString(3,md5Password );
					ps.setInt(4, gender);
					ps.setDate(5, selectedDate);
					ps.setString(6, degreeArray[0]);
					ps.setString(7, deptArray[0]);
					ps.setString(8, a1.getText());
					ps.setString(9, n3.getText());
					ps.setString(10, n4.getText());
					ps.setString(11, n5.getText());
					ps.setString(13, name_field.getText());
					int result=ps.executeUpdate();
					if(result==1) {
						System.out.println("Registration SuccessFully");
						Logoo reg=new Logoo();
						reg.setVisible(true);
			            reg.setVisible(false);
			            reg.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					}else {System.out.println("Registration Failed");}
					
				}
				catch(Exception insertException) {insertException.printStackTrace();}
			}
		}
	}
}

