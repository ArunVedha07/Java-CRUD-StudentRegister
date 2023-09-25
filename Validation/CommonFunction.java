package Common;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFileChooser;

public class CommonFunction {
public String generateMd5(String Password) {
	MessageDigest md=null;
	try {
		md=MessageDigest.getInstance("MD5");
	}catch(NoSuchAlgorithmException e) {
		e.printStackTrace();
	}
	byte[] hashInBytes=md.digest(Password.getBytes(StandardCharsets.UTF_8));
	StringBuilder sb=new StringBuilder();
for(byte b:hashInBytes) {
	sb.append(String.format("%02x", b));
}
return sb.toString();
}
public String userIdCreation(String label,String getIdSql,Connection con) throws SQLException{
	PreparedStatement getIdPs=con.prepareStatement(getIdSql);
	int id=1;
	ResultSet getRsId=getIdPs.executeQuery();
	if(getRsId.last()) {
		id=getRsId.getInt("id")+1;}
	String userId=String.format(label+"%04d", id);
	return userId;
	}
public File fileBrowseButton() {
	File file=null;
	JFileChooser chooser=new JFileChooser();
	int result=chooser.showOpenDialog(null);
if(JFileChooser.APPROVE_OPTION==result) {
	file=chooser.getSelectedFile();
}
return file;
}

}

