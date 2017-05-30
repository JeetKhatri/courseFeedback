<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin | Display chart</title>
<noscript>
	<div style="color: #FF0000">place enable java script</div>
</noscript>
<link rel="icon" href="photos/daiict.png" />
<script src="javaScript/init.js"></script>
</head>
<body>
<%@ include file="adminHeader.jsp"%>
<%
	String s = (String)request.getAttribute("chart");
%>
	<div style="margin-top: -850px; margin-left: 250px;">
		<section class="content-header">
		<h1>
			Chart <small>Display</small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="adminDashBoard.jsp"><i class="fa fa-dashboard"></i>
					Home</a></li>
			<li class="active">Chart</li>
		</ol>
		</section>
		<br> <br>
		&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
		<img src="photos/charts/<%=s %>" class="" width="640" height="480"></img>
	</div>
</body>
</html>