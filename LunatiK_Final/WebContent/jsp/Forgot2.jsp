<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page  import="java.sql.*,loginsystem.*" %>
<%
	String email = (String)session.getAttribute("femail");
	String msg = "";
	Forgot obj = new Forgot();
	String question = (String)session.getAttribute("question");
	String answer = (String)session.getAttribute("answer");
	boolean valid = false;
	if(question != null && answer != null)
		valid = true;
	if(request.getParameterMap().containsKey("forgclick2") && valid)
	{
		String ans = request.getParameter("ans");
		String pass = request.getParameter("pass");
		String cpass = request.getParameter("cpass");
		String str = (String)session.getAttribute("answer");
		if(ans.equals(str))
		{
			boolean done = obj.setNewPassword(email,pass);
			if(done)
				msg = "Password Changed Successfully!!";
			else
				msg = "Error!!";
		}
		else
		{
			msg = "Incorrect Answer";
		%>
			<script>document.getElementsByName("ans")[0].value = "";</script>
		<%}
	}

%>

<html>
	<head>
		<link rel=stylesheet type=text/css href="../css/forgotCss.css">
	</head>
	<body onload="askEmail('Enter Your E-mail : ')">
	<div class=header>
		<a href="Login.jsp" style="text-decoration : none;"><p id=main_head>Lunati<span id=s1 style="color:#DE2323;">k</span></p></a>
		</div>
		<div class=forgot-form>
		<form name = "pwdForm" onsubmit ="return passwordValidateForm()" action ="Forgot2.jsp" method ="post">
		<input type="password" name="pass" placeholder="New Password"/>
        <input type="password" name="cpass" placeholder="Confirm Password"/>
        <br>
        <h4 id=ques><%= session.getAttribute("question") %></h4>
        <br>
        <input type="text" name="ans" placeholder="Answer"/>
        <br>
		<input id=forgbutt name="forgclick2" type=submit value="Submit"/>
		<!-- <input id="myemail" type="hidden" name="hiddenmail" /> -->
		</form>
	</div>
	<p id="msg"><%= msg %></p>
	<script src="../js/Warnings.js"></script>
	<script>

	</script>
	</body>
</html>

