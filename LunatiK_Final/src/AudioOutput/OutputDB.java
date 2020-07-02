package AudioOutput;

import java.sql.*;
import core.Player;
import loginsystem.Connect;

public class OutputDB extends Connect
{
	public static Player p;
	public static float left;
	public static float right;
	public static float vol;
	private static Thread t2;
	public OutputDB() throws ClassNotFoundException, SQLException
	{
		super();
	}
	
	public static void setFrequency(String f_id) throws Exception
	{
		//String query = "select leftf,rightf,default_volume from frequency where f_id="+f_id+";";
		String query = "select left_f,right_f,default_volume from frequency where fid="+f_id+";";
		Statement ss = connect.createStatement();
		ResultSet rs = ss.executeQuery(query);
		while(rs.next())
		{
			System.out.println(rs.getFloat(1)+" and "+rs.getFloat(2));
			left = rs.getFloat(1);
			right = rs.getFloat(2);
			vol = rs.getFloat(3);
		}
	}
	
	public static void play(int seconds) throws Exception
	{
		p = new Player((int)left,(int)right,seconds,vol);
		p.create();
		t2 = new Thread()
		{
			public void run()
			{
				try
				{
					p.play();
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		};
		t2.start();
	}
	
	public static void stop()
	{
		System.out.println("STAAAAAAAAAAAAAAAAAAAAAAPHHHHHHHHHH");
		t2.interrupt();
		p.des();
	}
}