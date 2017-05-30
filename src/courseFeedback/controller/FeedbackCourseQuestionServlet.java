package courseFeedback.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import courseFeedback.bean.CourseDetailsBean;
import courseFeedback.bean.QuestionsBean;
import courseFeedback.dao.CourseDetailsDAO;
import courseFeedback.dao.FeedbackDAO;

@SuppressWarnings("serial")
public class FeedbackCourseQuestionServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String[] courseCode = (String[]) session.getAttribute("courseCode");
		int courseCounter = (int) session.getAttribute("courseCounter");

		if (courseCounter < courseCode.length) {
			CourseDetailsBean courseDetailsBean = new CourseDetailsDAO().getByCourseCode(courseCode[courseCounter]);
			if (courseDetailsBean != null) {
				ArrayList<QuestionsBean> listOfQuestionsForFeedback = (ArrayList<QuestionsBean>) new FeedbackDAO()
						.listQuestion(courseDetailsBean);
				request.setAttribute("courseDetailsBean", courseDetailsBean);
				request.setAttribute("listOfQuestionsForFeedback", listOfQuestionsForFeedback);
				request.getRequestDispatcher("feedbackCourseInsert.jsp").forward(request, response);
			} else {

			}
		} else {

			request.getRequestDispatcher("FeedbackInsertServlet").forward(request, response);

		}
	}

}
