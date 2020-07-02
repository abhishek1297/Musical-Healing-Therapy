<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<jsp:include page="../html/feedback.html" />
<%@page  import="loginsystem.*" %>
<%
	if(request.getParameterMap().containsKey("feedclick"))
	{
		String data = (String)request.getParameter("feedback");
		new FeedbackDB().insert(data);
	}
%>