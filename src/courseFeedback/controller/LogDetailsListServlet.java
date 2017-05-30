package courseFeedback.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import courseFeedback.bean.LogBean;
import courseFeedback.dao.LogDetailsDAO;

public class LogDetailsListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<LogBean> listOfLogs = new LogDetailsDAO().list();

		if (listOfLogs != null) {
			request.setAttribute("listOfLogs", listOfLogs);
			request.getRequestDispatcher("logList.jsp").forward(request, response);
		}

	}

}
