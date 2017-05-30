package courseFeedback.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import courseFeedback.bean.ProgramDetailsBean;
import courseFeedback.dao.ProgramDetailsDAO;

public class ProgramDetailsEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String programDetailsId = request.getParameter("programDetailsId");
		ProgramDetailsBean programDetailsBean = new ProgramDetailsDAO().getByPK(programDetailsId);

		if (programDetailsBean != null) {
			request.setAttribute("programDetailsBean", programDetailsBean);
			request.getRequestDispatcher("programDetailsEdit.jsp").forward(request, response);
		}

	}

}
