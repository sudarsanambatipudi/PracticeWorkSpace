<%@ page language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Create Employee</title>
<style type="text/css">
body {
	font-family: Verdana;
}

p {
	color: green;
}
</style>
</head>
<body>
	<h1 align="center">Employee Entry</h1>
	<hr />
	<br />

	<c:if test="${not empty msg}">

		<p align="center">
			<c:out value="${msg}" />
		</p>

	</c:if>
	<br />

	<form method="post" action="EmployeeController">
		<table align="center" cellpadding="6" cellspacing="4">
			<tr>
				<td>Employee Id</td>
				<td><input type="text" name="employeeId"
					value="${employee.id }" /></td>
			</tr>
			<tr>
				<td>Employee Name</td>
				<td><input type="text" name="employeeName"
					value="${employee.name}" /></td>
			</tr>
			<tr>
				<td>Salary</td>
				<td><input type="text" name="employeeSalary"
					value="${employee.salary }" /></td>
			</tr>
			<tr>
				<td></td>
				<c:choose>
					<c:when test="${not empty employee}">
						<td><input type="submit" value="Update" name="action" /></td>
					</c:when>

					<c:otherwise>
						<td><input type="submit" value="Create" name="action" /></td>
					</c:otherwise>
				</c:choose>
			</tr>
		</table>
	</form>
	<br />

	<p align="center">
		<a href="index.html">Cancel</a>
	</p>
</body>
</html>