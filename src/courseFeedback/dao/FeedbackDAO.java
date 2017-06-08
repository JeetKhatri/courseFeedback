package courseFeedback.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import courseFeedback.bean.AdminBean;
import courseFeedback.bean.CourseDetailsBean;
import courseFeedback.bean.CourseQueestionAVGBean;
import courseFeedback.bean.CourseQuestionAvgTabularBean;
import courseFeedback.bean.DateFeedbackCounterBean;
import courseFeedback.bean.FeedbackBean;
import courseFeedback.bean.LogDetailsBean;
import courseFeedback.bean.QuestionsBean;
import courseFeedback.bean.UGPGAvgBean;
import courseFeedback.util.DBConnection;

public class FeedbackDAO {

	private Connection connection = null;
	private PreparedStatement pstmt = null;
	private Statement stmt = null;
	private ResultSet rs = null;
	boolean result = false;

	public List<QuestionsBean> listQuestion(CourseDetailsBean courseDetailsBean) {
		List<QuestionsBean> listOfQuestions = new ArrayList<QuestionsBean>();
		connection = DBConnection.getConnection();

		if (connection != null) {
			String selectSQL = "SELECT * FROM questions q WHERE q.questionId NOT IN (SELECT questionId FROM removespecialquestion) and isSpecial=0 and isAvailable=1 and (l=1 ";
			try {

				String tStr = "";
				String pStr = "";
				if (courseDetailsBean.getT() > 0) {
					tStr = " or t=1";
				}
				if (courseDetailsBean.getP() > 0) {
					pStr = " or p=1";
				}
				selectSQL = selectSQL + tStr + pStr + ")";
				pstmt = connection.prepareStatement(selectSQL);

				rs = pstmt.executeQuery();
				QuestionsBean question = null;
				while (rs.next()) {
					question = new QuestionsBean();
					question.setQuestionId(rs.getString("questionId"));
					question.setQuestionContent(rs.getString("questionContent"));
					question.setIsAvailable(rs.getString("isAvailable"));
					question.setIsLecture(rs.getString("L"));
					question.setIsTutorial(rs.getString("T"));
					question.setIsPrectical(rs.getString("P"));
					question.setIsSpecial(rs.getString("isSpecial"));
					question.setAnsType(rs.getString("ansType"));
					question.setYearId(rs.getString("yearId"));
					question.setTermId(rs.getString("termId"));
					listOfQuestions.add(question);
				}
				String selectAddSQL = "select * from addspecialquestion rs,questions q,courseDetails cd where rs.termCourseId=cd.termCourseId and q.questionid=rs.questionid and cd.courseCode=?";
				try {
					pstmt = connection.prepareStatement(selectAddSQL);
					pstmt.setString(1, courseDetailsBean.getCourseCode());
					rs = pstmt.executeQuery();
					while (rs.next()) {
						question = new QuestionsBean();
						question.setQuestionId(rs.getString("questionId"));
						question.setQuestionContent(rs.getString("questionContent"));
						question.setIsAvailable(rs.getString("isAvailable"));
						question.setIsLecture(rs.getString("L"));
						question.setIsTutorial(rs.getString("T"));
						question.setIsPrectical(rs.getString("P"));
						question.setIsSpecial(rs.getString("isSpecial"));
						question.setAnsType(rs.getString("ansType"));
						question.setYearId(rs.getString("yearId"));
						question.setTermId(rs.getString("termId"));
						listOfQuestions.add(question);
					}

				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					try {
						connection.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return listOfQuestions;
	}

	public boolean insert(HashMap<String, ArrayList<FeedbackBean>> hashmap, String timeStart) {

		LogDetailsBean logDetailsBean = new LogDetailsDAO().termRetrive();
		String insert = "insert into feedback(courseCode,questionId,feedbackContent,yearId,termId,studentdetailsId) values";
		String values = "";
		String courseCode = "";
		connection = DBConnection.getConnection();
		int check = 0;
		int fcheck = 0;

		if (connection != null) {
			try {
				connection.setAutoCommit(false);
				String selectSql = "select * from dateFeedbackCounter where date=?";
				Date curr = new Date();
				String date = curr.getDate() + "";
				String month = (curr.getMonth() + 1) + "";
				String year = (curr.getYear() + 1900) + "";

				if (date.length() == 1) {
					date = "0" + date;
				}
				if (month.length() == 1) {
					month = "0" + month;
				}
				String d = year + "-" + month + "-" + date;
				pstmt = connection.prepareStatement(selectSql);
				pstmt.setString(1, d);

				rs = pstmt.executeQuery();
				if (!rs.next()) {
					String insertDateFeedbackCounter = "insert into dateFeedbackCounter(date,counter) values(?,?)";
					pstmt = connection.prepareStatement(insertDateFeedbackCounter);
					pstmt.setString(1, d);
					pstmt.setInt(2, 1);
				} else {
					String updateDateFeedbackCounter = "update dateFeedbackCounter set counter=? where date=?";
					pstmt = connection.prepareStatement(updateDateFeedbackCounter);
					pstmt.setInt(1, rs.getInt("counter") + 1);
					pstmt.setString(2, d);
				}

				check = pstmt.executeUpdate();

			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			String xyz = "";
			for (String s : hashmap.keySet()) {
				xyz += s + ",";
			}
			String sql = "insert into studentDetails(selectedCourseCode,sessionStartTime) values(?,?)";

			try {
				pstmt = connection.prepareStatement(sql);
				pstmt.setString(1, xyz);
				pstmt.setString(2, timeStart);
				pstmt.executeUpdate();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			for (String s : hashmap.keySet()) {
				courseCode += s + ",";
				ArrayList<FeedbackBean> arrayList = hashmap.get(s);
				for (FeedbackBean f : arrayList) {
					/*
					 * String temp=""; for(int
					 * i=0;i<f.getFeedbackContent().length();i++) {
					 * if(f.getFeedbackContent().charAt(i)=='\'') { temp+="\'";
					 * } else { temp+=f.getFeedbackContent().charAt(i); } }
					 */

					String temp = f.getFeedbackContent();
					if (f.getFeedbackContent() != null) {
						temp = temp.replaceAll("'", "\'");
					}

					values += ",('" + f.getCourseCode() + "'," + f.getQuestionId() + ",'" + temp + "',"
							+ logDetailsBean.getYearId() + "," + logDetailsBean.getTermId() + ",LAST_INSERT_ID())";
				}
			}
			Statement stmt = null;
			try {
				stmt = connection.createStatement();
			} catch (SQLException e) {
				e.printStackTrace();
			}

			String temp = values;

			try {
				fcheck = stmt.executeUpdate(insert + temp.substring(1, temp.length()) + "");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		try {
			if (check > 0 && fcheck > 0) {
				connection.commit();
				connection.setAutoCommit(true);
			} else {
				connection.rollback();
				connection.commit();
				connection.setAutoCommit(true);
			}
		} catch (Exception e) {
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	public ArrayList<FeedbackBean> listFeedback() {

		ArrayList<FeedbackBean> listOfFeedback = new ArrayList<>();

		connection = DBConnection.getConnection();

		if (connection != null) {
			String select = "select f.studentdetailsId,f.courseCode,cd.courseCode,f.isAvailable,f.questionId,f.yearId,f.termId,cd.courseName,"
					+ "q.questionId,f.feedbackContent,q.questionContent "
					+ "from questions q,feedback f,courseDetails cd where "
					+ "f.questionId=q.questionId and f.courseCode=cd.courseCode and f.isAvailable=1 order by f.yearId desc,f.termId desc";
			try {
				pstmt = connection.prepareStatement(select);
				rs = pstmt.executeQuery();
				FeedbackBean feedbackBean = new FeedbackBean();
				while (rs.next()) {
					feedbackBean = new FeedbackBean();
					feedbackBean.setCourseCode(rs.getString("courseCode"));
					feedbackBean.setCourseName(rs.getString("courseName"));
					feedbackBean.setFeedbackContent(rs.getString("feedbackcontent"));
					feedbackBean.setQuestionId(rs.getString("questionId"));
					feedbackBean.setQuestionContent(rs.getString("questionContent"));
					feedbackBean.setYearId(rs.getString("yearId"));
					feedbackBean.setTermId(rs.getString("termId"));
					feedbackBean.setStudentdetailsId(rs.getString("studentdetailsId"));
					listOfFeedback.add(feedbackBean);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

		return listOfFeedback;

	}

	public int todayFeedbackCounter() {
		String pattern = "yyy-MM-dd";
		String date = new SimpleDateFormat(pattern).format(new Date());
		String select = "select * from datefeedbackCounter where date=?";
		connection = DBConnection.getConnection();
		int cnt = 0;
		if (connection != null) {
			try {
				pstmt = connection.prepareStatement(select);
				pstmt.setString(1, date);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					cnt = rs.getInt("counter");
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return cnt;
	}

	public int todayLogCounter() {
		String pattern = "yyy-MM-dd";
		String date = new SimpleDateFormat(pattern).format(new Date());
		String select = "select * from logdetails";
		connection = DBConnection.getConnection();
		String str = "";
		int cnt = 0;
		if (connection != null) {
			try {
				pstmt = connection.prepareStatement(select);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					str = rs.getString("updatedAt");
					if (str.substring(0, 10).equals(date))
						cnt++;
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return cnt;
	}

	public int todayScheduleCounter() {
		String pattern = "yyy-MM-dd";
		String date = new SimpleDateFormat(pattern).format(new Date());
		String select = "select * from dateprogramdetails where date=?";
		connection = DBConnection.getConnection();
		int cnt = 0;
		if (connection != null) {
			try {
				pstmt = connection.prepareStatement(select);
				pstmt.setString(1, date);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					cnt++;
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return cnt;
	}

	public int FeedbackCounter() {
		String select = "select * from datefeedbackCounter";
		connection = DBConnection.getConnection();
		int cnt = 0;
		if (connection != null) {
			try {
				pstmt = connection.prepareStatement(select);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					cnt += rs.getInt("counter");
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return cnt;
	}

	public int LogCounter() {
		String select = "select * from logdetails";
		connection = DBConnection.getConnection();
		int cnt = 0;
		if (connection != null) {
			try {
				pstmt = connection.prepareStatement(select);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					cnt++;
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return cnt;
	}

	public int ScheduleCounter() {
		String select = "select * from dateprogramdetails";
		connection = DBConnection.getConnection();
		int cnt = 0;
		if (connection != null) {
			try {
				pstmt = connection.prepareStatement(select);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					cnt++;
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return cnt;
	}

	public ArrayList<FeedbackBean> uniqueCourse() {
		ArrayList<FeedbackBean> listOfFeedback = new ArrayList<>();

		connection = DBConnection.getConnection();

		if (connection != null) {
			String select = "select distinct cd.courseName,f.courseCode,cd.courseCode,f.isAvailable from feedback f,courseDetails cd where cd.coursecode=f.coursecode and f.isAvailable=1 and feedbackcontent > 0  order by cd.coursename";
			try {
				pstmt = connection.prepareStatement(select);
				rs = pstmt.executeQuery();
				FeedbackBean feedbackBean = new FeedbackBean();
				while (rs.next()) {
					feedbackBean = new FeedbackBean();
					feedbackBean.setCourseCode(rs.getString("courseCode"));
					feedbackBean.setCourseName(rs.getString("courseName"));
					listOfFeedback.add(feedbackBean);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

		return listOfFeedback;

	}

	public int[] getCount(String courseCode) {
		int[] count = new int[5];

		connection = DBConnection.getConnection();

		if (connection != null) {
			String select = "select f.courseCode,cd.courseCode,f.isAvailable,f.questionId,f.yearId,f.termId,cd.courseName,"
					+ "q.questionId,f.feedbackContent,q.questionContent "
					+ "from questions q,feedback f,courseDetails cd where "
					+ "f.questionId=q.questionId and f.courseCode=cd.courseCode and f.isAvailable=1 and f.courseCode=?";
			try {
				pstmt = connection.prepareStatement(select);
				pstmt.setString(1, courseCode);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					if (rs.getString("feedbackContent") != null) {
						if (rs.getString("feedbackContent").equals("1")) {
							count[0]++;
						}
						if (rs.getString("feedbackContent").equals("2")) {
							count[1]++;
						}
						if (rs.getString("feedbackContent").equals("3")) {
							count[2]++;
						}
						if (rs.getString("feedbackContent").equals("4")) {
							count[3]++;
						}
						if (rs.getString("feedbackContent").equals("5")) {
							count[4]++;
						}
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

		return count;
	}

	public List<DateFeedbackCounterBean> listFeedbackCounter() {
		List<DateFeedbackCounterBean> listOfCounter = new ArrayList<DateFeedbackCounterBean>();
		connection = DBConnection.getConnection();

		if (connection != null) {
			String selectSQL = "SELECT * FROM datefeedbackcounter";
			try {
				pstmt = connection.prepareStatement(selectSQL);
				rs = pstmt.executeQuery();
				DateFeedbackCounterBean admin = null;
				while (rs.next()) {
					admin = new DateFeedbackCounterBean();
					admin.setCounter(rs.getString("counter"));
					admin.setDate(rs.getString("date"));
					admin.setDateFeedbackCounterId(rs.getString("dateFeedbackCounterId"));
					listOfCounter.add(admin);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
		return listOfCounter;
	}

	public ArrayList<CourseQueestionAVGBean> getCourseQuestionAvg(String courseCode, String yearId, String termId) {
		ArrayList<CourseQueestionAVGBean> courseQueestionAVGBeans = new ArrayList<>();
		String sql = "select avg(feedbackcontent) as avg,feedback.courseCode,coursename,questioncontent,questions.questionId,feedback.yearId,feedback.termID from feedback,questions,coursedetails where feedback.coursecode=? and questions.questionId=feedback.questionId and anstype='radio' and feedback.yearId=? and feedback.termId=? and  courseDetails.coursecode=feedback.coursecode group by feedback.questionId;";
		connection = DBConnection.getConnection();

		if (connection != null) {
			try {
				pstmt = connection.prepareStatement(sql);
				System.out.println(courseCode + "<<<<<<<<");
				pstmt.setString(1, courseCode);
				pstmt.setString(2, yearId);
				pstmt.setString(3, termId);
				ResultSet rs = pstmt.executeQuery();
				CourseQueestionAVGBean courseQueestionAVGBean = new CourseQueestionAVGBean();
				String insertSql = "insert into coursequestionavgdetails(courseCode,yearId,termId,questionId,avg) values";
				while (rs.next()) {
					insertSql += "('" + rs.getString("courseCode") + "'," + rs.getString("yearId") + ","
							+ rs.getString("termId") + "," + rs.getString("questionId") + ","
							+ String.format("%.2f", (Double.parseDouble(rs.getString("avg")) * 100) / 100) + "),";
				}

				String deleteThisYear = "delete from coursequestionavgdetails where yearId= ? and termId=? and coursecode=?";
				pstmt = connection.prepareStatement(deleteThisYear);
				pstmt.setString(1, yearId);
				pstmt.setString(2, termId);
				pstmt.setString(3, courseCode);
				int a = pstmt.executeUpdate();
				System.out.println(a + " delete row");

				stmt = connection.createStatement();
				System.out.println(insertSql);
				int r = stmt.executeUpdate(insertSql.substring(0, insertSql.length() - 1));
				System.out.println(r + "insert row");
				pstmt = connection.prepareStatement(
						"select * from coursequestionavgdetails where yearId=? and termId=? and coursecode=?");
				pstmt.setString(1, yearId);
				pstmt.setString(2, termId);
				pstmt.setString(3, courseCode);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					courseQueestionAVGBean = new CourseQueestionAVGBean();
					courseQueestionAVGBean.setCourseCode(courseCode);
					courseQueestionAVGBean
							.setCourseName(new CourseDetailsDAO().getNameCourseCode(rs.getString("courseCode")));
					courseQueestionAVGBean
							.setQuestionContent(new QuestionsDAO().getNameQuestionId(rs.getString("questionId")));
					courseQueestionAVGBean.setTermId(rs.getString("termId"));
					courseQueestionAVGBean.setYearId(rs.getString("yearId"));
					courseQueestionAVGBean.setAvg(rs.getDouble("avg"));
					courseQueestionAVGBean.setQuestionNumber(rs.getString("questionId"));
					courseQueestionAVGBeans.add(courseQueestionAVGBean);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return courseQueestionAVGBeans;
	}

	public void getAllCourseQuestionAvg() {

		ArrayList<CourseQueestionAVGBean> courseQueestionAVGBeans = new ArrayList<>();
		String sql = "select avg(feedbackcontent) as avg,coursecode,feedBACK.questionid from feedback,questions where questions.questionid=feedback.questionid and feedbackcontent>0 and anstype='radio' and feedback.yearId=? and feedback.termId=?  group by coursecode,questionid;";
		connection = DBConnection.getConnection();
		LogDetailsBean logDetailsBean = new LogDetailsDAO().termRetrive();
		if (connection != null) {
			try {
				pstmt = connection.prepareStatement(sql);
				pstmt.setString(1, logDetailsBean.getYearId());
				pstmt.setString(2, logDetailsBean.getTermId());
				ResultSet rs = pstmt.executeQuery();
				CourseQueestionAVGBean courseQueestionAVGBean = new CourseQueestionAVGBean();
				String insertSql = "insert into coursequestionavgdetails(courseCode,yearId,termId,questionId,avg) values";
				while (rs.next()) {
					insertSql += "('" + rs.getString("courseCode") + "'," + logDetailsBean.getYearId() + ","
							+ logDetailsBean.getTermId() + "," + rs.getString("questionId") + ","
							+ String.format("%.2f", (Double.parseDouble(rs.getString("avg")) * 100) / 100) + "),";
				}

				String deleteThisYear = "delete from coursequestionavgdetails where yearId= ? and termId=?";
				pstmt = connection.prepareStatement(deleteThisYear);
				pstmt.setString(1, logDetailsBean.getYearId());
				pstmt.setString(2, logDetailsBean.getTermId());
				int a = pstmt.executeUpdate();

				stmt = connection.createStatement();
				System.out.println(insertSql.substring(0, insertSql.length() - 1));
				int r = stmt.executeUpdate(insertSql.substring(0, insertSql.length() - 1));

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public ArrayList<CourseQueestionAVGBean> allCourseAvg(String yearId, String termId, String type) {
		ArrayList<CourseQueestionAVGBean> courseQueestionAVGBeans = new ArrayList<>();

		if (type.equalsIgnoreCase("ug"))
			type = "<500";
		else
			type = ">500";
		String sql = "select avg(feedbackcontent) AVG,feedback.courseCode,courseName,termCourseId,feedback.yearID,feedback.termID from feedback,courseDetails,questions where feedbackcontent > 0 and (substring(feedback.coursecode,3,3))"
				+ type
				+ "  and  feedback.courseCode=courseDetails.courseCode and feedback.questionID=questions.questionId and anstype='radio' and feedback.yearId=? and feedback.termId=? group by feedback.courseCode;";

		connection = DBConnection.getConnection();
		String insertSql = "insert into courseavgdetails(courseCode,yearId,termId,avg) values";
		if (connection != null) {
			try {
				pstmt = connection.prepareStatement(sql);
				pstmt.setString(1, yearId);
				pstmt.setString(2, termId);
				ResultSet rs = pstmt.executeQuery();
				CourseQueestionAVGBean courseQueestionAVGBean = new CourseQueestionAVGBean();
				String sqlDD = "";
				boolean flag = false;
				while (rs.next()) {
					flag = true;
					System.out.println("inside");
					sqlDD += ",('" + rs.getString("courseCode") + "'," + rs.getString("yearID") + ","
							+ rs.getString("termID") + ","
							+ String.format("%.2f", (Double.parseDouble(rs.getString("avg")) * 100) / 100) + ")";
					System.out.println(sqlDD + "||||||||||||");
				}

				String deleteThisYear = "delete from courseavgdetails where yearId=? and termId=? and (substring(coursecode,3,3))"
						+ type;
				pstmt = connection.prepareStatement(deleteThisYear);
				pstmt.setString(1, yearId);
				pstmt.setString(2, termId);
				int a = pstmt.executeUpdate();
				System.out.println(a + " row deleted");
				stmt = connection.createStatement();
				System.out.println(insertSql + sqlDD);

				int r = 0;
				if (flag == true) {
					r = stmt.executeUpdate(insertSql + sqlDD.substring(1, sqlDD.length()));
					System.out.println(r + " row inserted");
				}
				pstmt = connection.prepareStatement(
						"select * from courseavgdetails  where yearId=? and termId=? and (substring(coursecode,3,3))"
								+ type);
				pstmt.setString(1, yearId);
				pstmt.setString(2, termId);
				rs = pstmt.executeQuery();
				System.out.println(rs + " <-rs");
				while (rs.next()) {
					System.out.println("IN");
					courseQueestionAVGBean = new CourseQueestionAVGBean();
					courseQueestionAVGBean.setCourseCode(rs.getString("courseCode"));
					courseQueestionAVGBean
							.setCourseName(new CourseDetailsDAO().getNameCourseCode(rs.getString("courseCode")));
					courseQueestionAVGBean.setTermId(rs.getString("termId"));
					courseQueestionAVGBean.setYearId(rs.getString("yearId"));
					courseQueestionAVGBean.setAvg(rs.getDouble("avg"));
					courseQueestionAVGBeans.add(courseQueestionAVGBean);
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return courseQueestionAVGBeans;
	}

	public ArrayList<UGPGAvgBean> allUGPGAvg() {
		getAllCourseQuestionAvg();
		String sql = "select * from courseQuestionavgDetails where avg > 0 and yearID = ? and termID = ?";
		connection = DBConnection.getConnection();
		int ugcnt = 0, pgcnt = 0;
		double ugAvg = 0.0, pgAvg = 0.0;
		LogDetailsBean logDetailsBean = new LogDetailsDAO().termRetrive();
		String s = "";
		if (connection != null) {
			try {
				pstmt = connection.prepareStatement(sql);
				System.out.println("hiiiiiiiiii         "+ logDetailsBean.getYearId()+"                         "+logDetailsBean.getTermId());
				pstmt.setString(1, logDetailsBean.getYearId());
				pstmt.setString(2, logDetailsBean.getTermId());
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					s = rs.getString("courseCode");
					if (Integer.parseInt(s.substring(2, 5)) > 500) {
						pgcnt++;
						pgAvg += rs.getDouble("avg");
					} else {
						ugcnt++;
						ugAvg += rs.getDouble("avg");
					}
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(pgAvg + "<><><>");
		/*pgAvg = pgAvg / pgcnt;
		ugAvg = ugAvg / ugcnt;*/
		String deleteThisYear = "delete from ugpgavgdetails where yearId= ? and termId=?";
		try {

			pstmt = connection.prepareStatement(deleteThisYear);
			pstmt.setString(1, logDetailsBean.getYearId());
			pstmt.setString(2, logDetailsBean.getTermId());
			int a = pstmt.executeUpdate();

			String insertsql = "insert into ugpgavgdetails(name,yearId,TermId,avg) values(?,?,?,?)";
			pstmt = connection.prepareStatement(insertsql);
			pstmt.setString(1, "UG");
			pstmt.setString(2, logDetailsBean.getYearId());
			pstmt.setString(3, logDetailsBean.getTermId());
			if(ugAvg==0)
			{
				ugAvg=0.0;
			}
			else
			{
				ugAvg = ugAvg / ugcnt;
			}
			pstmt.setDouble(4, ugAvg);
			pstmt.setString(4, String.format("%.2f", (Double.parseDouble(ugAvg + "") * 100) / 100));
			int b = pstmt.executeUpdate();

			if(pgAvg==0)
			{
				pgAvg=0.0;
			}
			else
			{
				pgAvg = pgAvg / pgcnt;
			}
			pstmt = connection.prepareStatement(insertsql);
			pstmt.setString(1, "PG");
			pstmt.setString(2, logDetailsBean.getYearId());
			pstmt.setString(3, logDetailsBean.getTermId());
			pstmt.setString(4, String.format("%.2f", (Double.parseDouble(pgAvg + "") * 100) / 100));
			int pg = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String sqls = "select * from ugpgavgdetails";
		ArrayList<UGPGAvgBean> ugpgAvgBeans = new ArrayList<>();
		try {
			pstmt = connection.prepareStatement(sqls);
			ResultSet rss = pstmt.executeQuery();

			UGPGAvgBean ugpgAvgBean;
			while (rss.next()) {
				ugpgAvgBean = new UGPGAvgBean();
				ugpgAvgBean.setAvg(rss.getDouble("avg"));
				ugpgAvgBean.setType(rss.getString("name"));
				ugpgAvgBean.setYearId(rss.getString("yearid"));
				ugpgAvgBean.setTermId(rss.getString("termid"));
				System.out.println(ugpgAvgBean.getAvg() + "      " + ugpgAvgBean.getType());
				ugpgAvgBeans.add(ugpgAvgBean);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		System.out.println(pgAvg + ">>>>>");
		return ugpgAvgBeans;
	}

	public List<FeedbackBean> getCourseFeedback(String courseCode) {

		ArrayList<FeedbackBean> courseFeedabck = new ArrayList<>();
		String sql = "select distinct(studentdetailsid) from feedback where coursecode=?";
		connection = DBConnection.getConnection();

		if (connection != null) {
			try {
				System.out.println(courseCode + "LLL");
				pstmt = connection.prepareStatement(sql);
				pstmt.setString(1, courseCode);
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					System.out.println("hhhhhhhhhhhhhhhhh	" + rs.getString("studentdetailsid"));
					String sql1 = "select f.questionid,questionContent,ansType,f.courseCode,cd.coursename,f.studentDetailsId,feedbackContent from feedback f,studentDetails sd,questions q,coursedetails cd where f.studentDetailsId=sd.studentDetailsId and f.courseCode=? and f.coursecode=cd.coursecode and f.studentDetailsId=? and q.questionId=f.questionId";
					pstmt = connection.prepareStatement(sql1);
					pstmt.setString(1, courseCode);
					pstmt.setString(2, rs.getString("studentdetailsid"));
					ResultSet rs1 = pstmt.executeQuery();
					FeedbackBean bean = null;
					while (rs1.next()) {
						bean = new FeedbackBean();
						bean.setCourseCode(courseCode);
						bean.setCourseName(rs1.getString("coursename"));
						bean.setQuestionId(rs1.getString("questionid"));
						bean.setQuestionContent(rs1.getString("questionContent"));
						bean.setFeedbackContent(rs1.getString("feedbackContent"));
						bean.setAnsType(rs1.getString("ansType"));
						courseFeedabck.add(bean);
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return courseFeedabck;
	}

	public ArrayList<UGPGAvgBean> getUGPGQuestionAVG(String yearId, String termId, String type) {
		ArrayList<UGPGAvgBean> ugpgAvgBeans = new ArrayList<>();
		String temp = type;

		if (type.equalsIgnoreCase("ug"))
			type = "<500";
		else
			type = ">500";
		String sql = "select round(avg(avg),2) as avg,questionId from coursequestionAvgdetails where substring(coursecode,3,3)"
				+ type
				+ " and avg>0  and coursequestionAvgdetails.yearId=? and coursequestionAvgdetails.termId=?  group by questionID;";

		connection = DBConnection.getConnection();

		if (connection != null) {
			try {
				pstmt = connection.prepareStatement("delete from ugpgQuestionAvgDetails where yearId=" + yearId
						+ " and type='" + temp + "' and termid=" + termId);
				pstmt.execute();
				String insert = "insert into ugpgQuestionAvgDetails(type,questionID,yearId,termId,Avg) value(?,?,?,?,?)";
				pstmt = connection.prepareStatement(sql);
				pstmt.setString(1, yearId);
				pstmt.setString(2, termId);
				rs = pstmt.executeQuery();

				pstmt = connection.prepareStatement(insert);
				while (rs.next()) {
					System.out.println(temp + " ddddddddslslsllsl");
					pstmt.setString(1, temp);
					pstmt.setString(2, "" + rs.getString("questionID"));
					pstmt.setString(3, "" + yearId);
					pstmt.setString(4, "" + termId);
					pstmt.setString(5,
							"" + String.format("%.2f", (Double.parseDouble(rs.getString("avg")) * 100) / 100));
					pstmt.execute();
				}
			} catch (Exception e) {

				e.printStackTrace();
			}

			try {
				pstmt = connection.prepareStatement(
						"select * from ugpgQuestionAvgDetails where type=? and yearId=? and termId=?");
				pstmt.setString(1, temp);
				pstmt.setString(2, yearId);
				pstmt.setString(3, termId);
				rs = pstmt.executeQuery();
				UGPGAvgBean avgBean = new UGPGAvgBean();
				while (rs.next()) {
					avgBean = new UGPGAvgBean();
					avgBean.setAvg(rs.getDouble("avg"));
					avgBean.setType(rs.getString("type"));
					avgBean.setId(rs.getString("questionId"));
					avgBean.setYearId(rs.getString("yearId"));
					avgBean.setTermId(rs.getString("termId"));
					ugpgAvgBeans.add(avgBean);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return ugpgAvgBeans;

	}

	public ArrayList<UGPGAvgBean> getPGQuestionAVG() {
		new FeedbackDAO().getAllCourseQuestionAvg();
		new FeedbackDAO().allUGPGAvg();
		new FeedbackDAO().getAllCourseQuestionAvg();
		// new FeedbackDAO().allCourseAvg();
		ArrayList<UGPGAvgBean> ugpgAvgBeans = new ArrayList<>();
		// select round(avg(avg),2) from coursequestionAvgdetails where
		// substring(coursecode,3,3)<500 and avg>0 group by questionID;
		String sql1 = "select round(avg(avg),2) as avg,questionId from coursequestionAvgdetails where substring(coursecode,3,3)<500 and avg>0 group by questionID;";
		String sql2 = "select round(avg(avg),2) as avg,questionId from coursequestionAvgdetails where substring(coursecode,3,3)>500 and avg>0 group by questionID;";

		connection = DBConnection.getConnection();

		LogDetailsBean detailsBean = new LogDetailsDAO().termRetrive();
		if (connection != null) {
			try {
				pstmt = connection.prepareStatement("delete from ugpgQuestionAvgDetails where yearId="
						+ detailsBean.getYearId() + " and termid=" + detailsBean.getTermId());
				pstmt.execute();
				String insert = "insert into ugpgQuestionAvgDetails(type,questionID,yearId,termId,Avg) value(?,?,?,?,?)";
				pstmt = connection.prepareStatement(sql1);
				rs = pstmt.executeQuery();

				pstmt = connection.prepareStatement(insert);
				while (rs.next()) {
					pstmt.setString(1, "UG");
					pstmt.setString(2, "" + rs.getString("questionID"));
					pstmt.setString(3, "" + detailsBean.getYearId());
					pstmt.setString(4, "" + detailsBean.getTermId());
					pstmt.setString(5,
							"" + String.format("%.2f", (Double.parseDouble(rs.getString("avg")) * 100) / 100));
					pstmt.execute();
				}

				pstmt = connection.prepareStatement(sql2);
				rs = pstmt.executeQuery();

				pstmt = connection.prepareStatement(insert);
				while (rs.next()) {
					pstmt.setString(1, "PG");
					pstmt.setString(2, "" + rs.getString("questionID"));
					pstmt.setString(3, "" + detailsBean.getYearId());
					pstmt.setString(4, "" + detailsBean.getTermId());
					pstmt.setString(5,
							"" + String.format("%.2f", (Double.parseDouble(rs.getString("avg")) * 100) / 100));
					pstmt.execute();
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				pstmt = connection.prepareStatement("select * from ugpgQuestionAvgDetails where type=?");
				pstmt.setString(1, "PG");
				rs = pstmt.executeQuery();
				UGPGAvgBean avgBean = new UGPGAvgBean();
				while (rs.next()) {
					avgBean = new UGPGAvgBean();
					avgBean.setAvg(rs.getDouble("avg"));
					avgBean.setType(rs.getString("type"));
					avgBean.setId(rs.getString("questionId"));
					avgBean.setYearId(rs.getString("yearId"));
					avgBean.setTermId(rs.getString("termId"));
					ugpgAvgBeans.add(avgBean);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return ugpgAvgBeans;

	}

	public ArrayList<String> getCourseQuestionId(String courseCode) {
		String selectsql = "select distinct questionId from feedback where coursecode=?";
		connection = DBConnection.getConnection();
		ArrayList<String> questionId = new ArrayList<>();
		if (connection != null) {
			try {
				pstmt = connection.prepareStatement(selectsql);
				pstmt.setString(1, courseCode);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					questionId.add(rs.getString("questionID"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		System.out.println("siszzzzzzzzzzzzzz " + questionId.size());
		return questionId;
	}

	public String getCourseAvg(String courseCode) {

		String select = "select * from courseAvgdetails where coursecode= ?";
		connection = DBConnection.getConnection();
		if (connection != null) {
			try {
				pstmt = connection.prepareStatement(select);
				pstmt.setString(1, courseCode);
				ResultSet rs10 = pstmt.executeQuery();
				while (rs10.next()) {
					return rs10.getString("avg");
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return "";
	}

	public ArrayList<CourseQuestionAvgTabularBean> getUGCompleteInfoOfCourseQuestion() {
		ArrayList<CourseQuestionAvgTabularBean> list = new ArrayList<>();
		String selectSql = "select * from courseDetails where substring(coursecode,3,3)<500 order by(coursename)";
		connection = DBConnection.getConnection();
		if (connection != null) {
			try {
				CourseQuestionAvgTabularBean bean;
				pstmt = connection.prepareStatement(selectSql);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					bean = new CourseQuestionAvgTabularBean();
					bean.setCourseCode(rs.getString("courseCode"));
					bean.setCourseName(rs.getString("courseName"));
					bean.setL(rs.getString("L"));
					bean.setT(rs.getString("T"));
					bean.setP(rs.getString("P"));

					double avg[] = new double[21];
					selectSql = "select * from courseQuestionAvgDetails where Coursecode=?";
					pstmt = connection.prepareStatement(selectSql);
					pstmt.setString(1, bean.getCourseCode());
					ResultSet rs1 = pstmt.executeQuery();
					while (rs1.next()) {
						avg[Integer.parseInt(rs1.getString("questionID"))] = rs1.getDouble("avg");
					}
					bean.setAvg(avg);
					selectSql = "select * from courseAvgDetails where CourseCode=?";
					pstmt = connection.prepareStatement(selectSql);
					pstmt.setString(1, bean.getCourseCode());
					ResultSet rs3 = pstmt.executeQuery();
					while (rs3.next()) {
						bean.setCourseAvg(rs3.getString("avg"));
					}

					selectSql = "select count(distinct studentdetailsid) as cnt from feedback where coursecode=?";
					pstmt = connection.prepareStatement(selectSql);
					pstmt.setString(1, bean.getCourseCode());
					ResultSet rs4 = pstmt.executeQuery();
					while (rs4.next()) {
						bean.setNoOfStudent(Integer.parseInt(rs4.getString("cnt")));
					}
					list.add(bean);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public ArrayList<CourseQuestionAvgTabularBean> getPGCompleteInfoOfCourseQuestion() {
		ArrayList<CourseQuestionAvgTabularBean> list = new ArrayList<>();
		String selectSql = "select * from courseDetails where substring(coursecode,3,3)>500 order by(coursename)";
		connection = DBConnection.getConnection();
		if (connection != null) {
			try {
				CourseQuestionAvgTabularBean bean;
				pstmt = connection.prepareStatement(selectSql);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					bean = new CourseQuestionAvgTabularBean();
					bean.setCourseCode(rs.getString("courseCode"));
					bean.setCourseName(rs.getString("courseName"));
					bean.setL(rs.getString("L"));
					bean.setT(rs.getString("T"));
					bean.setP(rs.getString("P"));

					double avg[] = new double[21];
					selectSql = "select * from courseQuestionAvgDetails where Coursecode=?";
					pstmt = connection.prepareStatement(selectSql);
					pstmt.setString(1, bean.getCourseCode());
					ResultSet rs1 = pstmt.executeQuery();
					while (rs1.next()) {
						avg[Integer.parseInt(rs1.getString("questionID"))] = rs1.getDouble("avg");
					}
					bean.setAvg(avg);
					selectSql = "select * from courseAvgDetails where CourseCode=?";
					pstmt = connection.prepareStatement(selectSql);
					pstmt.setString(1, bean.getCourseCode());
					ResultSet rs3 = pstmt.executeQuery();
					while (rs3.next()) {
						bean.setCourseAvg(rs3.getString("avg"));
					}

					selectSql = "select count(distinct studentdetailsid) as cnt from feedback where coursecode=?";
					pstmt = connection.prepareStatement(selectSql);
					pstmt.setString(1, bean.getCourseCode());
					ResultSet rs4 = pstmt.executeQuery();
					while (rs4.next()) {
						bean.setNoOfStudent(Integer.parseInt(rs4.getString("cnt")));
					}
					list.add(bean);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public int totalUGStudent() {
		String selectSql = "select count(distinct studentdetailsID) as cnt from feedback where substring(coursecode,3,3)<500;";
		connection = DBConnection.getConnection();
		if (connection != null) {

			try {
				pstmt = connection.prepareStatement(selectSql);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					return rs.getInt("cnt");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return 0;

	}

	public int totalPGStudent() {
		String selectSql = "select count(distinct studentdetailsID) as cnt from feedback where substring(coursecode,3,3)>500;";
		connection = DBConnection.getConnection();
		if (connection != null) {

			try {
				pstmt = connection.prepareStatement(selectSql);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					return rs.getInt("cnt");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return 0;
	}

	public double UGAvg() {
		String selectSql = "select * from ugpgavgdetails where name=?";
		connection = DBConnection.getConnection();
		if (connection != null) {

			try {
				pstmt = connection.prepareStatement(selectSql);
				pstmt.setString(1, "UG");
				rs = pstmt.executeQuery();
				while (rs.next()) {
					return rs.getDouble("avg");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return 0;

	}

	public double PGAvg() {
		String selectSql = "select * from ugpgavgdetails where name=?";
		connection = DBConnection.getConnection();
		if (connection != null) {

			try {
				pstmt = connection.prepareStatement(selectSql);
				pstmt.setString(1, "PG");
				rs = pstmt.executeQuery();
				while (rs.next()) {
					return rs.getDouble("avg");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return 0;

	}

	public static void main(String[] args) {

		new FeedbackDAO().getUGPGQuestionAVG("2017", "34", "UG");

	}
}