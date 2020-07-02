<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<jsp:include page="../html/signup.html" />
<%@page  import="java.sql.*,loginsystem.*" %>
<%! %>
<%
	if(request.getParameterMap().containsKey("signclick"))
	{
		Signup obj = new Signup();
		String info[] = new String[6];
		info[0] = request.getParameter("uname");
		info[1] = request.getParameter("email");
		info[2] = request.getParameter("pass");
		info[3] = request.getParameter("phone");
		info[4] = request.getParameter("ques");
		info[5] = request.getParameter("ans");
		
		if(!obj.userExists(info[1]))
		{
			if(obj.insert(info))
			{%>
				<p id="msg">Signed Up Successfully!!</p>
			<%}
		}
		else
		{%>
			<script>
				alert("E-mail is Taken!!");
				changeLocation("Signup.jsp");
			</script>
		<%}
	}
%>
