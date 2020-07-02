<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<jsp:include page="../html/output.html" />
<%@page import="AudioOutput.*"%>
<%!	
//OutputDB obj = null;
Runtime r = null;
boolean flag = false;
%>
<%
	
	new OutputDB();
	if(request.getParameterMap().containsKey("play"))
	{
		String timer = (String) request.getParameter("ch2");
		String fid = (String) request.getParameter("ch");
		if (timer == null || fid == null)
		{%>
			<script>
				alert("Pick Your Poison!");
			</script>
		<%}
		else
		{
			%> <script> yes = true;</script> <%
			flag = true;
			WavLoader.setWavPath(fid);
			OutputDB.setFrequency(fid);
			int time = Integer.parseInt(timer);
			time *= 60;
			session.setAttribute("running","true");
			WavLoader.loadClip();
			WavLoader.play(time);
			OutputDB.play(time);
			if(!flag)
			{%>
				<script>yes = false;</script>
			<%
			flag = false;
			response.sendRedirect("GetOutput.jsp");
			}
			
			session.setAttribute("running","false");
			session.removeAttribute("player");
			session.removeAttribute("timer");
		}
	}
	else
	if(request.getParameterMap().containsKey("stop"))
	{
			flag = false;
			%><script>yes = false;</script><%
			WavLoader.stop();
			OutputDB.stop();
			session.setAttribute("running","false");
		//r.gc();
	}
	/*else
	if(request.getParameterMap().containsKey("vol"))
	{
		
		int v = Integer.parseInt(request.getParameter("vol"));
		System.out.println("vol"+v);
		float vol = 0;
		switch(v)
		{

			case 1: vol = -6.0f; break;
			case 2: vol = -5.0f; break;
			case 3: vol = -4.0f; break;
			case 4: vol = -3.0f; break;
			case 5: vol = -2.0f; break;
			case 6: vol = -1.0f; break;
		}
		OutputDB.p.setVolume(vol);
		WavLoader.vol.setValue(vol+2.0f);
	}*/
%>