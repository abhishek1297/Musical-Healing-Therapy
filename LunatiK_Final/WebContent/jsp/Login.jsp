<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<jsp:include page="../html/login.html" />
<%@page  import="java.sql.*, loginsystem.*" %>
<%
	
	if(request.getParameterMap().containsKey("loginclick")) 
	{
		Login obj = new Login();
		String email = request.getParameter("email").toString();
		String pwd = request.getParameter("pass").toString();
		if(obj.userExists(email))
		{
			if(obj.validateUser(email,pwd))
			{
				String name = obj.getUserName(email);
				session.setAttribute("username",name);		
				session.setAttribute("email", email);
				%><script>
					window.open('Home.jsp', '_blank');
				  </script>
				<%
			}
			else
			{%>
				<script>alert("Incorrect Password!!");</script>
			<%}
		}
		else
		{%>
			<script>
				 let yes = confirm("Would You Like To Sign Up First??");
				 if(yes)
					 changeLocation("signup");
			</script> 
		<%}
			
	}
	
%>
