<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>CIM - Maintenance Company Information</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/bootstrap/3.3.6/css/bootstrap.min.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/fullcalendar/3.4.0/fullcalendar.css"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/font-awesome/4.6.3/css/font-awesome.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/customNav.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/fullcalendar/3.4.0/fullcalendar.min.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/webjars/jquery/2.2.4/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/customJS.js"></script>

    <script type="text/javascript" src="${pageContext.request.contextPath}/webjars/momentjs/2.19.3/min/moment.min.js"></script>
   <script type="text/javascript" src="${pageContext.request.contextPath}/webjars/fullcalendar/3.4.0/fullcalendar.js"></script>
     <script type="text/javascript" src="${pageContext.request.contextPath}/webjars/fullcalendar/3.4.0/gcal.js"></script>
     <script type="text/javascript">
     $(document).ready(function() {
         if("${viewType}" == 'google'){
       	 	 getGoogleEvents(); 	
       	 	 console.log("google Evnets");
       	 	 }else{
           	 getDBEvents();
           		console.log("DB Evnets");
           	 }

    	});

		function getDBEvents(){
			var xmlhttp;
			if(window.XMLHttpRequest){
				xmlhttp=new XMLHttpRequest();
			}else{
				xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");

			}
			xmlhttp.onreadystatechange = function(){
				
				if(xmlhttp.readyState==4 && xmlhttp.status==200){
					var event_array=[];
					//document.getElementById("txtHint").innerHTML = this.responseText;
					console.log(this.responseText);
					var splitted = JSON.parse(this.responseText);
					console.log("Splitted #1" + splitted);
					console.log(splitted.length);
					/* splitted = JSON.parse(splitted); */
					for(var i=0; i < splitted.length  ;i++){
		                   event_array.push(JSON.parse(splitted[i]));
		                   console.log("splitted:"+i+ " "+event_array[i]);
		             }
					$('#calendar').fullCalendar({
						 header:{
			    			 left: 'prev,next,today',
			    			 center: 'title',
			    			 right:'month,agendaWeek,agendaDay'
			    		 },
			    		 defaultView:'agendaWeek',
			    	     weekends:false,
			    	     weekNumbers:true,
			    	     close: 'glyphicon-remove',
			    	     prev: 'glyphicon-chevron-left',
			    	     next: 'glyphicon-chevron-right',
			    	     prevYear: 'glyphicon-backward',
			    	     nextYear: 'glyphicon-forward',
			    	     businessHours:  // specify an array instead
			    	    	    {
			    	    	        dow: [ 1, 2, 3,4,5 ], // Monday, Tuesday, Wednesday
			    	    	        start: '08:00', // 8am
			    	    	        end: '18:00' // 6pm
			    	    	    }
			    	    	,
			    	    
				            events:event_array
						});
					}
				
				}
			xmlhttp.open("GET","getCalendar",true);
			xmlhttp.send();
			}


		function getGoogleEvents(){
			$('#calendar').fullCalendar({
				 header:{
	    			 left: 'prev,next,today',
	    			 center: 'title',
	    			 right:'month,agendaWeek,agendaDay'
	    		 },
	    		 defaultView:'agendaWeek',
	    	     weekends:false,
	    	     weekNumbers:true,
	    	     close: 'glyphicon-remove',
	    	     prev: 'glyphicon-chevron-left',
	    	     next: 'glyphicon-chevron-right',
	    	     prevYear: 'glyphicon-backward',
	    	     nextYear: 'glyphicon-forward',
	    	     businessHours:  // specify an array instead
	    	    	    {
	    	    	        dow: [ 1, 2, 3,4,5 ], // Monday, Tuesday, Wednesday
	    	    	        start: '08:00', // 8am
	    	    	        end: '18:00' // 6pm
	    	    	    },
	    		 googleCalendarApiKey: 'AIzaSyDlzrXxpEP1yW5LKO0Ak4zxNciJuZefbS4',
	    	        eventSources: [
	    	            {
	    	                googleCalendarId: '${mc.calendarId}'
	    	            },
	    	            ]
				});
			$('#calendar').fullCalendar('rerenderEvents');
			}
	
     </script>
     
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
		<c:if test="${showType eq 'appointment' }">
		
		<div class="col-md-6 col-sm-offset-3 text-center">
		<p><a href ="show?appointment=google">Show Google Calendar Appointments</a>		</p>
		<p><a href ="show?appointment=db">Show Database Appointments</a></p>
		</div> 
		<div class="col-md-8 col-sm-offset-2" id="calendar"></div>
		
		
		</c:if>
		
		<c:if test="${showType eq 'cars' }">
		<div class="text-center">
			<h1>Cars Under Maintenance</h1>
		</div>
		<table class="table table-striped table-hover">
			<tr>
				<th>Car Owner</th>
				<th>Car Plate</th>
				<th>Appointment Date</th>
				<th>Status Message</th>
				<th>Change Status</th>
			</tr>
			<c:if test="${not empty maintenanceList}">
			<c:forEach var="maintenance" items="${maintenanceList}">
				<tr>
					<td>${maintenance.vehicle.vehicleOwner.firstName } ${maintenance.vehicle.vehicleOwner.lastName } </td>
					<td>${maintenance.vehicle.vehiclePlate}</td>
					<td>${maintenance.appointment.scheduleDate}</td>
					<td>${maintenance.maintenanceStatus}</td>
					<td><form action="updateStatus">
						<select name="status">
							<option value="Complete">Complete</option>
							<option value="Under Inspection">Under Inspection</option>
							<option value="Under Maintenance">Under Maintenance</option>
						</select>
						<input type="hidden" name="maintenanceId" value ="${maintenance.maintenanceId}"/>
 						<input type="submit" value="Update">
						</form>
				</tr>
					
			</c:forEach>
			</c:if>
			</table>
		</c:if>
		
	</div>
</div>

</body>
</html>
