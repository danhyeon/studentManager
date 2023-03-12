package studentManager.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAO {
	private Connection conn;
	ArrayList<UserDTO> userList = new ArrayList<UserDTO>();
	
	public UserDAO() {
		final String url = "jdbc:mysql://localhost/StudentManager";
		final String user = "root";
		final String password = "1234";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			this.conn = DriverManager.getConnection(url, user, password);
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<UserDTO> selectUser() {
		String sql = "select uid, password from user";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				UserDTO userDTO = new UserDTO();
				userDTO.setName(rs.getString(1));
				userDTO.setPassword(rs.getString(2));
				userList.add(userDTO);
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
