package studentManager.model;

public class AttendCheck extends StudentDTO {
	private StudentDTO stud;
	private String reason;
	
	public AttendCheck(StudentDTO stud, String reason) {
		this.stud = stud;
		this.reason = reason;
	}
	
	public StudentDTO getStud() {
		return stud;
	}
	
	public void setStud(StudentDTO stud) {
		this.stud = stud;
	}
	
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	
}
