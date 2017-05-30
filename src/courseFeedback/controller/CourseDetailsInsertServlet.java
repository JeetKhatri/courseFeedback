package courseFeedback.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import courseFeedback.bean.CourseDetailsBean;
import courseFeedback.dao.CourseDetailsDAO;
import courseFeedback.util.ValidationUtils;

public class CourseDetailsInsertServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String yearTerm = request.getParameter("cmbYearTerm");
		CourseDetailsBean courseDetailsBean = new CourseDetailsBean();
		Boolean isError = false;
		if (yearTerm.equals("0 0")) {
			isError = true;
			request.setAttribute("cmbYearTerm", "<font color=red>* Year-Term  is Required</font>");
		} else {
			request.setAttribute("yearTerm", yearTerm);
			String[] yearTermDetails = yearTerm.split(" ");
			courseDetailsBean.setYearId(Integer.parseInt(yearTermDetails[0]));
			courseDetailsBean.setTermId(Integer.parseInt(yearTermDetails[1]));
		}
		String courseCode = request.getParameter("txtCourseCode");
		String courseName = request.getParameter("txtCourseName");
		String credit = request.getParameter("cmbCredit");

		if (ValidationUtils.isEmpty(courseName)) {
			isError = true;
			request.setAttribute("courseName", "<font color=red>* Course Name is Required</font>");
		} else {
			request.setAttribute("txtCourseName", courseName);
			courseDetailsBean.setCourseName(courseName);
		}

		if (ValidationUtils.isEmpty(courseCode)) {
			isError = true;
			request.setAttribute("courseCode", "<font color=red>* Course Code is Required</font>");
		} else {
			if (new CourseDetailsDAO().getByCode(courseCode)) {
				courseDetailsBean.setCourseCode(courseCode);
				isError = true;
				request.setAttribute("txtCourseCode", courseCode);
				request.setAttribute("courseCode", "<font color=red>* Course Code Already Exists</font>");
			} else {
				request.setAttribute("txtCourseCode", courseCode);
				courseDetailsBean.setCourseCode(courseCode);
			}
		}

		String questionType[] = request.getParameterValues("rdoType");
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
			courseDetailsBean.setL(Double.parseDouble(L));
			courseDetailsBean.setT(Double.parseDouble(T));
			courseDetailsBean.setP(Double.parseDouble(P));
		}
		courseDetailsBean.setCredit(Double.parseDouble(credit));
		request.setAttribute("credit", credit);
		if (isError) {
			request.getRequestDispatcher("courseDetailsInsert.jsp").forward(request, response);
		} else {
			courseDetailsBean.setCredit(Double.parseDouble(credit));
			if (new CourseDetailsDAO().insert(courseDetailsBean)) {
				request.getRequestDispatcher("CourseDetailsListServlet").forward(request, response);
			}
		}
	}

}
