package Common;

import java.awt.*;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;

public class AllComponent {
public JLabel createLabel(String labelName,int xCoordinate,int yCoordinate,int width,int height,Color fontColor,int fontBold,int fontSize) {
	JLabel label=new JLabel(labelName); 
label.setFont(new Font("Times New Roman",fontBold,fontSize));
label.setSize(width,height);
label.setLocation(xCoordinate, yCoordinate);
label.setForeground(fontColor);
return label;
}

public JButton createButton(String ButtonName,Color bgColor,int xCoordinate,int yCoordinate,int width,int height,Color fontColor,int fontBold,int fontSize) {
	JButton button=new JButton(ButtonName);
	button.setFont(new Font("Times New Roman",fontBold,fontSize));
	button.setLocation(xCoordinate, yCoordinate);
	button.setSize(width, height);
	button.setForeground(fontColor);
	button.setBackground(bgColor);
	return button;
}
public JTextField createTextField(String FieldName,int xCoordinate,int yCoordinate,int width,int height,Color bgColor,int fontBold,int fontSize) {
	JTextField field=new JTextField(FieldName);
	field.setFont(new Font("Times New Roman",fontBold,fontSize));
	field.setLocation(xCoordinate, yCoordinate);
	field.setSize(width, height);
	field.setBackground(bgColor);
	return field;
}
public JPasswordField createPasswordField(String FieldName,int xCoordinate,int yCoordinate,int width,int height,Color bgColor,int fontBold,int fontSize) {
	JPasswordField password=new JPasswordField(FieldName);
	password.setFont(new Font("Times New Roman",fontBold,fontSize));
	password.setLocation(xCoordinate, yCoordinate);
	password.setSize(width, height);
	password.setBackground(bgColor);
	return password;
}
public JDatePickerImpl createDatePicker(JDatePanelImpl datePanel,String labelName,int xCoordinate,int yCoordinate,int width,int height,Color fontColor,int fontBold,int fontSize) 
{
	JDatePickerImpl datepicker=new JDatePickerImpl(datePanel,new DateLabelFormatter());
	datepicker.setFont(new Font("Times  New Roman",fontBold,fontSize));
	datepicker.setLocation(xCoordinate, yCoordinate);
	datepicker.setSize(width, height);
	datepicker.setBackground(fontColor);
	return datepicker;
}

public JTextArea createTextArea(String AreaName,int xCoordinate,int yCoordinate,int width,int height,Color bgColor,int fontBold,int fontSize) {
	JTextArea area=new JTextArea(AreaName);
	area.setFont(new Font("Times New Roman",fontBold,fontSize));
	area.setLocation(xCoordinate, yCoordinate);
	area.setSize(width, height);
	area.setBackground(bgColor);
	return area;
}
public JComboBox<String> createComboBox(int xCoordinate,int yCoordinate,int width,int height,Color fontColor,int fontBold,int fontSize)
{
JComboBox<String> coboBox=new JComboBox<String>();
coboBox.setFont(new Font("Times New Roman",fontBold,fontSize));
coboBox.setSize(width,height);
coboBox.setLocation(xCoordinate,yCoordinate);
coboBox.setForeground(fontColor);
return coboBox;
}
public JRadioButton createRadioButton(String fieldname,int xCoordinate,int yCoordinate,int width,int height)
{
JRadioButton RadioButton=new JRadioButton(fieldname);
RadioButton.setLocation(xCoordinate,yCoordinate);
RadioButton.setSize(width,height);
return RadioButton;
}


}

