package loginsystem;

import java.sql.*;

public class Forgot extends Connect
{
	public Forgot() throws SQLException, ClassNotFoundException
	{
		super();
	}
	
	public String[] getQAndA(String email) throws Exception
	{
		//String query = "select question,answer from sys_user where email=\'"+email+"\'";
		String query = "select question,answer from sysuser where email=\'"+email+"\'";
		Statement ss = connect.createStatement();
		ResultSet rs = ss.executeQuery(query);
		String array[] = new String[2];
		while(rs.next())
		{
			array[0] = rs.getString(1);
			array[1] = rs.getString(2);
		}
		return array;
	}
	
	public boolean validateUser(String email) throws SQLException
	{
		//String query = "select * from sys_user where email=\'"+email+"\'";
		String query = "select * from sysuser where email=\'"+email+"\'";
		Statement ss = connect.createStatement();
		ResultSet rs = ss.executeQuery(query);
		if(rs != null)
			return true;
		return false;
	}
	
	public boolean setNewPassword(String email,String pwd) throws SQLException 
	{
		pwd = h.encrypt(pwd);
		System.out.println("here");
		//String query = "update sys_user set password = \'"+pwd+"\' where email = \'"+email+"\';";
		String query = "update sysuser set password = \'"+pwd+"\' where email = \'"+email+"\';";
		Statement ss = connect.createStatement();
		int num = ss.executeUpdate(query);
		System.out.println("here");
		if(num > 0)
		{
			return true;
		}
		return false;
	}
	
}