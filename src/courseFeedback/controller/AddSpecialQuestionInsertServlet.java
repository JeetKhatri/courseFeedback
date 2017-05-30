package courseFeedback.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import courseFeedback.bean.AddSpecialQuestionBean;
import courseFeedback.dao.AddSpecialQuestionDAO;
import courseFeedback.util.ValidationUtils;

public class AddSpecialQuestionInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String termCourseId = request.getParameter("cmbCourseProgram");
		String questionContent = request.getParameter("txtQuestionContent");
		String questionType[] = request.getParameterValues("rdoType");
		String ansType = request.getParameter("ansType");
		AddSpecialQuestionBean addSpecialQuestionBean = new AddSpecialQuestionBean();
		boolean isError = false;

		if (termCourseId.equals("0")) {
			isError = true;
			request.setAttribute("courseProgramId", "<font color=red>* CourseCode is Required</font>");
		} else {
			request.setAttribute("cmbCourseProgramId", termCourseId);
			addSpecialQuestionBean.setTermCourseId(Integer.parseInt(termCourseId));
		}
		if (ValidationUtils.isEmpty(questionContent)) {
			isError = true;
			request.setAttribute("questionContent", "<font color=red>* questionContent is Required</font>");
		} else {
			request.setAttribute("txtQuestionContent", questionContent);
			addSpecialQuestionBean.setQuestionContent(questionContent);
		}

		if (questionType == null) {
			isError = true;
			request.setAttribute("questionType", "<font color=red>* questionType is Required</font>");
		} else {
			request.setAttribute("txtQuestionType", questionType);
			String L = "0", T = "0", P = "0";
			for (int i = 0; i < questionType.length; i++) {
				if (questionType[i].equals("L")) {
					request.setAttribute("isL", "1");
					L = "1";
				} else if (questionType[i].equals("T")) {
					request.setAttribute("isT", "1");
					T = "1";
				} else if (questionType[i].equals("P")) {
					request.setAttribute("isP", "1");
					P = "1";
				}
			}
			addSpecialQuestionBean.setIsLecture(L);
			addSpecialQuestionBean.setIsTutorial(T);
			addSpecialQuestionBean.setIsPrectical(P);
		}
		addSpecialQuestionBean.setAnsType(ansType);
		if (isError) {
			request.getRequestDispatcher("addSpecialQuestionInsert.jsp").forward(request, response);
		} else {
			addSpecialQuestionBean.setIsAvailable("1");
			if (new AddSpecialQuestionDAO().insert(addSpecialQuestionBean)) {
				response.sendRedirect("AddSpecialQuestionListServlet");
			} else {
				response.sendRedirect("AddSpecialQuestionListServlet");
			}

		}

	}

}
