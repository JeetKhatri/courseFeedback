<%@page import="java.util.ArrayList"%>
<%@page import="courseFeedback.bean.LogBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin | Log List</title>

<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<link rel="icon" href="photos/logo.ico" />
<style>
td, tr, th {
	text-transform: uppercase;
}
</style>
</head>
<body>
	<%@include file="adminHeader.jsp"%>
	<div style="margin-top: -850px; margin-left: 250px;">
		<section class="content content-header">
		<h1>
			Log <small>List</small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="adminDashBoard.jsp"><i class="fa fa-dashboard"></i>
					Home</a></li>
			<li class="active">Log</li>
		</ol>
		<br>
		<br>
		<%
			List<LogBean> listOfLogs = (ArrayList<LogBean>) request.getAttribute("listOfLogs");
			if (listOfLogs != null) {
		%>
		<div class="row">
			<div class="col-xs-12">
				<div class="box">
					<div class="box-body">
						<table id="example1"
							class="table table-bordered table-hover table-striped">
							<thead class="gujju-theme text-uppercase">
								<tr>
									<th><center>Year ID</center></th>
									<th><center>Term ID</center></th>
									<th><center>Table Name</center></th>
									<th><center>Updated By</center></th>
									<th><center>Updated At</center></th>
									<th><center>Query</center></th>
								</tr>
							</thead>
							<tbody>
								<%
									for (LogBean l : listOfLogs) {
								%>
								<tr>
									<td><%=l.getYearId()%></td>
									<td><%=l.getTermID()%></td>
									<td><%=l.getTableName()%></td>
									<td><%=l.getAdminEmail()%></td>
									<td><%=l.getUpdatedAt()%></td>
									<td><textarea disabled="disabled" rows="2" cols="40"><%=l.getQuery().toLowerCase()%></textarea></td>

								</tr>

								<%
									}
									}

									else {
								%>

								<h1>
									<center>No Record Found....!</center>
								</h1>
								<%
									}
								%>

							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
		</section>
	</div>
</body>
</html>