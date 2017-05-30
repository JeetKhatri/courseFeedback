package courseFeedback.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import courseFeedback.bean.DateProgramDetailsBean;
import courseFeedback.dao.DateProgramDetailsDAO;

public class DateProgramDetailsListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<DateProgramDetailsBean> listOfDateProgramDetails = new DateProgramDetailsDAO().list();

		if (listOfDateProgramDetails != null) {
			request.setAttribute("listOfDateProgramDetails", listOfDateProgramDetails);
			request.getRequestDispatcher("dateProgramDetailsList.jsp").forward(request, response);
		}

	}

}
