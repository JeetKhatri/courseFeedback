package courseFeedback.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import courseFeedback.bean.FeedbackBean;
import courseFeedback.dao.FeedbackDAO;

public class GetCourseFeedbackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String courseCode = request.getParameter("selCourseName");

		ArrayList<FeedbackBean> list = (ArrayList<FeedbackBean>) new FeedbackDAO().getCourseFeedback(courseCode);
		if (list != null) {
			request.setAttribute("listOfFeedback", list);
			request.getRequestDispatcher("feedbackByCourse.jsp").forward(request, response);
		}
	}

}
