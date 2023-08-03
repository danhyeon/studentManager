package studentManager.view;

import java.awt.EventQueue;

public class MainProcess {
	LoginView loginView;
	MainView mainView;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainProcess main = new MainProcess();
					main.loginView = new LoginView();
					main.loginView.setMain(main);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	
	public void showMainFrame() {
		loginView.dispose();
		this.mainView = new MainView();
		mainView.setMainView(mainView);
	}
}