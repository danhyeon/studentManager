package studentManager.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import studentManager.controller.StudentDB;
import studentManager.model.Student;

public class InfoView extends JFrame {
	MainView mainView;
	
	public void setMainView(MainView mainView) {
		this.mainView = mainView;
	}
	
    public InfoView(Student std, boolean flag) {
        setTitle("학생 정보");
        if(flag) {
        	setSize(280, 250);
        }else {
        	setSize(280, 200);
        }
        
        setResizable(false);
        setLocation(800, 450);
        
        JPanel panel = new JPanel();
        placeLoginPanel(panel, std, flag);
        add(panel);
        setVisible(true);
    }
   
    public void placeLoginPanel(JPanel panel, Student std, boolean flag){
        panel.setLayout(null);     
        JLabel nameLabel = new JLabel("이름");
        nameLabel.setBounds(10, 10, 80, 25);
        panel.add(nameLabel);
        
        JTextField nameText = new JTextField(20);
        nameText.setText(std.getName());
        nameText.setBounds(100, 10, 160, 25);
        nameText.setEditable(flag);
        panel.add(nameText);
       
        JLabel birthLabel = new JLabel("생년월일");
        birthLabel.setBounds(10, 40, 80, 25);
        panel.add(birthLabel);

        JTextField birthText = new JTextField(20);
        birthText.setText(std.getBirth().toString());
        birthText.setBounds(100, 40, 160, 25);
        birthText.setEditable(flag);
        panel.add(birthText);
        
        JLabel genderLabel = new JLabel("성별");
        genderLabel.setBounds(10, 70, 80, 25);
        panel.add(genderLabel);
        
        JTextField genderText = new JTextField(20);
        genderText.setText(std.getGender());
        genderText.setBounds(100, 70, 160, 25);
        genderText.setEditable(flag);
        panel.add(genderText);
       
        JLabel mobileLabel = new JLabel("연락처");
        mobileLabel.setBounds(10, 100, 80, 25);
        panel.add(mobileLabel);
        
        JTextField mobileText = new JTextField(20);
        mobileText.setText(std.getPhoneNumber());
        mobileText.setBounds(100, 100, 160, 25);
        mobileText.setEditable(flag);
        panel.add(mobileText);
        
        JLabel emailLabel = new JLabel("이메일");
        emailLabel.setBounds(10, 130, 80, 25);
        panel.add(emailLabel);
       
        JTextField emailText = new JTextField(20);
        emailText.setText(std.getEmail());
        emailText.setBounds(100, 130, 160, 25);
        emailText.setEditable(flag);
        panel.add(emailText);
        
        if(flag) {
	        JButton cancel = new JButton("취소");
	        cancel.setBounds(10, 160, 100, 25);
	        panel.add(cancel);
	        cancel.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	dispose();
	            }
	        });
	       
	        JButton saveBtn = new JButton("저장");
	        saveBtn.setBounds(160, 160, 100, 25);
	        panel.add(saveBtn);
	        saveBtn.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	try {
	            		java.util.Date d = new java.text.SimpleDateFormat("yyyy-MM-dd").parse(birthText.getText());
	            		new StudentDB().updateStudent(nameText.getText(), new java.sql.Date(d.getTime()),
	            				genderText.getText(), mobileText.getText(), emailText.getText(), std.getName());
		            }catch (ParseException pe) {
						pe.printStackTrace();
					}
	            	JOptionPane.showMessageDialog(null,"저장되었습니다.","확인",JOptionPane.PLAIN_MESSAGE);
	            	mainView.resetSeatLayout();
	            	mainView.resetStudentList();
	            	dispose();
	            }
	        });
        }
    }
}
