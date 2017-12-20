<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>CIM - Insured Vehicle Informations</title>
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
				<li class="dropdown active">
					<a href="" class="dropdown-toggle" data-toggle="dropdown">Services <span class="caret"></span><span style="font-size:16px;" class="pull-right hidden-xs showopacity glyphicon glyphicon-cog"></span></a>
					<ul class="dropdown-menu forAnimate">
						<li><a href="show?requests">Check Quote Request</a></li>
						<li><a href="show?pending">Pending Quotes</a></li>
						<li><a href="show?accepted">Accepted Quotes</a></li>
						<li><a href="showInsured">Insured Cars</a></li>
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
			<h1>Insured Cars by ${user.username}</h1>
		</div>
		<table class="table table-striped table-hover">
			<tr>
				<th>Policy Id</th>
				<th>Policy Expiration</th>
				<th>First Name</th>
				<th>Last Name </th>
				<th>Vehicle Plate</th>
				<th>Insurance Value</th>
				<th>Cancel Policy</th>
			</tr>
			<c:if test="${not empty list}">
			<c:forEach var="policy" items="${list}">
				<tr>
					<td>${policy.policyId}</td>
					<td>${policy.expirationDate }
					<td>${policy.quoteRequest.vehicle.vehicleOwner.firstName}</td>
					<td>${policy.quoteRequest.vehicle.vehicleOwner.lastName}</td>
					<td>${policy.quoteRequest.vehicle.vehiclePlate}</td>
					<td>${policy.quoteRequest.quoteCost}</td>
					<td><a href="cancelPolicy?id=${policy.policyId }"><span style="font-size:16px;" class="hidden-xs showopacity fa fa-trash"></span></a>
				</tr>
				
			</c:forEach>
			</c:if>
			</table>
	
	</div>
</div>

</body>
</html>
