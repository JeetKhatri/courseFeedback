<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin | Question Insert</title>
<noscript>
	<div style="color: #FF0000">place enable java script</div>
</noscript>
<script type="text/javascript" src="javaScript/mainscript.js"></script>
<link rel="icon" href="photos/daiict.png" />
</head>
<body>
	<%@ include file="adminHeader.jsp"%>
	<div style="margin-top: -850px; margin-left: 250px;">
		<section class="content-header">
		<h1>
			Question <small>Insert</small>
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
				<form action="QuestionsInsertServlet" method="post"
					name="Registration">
					<br />

					
					<div class="row">
						<label class="col-sm-2"> <font size="+1">Question :</font>
						</label>
						<div class="col-lg-6">
							<input type="text" class="form-control" name="txtQuestionContent"
								placeholder="question" maxlength="255" value="${txtQuestionContent}" />
							<font color="red">${questionContent} </font>
						</div>
					</div>
					<br />
					
					<div class="row">

						<%
							String t = (String) (request.getAttribute("isT")), l = (String) (request.getAttribute("isL")),
									p = (String) (request.getAttribute("isP"));
						%>

						<label class="col-sm-3"><font size="+1">Question
								Type : </font> </label>
						<div class="col-md-7">
							<div class="col-sm-3">
								<%
									if (l != null && l.equals("1")) {
								%>
								<input type="checkbox" name="rdoType" value="L" checked="<%=l%>" />&nbsp;&nbsp;&nbsp;Lecture
								<%
									} else {
								%>
								<input type="checkbox" name="rdoType" value="L" />&nbsp;&nbsp;&nbsp;Lecture
								<%
									}
								%>
							</div>
							<div class="col-sm-3">
								<%
									if (t != null && t.equals("1")) {
								%>
								<input type="checkbox" name="rdoType" value="T" checked="<%=t%>" />&nbsp;&nbsp;&nbsp;Tutorial
								<%
									} else {
								%>
								<input type="checkbox" name="rdoType" value="T" />&nbsp;&nbsp;&nbsp;Tutorial
								<%
									}
								%>
							</div>
							<div class="cal-sm-3">
								<%
									if (p != null && p.equals("1")) {
								%>
								<input type="checkbox" name="rdoType" value="P" checked="<%=p%>" />&nbsp;&nbsp;&nbsp;Prectical
								<%
									} else {
								%>
								<input type="checkbox" name="rdoType" value="P" />&nbsp;&nbsp;&nbsp;Prectical
								<%
									}
								%>
							</div>
						</div>
					</div>
					&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
					<font color="red">${questionType} </font> <br />

					<div class="row">
						<%
							String type = request.getParameter("ansType"), radio = "", textarea = "";
							if (type != null) {

								if (type.equals("radio"))
									radio = "checked";
								else
									textarea = "checked";
							} else {
								radio = "checked";
							}
						%>
						<label class="col-sm-3"><font size="+1">Answer Type
								: </font> </label>
						<div class="col-md-7">
							<div class="col-sm-6">
								<input type="radio" class="radio" name="ansType" value="radio"
									<%=radio%> />Radio button
							</div>
							<div class="col-sm-6">
								<input type="radio" class="radio" name="ansType" <%=textarea%>
									value="textarea" /> Textbox
							</div>
						</div>
					</div>






					<br /> <br /> <br />
					<label class="col-sm-2 control-label"></label> <input type="reset"
						value="Reset" name="reset" class="btn  btn-danger">
					&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
					<input type="Submit" value="Insert" name="submit"
						class="btn btn-success">

				</form>
			</div>
		</div>
	</div>
</body>
</html>






