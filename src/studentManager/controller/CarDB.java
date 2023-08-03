package studentManager.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CarDB {
	private Connection conn;
//	private ArrayList<Car> cars = new ArrayList<Car>();
	
	public CarDB() {
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
		}finally {
			if(conn != null) {
				try {
					conn.close();
				}catch(SQLException e) {}
			}
		}
	}
	
//	public void InsertCar() {
//		try {
//			//
//		}catch(SQLException e) {
//			e.printStackTrace();
//		}
//	}
	
//	public ArrayList<Car> selectCars() {
//		//
//		return cars;
//	}
}
