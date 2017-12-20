<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://ckeditor.com" prefix="ckeditor" %>
<%@ page session="false" %>
<html>
<head>
	<title>CIM - Admin Messages</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/bootstrap/3.3.6/css/bootstrap.min.css"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/font-awesome/4.6.3/css/font-awesome.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/customNav.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/messages.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/webjars/jquery/2.2.4/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/webjars/ckeditor/4.7.3/standard/ckeditor.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/webjars/ckeditor/4.7.3/standard/adapters/jquery.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/customJS.js"></script>
	<script type="text/javascript">

		$(document).ready(function(){
			$('textarea').ckeditor({
				});
			$('form').submit(function(){

				var user =$('#selectUser').val();
				if(user === 'NONE'){
					alert('Please select an user');
					return false;
					}
				var title =$.trim($('#messageTitle').val());
				if(title === '' ){
					alert ('Title can not be empty');
					return false;}
					var message =$.trim($('#messageBody').val());
					if(message === '' ){
						alert ('Message can not be empty');
						return false;}
				})
		});
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
				<li class="active"><a href="messages">Messages<span style="font-size:16px;" class="pull-right hidden-xs showopacity glyphicon glyphicon-envelope"></span></a></li>
				<li class="dropdown">
					<a href="" class="dropdown-toggle" data-toggle="dropdown">Activate <span class="caret"></span><span style="font-size:16px;" class="pull-right hidden-xs showopacity glyphicon glyphicon-cog"></span></a>
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
<div class="container">
	<div class="row">

		<section class="messagescontent">
			<h1>${userMessage.messageTitle }</h1>
			<div class="col-md-12">
				<div class="panel panel-default">
					<div class="panel-body">
						<div class="pull-right">
							
								<h3>Date: ${userMessage.messageDate }</h3>		
								<a href="deleteMessage?id=${userMessage.messageId }"><span style="font-size:16px;" class="hidden-xs showopacity fa fa-trash"> Delete Message</span></a>
										
						</div>
						
						<div class="table-container">
							${userMessage.messageBody}
						</div>
					</div>
				</div>
				
			</div>
		</section>
		
		<section id="contact" class="content-section text-center">
        <div class="contact-section">
            <div class="container">
              <h2>Reply To Message</h2>
              <div class="row">
                <div class="col-md-8 col-md-offset-2">
                  <form class="form-horizontal" action="sendMessage">
                    <div class="form-group">
                      
    					<input type="hidden" value="${userMessage.messageFromId }" name="messageTo" />
                    </div>
                    <div class="form-group">
                      <label for="messageTitle">Title</label>
                      <input type="text" class="form-control" id="messageTitle" name="messageTitle" placeholder="Please enter a Tittle for your message" required value="Re : ${userMessage.messageTitle }">
                     
                    </div>
                    <div class="form-group ">
                      <label for="messageBody">Message</label>
                     <textarea  id ="messageBody" name ="messageBody" class="form-control" placeholder="Enter your message here" required></textarea> 
                    </div>
                    <button id="sendButton" type="submit" class="btn btn-default">Send Message</button>
                  </form>

                  <hr>
                  
                </div>
              </div>
            </div>
        </div>
      </section>
		
	</div>
</div>
</div>
<!-- <script>
 		CKEDITOR.replace( 'messageBody', {
		
 		} );
	</script> -->
</body>
</html>
