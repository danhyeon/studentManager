package studentManager.model;

import java.sql.Date;

public class StudentDTO {
	private int sno;
	private String name;
	private Date birth;
	private String gender;
	private String phoneNumber;
	private String email;
	private int seatNumber;
	
	public StudentDTO() {}
	
	public StudentDTO(String name, Date birth, String gender, String phoneNumber, String email) {
		this.name = name;
		this.birth = birth;
		this.gender = gender;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}
	
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getSeatNumber() {
		return seatNumber;
	}
	public void setSeatNumber(int seatNumber) {
		this.seatNumber = seatNumber;
	}
}
