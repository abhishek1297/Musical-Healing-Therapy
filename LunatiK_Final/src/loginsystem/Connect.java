package loginsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect
{
	//public String user = "akash";
	//public String pwd = "sky";
	//public String url = "jdbc:postgresql://localhost/zzz";
	public String user = "postgres";
	public String pwd = "abhishek";
	public String url = "jdbc:postgresql://localhost/BB_PROJECT";
	public static Connection connect;
	public static String msg;
	public static Hashing h;
	
	public Connect() throws SQLException, ClassNotFoundException
	{
		try
		{
			Class.forName("org.postgresql.Driver");
			connect = DriverManager.getConnection(url, user, pwd);
			if(connect != null)
				msg = "Connected";
			else
				msg = "not";
			System.out.println(msg);
			h = new Hashing();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String args[]) throws ClassNotFoundException, SQLException
	{
		new Connect();
	}
}