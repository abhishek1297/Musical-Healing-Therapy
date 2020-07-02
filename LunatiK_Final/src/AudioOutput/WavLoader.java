package AudioOutput;

import java.io.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.UnsupportedAudioFileException;

public class WavLoader
{
	public static File f = null;
	public static Clip clip = null;
	public static AudioInputStream ais = null;
	private static final String delta = "1.wav";
	private static final String theta = "2.wav";
	private static final String alpha ="3.wav";
	private static final String beta ="4.wav";
	//private static final String dir = "/home/akash/eclipse-workspace/LunatiK_Final/src/loginsystem/wav/";
	private static final String dir = "E:\\HELL\\Java Server Pages\\LunatiK_Final\\src\\loginsystem\\wav\\";
	private static String path = null;
	public static FloatControl vol;
	private static Thread t1;
	public static void setWavPath(String fid)
	{
		if(fid.startsWith("4"))
			path = delta;
		else
		if(fid.startsWith("5"))
			path = theta;
		else
		if(fid.startsWith("6"))
			path = alpha;
		else
		if(fid.startsWith("7"))
			path = beta;
		if(path != null)	
			f = new File(dir,path);
	}

	public static void loadClip() throws UnsupportedAudioFileException, IOException, LineUnavailableException
	{
		Mixer.Info[] info = AudioSystem.getMixerInfo();
		Mixer mixer = AudioSystem.getMixer(info[1]);
		if(f.exists())
		{
			DataLine.Info data = new DataLine.Info(Clip.class,null);
			ais = AudioSystem.getAudioInputStream(f);
			clip = (Clip) mixer.getLine(data);
			clip.open(ais);
			vol = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			vol.setValue(-7.0f);
			System.out.println("Clip opened");
		}
		System.out.println("Clip Loaded");
	}
		
	public static void getClipInfo() throws UnsupportedAudioFileException, IOException
	{
		ais = AudioSystem.getAudioInputStream(f);
		System.out.println(ais.getFormat());
	}
	public static void play(int time)
	{
		t1 = new Thread()
		{
			public void run()
			{
				clip.start();
				try
				{
					Thread.sleep(time*1000);
				}
				catch(InterruptedException e)
				{
					e.printStackTrace();
				}

			}
		};
		t1.start();
	}
	public static void main(String args[]) throws Exception
	{
		//f = new File("/home/akash/eclipse-workspace/LunatiK_Final/src/loginsystem/wav/1.wav");
		setWavPath("4");
		loadClip();
		play(10);
	}
	public static void stop()
	{
		//if(!clip.isActive())
		//	return;
		t1.interrupt();
		clip.flush();
		clip.close();
	}
}