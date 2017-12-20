<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html ng-app="myApp" xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>CIM - Insurance Company Profile</title>
         <link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/bootstrap/3.3.6/css/bootstrap.min.css"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/font-awesome/4.6.3/css/font-awesome.css"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/customProfile.css"/>
	 <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/placeautocomplete.css"/>
    		 
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/customNav.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/webjars/jquery/2.2.4/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/webjars/angularjs/1.6.6/angular.min.js" ></script> 
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/customJS.js"></script>
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
				<li class="active"><a href="info">Profile<span style="font-size:16px;" class="pull-right hidden-xs showopacity glyphicon glyphicon-user"></span></a></li>
				<li ><a href="messages">Messages<span style="font-size:16px;" class="pull-right hidden-xs showopacity glyphicon glyphicon-envelope"></span></a></li>
				<li class="dropdown">
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
	<div class="panel-body">		
     	<div class="row">
     		<div class="col-md-4 col-sm-offset-4">

            <div class="card hovercard">
                <div class="cardheader">

                </div>
                <div class="avatar">
                		<c:choose>
			      	<c:when test="${not empty insurance.photoBytes}">
			      	<img src="showLogo" height="100px" width="100px"/>
			      	</c:when>
			      	<c:otherwise>
			      	<img src="https://placeimg.com/100/100/people" alt=""></img>
			      	</c:otherwise>
			      	</c:choose>
                	
                    
                </div>
                <div class="info">
                    <div class="title">
                        <c:choose>
                    		<c:when test="${not empty insurance.insuranceCompanyName }">
                    			${insurance.insuranceCompanyName }
                    		</c:when>
                    		<c:otherwise>
                        ${user.username }
                        </c:otherwise>
                        </c:choose>
                    </div>
                    <div class="desc">${user.role}</div>
                    <div class="desc"> <c:if test="${user.active eq true }">
			         	<c:out value="Activated"/>
			        </c:if>
			        <c:if test="${user.active eq false }">
			         	<c:out value="Waiting for Activation"/>
			        </c:if></div>
                    
                </div>
                <div class="bottom">
                 <form action="updateLogoPicture" method="post" enctype="multipart/form-data">
                 <input type="file" name="pic" />
			     <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> 
			     <input type="submit" value="Upload"></input>
                 </form>
                </div>
               
            </div>
			</div>
        </div>
     	 <div class="spacer15"> </div>
     	<div class="row">
     		 <c:if test="${not empty errMessage }">
				<div class="alert alert-danger fade in text-center">
				<c:choose>
				    <c:when test="${insurance.requestActivate eq true }">
				       	Your membership is waiting for Admin approval 
				        <br />
				    </c:when>    
					    <c:otherwise>
					       You have to fill your profile information before using any functionalities
									
					        <br />
					    </c:otherwise>
				</c:choose>
				</div>
			</c:if>
			<div class="col-md-6 col-sm-offset-3">	
     		    <c:if test="${insurance.requestActivate eq false }">
     		    <form:form action="updateInsuranceCompany" method="post" modelAttribute="insuranceM">
     		    		<table id="address">
     		    		<tr>
     		    		<td class="label"> Company Name :</td>
     		    		<td class="wideField" colspan="4">
     		    		<form:input class="field" path="insuranceCompanyName"/>
     		    	
     		    		</td>
     		    		<td class="slimField" >	<form:errors class="field" path="insuranceCompanyName" /></td>
     		    		</tr>
     		    		<tr>
     		    		<td class="label"> Contact Phone :</td>
     		    		<td class="wideField" colspan="3">
     		    		<form:input class="field" ui-mask="(999) 999-9999" ng-model="telNumb" path="insuranceCompanyContact"/>
     		    		</td>
     		    		<td class="slimField">	<form:errors class="field" path="insuranceCompanyContact" /></td>
     		    		</tr>
     		    		
     		    		<tr>
				<td colspan="6" align="center"><input type="submit" value="Update Information" /></td>
			</tr>
     		    		
     		    		</table>
     		    </form:form>
     		    
     		    </c:if>
     		    </div>
     	 </div>
      </div>
   </div>    
</body>
</html> 