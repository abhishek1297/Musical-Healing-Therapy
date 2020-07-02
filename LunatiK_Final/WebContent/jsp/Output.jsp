<%@page import="java.io.*,javax.sound.sampled.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<jsp:include page="../html/output.html" />
<%@page import="java.sql.*,loginsystem.*"%>
<%!
	FloatControl clipVolume;
	Mixer.Info[] info = AudioSystem.getMixerInfo();//Mixer type array for clips
	Mixer mixer = AudioSystem.getMixer(info[1]);//info used to get mixer
	File f = new File("wav");//wav directory
	//int count = 0;
	Clip clip = null;
	AudioInputStream ais = null;
	Thread t;
	OutputDB obj = null;

	void playWavFiles()
	{
		//clipVolume =  (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		try
		{
			DataLine.Info data = new DataLine.Info(Clip.class, null);//Data Line for Output
			//excluding wav files
			ais = AudioSystem.getAudioInputStream(f);
			clip = (Clip) mixer.getLine(data);
			clip.open(ais);
			//clipVolume.setValue(-10.0f);
			clip.start();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

%>
<%
	if (request.getParameterMap().containsKey("play") && obj == null)
	{
		String vol = request.getParameter("vol");
		System.out.println("VOLUME :"+vol);
		String timer = (String) request.getParameter("ch2");
		String fid = (String) request.getParameter("ch");
		if (timer == null || fid == null)
		{%>
			<script>
				alert("Please Select Required Fields!");
			</script>
		<%}
		else
		{

			obj = new OutputDB();
			obj.setFrequency(fid);
			int time = Integer.parseInt(timer);
			time *= 60;
			session.setAttribute("running","true");
			//session.setAttribute("timer", "" + time);
			//if (session.getAttribute("player") != null && session.getAttribute("timer") != null)
			playWavFiles();
			obj.play(time);
			session.removeAttribute("player");
			session.removeAttribute("timer");
			request.removeAttribute("play");
		}
	}
	else
	if(request.getParameterMap().containsKey("stop") && obj != null)
	{
			try
			{
				obj.stop();
				Thread.sleep(1000);
				obj = null;
				response.sendRedirect("GetOutput.jsp");
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
	}


%>
			<!--
				 <audio  autoplay>
					<source src="http://localhost:8080/Binaural_Beats/jsp/wav files/riff.wav" type="audio/wav">
				</audio>
					<script>
					var p = new Audio("riff.wav");
					p.autoplay = true;
					p.play();
				</script>

				<embed src="http://localhost:8080/Binaural_Beats/jsp/wav files/riff.wav" autostart="true" loop="false" hidden="false"  height="200" width="300" ></embed>
				-->