package studentManager.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import studentManager.model.User;

public class UserDB {
	private Connection conn;
	ArrayList<User> userList = new ArrayList<User>();
	
	public UserDB() {
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
	
	public ArrayList<User> selectUser() {
		String sql = "select uid, password from user";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				User user = new User();
				user.setName(rs.getString(1));
				user.setPassword(rs.getString(2));
				userList.add(user);
			}
			
			if ( rs != null ) rs.close();
			if ( pstmt != null ) pstmt.close();
			if ( conn != null ) conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return userList;
	}
}
