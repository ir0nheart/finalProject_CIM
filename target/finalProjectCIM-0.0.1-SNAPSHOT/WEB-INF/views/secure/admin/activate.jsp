<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>CIM - Admin Home</title>
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
<nav class="navbar navbar-inverse sidebar" role="navigation">
    <div class="container-fluid">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-sidebar-navbar-collapse-1">
				<span class="sr-only">Toggle navigation</span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="">${user.username }</a>
		</div>
		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse" id="bs-sidebar-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li ><a href="home">Home<span style="font-size:16px;" class="pull-right hidden-xs showopacity glyphicon glyphicon-home"></span></a></li>
				<li ><a href="info">Profile<span style="font-size:16px;" class="pull-right hidden-xs showopacity glyphicon glyphicon-user"></span></a></li>
				<li ><a href="messages">Messages<span style="font-size:16px;" class="pull-right hidden-xs showopacity glyphicon glyphicon-envelope"></span></a></li>
				<li class="dropdown">
					<a href="" class="dropdown-toggle active" data-toggle="dropdown">Activate <span class="caret"></span><span style="font-size:16px;" class="pull-right hidden-xs showopacity glyphicon glyphicon-cog"></span></a>
					<ul class="dropdown-menu forAnimate">
						<li><a href="activate?carowner">Car Owners</a></li>
						<li><a href="activate?insurance">Insurance Companies</a></li>
						<li><a href="activate?maintenance">Maintenance Companies</a></li>
					</ul>
				</li>
				<li ><a href="<c:url value="/logout" />">Logout<span stype="style="font-size:16px;" class="pull-right hidden-xs showopacity glyphicon glyphicon glyphicon-log-out"></span></a></li>
				
			</ul>
		</div>
	</div>
</nav>
<div class="main">
	<div class="row">
		<div class="text-center">
			<h1>Awaiting Activations</h1>
			<h2> for </h2>
		</div>
		<c:if test="${listType == 'carowner' }">
		<div class="text-center">
			<h2> Car Owners </h2>
		</div>
			<table class="table table-striped table-hover">
			<tr>
				<th>Car Owner Id</th>
				<th>Car First Name</th>
				<th>Car Last Name</th>
				<th>Activate</th>
				<th>Decline</th>
			</tr>
			<c:if test="${not empty list}">
			<c:forEach var="CarOwner" items="${list}">
				<tr>
					<td>${CarOwner.carownerid}</td>
					<td>${CarOwner.firstName}</td>
					<td>${CarOwner.lastName}</td>
					<td><a href="activateCarOwner?id=${CarOwner.carownerid}"><span class="center-block hidden-xs showopacity glyphicon glyphicon-ok"></span></a></td>
					<td><a href="declineCarOwner?id=${CarOwner.carownerid}"><span class="glyphicon glyphicon-remove"></span></a></td>
				</tr>
				
			</c:forEach>
			</c:if>
			</table>
		</c:if>
		<c:if test="${listType == 'insurance' }">
		<div class="text-center">
			<h2> Insurance Companies </h2>
		</div>
			<table class="table table-striped table-hover">
			<tr>
				<th>Insurance Company Id</th>
				<th>Insurance Company Name</th>
				<th>Contact Phone</th>
				<th>Activate</th>
				<th>Decline</th>
			</tr>
			<c:if test="${not empty list}">
			<c:forEach var="InsuranceCompany" items="${list}">
				<tr>
					<td>${InsuranceCompany.insuranceCompanyId}</td>
					<td>${InsuranceCompany.insuranceCompanyName}</td>
					<td>${InsuranceCompany.insuranceCompanyContact}</td>
					<td><a href="activateInsuranceCompany?id=${InsuranceCompany.insuranceCompanyId}"><span class="center-block hidden-xs showopacity glyphicon glyphicon-ok"></span></a></td>
					<td><a href="declineInsuranceCompany?id=${InsuranceCompany.insuranceCompanyId}"><span class="glyphicon glyphicon-remove"></span></a></td>
				</tr>
				
			</c:forEach>
			</c:if>
			</table>
		</c:if>
		<c:if test="${listType == 'maintenance' }">
		<div class="text-center">
			<h2> Maintenance Companies </h2>
		</div>
			<table class="table table-striped table-hover">
			<tr>
				<th>Maintenance Company Id</th>
				<th>Maintenance Company Name</th>
				<th colspan="2">Maintenance Company Address</th>
				<th>Maintenance Company Contact</th>
				<th>Activate</th>
				<th>Decline</th>
			</tr>
			<c:if test="${not empty list}">
			<c:forEach var="MaintenanceCompany" items="${list}">
				<tr>
					<td>${MaintenanceCompany.maintenanceCompanyId}</td>
					<td>${MaintenanceCompany.maintenanceCompanyName}</td>					
					<td colspan="2">${MaintenanceCompany.maintenanceCompanyAddress}</td>
					<td>${MaintenanceCompany.maintenanceCompanyContact}</td>
					
					<td><a href="activateMaintenanceCompany?id=${MaintenanceCompany.maintenanceCompanyId}"><span class="center-block hidden-xs showopacity glyphicon glyphicon-ok"></span></a></td>
					<td><a href="declineMaintenanceCompany?id=${MaintenanceCompany.maintenanceCompanyId}"><span class="glyphicon glyphicon-remove"></span></a></td>
				</tr>
				
			</c:forEach>
			</c:if>
			</table>
		</c:if>
	</div>
</div>

</body>
</html>
