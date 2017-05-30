package courseFeedback.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import courseFeedback.bean.TermDetailsBean;
import courseFeedback.dao.TermDetailsDAO;

public class TermDetailsListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<TermDetailsBean> listOfTermDetails = new TermDetailsDAO().list();

		if (listOfTermDetails != null) {
			request.setAttribute("listOfTermDetails", listOfTermDetails);
			request.getRequestDispatcher("termDetailsList.jsp").forward(request, response);
		}

	}

}
