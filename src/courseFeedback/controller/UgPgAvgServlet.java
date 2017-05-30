package courseFeedback.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import courseFeedback.bean.CourseQueestionAVGBean;
import courseFeedback.bean.UGPGAvgBean;
import courseFeedback.dao.FeedbackDAO;

public class UgPgAvgServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<UGPGAvgBean> bean =  new FeedbackDAO().allUGPGAvg();

		request.setAttribute("courseQuestionAvg", bean);
		request.getRequestDispatcher("UgPgAvgList.jsp").forward(request, response);
	}

}
