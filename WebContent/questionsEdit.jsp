<%@page import="courseFeedback.bean.QuestionsBean"%>
<%@page import="courseFeedback.bean.TermDetailsBean"%>
<%@page import="java.util.List"%>
<%@page import="courseFeedback.dao.TermDetailsDAO"%>
<%@page import="courseFeedback.bean.CourseDetailsBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin | Question Update</title>
<noscript>
	<div style="color: #FF0000">place enable java script</div>
</noscript>
<script type="text/javascript" src="javaScript/mainscript.js"></script>
<link rel="icon" href="photos/daiict.png" />
</head>
<body>
	<%
		QuestionsBean questionsBean = (QuestionsBean) request.getAttribute("questionsBean");
		if (questionsBean != null) {
			String questionContent = questionsBean.getQuestionContent();
			if (questionContent == null)
				questionContent = "";
	%>
	<%@ include file="adminHeader.jsp"%>
	<div style="margin-top: -850px; margin-left: 250px;">
		<section class="content-header">
		<h1>
			Question <small>Update</small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="adminDashBoard.jsp"><i class="fa fa-dashboard"></i>
					Home</a></li>
			<li class="active">Question</li>
		</ol>
		</section>
		<br> <br>
		<div class="col-lg-6">
			<div class="container">
				<form action="QuestionsUpdateServlet" method="post"
					name="Registration">
					<br /> <input type="hidden" name="questionId"
						value="<%=request.getParameter("questionId")%>" />
					<div class="row">
						<label class="col-sm-2"> <font size="+1">Question
								Content :</font>
						</label>
						<div class="col-lg-6">
							<input type="text" class="form-control" name="txtQuestionContent"
								placeholder="????" maxlength="30" value="<%=questionContent%>" />
							<font color="red">${questionContent} </font>
						</div>
					</div>
					<br />

					<div class="row">

						<%
							String t = questionsBean.getIsTutorial() + "", l = questionsBean.getIsLecture() + "",
										p = questionsBean.getIsPrectical() + "";
						%>

						<label class="col-sm-3"><font size="+1">Question
								Type : </font> </label>
						<div class="col-md-7">
							<div class="col-sm-3">

								<%
									if (l != null && l.equals("1")) {
								%>
								<input type="checkbox" name="rdoType" value="L" checked="<%=l%>" />Lecture
								<%
									} else {
								%>
								<input type="checkbox" name="rdoType" value="L" />Lecture
								<%
									}
								%>
							</div>
							<div class="col-sm-3">
								<%
									if (t != null && t.equals("1")) {
								%>
								<input type="checkbox" name="rdoType" value="T" checked="<%=t%>" />Tutorial
								<%
									} else {
								%>
								<input type="checkbox" name="rdoType" value="T" />Tutorial
								<%
									}
								%>
							</div>
							<div class="cal-sm-3">
								<%
									if (p != null && p.equals("1")) {
								%>
								<input type="checkbox" name="rdoType" value="P" checked="<%=p%>" />Prectical
								<%
									} else {
								%>
								<input type="checkbox" name="rdoType" value="P" />Prectical
								<%
									}
								%>
							</div>
						</div>
					</div>
					<br /> 
					<div class="row">
						<%
							String type = questionsBean.getAnsType();
							String radio = "", textarea = "";
							if (type.equals("RADIO"))
								radio = "checked";
							else
								textarea = "checked";
						%>
						<label class="col-sm-3"><font size="+1">Ans Type : </font>
						</label>
						<div class="col-md-7">
							<div class="col-sm-6">
								<input type="radio" name="ansType" id="rdoGender" value="radio"
									<%=radio%> /> <font>radio</font>
							</div>
							<div class="col-sm-6">
								<input type="radio" class="radio" name="ansType"
									 <%=textarea%> value="textarea" /> <font>textarea</font>
							</div>
						</div>
				</div>
				<br />
					<div class="row">

						<%
							String isAvailable = (String) (questionsBean.getIsAvailable() + "");
								String yes = "", no = "";
								if (isAvailable.equals("1"))
									yes = "checked";
								else
									no = "checked";
						%>

						<label class="col-sm-3"><font size="+1">Is
								Available : </font> </label>
						<div class="col-md-7">
							<div class="col-sm-6">
								<input type="radio" class="radio" name="isAvailable" value="1"
									<%=yes%> />Yes
							</div>
							<div class="col-sm-6">
								<input type="radio" class="radio" name="isAvailable" value="0"
									<%=no%> />No
							</div>
						</div>

					</div>
					&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
					<label class="col-sm-2 control-label"></label> <input type="reset"
						value="Reset" name="reset" class="btn  btn-danger">
					&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
					<input type="Submit" value="Update" name="submit"
						class="btn btn-success">

				</form>
			</div>
		</div>
	</div>
	<%
		} else {

			request.setAttribute("msgLogin", "Please Login To Continue");
			response.sendRedirect("login.jsp");

		}
	%>
</body>
</html>

