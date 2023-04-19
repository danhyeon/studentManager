package studentManager.controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import studentManager.model.AttendCheck;
import studentManager.model.Student;

public class StudentDB {
	private Connection conn;
	private ArrayList<Student> students = new ArrayList<Student>();
	
	public StudentDB() {
		String url = "jdbc:mysql://localhost:3306/StudentManager";
		String user = "studentManager";
		String password = "dksxl124";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			this.conn = DriverManager.getConnection(url, user, password);
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteStudent(Student stud) {
		try {
			String sql = "delete from students where name = ? ";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, stud.getName());
			pstmt.executeUpdate();
			
			if ( pstmt != null ) pstmt.close();
			if ( conn != null ) conn.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void insertStudent(Student stud) {
		try {
			String sql = new StringBuilder()
					.append("insert into students (name, birth, gender, phoneNumber, email) ")
					.append("values (?, ?, ?, ?, ?) ")
					.toString();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, stud.getName());
			pstmt.setDate(2, stud.getBirth());
			pstmt.setString(3, stud.getGender());
			pstmt.setString(4, stud.getPhoneNumber());
			pstmt.setString(5, stud.getEmail());
			pstmt.executeUpdate();
			
			if ( pstmt != null ) pstmt.close();
			if ( conn != null ) conn.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateSeatNumber(String name, int seatNo) {
		try {
			String sql = "update students set seatNumber=? where name = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, seatNo);
			pstmt.setString(2, name);
			pstmt.executeUpdate();
			
			if ( pstmt != null ) pstmt.close();
			if ( conn != null ) conn.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void updateStudent(String changedName, Date birth, String gender, String phoneNumber, String email, String name) {
		try {
			String sql = "update students set name=?, birth=?, gender=?, phoneNumber=?, email=? where name = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, changedName);
			pstmt.setDate(2, birth);
			pstmt.setString(3, gender);
			pstmt.setString(4, phoneNumber);
			pstmt.setString(5, email);
			pstmt.setString(6, name);
			pstmt.executeUpdate();
			
			if ( pstmt != null ) pstmt.close();
			if ( conn != null ) conn.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public int countStudents() {
		int count=0;
		String sql = "select count(*) from students";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
			
			if ( rs != null ) rs.close();
			if ( pstmt != null ) pstmt.close();
			if ( conn != null ) conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return count;
	}
	
	public Student selectStudentBySeat(int seatNo) {
		Student st = new Student();
		String sql = "select name, birth, gender, phoneNumber, email from students where seatNumber=?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, seatNo);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				st.setName(rs.getString(1));
				st.setBirth(rs.getDate(2));
				st.setGender(rs.getString(3));
				st.setPhoneNumber(rs.getString(4));
				st.setEmail(rs.getString(5));
			}
			
			if ( rs != null ) rs.close();
			if ( pstmt != null ) pstmt.close();
			if ( conn != null ) conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return st;
	}
	
	public AttendCheck selectStudentByName(String name) {
		AttendCheck st = new AttendCheck();
		String sql = "select name, birth, gender, phoneNumber, email from students where name=?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				st.setName(rs.getString(1));
				st.setBirth(rs.getDate(2));
				st.setGender(rs.getString(3));
				st.setPhoneNumber(rs.getString(4));
				st.setEmail(rs.getString(5));
			}
			
			if ( rs != null ) rs.close();
			if ( pstmt != null ) pstmt.close();
			if ( conn != null ) conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return st;
	}
	
	public ArrayList<Student> selectStudents() {
		String sql = "select sno, name, seatNumber from students order by name";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Student st = new Student();
				st.setSno(rs.getInt(1));
				st.setName(rs.getString(2));
				st.setSeatNumber(rs.getInt(3));
				students.add(st);
			}
			
			if ( rs != null ) rs.close();
			if ( pstmt != null ) pstmt.close();
			if ( conn != null ) conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return students;
	}
}
