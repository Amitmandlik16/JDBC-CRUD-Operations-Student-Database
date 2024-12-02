package persistence;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.Student;

//Persistence logic using JDBC API
public class StudentDaoImpl implements IStudentDao {
	Connection connection = null;
	PreparedStatement pstmt = null;
	PreparedStatement pstmtd = null;
	PreparedStatement pstmtu = null;
	ResultSet resultSet = null;

	@Override
	public String addStudent(Integer sid, String sname, Integer sage, String address) {
		String sqlInsertQuery = "insert into student(`id`,`name`,`age`, `address`) values(?,?,?,?)";
		try {
			connection = JdbcUtil.getJdbcConnection();

			if (connection != null)
				pstmt = connection.prepareStatement(sqlInsertQuery);

			if (pstmt != null) {
				pstmt.setInt(1, sid);
				pstmt.setString(2, sname);
				pstmt.setInt(3, sage);
				pstmt.setString(4, address);
				int rowAffected = pstmt.executeUpdate();

				if (rowAffected == 1) {
					return "success";
				}
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}

		return "failure";
	}

	@Override
	public Student searchStudent(Integer sid) {

		String sqlSearchQuery = "select id,name,age,address from student where id=?";
		Student student = null;
		try {
			connection = JdbcUtil.getJdbcConnection();

			if (connection != null)
				pstmt = connection.prepareStatement(sqlSearchQuery);

			if (pstmt != null) {
				pstmt.setInt(1, sid);
				resultSet = pstmt.executeQuery();

				if (resultSet != null) {
					if (resultSet.next()) {
						student = new Student();

						// copy resultSet data to student object
						student.setSid(resultSet.getInt(1));
						student.setSname(resultSet.getString(2));
						student.setSage(resultSet.getInt(3));
						student.setAddress(resultSet.getString(4));

						return student;
					}

				}
			}

		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		return student;

	}

	@Override
	public String updateStudent(Integer sid, String sname, Integer sage, String address) {
		String sqlUpdateQuery = "update student set name=?,age=?,address=? where id=?";
		try {
			connection = JdbcUtil.getJdbcConnection();

			if (connection != null)
				pstmtu = connection.prepareStatement(sqlUpdateQuery);

			if (pstmtu != null) {
				pstmtu.setString(1, sname);
				pstmtu.setInt(2, sage);
				pstmtu.setString(3, address);
				pstmtu.setInt(4, sid);
				int rowAffected = pstmtu.executeUpdate();

				if (rowAffected == 1) {
					return "success";
				}
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}

		return "failure";
	}

	@Override
	public String deleteStudent(Integer sid) {
		String sqlDeleteQuery = "delete from student where id=?";
		try {
			connection = JdbcUtil.getJdbcConnection();

			if (connection != null)
				pstmtd = connection.prepareStatement(sqlDeleteQuery);

			if (pstmtd != null) {
				pstmtd.setInt(1, sid);
				int rowAffected = pstmtd.executeUpdate();

				if (rowAffected == 1) {
					return "success";
				} else {
					return "not found";
				}
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}

		return "failure";
	}

}
