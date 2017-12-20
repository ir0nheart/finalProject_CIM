<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>CIM - Admin show Information</title>
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
			<h1>Detailed Driver Information</h1>
			<table class="table">
				<tr>
					<td> First Name </td>
					<td>${qr.vehicle.vehicleOwner.firstName}</td>
				</tr>
				<tr>
					<td> Last Name </td>
					<td>${qr.vehicle.vehicleOwner.lastName}</td>
				</tr>
				<tr>
					<td> Date of Birth </td>
					<td>${qr.vehicle.vehicleOwner.dateOfBirth}</td>
				</tr>
				<tr>
					<td> Driver License No </td>
					<td>${qr.vehicle.vehicleOwner.driverLicenseNo}</td>
				</tr>
				<tr>
					<td> Driver License Since </td>
					<td>${qr.vehicle.vehicleOwner.driverLicenseSince}</td>
				</tr>
				<tr>
					<td> Driver License Expiration </td>
					<td>${qr.vehicle.vehicleOwner.driverLicenseExpiration}</td>
				</tr>
				
			</table>
				
		
		
		</div>
		
		<div class="span15"></div>
		<div class="text-center">
			<h1>Detailed Vehicle Information</h1>
			<table class="table">
				<tr>
					<td> Vehicle Make </td>
					<td>${qr.vehicle.vehicleMake}</td>
				</tr>
				<tr>
					<td> Vehicle Model </td>
					<td>${qr.vehicle.vehicleModel}</td>
				</tr>
				<tr>
					<td> Vehicle Year </td>
					<td>${qr.vehicle.vehicleYear}</td>
				</tr>
				<tr>
					<td> Vehicle KM </td>
					<td>${qr.vehicle.vehicleKM}</td>
				</tr>
				<tr>
					<td> Vehicle Transmission </td>
					<td>${qr.vehicle.vehicleTransmission}</td>
				</tr>
				<tr>
					<td> Vehicle Plate </td>
					<td>${qr.vehicle.vehiclePlate}</td>
				</tr>
				
			</table>
				
			<form action="giveQuote">
				<table class="table">
					<tr>
					<td align="right"> ${qr.quoteTerm } Months Insurance Package Cost for Quote #: ${qr.quoteId }</td>
					<td ><input type="text" name="quoteVal"/><span style="font-size:16px;" class="hidden-xs showopacity fa fa-money"></span></td></tr>
					<tr><td align="center" colspan="2"><input type="submit" value="Send Quote" /></td></tr>
				</table>
				<input type="hidden" name="id" value="${qr.quoteId }"/>
			</form>
		
		</div>
	</div>
</div>

</body>
</html>
