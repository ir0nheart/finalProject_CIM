<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>CIM - Car Owner Insurance Services</title>
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
						<li class="active"><a href="insuranceservices">Insurance Companies</a></li>
						<li><a href="maintenanceservices"> Maintenance Companies</a></li>
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
			<h1>Vehicle Details</h1>
		</div>
		<table class="table table-hover">
				<tr>
					<td>Vehicle Plate</td>
					<td>${vehicle.vehiclePlate }</td>
				</tr>
				<tr>	
					<td>Vehicle Make</td>
					<td>${vehicle.vehicleMake}</td>
					</tr>
				<tr>	
					<td>Vehicle Model</td>
					<td>${vehicle.vehicleModel}</td>
					</tr>
				<tr>			
					<td>Vehicle Year</td>
					<td>${vehicle.vehicleYear }</td>
					</tr>
				<tr>		
					<td>Vehicle KM</td>	
					<td>${vehicle.vehicleKM}</td>
					</tr>
				<tr>		
					<td>Vehicle Transmission</td>
					<td>${vehicle.vehicleTransmission }</td>	
					</tr>
				<tr>	
					<td>Insurance Status</td>
					
					<td>${insuranceStat}</td>
					</tr>
				
					<c:if test="${insuranceStat eq 'insured'}">
				<tr>		
					<td>Insurance Company</td>
					<td>${policy.insuranceCompany.insuranceCompanyName }</td>
					</tr>
				<tr>	
					<td>Insurance Contact</td>
					<td>${policy.insuranceCompany.insuranceCompanyContact }</td>
					</tr>
				<tr>	
					<td>Policy</td>
					<td><a href="downloadPDF?id=${policy.quoteRequest.quoteId }"><span style="font-size:16px;" class="hidden-xs showopacity fa fa-file"></span></a></td>
				</tr>
					</c:if>	
					
				
				
		</table>
		
	</div>
</div>

</body>
</html>
