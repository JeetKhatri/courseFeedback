<%@page import="courseFeedback.dao.TermDetailsDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="courseFeedback.bean.UGPGAvgBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin | UG/PG Question List</title>
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
	<%
		ArrayList<UGPGAvgBean> ugpgAvgBeans = (ArrayList<UGPGAvgBean>) request.getAttribute("ugpgQuestionAvg");
		if (ugpgAvgBeans != null) {
	%>
	<div style="margin-top: -850px; margin-left: 250px;">
		<section class="content content-header">
		<h1>
			<%=ugpgAvgBeans.get(0).getType()%> Question Average <small>List</small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="adminDashBoard.jsp"><i class="fa fa-dashboard"></i>
					Home</a></li>
			<li class="active">Average</li>
		</ol>
		<br>
		<br>
		<div class="row">
			<div class="col-xs-12">
				<div class="box">
					<div class="box-body">
						<table id="example1"
							class="table table-bordered table-hover table-striped">

							<thead class="gujju-theme text-uppercase">
								<tr>
									<th><center>Year - Term</center></th>
									<th><center>Question Id</center></th>
									<th><center>Average</center></th>
								</tr>
							</thead>
							<tbody>
								<%
									UGPGAvgBean avgBean = new UGPGAvgBean();
										for (int i = 0; i < ugpgAvgBeans.size(); i++) {
											avgBean = ugpgAvgBeans.get(i);
								%>

								<tr>
								<%
								String yearName = new TermDetailsDAO().getYearName(avgBean.getYearId());
								String termName = new TermDetailsDAO().getTermName(avgBean.getTermId());
								
								%>
									<td align="center"><%=yearName%>&emsp;&emsp;  <%=termName%></td>
									<td align="center"><%=avgBean.getId()%></td>
									<td align="center"><%=avgBean.getAvg()%></td>
								</tr>

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
	<%
		} else {
	%>

	<h1>
		<center>No Record Found....!</center>
	</h1>
	<%
		}
	%>
</body>
</html>







