<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page  import="loginsystem.*" %>
<html>
	<head>
		<link rel=stylesheet type=text/css href="../css/forgotCss.css">
	</head>
	<body>
		<div class=header>
			<a href="Home.jsp" style="text-decoration : none;"><p id=main_head>Lunati<span id=s1 style="color:#DE2323;">k</span></p></a>
		</div>
		<div class=forgot-form>
			<p id=forg_head>Change Password</p>
		<form id="forg" name = "pwdForm" onsubmit ="return passwordValidateForm()" action ="ChangePass.jsp" method ="post">
		  <input type="text" name="email" value=<%= session.getAttribute("email") %> placeholder="Email"/><br>
		  <input type="password" name="pass" placeholder="New Password"/><br>
          <input type="password" name="cpass" placeholder="Confirm Password"/><br>
		  <input id=changebutt name="changeclick" type=submit value="Submit"/>
		</form>
	</div>
	<script src="../js/Warnings.js"></script>
	</body>
</html>
<%
	if(request.getParameterMap().containsKey("changeclick"))
	{
		ChangePwdDB obj = new ChangePwdDB();
		String email = request.getParameter("email");
		String pwd = request.getParameter("pass");
		boolean done = obj.changePassword(email,pwd);
		if(done)
		{
			%>
				<p id="msg">Password Changed Successfully!!</p>
			<%
		}
		else
		{
			%>
			<p id="msg"><%=done %>Error!,Please Retry!</p>
			<%
		}
	}
%>
