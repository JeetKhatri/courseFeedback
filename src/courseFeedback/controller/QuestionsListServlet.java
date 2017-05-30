package courseFeedback.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import courseFeedback.bean.QuestionsBean;
import courseFeedback.dao.QuestionsDAO;

public class QuestionsListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<QuestionsBean> listOfQuestions = new QuestionsDAO().list();

		if (listOfQuestions != null) {
			request.setAttribute("listOfQuestions", listOfQuestions);
			request.getRequestDispatcher("questionsList.jsp").forward(request, response);
		}

	}

}
