<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>CIM - Maintenance Company Home</title>
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
				<li class="active"><a href="home">Home<span style="font-size:16px;" class="pull-right hidden-xs showopacity glyphicon glyphicon-home"></span></a></li>
				<li ><a href="info">Profile<span style="font-size:16px;" class="pull-right hidden-xs showopacity glyphicon glyphicon-user"></span></a></li>
				<li ><a href="messages">Messages<span style="font-size:16px;" class="pull-right hidden-xs showopacity glyphicon glyphicon-envelope"></span></a></li>
				<li class="dropdown">
					<a href="" class="dropdown-toggle" data-toggle="dropdown">Services <span class="caret"></span><span style="font-size:16px;" class="pull-right hidden-xs showopacity glyphicon glyphicon-cog"></span></a>
					<ul class="dropdown-menu forAnimate">
						<li><a href="show?appointment">Appointment Requests</a></li>
						<li><a href="show?cars">Car's under Maintenance</a></li>		
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
			<img alt="" src="http://www.freeiconspng.com/uploads/maintenance-icon-24.png" width="400px" height="400px"/>
		</div>
</div>
</div>

</body>
</html>
