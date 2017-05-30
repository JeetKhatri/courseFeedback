package courseFeedback.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import courseFeedback.bean.SemesterProgramDetailsBean;
import courseFeedback.util.DBConnection;

public class SemesterProgramDetailsDAO {

	private Connection connection = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	boolean result = false;

	public List<SemesterProgramDetailsBean> list(String programDetailsId) {
		List<SemesterProgramDetailsBean> list = new ArrayList<SemesterProgramDetailsBean>();
		connection = DBConnection.getConnection();

		if (connection != null) {
			String selectSQL = "SELECT * FROM SemesterProgramDetails,ProgramDetails where "
					+ "SemesterProgramDetails.programDetailsId=? and "
					+ "ProgramDetails.ProgramDetailsId=SemesterProgramDetails.ProgramDetailsId order by SemesterProgramDetails.batchName";
			try {
				pstmt = connection.prepareStatement(selectSQL);
				pstmt.setString(1, programDetailsId);
				rs = pstmt.executeQuery();
				SemesterProgramDetailsBean bean = null;
				while (rs.next()) {
					bean = new SemesterProgramDetailsBean();
					bean.setBatchId(rs.getString("batchId"));
					bean.setBatchName(rs.getString("batchName"));
					bean.setProgramDetailsId(rs.getString("SemesterProgramDetails.programDetailsId"));
					bean.setProgramName(rs.getString("programName"));
					bean.setSemId(rs.getString("semId"));
					bean.setSemName(rs.getString("semName"));
					bean.setTermId(rs.getString("termId"));
					bean.setYearId(rs.getString("yearId"));

					list.add(bean);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
		return list;
	}

	public List<SemesterProgramDetailsBean> listOfCourseName(String semId) {
		List<SemesterProgramDetailsBean> list = new ArrayList<SemesterProgramDetailsBean>();
		connection = DBConnection.getConnection();

		if (connection != null) {
			String selectSQL = "select * from coursedetails,semestercoursedetails " + "where semId=? and "
					+ "coursedetails.termCourseId=semestercoursedetails.termCourseId order by courseName";
			try {
				pstmt = connection.prepareStatement(selectSQL);
				pstmt.setString(1, semId);
				rs = pstmt.executeQuery();
				SemesterProgramDetailsBean bean = null;
				while (rs.next()) {
					bean = new SemesterProgramDetailsBean();
					bean.setSemId(rs.getString("semId"));
					bean.setCourseCode(rs.getString("courseCode"));
					bean.setCourseName(rs.getString("courseName"));
					list.add(bean);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
		return list;
	}

}