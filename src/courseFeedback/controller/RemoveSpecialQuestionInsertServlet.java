package courseFeedback.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import courseFeedback.bean.RemoveSpecialQuestionBean;
import courseFeedback.dao.RemoveSpecialQuestionDAO;
import courseFeedback.util.GenrateMathodsUtils;
import courseFeedback.util.ValidationUtils;

@SuppressWarnings("serial")
public class RemoveSpecialQuestionInsertServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String termCourseId = request.getParameter("cmbCourseProgram");
		String questionId = request.getParameter("cmbQuestion");
		RemoveSpecialQuestionBean removeSpecialQuestionBean = new RemoveSpecialQuestionBean();
		boolean isError = false;

		if (termCourseId.equals("0")) {
			isError = true;
			request.setAttribute("courseProgramId", "<font color=red>* CourseCode is Required</font>");
		}

		else {
			request.setAttribute("cmbCourseProgramId", termCourseId);
			removeSpecialQuestionBean.setTermCourseId(Integer.parseInt(termCourseId));
		}

		if (questionId.equals("0")) {
			isError = true;
			request.setAttribute("questionId", "<font color=red>* Question is Required</font>");
		}

		else {
			request.setAttribute("cmbQuestionId", questionId);
			removeSpecialQuestionBean.setQuestionId(Integer.parseInt(questionId));
		}

		if (isError) {
			request.getRequestDispatcher("removeSpecialQuestionInsert.jsp").forward(request, response);
		} else {
			if (new RemoveSpecialQuestionDAO().insert(removeSpecialQuestionBean)) {
				response.sendRedirect("RemoveSpecialQuestionListServlet");
			} else {
				request.getRequestDispatcher("removeSpecialQuestionInsert.jsp").forward(request, response);
			}
		}
	}

}
