<%@page import="courseFeedback.bean.FeedbackBean"%>
<%@page import="courseFeedback.bean.CourseQueestionAVGBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<%
	ArrayList<FeedbackBean> feedbackbeans = (ArrayList<FeedbackBean>) request.getAttribute("courseQuestionAvg");
	ArrayList<String> questionId = (ArrayList<String>) request.getAttribute("courseQuestionId");
	String avg=(String)request.getAttribute("avg");
	int studentcnt = 0, cnt = 0;
%>
<body>
	<center>
		<table border="1">
			<tr>
				<th rowspan="2"><center>Sr.No</center></th>
				<th colspan="<%=questionId.size() - 1%>"><center>Question
						No.</center></th>
				<th rowspan="2"><center>Any Comments</center></th>
			</tr>
			<tr>
				<%
					for (String s : questionId) {
						if (!s.equals("21")) {
				%>
				<th><%=s%></th>
				<%
					}
					}
				%>
			</tr>
			<%
				for (FeedbackBean bean : feedbackbeans) {
					++cnt;
					if (bean.getQuestionId().equals("1")) {
						if (!bean.getFeedbackContent().equalsIgnoreCase("null")) {
			%>
			<tr>
				<td><center><%=++studentcnt%></center></td>
				<td><center><%=bean.getFeedbackContent()%></center></td>
				<%
					} else {
				%>
			
			<tr>
				<td><center><%=++studentcnt%></center></td>
				<td><center>&nbsp;</center></td>
				<%
					}
						} else if (cnt <= questionId.size() - 1) {
							if (!bean.getFeedbackContent().equalsIgnoreCase("null")) {
				%>
				<td><center><%=bean.getFeedbackContent()%></center></td>
				<%
					} else {
				%>
				<td><center>&nbsp;</center></td>
				<%
					}
						} else {
							cnt = 0;
							if (!bean.getFeedbackContent().equalsIgnoreCase("null")) {
				%>
				<td><%=bean.getFeedbackContent()%></td>
			</tr>
			<%
				} else {
			%>
			<td>jj&nbsp;</td>
			</tr>
			<%
				}

					}
				}
			double x=0.0;
			%>

			<tr>
			<td>Total <%=studentcnt%></td> 
			<%
				ArrayList<CourseQueestionAVGBean> courseQueestionAVGBeans = (ArrayList<CourseQueestionAVGBean>) request
						.getAttribute("courseQuestion");
			for(CourseQueestionAVGBean c: courseQueestionAVGBeans)
			{
			
			x+=c.getAvg();
			%>
				<td><%=c.getAvg()%></td>
			<%}
			%>
			<td>Course Avg - ><%=avg%>
			</tr>

		</table>
	</center>


</body>
</html>