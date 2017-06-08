package courseFeedback.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import courseFeedback.bean.UGPGAvgBean;
import courseFeedback.dao.FeedbackDAO;

public class UgPgQuestionAvgServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		String yearId = request.getParameter("yearId");
		String termId = request.getParameter("termId");
		ArrayList<UGPGAvgBean> ugpgAvgBeans= new FeedbackDAO().getUGPGQuestionAVG(yearId,termId,type);
		request.setAttribute("ugpgQuestionAvg", ugpgAvgBeans);
		request.getRequestDispatcher("ugpgQuestionAvg.jsp").forward(request, response);
	}

}
