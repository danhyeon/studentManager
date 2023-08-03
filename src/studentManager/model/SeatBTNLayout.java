package studentManager.model;

import javax.swing.JButton;

public class SeatBTNLayout {
	private JButton btn;
	private String name;
	private int seatNumber;
	
	public SeatBTNLayout(JButton btn, String name, int seatNumber ) {
		this.btn = btn;
		this.name = name;
		this.seatNumber = seatNumber;
		
	}

	public JButton getBtn() {
		return btn;
	}

	public void setBtn(JButton btn) {
		this.btn = btn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(int seatNumber) {
		this.seatNumber = seatNumber;
	}

	
}
