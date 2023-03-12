package studentManager.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StudentDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private String sql;
	final String url = "jdbc:mysql://localhost/StudentManager";
	final String user = "root";
	final String password = "1234";
	private ArrayList<StudentDTO> studentDTOs = new ArrayList<StudentDTO>();
	private static StudentDAO instance = new StudentDAO();
	
	private void connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			this.conn = DriverManager.getConnection(url, user, password);
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static StudentDAO getInstance() { // 싱글톤 패턴
		instance.connect();
		return instance;
	}
	
	private void close() throws SQLException { // 리소스 할당 해제
		if ( rs != null ) rs.close();
		if ( pstmt != null ) pstmt.close();
		if ( conn != null ) conn.close();
	}
	
	public void deleteStudent(StudentDTO stud) { // 학생 삭제
		try {
			sql = "delete from students where name = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, stud.getName());
			pstmt.executeUpdate();
			close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void insertStudent(StudentDTO stud) { // 학생 추가
		try {
			sql = new StringBuilder()
					.append("insert into students (name, birth, gender, phoneNumber, email) ")
					.append("values (?, ?, ?, ?, ?) ")
					.toString();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, stud.getName());
			pstmt.setDate(2, stud.getBirth());
			pstmt.setString(3, stud.getGender());
			pstmt.setString(4, stud.getPhoneNumber());
			pstmt.setString(5, stud.getEmail());
			pstmt.executeUpdate();
			close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateSeatNumber(String name, int seatNo) {
		try {
			sql = "update students set seatNumber=? where name = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, seatNo);
			pstmt.setString(2, name);
			pstmt.executeUpdate();
			close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void updateStudent(StudentDTO stud, String name) {
		try {
			sql = new StringBuilder()
					.append("update students)")
					.append("set name=?, birth=?, gender=?, phoneNumber=?, email=? ")
					.append("where name = ?")
					.toString();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, stud.getName());
			pstmt.setDate(2, stud.getBirth());
			pstmt.setString(3, stud.getGender());
			pstmt.setString(4, stud.getPhoneNumber());
			pstmt.setString(5, stud.getEmail());
			pstmt.setString(6, name);
			pstmt.executeUpdate();
			close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public int countStudents() {
		int count = 0;
		try {
			sql = "select count(*) from students";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) count = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	public StudentDTO selectStudentBySeat(int seatNo) {
		StudentDTO st = new StudentDTO();
		sql = "select name, birth, gender, phoneNumber, email from students where seatNumber=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, seatNo);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				st.setName(rs.getString(1));
				st.setBirth(rs.getDate(2));
				st.setGender(rs.getString(3));
				st.setPhoneNumber(rs.getString(4));
				st.setEmail(rs.getString(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return st;
	}
	
	public StudentDTO selectStudentByName(String name) {
		StudentDTO st = new StudentDTO();
		sql= "select name, birth, gender, phoneNumber, email from students where name=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				st.setName(rs.getString(1));
				st.setBirth(rs.getDate(2));
				st.setGender(rs.getString(3));
				st.setPhoneNumber(rs.getString(4));
				st.setEmail(rs.getString(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return st;
	}
	
	public ArrayList<StudentDTO> selectStudents() {
		sql = "select sno, name, seatNumber from students order by name";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				StudentDTO st = new StudentDTO();
				st.setSno(rs.getInt(1));
				st.setName(rs.getString(2));
				st.setSeatNumber(rs.getInt(3));
				studentDTOs.add(st);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return studentDTOs;
	}
}
