<%@page import="courseFeedback.bean.*"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin | Feedback Counter List</title>
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<link rel="icon" href="photos/daiict.png" />
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
			Feedback <small>List</small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="adminDashBoard.jsp"><i class="fa fa-dashboard"></i>
					Home</a></li>
			<li class="active">Feedback Counter Details</li>
		</ol>
		<br>
		<br>
		<br>
		<div class="row">
			<div class="col-xs-12">
				<div class="box">
					<div class="box-body">
						<table id="example1"
							class="table table-bordered table-hover table-striped">
							<%
								ArrayList<DateFeedbackCounterBean> listofCounter = (ArrayList) request.getAttribute("listofCounter");
								if (listofCounter != null) {
							%>
							<thead class="gujju-theme text-uppercase">
								<tr>
									<th><center>Date</center></th>
									<th><center>Counter</center></th>
								</tr>
							</thead>
							<tbody>
								<%
									for (int i = 0; i < listofCounter.size(); i++) {
										DateFeedbackCounterBean dateFeedbackCounterBean = listofCounter.get(i);
								%>

								<tr>
									<td align="center"><%=dateFeedbackCounterBean.getDate()%></td>
									<td align="center"><%=dateFeedbackCounterBean.getCounter()%></td>
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