package courseFeedback.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import courseFeedback.bean.QuestionsBean;
import courseFeedback.dao.QuestionsDAO;

public class QuestionsEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String questionId = request.getParameter("questionId");
		QuestionsBean questionsBean = new QuestionsDAO().getByPK(questionId);

		if (questionsBean != null) {
			request.setAttribute("questionsBean", questionsBean);
			request.getRequestDispatcher("questionsEdit.jsp").forward(request, response);
		}

	}

}
