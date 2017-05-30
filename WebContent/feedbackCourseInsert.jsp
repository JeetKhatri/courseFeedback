
<%@page import="courseFeedback.dao.CourseDetailsDAO"%>
<%@page import="courseFeedback.bean.FeedbackBean"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.HashSet"%>
<%@page import="courseFeedback.bean.QuestionsBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="courseFeedback.bean.CourseDetailsBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="javaScript/bootbox.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Course Feedback</title>
<SCRIPT type="text/javascript">
	window.history.forward();
	function noBack() {
		window.history.forward();
	}
</SCRIPT>

<script type="text/javascript">
	function method1() {
		var a = document.getElementById("questionNo").value;
		var cc = document.getElementById("CC").value;
		var cn = document.getElementById("CN").value;
		var text21 = document.getElementById("que21").value;
		/* alert(cc);
		alert(cn); */
		var b = a.split(" ");
		var c = b.length - 1;
		if (text21) {
			c = c - 1;
		} else {
		}
		var k = $('input[type=radio]:checked').size(); //Total
		if (k == c) {
			var r = confirm(cc + " - " + cn + " \n You have Skipped " + (k - c)
					+ " Questions.");
			if (r == true) {
				document.getElementById("action").value = "next";
				document.getElementById("myform").submit();
			} else {

				/* txt = "You pressed Cancel!"; */
			}
		} else {
			var r = confirm(cc + " - " + cn + " \n You have Skipped " + (k - c)
					+ " Questions.");
			if (r == true) {
				document.getElementById("action").value = "next";
				document.getElementById("myform").submit();
			} else {

				/* txt = "You pressed Cancel!"; */
			}
		}

	}
	function method2() {
		document.getElementById("action").value = "prev";
		document.getElementById("myform").submit();

	}
	/*
	function prev(){
	var http;
	if(window.XMLHttpRequest)
		xhttp=new XMLHttpRequest();
	else
		xhttp=new ActiveXObject('Microsoft.XMLHTTP');
	xhttp.onreadystatechange=function(){
		if(xhttp.readyState==4 && xhttp.status==200)
		{
			alert("done");
			window.location='FeedbackCourseQuestionServlet';
		}
	};
	xhttp.open('GET','AjaxServlet?counter=1',true);
	xhttp.send();
	}*/

	function fun() {
		var a = document.getElementById("questionNo").value;
		var b = a.split(" ");
		var c = b.length - 1;
		var cc = document.getElementById("CC").value;
		var cn = document.getElementById("CN").value;
		var text21 = document.getElementById("que21").value;
		if (text21) {
			c = c - 1;
		} else {
		}


		var k = $('input[type=radio]:checked').size();
		if (k == c) {

			var r = confirm(cc + " - " + cn + " \n You have Skipped " + (k - c)
					+ " Questions. \n Are You Sure To Submit Feedback..!?");
			if (r == true) {
				document.getElementById("action").value = "next";
				document.getElementById("myform").submit();
			} else {

				/* txt = "You pressed Cancel!"; */
			}

		} else {

			var r = confirm(cc + " - " + cn + " \n You have Skipped " + (k - c)
					+ " Questions.");
			if (r == true) {
				var temp = document.getElementById("courseCodes").value;
				var c = confirm("You have given the feedback for "
						+ (temp)
						+ " Courses \n Once Submitted you will not be able to change your feedback. \n "
						+ " Are You Sure want to submit...?");
				if (c == true)
					document.getElementById("action").value = "next";
				document.getElementById("myform").submit();
			} else {

				/* txt = "You pressed Cancel!"; */
			}
		}
		/* if (r == true) {
			method1();
		} else {
			txt = "You pressed Cancel!";
		} */

	}
</script>
<script>
	function countChar(val) {
		var len = val.value.length;
		if (len >= 1000) {
			val.value = val.value.substring(0, 1000);
		} else {
			$('#charNum').text(1000- len + " Characters Reamining");
		}
	};
</script>
<link rel="icon" href="photos/daiict.png" />
</head>
<body onload="noBack();" onpageshow="if (event.persisted) noBack();"
	onunload="">
	<%@ include file="userHeader.jsp"%>
	<div class="section white" id="index-banner">
		<div class="container" style="width: 90%">
			<div class="row">
				<form action="FeedbackSetServlet" id="myform" name="myform"
					class="col s12" method="post">
					<div class="row">
						<div class="input-field col s12">
							<%
								String[] courseCode = (String[]) session.getAttribute("courseCode");
								String courseCodes = " ";
								for (int i = 0; i < courseCode.length; i++) {
									courseCodes += courseCode[i];
									courseCodes += " ";
								}
								int courseCounter = Integer.parseInt(session.getAttribute("courseCounter") + "");
								CourseDetailsBean courseDetailsBean = new CourseDetailsDAO().getByCourseCode(courseCode[courseCounter]);
								int cnt = 0;
								//s	CourseDetailsBean courseDetailsBean = (CourseDetailsBean) request.getAttribute("courseDetailsBean");
								ArrayList<QuestionsBean> listOfQuestionsForFeedback = (ArrayList<QuestionsBean>) request
										.getAttribute("listOfQuestionsForFeedback");
								HashMap<String, ArrayList<FeedbackBean>> hashmap = (HashMap<String, ArrayList<FeedbackBean>>) session
										.getAttribute("feedbackedCourse");
								ArrayList<FeedbackBean> listOfFeedback = null;
								if (hashmap != null)
									listOfFeedback = hashmap.get(courseDetailsBean.getCourseCode());
								out.print("<h5>" + courseDetailsBean.getCourseCode() + " - " + courseDetailsBean.getCourseName() + "</h5>");
								//out.print("<h6>" + courseDetailsBean.getCourseCode() + "</h6>");

								String codes[] = (String[]) session.getAttribute("courseCode");

								int counter = (Integer) session.getAttribute("courseCounter");
								String questionNo = "";
								for (int i = 0; i < listOfQuestionsForFeedback.size(); i++) {
									QuestionsBean bean = listOfQuestionsForFeedback.get(i);
									if (bean.getQuestionId().equals("1")) {
										out.println("<h5>&nbsp;&nbsp;</h5>");
										out.println("<h5>&nbsp;&nbsp;&nbsp;&nbsp;Course organization and planning </h5>");
									}

									if (bean.getQuestionId().equals("5")) {
										out.println("<h5>&nbsp;&nbsp;</h5>");
										out.println("<h5>&nbsp;&nbsp;&nbsp;&nbsp;Communication and delivery </h5>");
									}

									if (bean.getQuestionId().equals("8")) {
										out.println("<h5>&nbsp;&nbsp;</h5>");
										out.println("<h5>&nbsp;&nbsp;&nbsp;&nbsp;Instructor outreach </h5>");
									}

									if (bean.getQuestionId().equals("11")) {
										out.println("<h5>&nbsp;&nbsp;</h5>");
										out.println("<h5>&nbsp;&nbsp;&nbsp;&nbsp;Assignments, Exams, and Grading </h5>");
									}

									if (bean.getQuestionId().equals("15")) {
										out.println("<h5>&nbsp;&nbsp;</h5>");
										out.println("<h5>&nbsp;&nbsp;&nbsp;&nbsp;Laboratories </h5>");
									}

									if (bean.getQuestionId().equals("18")) {
										out.println("<h5>&nbsp;&nbsp;</h5>");
										out.println("<h5>&nbsp;&nbsp;&nbsp;&nbsp;Tutorials </h5>");
									}
							%>
							<%
								if (bean.getAnsType().equalsIgnoreCase("radio")) {
										questionNo += (Integer.parseInt(bean.getQuestionId())) + " ";

										String content = "";
										if (listOfFeedback != null)
											for (FeedbackBean bn : listOfFeedback) {
												if (bn.getQuestionId().equals(bean.getQuestionId())) {
													content = bn.getFeedbackContent();
													break;
												}
											}
										else {
										}
										content = content == null ? "" : content;
							%>
							<div class="input-field col s12">
								<label class="card-title black-text col s12"><%=cnt + 1%>
									. <%=bean.getQuestionContent()%></label><br />
								<div class="input-field">
									<input type="hidden" id="CC" name="CC"
										value="<%=courseDetailsBean.getCourseCode()%>"> <input
										type="hidden" id="CN" name="CN"
										value="<%=courseDetailsBean.getCourseName()%>"> <input
										class="with-gap" name="que<%=bean.getQuestionId()%>"
										type="radio" id="<%=bean.getQuestionId()%>_op5" value="5"
										<%=content.equals("5") ? "checked" : ""%> /> <label
										for="<%=bean.getQuestionId()%>_op5"
										class="card-title black-text">5- Strongly Agree</label> <input
										class="with-gap" name="que<%=bean.getQuestionId()%>"
										type="radio" id="<%=bean.getQuestionId()%>_op4" value="4"
										<%=content.equals("4") ? "checked" : ""%> /> <label
										for="<%=bean.getQuestionId()%>_op4"
										class="card-title black-text">4- Agree</label> <input
										class="with-gap" name="que<%=bean.getQuestionId()%>"
										type="radio" id="<%=bean.getQuestionId()%>_op3" value="3"
										<%=content.equals("3") ? "checked" : ""%> /> <label
										for="<%=bean.getQuestionId()%>_op3"
										class="card-title black-text">3- Neutral</label> <input
										class="with-gap" name="que<%=bean.getQuestionId()%>"
										type="radio" id="<%=bean.getQuestionId()%>_op2" value="2"
										<%=content.equals("2") ? "checked" : ""%> /> <label
										for="<%=bean.getQuestionId()%>_op2"
										class="card-title black-text">2- Disagree</label> <input
										class="with-gap" name="que<%=bean.getQuestionId()%>"
										type="radio" id="<%=bean.getQuestionId()%>_op1" value="1"
										<%=content.equals("1") ? "checked" : ""%> /> <label
										for="<%=bean.getQuestionId()%>_op1"
										class="card-title black-text">1- Strongly Disagree</label>

								</div>

								<%
									}
								%>
							</div>
							<%
								cnt++;
								}

								for (QuestionsBean bean : listOfQuestionsForFeedback) {
	
									if (bean.getAnsType().equalsIgnoreCase("textarea")) {
										questionNo += (Integer.parseInt(bean.getQuestionId())) + " ";
										String content = "";
										if (listOfFeedback != null)
											for (FeedbackBean bn : listOfFeedback) {
												if (bn.getQuestionId().equals(bean.getQuestionId())) {
													content = bn.getFeedbackContent();
													break;
												}
											}
										else {
										}
										content = content == null ? "" : content;
							%>

							<div class=" input-field col s12 ">
								<label class="card-title black-text col s12"
									style="margin-left: 12px;"><%=cnt++%>. <%=bean.getQuestionContent()%></label><br />
								<br />

								<textarea class="input-field col s12 white black-text"
									name="que<%=bean.getQuestionId()%>" id="que<%=bean.getQuestionId() %>" onkeyup="countChar(this)"
									style="margin-left: 50px; width: 524.5px; height: 72px;"><%=content %></textarea>
									<div id="charNum" class="input-field col s12 white black-text" style="margin-left: 40px;"></div>
								<input type="hidden" id="courseCode" name="courseCode"
									value="<%=courseDetailsBean.getCourseCode()%>"> <input
									type="hidden" id="courseName" name="courseName"
									value="<%=courseDetailsBean.getCourseName()%>">
							</div>
							<br />


							<%
								}
							%>
							<%
								}
							%>

						</div>
						<div class="row">
							<div class="input-field col s4">
								<input type="hidden" name="feedbackedCourseCode"
									value="<%=courseDetailsBean.getCourseCode()%>"> <input
									type="hidden" name="courseCodes" id="courseCodes"
									value="<%=courseCodes%>"> <input type="hidden"
									name="questionNo" id="questionNo" value="<%=questionNo%>">
								<input type="hidden" id="action" name="action" />
							</div>

							<%
								if (counter != 0) {
							%>
							<div class="input-field col s4">
								<input type="button" onclick="method2()"
									class="btn waves-effect waves-light blue accent-4 hoverable"
									value="< Previous">
							</div>
							<%
								}
							%>

							<%
								if (counter != codes.length - 1) {
							%>
							<div class="input-field col s4">
								<input type="button" onclick="method1()"
									class="btn waves-effect waves-light blue accent-4 hoverable"
									value="Next > ">
							</div>
							<%
								} else {
							%>
							<div class="input-field col s4">
								<input type="button" onclick="fun()"
									class="btn waves-effect waves-light blue accent-4 hoverable"
									value="Submit">
							</div>
							<%
								}
							%>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<%@ include file="userFooter.jsp"%>
</body>
</html>