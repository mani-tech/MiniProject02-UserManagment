<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<font color="red">${errorMsg}</font>

<font color="green">${successMsg}</font>

<form:form action="checkPwd?emailId=${unlockAcc.emailId}" method="post" modelAttribute="unlockAcc">
<table>
<tr>
	<td><label>Email ID :</label></td>
	<td>${unlockAcc.emailId}</td>
</tr>
<tr>
	<td><label>Temporary password :</label></td>
	<td><form:password  path="tempPwd"/></td>
</tr>
<tr>
	<td><label>New password :</label></td>
	<td><form:password path="newPwd"/></td>
</tr>
<tr>
	<td><label>Confirm password :</label></td>
	<td><form:password  path="cnfrmPwd"/></td>
</tr>
</table>
<input type="submit" value="Submit"/>
</form:form>
</body>
</html>