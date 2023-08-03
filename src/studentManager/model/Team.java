package studentManager.model;

public class Team {
	private int teamNumber;
	private String teamInfo;
	public int getTeamNumber() {
		return teamNumber;
	}
	public void setTeamNumber(int teamNumber) {
		this.teamNumber = teamNumber;
	}
	public String getTeamInfo() {
		return teamInfo;
	}
	public void setTeamInfo(String teamInfo) {
		this.teamInfo = teamInfo;
	}
	@Override
	public String toString() {
		return "Team [teamNumber=" + teamNumber + ", teamInfo=" + teamInfo + "]";
	}
}
