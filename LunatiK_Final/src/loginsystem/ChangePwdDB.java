package loginsystem;
import java.sql.*;

public class ChangePwdDB extends Connect
{
	public ChangePwdDB() throws SQLException, ClassNotFoundException
	{
		super();
	}
	
	public boolean changePassword(String email,String pwd) throws Exception
	{
		pwd = h.encrypt(pwd);
		//String query = "update sys_user set password=\'"+pwd+"\' where email=\'"+email+"\'";
		String query = "update sysuser set password=\'"+pwd+"\' where email=\'"+email+"\'";
		Statement ss = connect.createStatement();
		int num = ss.executeUpdate(query);
		if(num > 0)
			return true;
		return false;
	}
}