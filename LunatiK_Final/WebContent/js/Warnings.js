//Location Function
function changeLocation(str)
{
  switch(str)
  {
    case "login":window.location.assign("Login.jsp");break;

    case "signup":window.location.assign("Signup.jsp");break;

    case "home":window.location.assign("Home.jsp");break;

    case "forgot1":window.location.assign("Forgot1.jsp");break;

    case "forgot2":window.location.assign("Forgot2.jsp");break;
  }
}
//SIGN UP FORM
function signupValidateForm()
{
    var email = document.myForm.email;
    var pass  = document.myForm.pass;
    var addr  = document.myForm.addr;
    var cpass = document.myForm.cpass;
    var uname = document.myForm.uname;
    var phone = document.myForm.phone;
    var ques  = document.myForm.ques;
    var ans  = document.myForm.ans;
    var reg = "/^[_a-z0-9-]+(\.[_a-z0-9-]+)*@[a-z0-9-]+(\.[a-z0-9-]+)*(\.[a-z]{2,4})$/";

    if (email.value == "") {
        alert("Email Cannot Be Blank!");
        email.focus();
        document.myForm.email="";
        return false;
    }
    else
    if(!(reg.test(email.value)))
    {
        alert("Enter Valid E-mail ID");
        email.focus();
        document.myForm.email="";
        return false;
    }
    else
    if (pass.value == "") {
        alert("Password Cannot Be Blank!");
        pass.focus();
        document.myForm.pass="";
        return false;
    }
    else
    if (cpass.value == "") {
        alert("Confirm Password Cannot Be Blank!");
        document.myForm.cpass="";
        return false;
    }
    else
    if (pass.value.length < 8)
    {
        alert("Password Atleast 8 Characters");
        document.myForm.pass="";
        return false;
    }
    else
    if(cpass.value.length < 8)
    {
        alert("Password Atleast 8 Characters");
        document.myForm.pass="";
        return false;
    }
    else
    if (pass.value.length > 30)
    {
        alert("Password Atmost 30 Characters");
        document.myForm.pass="";
        return false;
    }
    else
    if (cpass.value.length > 30)
    {
        alert("Password Atmost 30 Characters");
        document.myForm.cpass="";
        return false;
    }
    else
    if(pass.value !== cpass.value)
    {
        alert("Passwords Should Match");
        document.myForm.pass="";
        document.myForm.cpass="";
        return false;
    }
    else
    if (uname.value == "") {
        alert("User Name Cannot Be Blank!");
        return false;
    }
    else
    if (uname.value.length > 30)
    {
        alert("Name Must Be Less Than 30 Characters");
        document.myForm.lname="";
        return false;
    }
    else
    if (ans.value == "") {
        alert("Security Answer Cannot Be Blank!");
        return false;
    }
    else
    if (ques.value == "") {
        alert("Security Question Cannot Be Blank!");
        return false;
    }
    else
    if (phone.value == "") {
        alert("Phone Number Cannot Be Blank!");
        return false;
    }
    else
    if (!(/[0-9]{10}/.test(phone.value)))
    {
      alert("Invalid Number!!");
      document.myForm.phone="";

      return false;
    }
    else
    if (addr.value == "") {
        addr.focus();
        alert("Address Cannot Be Blank!");
        return false;
    }

    return true;
}

//LOGIN FORM
function loginValidateForm() {
  var email = document.forms["login"]["email"].value;
    var pass = document.forms["login"]["pass"].value;

    if (email == "") {
        alert("Email Cannot Be Blank!");
        return false;
    }
    else
    if (pass == "") {
        alert("Password Cannot Be Blank!");
        return false;
    }
}
//FORGOT PASSWORD form
function passwordValidateForm()
{
    var pass  = document.pwdForm.pass;
    var cpass = document.pwdForm.cpass;
    var ans  = document.pwdForm.ans;
    if (pass.value == "")
    {
        alert("Password Cannot Be Blank!");
        pass.focus();
        return false;
    }
    else
    if (cpass.value == "") {
        alert("Confirm Password Cannot Be Blank!");
        return false;
    }
    else
    if (pass.value.length < 8)
    {
        alert("Password Atleast 8 Characters");
        return false;
    }
    else
    if(cpass.value.length < 8)
    {
        alert("Password Atleast 8 Characters");
        return false;
    }
    else
    if (pass.value.length > 30)
    {
        alert("Password Atmost 30 Characters");
        return false;
    }
    else
    if (cpass.value.length > 30)
    {
        alert("Password Atmost 30 Characters");
        return false;
    }
    else
    if(pass.value != cpass.value)
    {
        alert("Passwords Should Match");
        return false;
    }
    else
    if (ans.value == "") {
        alert("Security Answer Cannot Be Blank!");
        return false;
    }
  return true;
}
//Cancel Forward access on login page
function cancelForward()
{
	if(window.location.href == "Login.jsp")
	{
		window.onload = window.history.forward(-1);
	}
}
