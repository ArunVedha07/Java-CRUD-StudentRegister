package Common;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationProcess {
public boolean mailValidation(String email) {
	String regex="^[\\w-\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
	boolean emailFlag=email.matches(regex);
	return emailFlag;
}
public boolean onlyTenDigitNumber(String contact) {
	Pattern pattern=Pattern.compile("^\\d{10}$");
	Matcher matcher=pattern.matcher(contact);
	boolean contactFlag=matcher.matches();
	return contactFlag;
}
public boolean onlySixDigitNumber(String PIN) {
	Pattern pattern=Pattern.compile("^\\d{6}$");
	Matcher matcher=pattern.matcher(PIN);
	boolean pinFlag=matcher.matches();
	return pinFlag;
}
public boolean isLessThan(int age,Date selectedDate) {
	String dateString=selectedDate.toString();
	Calendar calendar=GregorianCalendar.getInstance();
	calendar.set(Calendar.YEAR,calendar.get(Calendar.YEAR)-18);
	boolean datePickerFlag=calendar.getTime().before(selectedDate);
	return datePickerFlag;
}

}
