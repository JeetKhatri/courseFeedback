package courseFeedback.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import courseFeedback.bean.AdminBean;
import courseFeedback.dao.AdminDAO;

public class AdminListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<AdminBean> listOfAdmin = new AdminDAO().list();

		if (listOfAdmin != null) {
			request.setAttribute("listOfAdmin", listOfAdmin);
			request.getRequestDispatcher("adminList.jsp").forward(request, response);
		}

	}

}
