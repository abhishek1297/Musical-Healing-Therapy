package core;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.Mixer;

import AudioOutput.WavLoader;

public class Player
{
	public volatile Clipper left;
	public volatile Clipper right;
	private static Mixer.Info[] info = AudioSystem.getMixerInfo();
	private static Mixer mixer;
	private int sec;
	private float volume;
	//SourceDataLine line= null;
	private Thread tr1,tr2;
	public FloatControl vol;
	public Player(int leftFrequency,int rightFrequency,int seconds,float vol)
	{
		sec = seconds;
		mixer = AudioSystem.getMixer(info[1]);
		left = new Clipper(seconds,leftFrequency);
		right = new Clipper(seconds,rightFrequency);
		volume = vol;
	}
	
/*	void createSDL() throws Exception
	{
		DataLine.Info sdli = new DataLine.Info(SourceDataLine.class, left.format);
		line = (SourceDataLine) AudioSystem.getLine(sdli);
		line.open(left.format);
		System.out.println("Success!");
	}
	
	void playSDL() 
	{
		line.start();
		int nByW = 0;
		int nBytesRead = 0;
		int i = 1;
		
		while(nBytesRead != -1)
		{
			try
			{
				if(i%2 == 0)
				{
					nBytesRead = left.sampleStream.read(left.sample.buffer, 0, left.sample.buffer.length);
					if(nBytesRead >= 0)
					{
						nByW = line.write(left.sample.buffer, 0, nBytesRead);
					}	
				}
				else
				{
					nBytesRead = right.sampleStream.read(right.sample.buffer, 0, right.sample.buffer.length);
					if(nBytesRead >= 0)
					{
						nByW = line.write(right.sample.buffer, 0, nBytesRead);
					}
				}
				++i;
			}
			catch(Exception e)
			{
				System.out.print("In Write SDL\n");
				e.printStackTrace();
			}
		}
	}
	void des()
	{
		line.flush();
		line.close();
	}
*/
	public void create() throws Exception
	{
		left.createClip(mixer,volume);
		right.createClip(mixer,volume);
	}

	public void play() throws Exception
	{
		tr1 = new Thread()
		{
			public void run()
			{
				left.play();
			}
		};

		tr2 = new Thread()
		{
			public void run()
			{
				right.play();
				
			}
		};
		System.out.println(java.util.Calendar.getInstance().getTime());
		tr1.start();
		tr2.start();
		try
		{
			Thread.sleep(sec * 1000);	
		}
		catch(InterruptedException e)
		{
			e.printStackTrace();
		}
		tr1.interrupt();
		tr2.interrupt();
		WavLoader.stop();
		System.out.println("DONE");
		System.out.println(java.util.Calendar.getInstance().getTime());
	}

	public void des()
	{
		System.out.println("Inside player");
		tr1.interrupt();
		tr2.interrupt();
		left.sTop();
		right.sTop();
	}
	
	public boolean isActive()
	{
		try
		{
			if(left.clip.isActive() || right.clip.isActive())
				return true;
		}
		catch(Exception e)
		{
			System.out.println("here");
		}
		return false;
	}
	
	public void setVolume(float v)
	{
		left.vol.setValue(v);
		right.vol.setValue(v);
	}
	
	public static void main(String[] args) throws Exception
	{
		Player pl = new Player((int)105.0f,(int)95.0f,10,-10.0f);
		pl.create();
		pl.play();
	}
}	