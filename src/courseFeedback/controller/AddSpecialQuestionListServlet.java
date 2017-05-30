package courseFeedback.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import courseFeedback.bean.AddSpecialQuestionBean;
import courseFeedback.dao.AddSpecialQuestionDAO;

public class AddSpecialQuestionListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<AddSpecialQuestionBean> listOfAddSpecialQuestionBean= new AddSpecialQuestionDAO().list();

		if (listOfAddSpecialQuestionBean != null) {
			request.setAttribute("listOfAddSpecialQuestionBean", listOfAddSpecialQuestionBean);
			request.getRequestDispatcher("addSpecialQuestionList.jsp").forward(request, response);
		}

	}
}
