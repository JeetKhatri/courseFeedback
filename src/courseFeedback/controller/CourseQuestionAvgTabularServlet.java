package courseFeedback.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import courseFeedback.bean.CourseQueestionAVGBean;
import courseFeedback.bean.FeedbackBean;
import courseFeedback.dao.FeedbackDAO;

public class CourseQuestionAvgTabularServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// String courseCode = "IT618";
		String courseCode = request.getParameter("courseCode");
		new FeedbackDAO().getAllCourseQuestionAvg();
	/*	ArrayList<FeedbackBean> list = (ArrayList<FeedbackBean>) new FeedbackDAO().getCourseFeedback(courseCode);
		ArrayList<String> questionId = new FeedbackDAO().getCourseQuestionId(courseCode);
		ArrayList<CourseQueestionAVGBean> courseQueestionAVGBeans = new FeedbackDAO().getCourseQuestionAvg(courseCode);

		request.setAttribute("courseQuestionAvg", list);
		request.setAttribute("courseQuestion", courseQueestionAVGBeans);
		request.setAttribute("courseQuestionId", questionId);
		request.setAttribute("avg", new FeedbackDAO().getCourseAvg(courseCode));
		request.getRequestDispatcher("courseQuetionAvgTabularList.jsp").forward(request, response);
*/	}

}
