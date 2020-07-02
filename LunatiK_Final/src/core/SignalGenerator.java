package core;

public class SignalGenerator 
{
	private final int sample_rate = 44100;
	public byte[] buffer;
	private float frequency;
	
	public SignalGenerator(int seconds,float frequency)
	{
		this.frequency = frequency;
		buffer = new byte[(sample_rate*seconds) * 2];
		fillBuffer();
		System.out.println("Buffer Created");
	}
	
	private void fillBuffer() 
    {
		System.out.println("The Length Of Buffer is :\t"+buffer.length);
		double period = (double) sample_rate / frequency;
     	for (int i = 0; i < buffer.length; i++) 
     	{                                                            // Fill each byte of the buffer
	        double angle = 2.0 * Math.PI * i / period;
    		buffer[i] = (byte) ((Math.cos(angle) * 127f));
     	}
    }
	
	public byte[] getBuffer()
	{
		return buffer;
	}
}

