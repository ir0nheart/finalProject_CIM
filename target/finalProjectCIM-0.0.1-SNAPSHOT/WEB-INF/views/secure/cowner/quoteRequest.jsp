<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>CIM - Car Owner Maintenance Services</title>
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
				<li ><a href="vehicles">Vehicles<span style="font-size:16px;" class="pull-right hidden-xs showopacity fa fa-car"></span></a></li>	
				<li ><a href="messages">Messages<span style="font-size:16px;" class="pull-right hidden-xs showopacity glyphicon glyphicon-envelope"></span></a></li>
				<li class="dropdown active">
					<a href="" class="dropdown-toggle" data-toggle="dropdown">Services <span class="caret"></span><span style="font-size:16px;" class="pull-right hidden-xs showopacity glyphicon glyphicon-wrench"></span></a>
					<ul class="dropdown-menu forAnimate">
						<li class="active" ><a href="insuranceservices">Insurance Companies</a></li>
						<li ><a href="maintenanceservices"> Maintenance Companies</a></li>
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
			<h1>Request a Quote from ${insurance.insuranceCompanyName }</h1>
		</div>
		<form action="askQuote">
		<table class="table table-hover">
				<tr>
					<th>Vehicle</th>
					<th>Insurance Term</th>				
				</tr>
				
				<tr>
				<td><select name="vehicle">
					<c:forEach var="Vehicle" items="${vehicleList}">
					<option value="${Vehicle.vehicleId}"> ${Vehicle.vehiclePlate}</option>
					</c:forEach>
				</select>
				</td>
				<td>
				<select name="term">
					<option value="6"> 6 Months</option>
					<option value="12"> 12 Months</option>
					<option value="18"> 18 Months</option>
					<option value="24"> 24 Months</option>
				</select>
				
				</td>
				</tr>
				
			</table>	
				
				<input type="hidden" name="insurance" value="${insurance.insuranceCompanyId }"/>
				<input type="submit" value="Request Quote" />
			</form>
				
			<c:if test="${not empty pendingQuotes }">
				<table class="table table-hover">
				<tr>
					<th>Insurance Company</th>
					<th>Vehicle Plate</th>
					<th>Accept/Decline Quote</th>
				
				</tr>
				
				<c:forEach var="QuoteRequest" items="${pendingQuotes}">
				<tr>
					<td>${QuoteRequest.insuranceCompany.insuranceCompanyName}</td>
					<td>${QuoteRequest.vehicle.vehiclePlate}</td>
					<td><a href="downloadPDF?id=${QuoteRequest.quoteId }"><span class="center-block hidden-xs showopacity fa fa-file-text"></span></a></td>
				
				
					</tr>
			</c:forEach>
			
			</table>
			
			</c:if>	
		
		
	</div>
</div>

</body>
</html>
