package Common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
static Connection conobj=null;
public static Connection getConnection() {
	try {
		Class.forName("com.mysql.jdbc.Driver");
		conobj=(Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/java","root","");}
	catch(ClassNotFoundException |SQLException e){
		e.printStackTrace();
	}
	return conobj;
}
}
