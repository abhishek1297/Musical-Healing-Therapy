package loginsystem;


import java.sql.ResultSet;
import java.sql.Statement;

public class Signup extends Connect
{
	public boolean inserted = false;
	public Signup() throws Exception
	{
		super();
	}
	public boolean userExists(String email) throws Exception
	{
		//String query = "select * from sys_user where email=\'"+email+"\';";
		String query = "select * from sysuser where email=\'"+email+"\';";
		Statement ss = connect.createStatement();
		ResultSet rs = ss.executeQuery(query);
		if(rs.next())
			return true;
		return false;
	}
	public boolean insert(String info[]) throws Exception
	{
		info[2] = h.encrypt(info[2]);
		//change to sys_user
		String query = "insert into sysuser(u_name,email,password,phone,question,answer)"
		+ "values(\'"+info[0]+"\',\'"+info[1]+"\',\'"+info[2]+"\',"+info[3]+",\'"+info[4]+"\',\'"+info[5]+"\');";
		Statement ss = connect.createStatement();
		int num = ss.executeUpdate(query);
		if(num > 0)
			return true;
		return false;
	}
}