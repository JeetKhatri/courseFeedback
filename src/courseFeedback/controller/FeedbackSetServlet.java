package courseFeedback.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import courseFeedback.bean.FeedbackBean;

@SuppressWarnings("serial")
public class FeedbackSetServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String feedbackedCourseCode = request.getParameter("feedbackedCourseCode");
		HttpSession session = request.getSession();
		HashMap<String, ArrayList<FeedbackBean>> hashmap;

		String questionNo = (String) request.getParameter("questionNo");
		String action = (String) request.getParameter("action");
		String[] x = questionNo.split(" ");
		ArrayList<FeedbackBean> listOfFeedbackBean = new ArrayList<>();
		FeedbackBean feedbackBean = new FeedbackBean();
		for (String id : x) {
			feedbackBean = new FeedbackBean();
			feedbackBean.setCourseCode(feedbackedCourseCode);
			feedbackBean.setQuestionId(id);
			feedbackBean.setFeedbackContent(request.getParameter("que" + id));
			listOfFeedbackBean.add(feedbackBean);
		}
		String[] courseCode = (String[]) session.getAttribute("courseCode");
		int courseCounter = (int) session.getAttribute("courseCounter");

		if (courseCounter == 0) {
			if (session.getAttribute("feedbackedCourse") == null)
				hashmap = new HashMap<>();
			else
				hashmap = (HashMap<String, ArrayList<FeedbackBean>>) session.getAttribute("feedbackedCourse");
			hashmap.put(feedbackedCourseCode, listOfFeedbackBean);
			session.setAttribute("feedbackedCourse", hashmap);
		} else {
			hashmap = (HashMap<String, ArrayList<FeedbackBean>>) session.getAttribute("feedbackedCourse");
			hashmap.put(feedbackedCourseCode, listOfFeedbackBean);
			session.setAttribute("feedbackedCourse", hashmap);
		}
		if (courseCounter < courseCode.length) {

			if (action.equals("next"))
				courseCounter++;
			else
				courseCounter--;
			session.setAttribute("courseCounter", courseCounter);
			request.getRequestDispatcher("FeedbackCourseQuestionServlet").forward(request, response);
		} else {
			request.getRequestDispatcher("thankYou.jsp").forward(request, response);
		}

	}

}
