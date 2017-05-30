package courseFeedback.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class FeedbackCourseSelectedServlet extends HttpServlet {
	private static final long serialVersionUID = -4411148427247402591L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String[] courseCode = request.getParameterValues("selCourseName");
		boolean isError = false;
		if (courseCode == null) {
			request.setAttribute("selectMultiCourse", "<font color=red>* Batch,Course Selection is Required.</font>");
			isError = true;
		}
		if (courseCode.length > 10) {
			request.setAttribute("selectMultiCourse", "<font color=red>* You cannot select more than 9 Courses.</font>");
			isError = true;
		}

		if (isError) {
			request.getRequestDispatcher("feedbackIndex.jsp").forward(request, response);
		} else {

			HttpSession session = request.getSession();
			session.setAttribute("courseCounter", 0);
			Date d = new Date();
			long date = d.getTime();
			Timestamp t = new Timestamp(date);
			String str = t + "";

			session.setAttribute("timeStart", str.substring(0, str.length() - 4));
			session.setAttribute("courseCode", courseCode);

			request.getRequestDispatcher("FeedbackCourseQuestionServlet").forward(request, response);
		}

	}

}
