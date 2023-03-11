package studentManager.view;

import javax.swing.JFrame;

public class TextPopupForm extends JFrame{
	private static final long serialVersionUID = 1L;
	
	public TextPopupForm(int width, int height) {
		initialize(width, height);
	}
	
	private void initialize(int width, int height) {
		setSize(width,height);
		setLocation(800,500);
		setVisible(true);
	}
}
