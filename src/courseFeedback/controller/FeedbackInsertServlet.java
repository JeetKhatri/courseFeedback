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
import courseFeedback.dao.FeedbackDAO;

public class FeedbackInsertServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		HashMap<String, ArrayList<FeedbackBean>> hashmap = (HashMap<String, ArrayList<FeedbackBean>>) session
				.getAttribute("feedbackedCourse");
		String date = (String) session.getAttribute("timeStart");
		if (!new FeedbackDAO().insert(hashmap, date)) {
			request.getRequestDispatcher("thankYou.jsp").forward(request, response);
		}
	}

}
