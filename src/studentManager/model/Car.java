package studentManager.model;

import java.sql.Date;

public class Car {
	private int sno;
	private String owner;
	private String carNumber;
	private Date expireDate;
	
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getCarNumber() {
		return carNumber;
	}
	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}
	public Date getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}
	@Override
	public String toString() {
		return "Car [sno=" + sno + ", owner=" + owner + ", carNumber=" + carNumber + ", expireDate=" + expireDate + "]";
	} 
}