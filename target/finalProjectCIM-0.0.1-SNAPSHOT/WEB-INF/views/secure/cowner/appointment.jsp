<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html ng-app="carOwner">
<head>
	<title>CIM - Car Owner Maintenance Services</title>
	    <script type="text/javascript" src="${pageContext.request.contextPath}/webjars/jquery/2.2.4/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
     <script type="text/javascript" src="${pageContext.request.contextPath}/webjars/angularjs/1.6.6/angular.min.js" ></script>
     <script type="text/javascript" src="${pageContext.request.contextPath}/webjars/angularjs/1.6.6/angular-animate.min.js" ></script>
          <script type="text/javascript" src="${pageContext.request.contextPath}/webjars/angularjs/1.6.6/angular-touch.min.js" ></script>
               <script type="text/javascript" src="${pageContext.request.contextPath}/webjars/angularjs/1.6.6/angular-scenario.js" ></script>
     <script type="text/javascript" src="${pageContext.request.contextPath}/webjars/angularjs/1.6.6/angular-sanitize.js" ></script>
     
    <script type="text/javascript" src="${pageContext.request.contextPath}/webjars/angular-ui-bootstrap/2.2.0/ui-bootstrap-tpls.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/customJS.js"></script>
     <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/datepicker.js"></script>
     <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/app.js"></script>
     <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/customJQuery.js"></script>
     <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/mask.min.js"></script>

     <link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/bootstrap/3.3.6/css/bootstrap.min.css"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/font-awesome/4.6.3/css/font-awesome.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/customNav.css"/>
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
						<li ><a href="insuranceservices">Insurance Companies</a></li>
						<li class="active"><a href="maintenanceservices"> Maintenance Companies</a></li>
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
			<c:if test="${not empty maintenance.photoBytes}">
			      	<img src="showLogo?type=maintenance&id=${maintenance.maintenanceCompanyId }" height="150px" width="150px"/>
			 </c:if>
			<h1>${maintenance.maintenanceCompanyName} Appointment Schedule</h1>
			
			      	
			      	
			
		</div>
<div ng-controller="DatepickerCtrl">	
	<div class="col-md-6 show-grid text-center">
    
    <pre>Selected date is: <em>{{dt | date:'fullDate' }}</em></pre>
    <div style="display:inline-block; min-height:290px;">
      <div uib-datepicker ng-model="dt" class="well well-sm" datepicker-options="options"></div>
    </div>

    <hr />
    <button type="button" class="btn btn-sm btn-info" ng-click="today()">Today</button>
	</div>
	<div class="col-md-6 show-grid text-center">	
	<pre>Appointment Schedule for : <em>{{dt | date:'fullDate' }}</em></pre>

	<form action="makeAppoint" method="post">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> 
		<input type="hidden" name="maintenanceCompany" value="${ maintenance.maintenanceCompanyId}"/>
		<input type="hidden" name="appointmentDate" value="{{dt | date:'yyyy-MM-dd' }}"/>
	<table class="table table-responsive table-condensed table-bordered ">
		<thead ><tr>
		<th class="text-center" colspan="2">Time</th>
		<th  class="text-center" colspan="4">Make Appointment</th>
		</tr>
		</thead>
		<tbody>
		<tr ng-repeat="timex in times">
			<td width="20%">{{timex.time}}</td>
			<td width="10%"><input type="radio" name="radio" id="radio{{timex.id}}" value="radio{{timex.id}}" ></td>
			<td width="50%"><select name="car" id="carSelect{{timex.id}}" disabled="disabled"><c:forEach var="vehicle" items="${vehicleList}"><option value="${vehicle.vehicleId}"> ${vehicle.vehiclePlate}</option></c:forEach></select></td>
			<td width="20%"><input type="submit" id="submit{{timex.id}}" value="Make Appointment" disabled="disabled"></td>
		</tr>
		</tbody>
		
	</table>
	</form>
	</div>
</div>
			
		
		
	</div>
</div>



</body>
</html>