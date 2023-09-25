package LogIn;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;

import Common.AllComponent;
import Common.Database;

public class ListScreen extends JFrame implements ActionListener{
private static final long serialVersionUID=1L;
private JTable table;
private JLabel searchlbl,lbhead;
private JTextField searchText;
Color  color;

public String studentID,UserName,mail,dob,contact,gender;
public int id,delFlag;
private TableRowSorter<DefaultTableModel> sorter;

AllComponent ac=new AllComponent();



public String getStudentID() {
	return studentID;
}
public void setStudentID(String studentID) {
	this.studentID = studentID;
}
public String getUserName() {
	return UserName;
}
public void setUserName(String userName) {
	UserName = userName;
}
public String getMail() {
	return mail;
}
public void setMail(String mail) {
	this.mail = mail;
}
public String getDob() {
	return dob;
}
public void setDob(String dob) {
	this.dob = dob;
}
public String getContact() {
	return contact;
}
public void setContact(String contact) {
	this.contact = contact;
}
public String getGender() {
	return gender;
}
public void setGender(String gender) {
	this.gender = gender;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getDelFlag() {
	return delFlag;
}
public void setDelFlag(int delFlag) {
	this.delFlag = delFlag;
}
public void search(String str) {
	if(str.length()==0) {
		sorter.setRowFilter(null);
	}else {
		sorter.setRowFilter(RowFilter.regexFilter(str));
	}
}
public ListScreen (int id,String studentId,String UserName,String gender,String dob,String mail,String contact,int del) {
	this.id=id;
	this.studentID=studentId;
	this.UserName=UserName;
	this.mail=mail;
	this.dob=dob;
	this.contact=contact;
	this.gender=gender;
	this.delFlag=del;
			
}
public static void main(String[] args)throws Exception{
	new ListScreen();
}
public ListScreen()throws Exception{
	initialize();
}
private void initialize()throws Exception{
	setVisible(true);
	setBounds(0,0,1380,735);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	getContentPane().setLayout(null);
	String use="use";
	String notUse="NotUse";
	String view="View";
DefaultTableModel model=new DefaultTableModel();
String[] columnNames= {"","S.No","Student ID","Name","Gender","DOB","Mail","Contact No","",""};
model.setColumnIdentifiers(columnNames);
int sno=1;
Object[] rowData=new Object[10];
ArrayList<ListScreen> getUsersData=getUsers();
for(int i=0;i<getUsersData.size();i++) {
	rowData[1]=sno;
	rowData[2]=getUsersData.get(i).getStudentID();
	rowData[3]=getUsersData.get(i).getUserName();
    rowData[4]=getUsersData.get(i).getGender();
	rowData[5]=getUsersData.get(i).getDob();
	rowData[6]=getUsersData.get(i).getMail();
	rowData[7]=getUsersData.get(i).getContact();
	if(getUsersData.get(i).getDelFlag()==0) 
	{rowData[8]=notUse;}
	else {rowData[8]=use;}
	rowData[9]=view;
	model.addRow(rowData);
	sno++;
}
sorter =new TableRowSorter<>(model);
table=new JTable(model);
table.setRowSorter(sorter);
table.setShowVerticalLines(true);

JScrollPane scrollpane=new JScrollPane(table);
scrollpane.setBounds(200,150,873,180);
getContentPane().add(scrollpane);
table.setDefaultEditor(Object.class, null);
table.getColumnModel().getColumn(0).setMinWidth(0);
table.getColumnModel().getColumn(0).setMaxWidth(0);
table.getColumnModel().getColumn(0).setWidth(0);
table.setCellSelectionEnabled(true);

JTableHeader header=table.getTableHeader();
header.setBackground(color.YELLOW);

DefaultTableCellRenderer tcr=new DefaultTableCellRenderer();
DefaultTableCellRenderer tcr1=new DefaultTableCellRenderer();

tcr.setHorizontalAlignment(JLabel.CENTER);
tcr1.setHorizontalAlignment(JLabel.CENTER);
tcr1.setForeground(color.BLUE);

table.getColumnModel().getColumn(1).setCellRenderer(tcr);
table.getColumnModel().getColumn(2).setCellRenderer(tcr);
table.getColumnModel().getColumn(3).setCellRenderer(tcr);
table.getColumnModel().getColumn(4).setCellRenderer(tcr);
table.getColumnModel().getColumn(5).setCellRenderer(tcr);
table.getColumnModel().getColumn(6).setCellRenderer(tcr);
table.getColumnModel().getColumn(7).setCellRenderer(tcr);
table.getColumnModel().getColumn(8).setCellRenderer(tcr1);
table.getColumnModel().getColumn(9).setCellRenderer(tcr1);

table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
table.getColumnModel().getColumn(1).setPreferredWidth(50);
table.getColumnModel().getColumn(2).setPreferredWidth(80);
table.getColumnModel().getColumn(3).setPreferredWidth(200);
table.getColumnModel().getColumn(4).setPreferredWidth(50);
table.getColumnModel().getColumn(5).setPreferredWidth(80);
table.getColumnModel().getColumn(6).setPreferredWidth(200);
table.getColumnModel().getColumn(7).setPreferredWidth(90);
table.getColumnModel().getColumn(8).setPreferredWidth(60);
table.getColumnModel().getColumn(9).setPreferredWidth(60);

lbhead=ac.createLabel("List Screen",20,20,200,30,Color.BLACK,Font.BOLD,25);
searchlbl=ac.createLabel("Search", 850, 100, 150, 30,Color.BLACK, Font.BOLD,20);
searchText=ac.createTextField("", 920, 100, 150, 30, Color.WHITE, Font.PLAIN, 20);

searchText.getDocument().addDocumentListener(new DocumentListener() {

	@Override
	public void insertUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		search(searchText.getText());
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		search(searchText.getText());
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		search(searchText.getText());
	}
	
});

JButton register,black;
register=ac.createButton("Register", Color.GREEN, 940, 50, 130, 30, color.BLACK, Font.BOLD,20);
black=ac.createButton("Back", Color.CYAN, 200, 50, 100, 30, color.BLACK, Font.BOLD,20);
register.addActionListener(new ActionListener()
{@Override
public void actionPerformed(ActionEvent e) {
		StudentRegister r=new StudentRegister();
		setVisible(false);
		r.setVisible(true);

}
});
add(lbhead);
add(searchlbl);
add(searchText);
add(register);
add(black);




ListSelectionModel select=table.getSelectionModel();
select.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

select.addListSelectionListener(new ListSelectionListener() {

	@Override
	public void valueChanged(ListSelectionEvent listSE) {
	ListScreen frame=null;
	try {
		frame=new ListScreen();
		frame.setVisible(false);
	}catch(Exception exception) {
		exception.printStackTrace();
	}
		if(frame!=null) {
			frame.setVisible(false);
		}
		String columnName=null;
		String tableUserId=null;
		int[] row=table.getSelectedRows();
		int[] columns=table.getSelectedColumns();
		for(int i=0;i<row.length;i++) {
			for(int j=0;j<row.length;j++) {
				columnName=(String) table.getValueAt(row[i],columns[j]);
				tableUserId=(String) table.getValueAt(row[i], 2);
			}
		}
		System.out.println(columnName);
		System.out.println(tableUserId);
		if(columnName==use) {
			try {
				Connection con=Database.getConnection();
				PreparedStatement pst=con.prepareStatement("UPDATE t_student  set delFlg=0 where studentId=?");
				pst.setString(1, tableUserId);
				int b=pst.executeUpdate();
				if(b==1) {
					setVisible(false);
					frame.setVisible(true);
					setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				}
			}
			catch(Exception e) {
				System.out.println(e);
			}
		}
		
			if(columnName==notUse) {
				try {
					Connection con=Database.getConnection();
					PreparedStatement pst=con.prepareStatement("UPDATE t_student  set delFlg=1 where studentId=?");
					pst.setString(1, tableUserId);
					int b=pst.executeUpdate();
					if(b==1) {
						setVisible(false);
						frame.setVisible(true);
						setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					}
				}
				catch(Exception e1) {
					System.out.println(e1);}
		}
		
	}
	});
}
static ArrayList<ListScreen> getUsers()  throws Exception{
	ArrayList<ListScreen> users=new ArrayList<ListScreen>();
	ListScreen u;
	try {
		Connection con=Database.getConnection();
		Statement statement=con.createStatement();
		String QUERY="select id,studentId,studentName,gender,dob,mail,contact,delflg from t_student order by studentId desc";
		ResultSet rs=statement.executeQuery(QUERY);
		while (rs.next()) {
			String genderStr;
			if(rs.getInt("gender")==1) 
			{	genderStr="M";}
			else {genderStr="F";}
			u=new ListScreen(
					rs.getInt("Id"),
					rs.getString("studentId"),
					rs.getString("studentName"),
					genderStr,
					rs.getString("dob"),
					rs.getString("mail"),
					rs.getString("contact"),
					rs.getInt("delFlg"));
		users.add(u);}
	}
	catch(SQLException ex) {System.out.println(ex);}
	return users;
}
@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	
}
}
