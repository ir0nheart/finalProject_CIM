<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>CIM - Home</title>
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
<div class="row">
<div class="text-center">
<h1>
	Welcome to <br>CarOwner - Insurance Company - Maintenance Company <br>Platform!  
</h1>
</div>
</div>

<div class="row">
<div class="panel-heading">
						<div class="row">
							<div class="col-xs-6 text-center">
								<a href="Login" class="active" id="login-form-link">Login</a>
							</div>
							<div class="col-xs-6 text-center">
								<a href="Register" id="register-form-link">Register</a>
							</div>
						</div>
						<hr>
					</div>
					
</div>
</div>
</body>
</html>
