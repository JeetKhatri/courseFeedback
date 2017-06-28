<%@page import="courseFeedback.bean.AdminBean"%>
<%@page import="courseFeedback.bean.FeedbackBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Admin | Feedback</title>
<link rel="icon" href="photos/daiict.png" />
<style>
p.break1 {
	page-break-before: always;
}
</style>
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script type="text/javascript"
	src="http://cdnjs.cloudflare.com/ajax/libs/jspdf/0.9.0rc1/jspdf.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

</head>

<%
	AdminBean adminBeanHeader = (AdminBean) session.getAttribute("adminBean");

	if (adminBeanHeader != null) {
%>

<style>
.table {
	display: block;
}

.table .header {
	font-weight: bold;
	font-size: 1.3em;
	display: block;
	text-align: center;
	padding: 10px 0;
}

.table .question {
	font-size: 1em;
	padding: 5px;
}

.table .question .q {
	display: inline-block;
	width: 60%;
	box-sizing: border-box;
}

.table .question .s {
	display: inline-block;
	width: 39%;
	padding-left: 10px;
	box-sizing: border-box;
}

.table .question .s .input {
	margin-right: 2px;
	display: inline-block;
}

@media ( max-width :800px) {
	.table .question .q {
		width: 71%;
	}
	.table .question .s {
		width: 28%;
		padding-top: 10px;
		padding-left: 0;
	}
}
</style>
<!-- <style>
tr {
	border: medium;
}

table {
	border: 1px solid white;
}
</style> -->
<script>
	function p() {
		window.print();
	}
	function home() {
		window.open("adminDashBoard.jsp", "_self");
	}
	function back() {
		window.history.back();
	}
</script>
<!-- <script>
	$(document).ready(function() {
		$("#print").click(function() {
			$("#print").hide();
			$("#back").hide();
			$("#home").hide();
			p();
		});
	});
</script> -->

<!-- <button id="print"
	style="margin-left: 200px; position: absolute; background-color: rgb(51, 118, 219);">
	<i>&emsp;<b>Print</b>&emsp;
	</i>
</button> -->
<style type="text/css">
@media print {
	#printPageButton {
		display: none;
	}
}
</style>
<button onclick="back()" id="printPageButton"
	style="margin-left: 10px; position: absolute; background-color: rgb(51, 118, 219);">
	<i>&emsp;<b>Back</b>&emsp;
	</i>
</button>
<button onclick="home()" id="printPageButton"
	style="margin-left: 100px; position: absolute; background-color: rgb(51, 118, 219);">
	<i>&emsp;<b>Home</b>&emsp;
	</i>
</button>
<button id="printPageButton" onClick="window.print();"
	style="margin-left: 200px; position: absolute; background-color: rgb(51, 118, 219);">
	<i>&emsp;<b>Print</b>&emsp;
	</i>
</button>
<body>
	<%
		int qcnt = 0;
			ArrayList<FeedbackBean> list = (ArrayList<FeedbackBean>) request.getAttribute("listOfFeedback");
			if (list != null) {
				int cnt = 0;
	%>

	<div class="table" style="font-size: small;">
		<!-- <table
			style="font-size: small; border-bottom: 1; border-left: 1;border-top=1;border-right=1; "> -->
		<%
			FeedbackBean bean = null;
					int cntStudent = 0;
					for (int i = 0; i < list.size(); i++) {
						bean = list.get(i);
						if (bean.getQuestionId().equals("1")) {

							if ((qcnt == 20 || qcnt == 17 || qcnt == 14) && cntStudent > 0) {
		%>
		<%
			
		%>
		<!-- <div style="page-break-before: always;"> -->
		<div class="header">
			Student -
			<%=++cntStudent%>'s
			<%=bean.getCourseCode()%>-<%=bean.getCourseName()%><br> <br>
			<br> <br>
		</div>
		<%-- <td><center>
					
						<h3 style="page-break-before: always;">
							Student -
							<%=++cntStudent%>'s
							<%=bean.getCourseCode()%>!-<%=bean.getCourseName()%></h3>
					</center></td> --%>
		<%
			qcnt = 0;
							} else {
		%>
		<%
			
		%>
		<p class="break1"></p>
		<div class="header">
			Student -
			<%=++cntStudent%>'s
			<%=bean.getCourseCode()%>-<%=bean.getCourseName()%>
		</div>
		<%-- <tr>
					<td>
						<center>
							<h3>
								Student -
								<%=++cntStudent%>'s
								<%=bean.getCourseCode()%>-<%=bean.getCourseName()%></h3>
						</center>
					</td>

				</tr> --%>
		<%
			qcnt = 0;
							}
						}
		%>
		<%-- <h1>Student - <%=bean.getCourseCode()%></h1> --%>
		<%
			String s1 = "unchecked", s2 = "unchecked", s3 = "unchecked", s4 = "unchecked", s5 = "unchecked";

						if (bean.getAnsType().equalsIgnoreCase("radio")) {
							if (bean.getFeedbackContent().equals("1")) {
								s1 = "checked";
							}
							if (bean.getFeedbackContent().equals("2")) {
								s2 = "checked";
							}
							if (bean.getFeedbackContent().equals("3")) {
								s3 = "checked";
							}
							if (bean.getFeedbackContent().equals("4")) {
								s4 = "checked";
							}
							if (bean.getFeedbackContent().equals("5")) {
								s5 = "checked";
							}
		%><div class="question">
			<div class="q"><%=++qcnt + ") " + bean.getQuestionContent()%></div>
			<div class="s">
				<div class="input">
					<input type="radio" id="que<%=bean.getQuestionId()%>" <%=s5%>
						disabled="disabled">5
				</div>
				<div class="input">
					<input type="radio" id="que<%=bean.getQuestionId()%>" <%=s4%>
						disabled="disabled">4
				</div>
				<div class="input">
					<input type="radio" id="que<%=bean.getQuestionId()%>" <%=s3%>
						disabled="disabled">3
				</div>
				<div class="input">
					<input type="radio" id="que<%=bean.getQuestionId()%>" <%=s2%>
						disabled="disabled">2
				</div>
				<div class="input">
					<input type="radio" id="que<%=bean.getQuestionId()%>" <%=s1%>
						disabled="disabled">1
				</div>
			</div>
		</div>
		<%
			} else if (bean.getAnsType().equalsIgnoreCase("textarea")) {
		%>
		<div class="question">
			<div class="q"><%=++qcnt + ") " + bean.getQuestionContent()%></div>
		</div>
		<div class="question">
			<textarea cols="91" rows="11" disabled="disabled"><%=bean.getFeedbackContent()%></textarea>
		</div>
		<%
			}
		%>
		<%-- <tr>
				<td><%=bean.getQuestionContent()%></td>
				<td><%=bean.getFeedbackContent()%></td>
			</tr> --%>
		<%
			}
				}
		%>
	</div>
</body>
<%
	} else {

		request.setAttribute("msgLogin", "Please Login To Continue");
		response.sendRedirect("login.jsp");

	}
%>
</html>







<%-- <%@page import="courseFeedback.bean.FeedbackBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
		ArrayList<FeedbackBean> list = (ArrayList<FeedbackBean>) request.getAttribute("listOfFeedback");
		if (list != null) {
			int cnt = 0;
	%>
	<form action="">
		<table>
			<tr>
				<td>Question Content</td>
				<td>Feedback Content</td>
			</tr>

			<%
				FeedbackBean bean = null;
					for (int i = 0; i < list.size(); i++) {
						bean = list.get(i);
			%>
			<tr>
				<td><%=bean.getQuestionContent()%></td>
				<td><%=bean.getFeedbackContent()%></td>
			</tr>
			<%
				}
			%>
		</table>
		<%
			}
		%>
	</form>
</body>
</html> --%>