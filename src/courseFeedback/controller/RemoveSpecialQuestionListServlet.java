package courseFeedback.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import courseFeedback.bean.RemoveSpecialQuestionBean;
import courseFeedback.dao.RemoveSpecialQuestionDAO;

public class RemoveSpecialQuestionListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<RemoveSpecialQuestionBean> listOfRemoveSpecialQuestionBean = new RemoveSpecialQuestionDAO().list();

		if (listOfRemoveSpecialQuestionBean != null) {
			request.setAttribute("listOfRemoveSpecialQuestionBean", listOfRemoveSpecialQuestionBean);
			request.getRequestDispatcher("removeSpecialQuestionList.jsp").forward(request, response);
		}

	}
}
