<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
     <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html ng-app="myApp" xmlns="http://www.w3.org/1999/xhtml">
<head >
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>CIM - Car Owner - Add a Vehicle</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/bootstrap/3.3.6/css/bootstrap.min.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/fullcalendar/3.4.0/fullcalendar.css"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/font-awesome/4.6.3/css/font-awesome.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/customNav.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/customProfile.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/fullcalendar/3.4.0/fullcalendar.min.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/webjars/jquery/2.2.4/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/webjars/angularjs/1.6.6/angular.min.js" ></script>    
    
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/customJS.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/addCalendar.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/webjars/fullcalendar/3.4.0/gcal.js"></script>
   <script type="text/javascript" src="${pageContext.request.contextPath}/webjars/fullcalendar/3.4.0/locale-all.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/webjars/momentjs/2.19.3/min/moment.min.js"></script>
   <script type="text/javascript" src="${pageContext.request.contextPath}/webjars/fullcalendar/3.4.0/fullcalendar.js"></script>
   <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/mask.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/app.js"></script>

   <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/telFilter.js"></script>
  
   
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
				<li class="active"><a href="vehicles">Vehicles<span style="font-size:16px;" class="pull-right hidden-xs showopacity fa fa-car"></span></a></li>	
				<li ><a href="messages">Messages<span style="font-size:16px;" class="pull-right hidden-xs showopacity glyphicon glyphicon-envelope"></span></a></li>
				<li class="dropdown">
					<a href="" class="dropdown-toggle" data-toggle="dropdown">Services <span class="caret"></span><span style="font-size:16px;" class="pull-right hidden-xs showopacity glyphicon glyphicon-wrench"></span></a>
					<ul class="dropdown-menu forAnimate">
						<li><a href="insuranceservices">Insurance Companies</a></li>
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
	
		<div class="col-md-8 col-sm-offset-2 text-center">
			<h1>Fill the form and register a new vehicle</h1>
		</div>
		<div class="col-md-6 col-sm-offset-3">
		<form:form action="registerVehicle" method="post" modelAttribute="vehicleM">
     		    		<table id ="acrylic" class="table-striped table-bordered table-responsive table-hover">
     		    		<tr>
     		    		<td> Vehicle Plate :</td>
     		    		<td><form:input path="vehiclePlate"/></td>
     		    		<td>	<form:errors path="vehiclePlate" /></td>
     		    		</tr>
     		    		<tr>
     		    		<td> Vehicle Make :</td>
     		    		<td><form:input path="vehicleMake"/></td>
     		    		<td><form:errors path="vehicleMake"/></td>
     		    		</tr>
     		    		<tr>
     		    		<td> Vehicle Model :</td>
     		    		<td><form:input path="vehicleModel"/></td>
     		    		<td>	<form:errors path="vehicleModel" /></td>
     		    		</tr>
     		    		<tr>
     		    		<td> Vehicle Year :</td>
     		    		<td><form:input ng-model="telNumb" ui-mask="9999" placeholder="YYYY" ui-mask-placeholder-char="-" path="vehicleYear"/></td>
     		    		<td>	<form:errors path="vehicleYear" /></td>
     		    		</tr>
     		    		<tr>
     		    		<td> Vehicle Transmission</td>
     		    		<td>
     		    		<form:select path="vehicleTransmission">
     		    			<form:option value="Automatic">Automatic</form:option>
     		    			<form:option value="Manual">Manual</form:option>
     		    			<form:option value="Tiptronic">Tiptronic</form:option>
     		    		</form:select></td>
     		
     		    		<td>	<form:errors path="vehicleTransmission" /></td>
     		    		</tr>
     		    		<tr>
     		    		<td> Vehicle KM :</td>
     		    		<td><form:input path="vehicleKM"/></td>
     		    		
     		    		<td>	<form:errors path="vehicleKM" /></td>
     		    		</tr>
     		    		<tr>
					<td colspan="3" align="center"><input type="submit" value="Register Vehicle" /></td>
					</tr>
     		    		
     		    		</table>
     		    </form:form>
     		    </div>
		
	</div>
</div>
</body>
</html>