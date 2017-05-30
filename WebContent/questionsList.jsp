<%@page import="courseFeedback.bean.*"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin | Question List</title>

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
			Question <small>List</small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="adminDashBoard.jsp"><i class="fa fa-dashboard"></i>
					Home</a></li>
			<li class="active">Question</li>
		</ol>
		<br>
		<br>
		<a href="questionsInsert.jsp"><input type="button" value="ADD"
			name="ADD" class="btn btn-primary"></a>
		<br>
		<br>
		<div class="row">
			<div class="col-xs-12">
				<div class="box">
					<div class="box-body">
						<table id="example1"
							class="table table-bordered table-hover table-striped">
							<%
								ArrayList<QuestionsBean> listOfQuestions = (ArrayList) request.getAttribute("listOfQuestions");
								if (listOfQuestions != null) {
							%>
							<thead class="gujju-theme text-uppercase">
								<tr>
									<th><center>Year Id</center></th>
									<th><center>Term Id</center></th>
									<th><center>Question Content</center></th>
									<th><center>Lecture</center></th>
									<th><center>Tutorial</center></th>
									<th><center>Prectical</center></th>
									<th><center>Ans Type</center></th>
									<th><center>is Available</center></th>
									<th><center>Action</center></th>
								</tr>
							</thead>


							<tbody>
								<%
									for (int i = 0; i < listOfQuestions.size(); i++) {
											QuestionsBean questionsBean = listOfQuestions.get(i);
								%>
								<tr>
									<td align="center"><%=questionsBean.getYearId()%></td>
									<td align="center"><%=questionsBean.getTermId()%></td>
									<td align="center"><textarea disabled="disabled" rows="2" cols="40"><%=questionsBean.getQuestionContent()%></textarea></td>
									<%
										String isLecture = questionsBean.getIsLecture();
												String isTutorial = questionsBean.getIsTutorial();
												String isPrectical = questionsBean.getIsPrectical();
												String isAvailable = questionsBean.getIsAvailable();
												String isSpecial = questionsBean.getIsSpecial();
												if (isLecture.equals("0"))
													isLecture = "No";
												else
													isLecture = "Yes";
												if (isTutorial.equals("0"))
													isTutorial = "No";
												else
													isTutorial = "Yes";
												if (isPrectical.equals("0"))
													isPrectical = "No";
												else
													isPrectical = "Yes";
												if (isAvailable.equals("0"))
													isAvailable = "No";
												else
													isAvailable = "Yes";
									%>
									<td align="center"><%=isLecture%></td>
									<td align="center"><%=isTutorial%></td>
									<td align="center"><%=isPrectical%></td>
									<td align="center"><%=questionsBean.getAnsType()%></td>

									<%
										if (questionsBean.getIsAvailable().equals("0")) {
									%>
									<td align="center"><img src="photos/no1.jpg" height="30"
										width="30" class="img-rounded" /></td>
									<%
										} else {
									%>
									<td align="center"><img src="photos/yes1.jpg" height="30"
										width="30" class="img-rounded" /></td>
									<%
										}
									%>
									<td align="center"><a
										href="QuestionsEditServlet?questionId=<%=questionsBean.getQuestionId()%>"><img
											src="photos/edit.ico" height="30" width="30"
											class="img-rounded" /></a><a
										href="QuestionsDeleteServlet?questionId=<%=questionsBean.getQuestionId()%>"><img
											src="photos/Recycle Bin.ico" height="30" width="30"
											class="img-rounded" /></a></td>
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







