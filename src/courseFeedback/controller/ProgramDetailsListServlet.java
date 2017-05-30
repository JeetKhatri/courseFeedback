package courseFeedback.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import courseFeedback.bean.ProgramDetailsBean;
import courseFeedback.dao.ProgramDetailsDAO;

public class ProgramDetailsListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<ProgramDetailsBean> listOfProgramDetails = new ProgramDetailsDAO().list();

		if (listOfProgramDetails != null) {
			request.setAttribute("listOfProgramDetails", listOfProgramDetails);
			request.getRequestDispatcher("programDetailsList.jsp").forward(request, response);
		}

	}

}
