package loginsystem;

import java.util.Random;

public class Hashing 
{
	private final int key;
	byte array[] = null;
	public Hashing()
	{
		Random r = new Random();
		//key = r.nextInt(0xFF) + 0x01;
		key = r.nextInt(5) + 1;
	}
	public String encrypt(String msg)
	{
		array = msg.getBytes();
		for(int i=0 ;i<array.length ;i++)
			array[i] = (byte) (array[i] + key);
		return new String(array);
	}
	
	public String decrypt(String msg,int key)
	{
		array = msg.getBytes();
		for(int i=0 ;i<array.length ;i++)
			array[i] = (byte) (array[i] - key);
		return new String(array);
	}

}