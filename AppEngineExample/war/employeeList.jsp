<%@ page language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

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

	<h2 align="center">List of Employees</h2>

	<hr />

	<br />

	<c:if test="${not empty msg}">
		<p align="center">
			<c:out value="${msg }" />
		</p>
	</c:if>
	<br />

	<c:if test="${fn:length(list) eq  0}">
		<p align="center">The List is Empty</p>
	</c:if>
	<c:if test="${fn:length(list) gt  0}">
		<table align="center" border="1" cellpadding="5" cellspacing="2">

			<tr>
				<th>Employee Id</th>
				<th>Employee Name</th>
				<th>Employee Salary</th>
				<th colspan="2">Action</th>
			</tr>

			<c:forEach items="${list}" var="employee">
				<tr>
					<td><c:out value="${employee.id }" /></td>
					<td><c:out value="${employee.name }" /></td>
					<td><c:out value="${employee.salary }" /></td>
					<td><a
						href="${pageContext.request.contextPath}/EmployeeController?action=edit&id=${employee.id}">Edit</a></td>
					<td><a
						href="${pageContext.request.contextPath}/EmployeeController?action=delete&id=${employee.id}">Delete</a></td>

				</tr>
			</c:forEach>

		</table>
	</c:if>


	<br />

	<p align="center">
		| <a href="index.html">Cancel</a> | | <a href="createEmployee.jsp">Create
			new Employee</a> |
	</p>
</body>
</html>