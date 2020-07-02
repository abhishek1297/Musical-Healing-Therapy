package loginsystem;

import java.sql.*;

public class FeedbackDB extends Connect
{
	public FeedbackDB() throws ClassNotFoundException, SQLException
	{
		super();
	}
	
	public boolean insert(String feedback) throws Exception
	{
		String query = "insert into feedback(data) values(\'"+feedback+"\');";
		Statement ss = connect.createStatement();
		int num = ss.executeUpdate(query);
		if(num >  0)
			return true;
		return false;
	}
}