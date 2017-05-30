package courseFeedback.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import courseFeedback.bean.DateProgramDetailsBean;
import courseFeedback.dao.DateProgramDetailsDAO;


public class DateProgramDetailsEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String dateProgramDetailsId = request.getParameter("dateProgramDetailsId");

		DateProgramDetailsBean dateProgramDetailsBean = new DateProgramDetailsDAO().getByPK(dateProgramDetailsId);

		if (dateProgramDetailsBean != null) {
			request.setAttribute("dateProgramDetailsBean", dateProgramDetailsBean);
			request.getRequestDispatcher("dateProgramDetailsEdit.jsp").forward(request, response);
		}

	}

}
