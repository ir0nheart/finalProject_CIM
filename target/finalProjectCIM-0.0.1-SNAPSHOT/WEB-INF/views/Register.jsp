<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
  <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/bootstrap/3.3.6/css/bootstrap.min.css"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/font-awesome/4.6.3/css/font-awesome.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/customNav.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/webjars/jquery/2.2.4/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/customJS.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Register to CarOwner - InsuranceCompany - MaintenanceCompany Platform</title>
<style type="text/css">
.error {
	color: red;
}
</style>
</head>
<body>
  	<nav class="navbar navbar-inverse">
	<div class="container">
		<div class="navbar-header">
			
			<a class="navbar-brand" href="${pageContext.request.contextPath}/"><i class="fa fa-home" aria-hidden="true"></i>&nbsp;CarOwner - Insurance Company - Maintenance Company Platform</a>
			
		</div>
	</div>
</nav>
<div align="center">
<h1> User Registration Form</h1>
<form:form action="Register" method="post" modelAttribute="userinfo">
<table>
			
			<tr><td colspan="2" align="center"></td></tr>
			<tr>
				<td>Username:</td>
				<td><form:input path="username"/></td><td colspan="2" align="center"><c:if test="${not empty messageUserName}">
				${messageUserName }
				</c:if>
				<form:errors path="username" cssClass="error" /></td>
			</tr>
			<tr><td colspan="2" align="center"></td></tr>
			<tr>
				<td>Password:</td>
				<td><form:password path="password"/></td><td colspan="2" align="center"><form:errors path="password" cssClass="error"/></td>
			</tr>
			<tr><td colspan="2" align="center"></td></tr>
			<tr>
				<td>E-mail:</td>
				<td><form:input path="email"/></td><td colspan="2" align="center"><form:errors path="email" cssClass="error"/></td>
			</tr>
			<tr><td colspan="2" align="center"></td></tr>
			<tr>
				<td>User Type:</td>
				<td><form:select path="role">
				<form:option value="NONE" label="--- Select ---"></form:option>
				<form:options items="${userTypeList}"/>
				</form:select>
				</td>
				<td colspan="2" align="center"><c:if test="${not empty message}">
				${message }
				</c:if></td>
			</tr>
			
			<tr>
				<td colspan="2" align="center"><input type="submit" value="Register" /></td>
			</tr>
		</table>
		</form:form>
	</div>
</body>
</html>