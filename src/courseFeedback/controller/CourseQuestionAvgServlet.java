package courseFeedback.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import courseFeedback.bean.CourseQueestionAVGBean;
import courseFeedback.dao.FeedbackDAO;

public class CourseQuestionAvgServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String yearId = request.getParameter("yearId");
		String termId = request.getParameter("termId");
		String courseCode = request.getParameter("code");

		ArrayList<CourseQueestionAVGBean> courseQuestionAVG = new FeedbackDAO().getCourseQuestionAvg(courseCode,yearId,termId);

		
		request.setAttribute("courseQuestionAvg", courseQuestionAVG);
		request.getRequestDispatcher("courseQuetionAvgList.jsp").forward(request, response);
	}

}
