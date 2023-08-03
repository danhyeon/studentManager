package studentManager.view;
 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import studentManager.controller.UserDB;
 
public class LoginView extends JFrame{
	private static final long serialVersionUID = 1L;
	private MainProcess main;
   
    private JButton btnLogin;
    private JButton btnInit;
    private JPasswordField passText;
    private JTextField userText;
    private boolean bLoginCheck;
    
    public LoginView() {
        setTitle("로그인");
        setSize(280, 150);
        setResizable(false);
        setLocation(1200, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        JPanel panel = new JPanel();
        placeLoginPanel(panel);
        add(panel);
        setVisible(true);
    }
   
    public void placeLoginPanel(JPanel panel){
        panel.setLayout(null);     
        JLabel userLabel = new JLabel("아이디");
        userLabel.setBounds(10, 10, 80, 25);
        panel.add(userLabel);
       
        JLabel passLabel = new JLabel("비밀번호");
        passLabel.setBounds(10, 40, 80, 25);
        panel.add(passLabel);
       
        userText = new JTextField(20);
        userText.setBounds(100, 10, 160, 25);
        panel.add(userText);
       
        passText = new JPasswordField(20);
        passText.setBounds(100, 40, 160, 25);
        panel.add(passText);
        passText.addActionListener(new ActionListener() {          
            @Override
            public void actionPerformed(ActionEvent e) {
                isLoginCheck();        
            }
        });
       
        btnInit = new JButton("재입력");
        btnInit.setBounds(10, 80, 100, 25);
        panel.add(btnInit);
        btnInit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userText.setText("");
                passText.setText("");
            }
        });
       
        btnLogin = new JButton("로그인");
        btnLogin.setBounds(160, 80, 100, 25);
        panel.add(btnLogin);
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	isLoginCheck();
            }
        });
    }
   
    public void isLoginCheck(){
    	try {
	    	new UserDB().selectUser().forEach(user->{
	    		if(userText.getText().equals(user.getName()) && new String(passText.getPassword()).equals(user.getPassword())){
	                bLoginCheck = true;
	                if(isLogin()) main.showMainFrame();
	            }else{
	                JOptionPane.showMessageDialog(null, "로그인 실패");
	            }
	    	});
    	}catch(Exception e) {
    		JOptionPane.showMessageDialog(null, "서버와 연결에 실패했습니다", "서버 연결 에러", JOptionPane.ERROR_MESSAGE);
    	}
    }
   
    public void setMain(MainProcess main) {
        this.main = main;
    }
   
 
    public boolean isLogin() {     
        return bLoginCheck;
    }
 
}