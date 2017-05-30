package courseFeedback.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import courseFeedback.bean.DateFeedbackCounterBean;
import courseFeedback.dao.FeedbackDAO;

public class DateFeedbackCounterListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<DateFeedbackCounterBean> listofCounter = (ArrayList<DateFeedbackCounterBean>) new FeedbackDAO().listFeedbackCounter();

		if (listofCounter.size() > 0) {
			request.setAttribute("listofCounter", listofCounter);
			request.getRequestDispatcher("displayFeedbackCounter.jsp").forward(request, response);
		}
		
	}
}
