<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>Login to CarOwner - InsuranceCompany - MaintenanceCompany Platform</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/bootstrap/3.3.6/css/bootstrap.min.css"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/font-awesome/4.6.3/css/font-awesome.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/customNav.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/webjars/jquery/2.2.4/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/customJS.js"></script>

    </head>
    <body>
    
    	<nav class="navbar navbar-inverse">
	<div class="container">
		<div class="navbar-header">
			
			<a class="navbar-brand" href="${pageContext.request.contextPath}/"><i class="fa fa-home" aria-hidden="true"></i>&nbsp;CarOwner - Insurance Company - Maintenance Company Platform</a>
			
		</div>
	</div>
</nav>
<div class="container">
<div class="panel-body">
	<div class="row">

	<div class="text-center">
	<h1>User Login Form</h1>
	</div>
<div class="text-center">
							

<c:if test="${not empty error }">
${error }
</c:if>
<form class="form-horizontal text-center" action="<%=request.getContextPath()%>/appLogin" method="post">
			<div class="form-group form-group-lg">
				<div class="col-sm-12 text-center">
				<label class="control-label">Username</label>
				</div>
				<div class="col-sm-12">
					<input type="text" name="app_username"/>
				</div>
				
			</div>
			<div class="form-group form-group-lg">
			<div class="col-sm-12 text-center">
				<label class="control-label">Password</label>
				</div>
				<div class="col-sm-12">
					<input type="password" name="app_password"/> 
					</div>
				
			</div>
			
			<div class="form-group">
				<div class="col-sm-12">
					<input type="submit" value="Login" />
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
				</div>
			</div>
		
		</form>
		</div>
</div>

</div>
</div>
   </body>
</html>  