<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div align="center">
<h2>Sign Up Here</h2>
<font color="red">${errorMsg }</font>
<font color="red">${errorMsg1 }</font>
<form action="login" method="Post">
<table>
<tr>
	<td><label for="emailId">Email Id</label></td>
	<td><input type="text" name="emailId"></td>
</tr>
<tr>
	<td><label for="password">Password</label></td>
	<td><input type="password" name="password"></td>
</tr>
<tr >
	<td><input type="submit" value="Sign-In"></td>
</tr>
<tr>
	<td><a href="forgotPwd">Forgot Pwd?</a></td>
	<td><a href="register">Sign-Up</a></td>
</tr>
</table>
</form>
</div>
</body>
</html>