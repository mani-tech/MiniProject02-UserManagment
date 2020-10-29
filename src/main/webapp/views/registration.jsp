<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#emailId").blur(function(){
		$("#errMsg").text("");
		$.ajax({
			type:"GET",
			url:"checkEmail?emailId="+$("#emailId").val(),
			success:function(action){
				if(action=="Duplicate")
					{
					$("#errMsg").text("Duplicate Email");
					$("#submit").prop("disabled",true);
					}
				else{
					$("#submit").prop("disabled",false);
					}
				}
			});
		});
	$("#cntryId").change(function(){
		$("#stateId").find('option:not(:first)').remove();
		$("#cityId").find('option:not(:first)').remove();
		$.ajax({
			type:"GET",
			url:"loadStates?countryId="+$("#cntryId").val(),
			success:function(action){
				$.each(action,function(id,value){
					$("#stateId").append($('<option>').text(value).attr('value',id));
					});
				}
			});
		});

	$("#stateId").change(function(){
		$("#cityId").find('option:not(:first)').remove();
		$.ajax({
			type:"GET",
			url:"loadCities?stateId="+$("#stateId").val(),
			success:function(action){
				$.each(action,function(id,value){
					$("#cityId").append($('<option>').text(value).attr('value',id));
					});
				}
			});
		});
});

</script>
</head>
<body>
<font color="red">${successMsg}</font>
<font color="green">${errorMsg}</font>

<div align="center">
<h2>Registration Form</h2>
<form:form action="saveUser" method="post" modelAttribute="user">
<table>
<tr>
	<td>First Name</td>
	<td><form:input path="firstName"/></td>
</tr>
<tr>
	<td>Last Name</td>
	<td><form:input path="lastName"/></td>
</tr>
<tr>
	<td>Email</td>
	<td><form:input path="emailId" id="emailId"/></td>
	<td><span id="errMsg"></span></td>	
</tr>
<tr>
	<td>Phone Number</td>
	<td><form:input path="phoneNumber"/></td>
</tr>
<tr>
	<td>Dob</td>
	<td><form:input path="dob"/></td>
</tr>
<tr>
	<td>Gender</td>
	<td>
	<form:radiobutton path="gender" value="M"/>Male
	<form:radiobutton path="gender" value="F"/>FeMale
	</td>
</tr>
<tr>
	<td>Select Country</td>
	<td>
	<form:select path="country" id="cntryId">
	<form:option value="">--Select--</form:option>
	<form:options items="${countries}"/>
	</form:select>
	</td>
</tr>
<tr>
	<td>Select States</td>
	<td>
	<form:select path="state" id="stateId">
	<form:option value="">--Select--</form:option>
	</form:select>
	</td>
</tr>
<tr>
	<td>Select City</td>
	<td><form:select path="city" id="cityId">
	<form:option value="">--Select--</form:option>
	</form:select>
	</td>
</tr>
<tr>
	<td><input type="reset" value="Reset"></td>
	<td><input type="submit" value="Register" id="submit"></td>
</tr>
</table>

</form:form>
</div>
</body>
</html>