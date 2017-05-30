package courseFeedback.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import courseFeedback.bean.CourseProgramDetailsBean;
import courseFeedback.dao.CourseProgramDetailsDAO;

public class CourseProgramDetailsListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		List<CourseProgramDetailsBean> listOfCourseProgramDetails = new CourseProgramDetailsDAO().list();

		if (listOfCourseProgramDetails != null) {
			request.setAttribute("listOfCourseProgramDetails", listOfCourseProgramDetails);
			request.getRequestDispatcher("courseProgramDetailsList.jsp").forward(request, response);
		}

	
	}

}
