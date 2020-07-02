package core;

import java.io.ByteArrayInputStream;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.Mixer;

public class Clipper
{
	private SignalGenerator sample = null;
	public FloatControl vol;
	public Clip clip = null;
	private AudioInputStream sampleStream = null;
	private AudioFormat format = new AudioFormat(44100,8,2,true,false);
	private DataLine.Info info = new DataLine.Info(Clip.class,format);

	public Clipper(int time, float frequency)
	{
		sample = new SignalGenerator(time,frequency);
		sampleStream = new AudioInputStream( (new ByteArrayInputStream(sample.buffer)), format ,sample.buffer.length);
	}

	public void createClip(Mixer mix,float v) throws Exception
	{
		clip = (Clip) mix.getLine(info);
		clip.open(sampleStream);
		vol = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		vol.setValue(v);
		System.out.println("clip created and opened");
	}

	public Clip getClip()
	{
		return clip;
	}

	public void play()
	{
		clip.start();
	}
	
	public void sTop()
	{
		clip.flush();
		clip.close();
	}
}