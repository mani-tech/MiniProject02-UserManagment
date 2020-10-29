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
<h2>Forgot Password</h2>
<font color="red">${errorMsg }</font>
<font color="green">${successMsg }</font>


<form action="pwd" method="Post">
<table>
<tr>
	<td><label for="emailId">Email Id</label></td>
	<td><input type="text" name="emailId"></td>
</tr>

<tr >
	<td><input type="submit" value="Submit"></td>
</tr>
</table>
</form>
</div>
</body>
</html>