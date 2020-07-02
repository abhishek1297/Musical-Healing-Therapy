package loginsystem;


import java.sql.*;

public class Login extends Connect
{
	public Login() throws SQLException, ClassNotFoundException
	{
		super();
	}
	public boolean userExists(String email) throws Exception
	{
		//String query = "select * from sys_user where email=\'"+email+"\';";
		String query = "select * from sysuser where email=\'"+email+"\';";
		Statement ss = connect.createStatement();
		ResultSet rs = ss.executeQuery(query);
		if(rs == null)
			return false;
		return true;
	}
	public boolean validateUser(String email,String pwd) throws SQLException
	{
		//String getPwd = "select password from sys_user where email=\'"+email+"\';";
		String getPwd = "select password from sysuser where email=\'"+email+"\';";
		Statement ss = connect.createStatement();
		ResultSet rs = ss.executeQuery(getPwd);
		String temp = null;
		if(rs != null)
		{
			while(rs.next())
			{
				System.out.println(rs.getString(1));
				temp = rs.getString(1);
			}
			for(int i=1 ;i<=5 ;i++)
			{
				String pass =  h.decrypt(temp,i);
				System.out.println("List :" + pass);
				if(pass.equals(pwd))
					return true;
			}
		}	
		return false;
	}

	public String getUserName(String email) throws Exception
	{
		String name = null;
		//String query = "select u_name from sys_user where email=\'"+email+"\'";
		String query = "select uname from sysuser where email=\'"+email+"\'";
		Statement ss = connect.createStatement();
		ResultSet rs = ss.executeQuery(query);
		while(rs.next())
		{
			name = rs.getString(1);
		}
		return name;
	}

}