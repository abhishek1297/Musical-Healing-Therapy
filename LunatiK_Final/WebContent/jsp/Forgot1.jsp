
<html>
	<head>
		<link rel=stylesheet type=text/css href="../css/forgotCss.css">
	</head>
	<body onload="askEmail('Enter Your E-mail : ')">
	<div class=header>
		<a href="Login.jsp" style="text-decoration : none;"><p id=main_head>Lunati<span id=s1 style="color:#DE2323;">k</span></p></a>
		</div>
		<div class=forgot-form>
		<form name = "pwdForm" action ="Forgot1.jsp" method ="post" onsubmit ="return passwordValidateForm()">
		<input id="fogemail" type="text" name="email" placeholder="Email" />
		<br>
		<input id=forgbutt name="forgclick" type=submit value="Submit"/>
		<!-- <input id="myemail" type="hidden" name="hiddenmail" /> -->
		</form>
	</div>
	<script src="../js/Warnings.js"></script>
	<script>

	</script>
	</body>
</html>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page  import="java.lang.*,java.sql.*,loginsystem.*" %>


<%
	if(request.getParameterMap().containsKey("forgclick"))
	{
		String email = (String) request.getParameter("email");
		Forgot obj = new Forgot();
		String array[] = new String[2];
		String question = "",answer = "";
		boolean valid = false;
		if(!email.equals(""))
			 valid = obj.validateUser(email);
		if(valid)
		{
			array = obj.getQAndA(email);
			session.setAttribute("femail",email);
			session.setAttribute("question",array[0]);
			session.setAttribute("answer",array[1]);
			question = array[0];
			answer = array[1];
			response.sendRedirect("Forgot2.jsp");
		}
		else
		if(!email.equals(""))
		{
		%>
			<script>
				let yes = confirm("Invalid Email,Would You Like To Sign Up??");
				if(yes)
					changeLocation("signup");
				else
					changeLocation("login");
			</script>
		<%}
	}
%>