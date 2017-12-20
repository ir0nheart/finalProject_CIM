<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ page session="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>An Error Occurred</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/bootstrap/3.3.6/css/bootstrap.min.css"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/font-awesome/4.6.3/css/font-awesome.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/customNav.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/webjars/jquery/2.2.4/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/customJS.js"></script>

</head>
<body>

<div class="container">

	<div class="row">
		<div class="col-md-8 col-sm-offset-2 text-center">
		
 			<h1>Ooopssss!!</h1>
 			<h2>Something unexpected happened..</h2>
 			<h3>We are sorry for inconvenience</h3>
 			<h4>You will be redirected Home Page in 5 seconds</h4>
 			<p> Error : ${errorMsg}</p>
 			 			
				<script type="text/javascript">
					window.setTimeout(function() {
   					window.location.href = '${pageContext.request.contextPath}';
					}, 5000);
				</script>
		</div>
	</div>
</div>

</body>
</html>