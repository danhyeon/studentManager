package studentManager.controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.ParseException;

import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import studentManager.model.AttendCheck;
import studentManager.model.StudentDAO;
import studentManager.model.StudentDTO;
import studentManager.view.InfoView;
import studentManager.view.MainView;
import studentManager.view.TextPopupForm;

public class MainViewController {
	MainView view;
	InfoView infoview;
	
	public MainViewController(MainView view) {
		this.view = view;
	}
	
	public void studentInsertBTNAction() {
		this.view.AddListener_StudentInsertBTN(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StudentDTO studentDTO = new StudentDTO();
				studentDTO.setName(JOptionPane.showInputDialog("이름을 입력하세요"));
				try {
					java.util.Date d = new java.text.SimpleDateFormat("yyyyMMdd")
							.parse(JOptionPane.showInputDialog("생년월일을 입력하세요(8자리)"));
					studentDTO.setBirth(new java.sql.Date(d.getTime()));
				}catch (ParseException pe) {                                                                  
					pe.printStackTrace();
				}
				studentDTO.setGender(JOptionPane.showInputDialog("성별을 입력하세요(남/여)"));
				studentDTO.setPhoneNumber(JOptionPane.showInputDialog("연락처를 입력하세요"));
				studentDTO.setEmail(JOptionPane.showInputDialog("이메일를 입력하세요"));
			
				StudentDAO.getInstance().insertStudent(studentDTO);
				
				view.internalFrame.setVisible(false);
				JCheckBox chckbxNewCheckBox = new JCheckBox(studentDTO.getName());
				chckbxNewCheckBox.addItemListener(new ItemListener() {
					@Override
					public void itemStateChanged(ItemEvent e) {
						if(chckbxNewCheckBox.isSelected()) {
							AttendCheck ac = new AttendCheck(StudentDAO.getInstance().selectStudentByName(((JCheckBox)e.getItem()).getText()), "");
							chckbxNewCheckBox.setBackground(Color.BLUE);
							chckbxNewCheckBox.setForeground(Color.WHITE);
							view.tempList.add(ac);
						} else {
							view.tempList.removeIf(stud -> stud.getName().equals(chckbxNewCheckBox.getText()));
							chckbxNewCheckBox.setBackground(new Color(0xf0f0f0));
							chckbxNewCheckBox.setForeground(Color.BLACK);
						}
					}
				});
				view.checkBoxList.add(chckbxNewCheckBox);
				view.sidePanel_2.add(chckbxNewCheckBox);
				view.internalFrame.setVisible(true);
			}
		});
	}
	
	public void studentUpdateBTNAction() {
		this.view.AddListener_StudentUpdateBTN(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				view.tempList.forEach(stud->{
					infoview = new InfoView(stud, true);
					infoview.setMainView(view);
				});
				view.tempList.clear();
				view.checkBoxList.forEach( chbx -> {
					chbx.setBackground(new Color(0xf0f0f0));
					chbx.setForeground(Color.BLACK);
					chbx.setSelected(false);
				});
			}
		});
	}
	
	public void studentDeleteBTNAction() {
		this.view.AddListener_StudentDeleteBTN(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				view.tempList.forEach(stud->{
					int confirm = JOptionPane.showConfirmDialog(null, stud.getStud().getName() + "을(를) 삭제하시겠습니까?", "confirm",JOptionPane.YES_NO_OPTION);
					if(confirm==0) {
						StudentDAO.getInstance().deleteStudent(stud);
						view.internalFrame.setVisible(false);
						view.checkBoxList.forEach( chbx -> {
							if(chbx.getText().equals(stud.getName())) {
								view.sidePanel_2.remove(chbx);
							}
						});
						view.internalFrame.setVisible(true);
					}
				});
				view.tempList.clear();
				view.resetSeatLayout();
			}
		});
	}
	
	public void absentBTNAction() {
		this.view.AddListener_absentBTN(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				view.checkBoxList.forEach( chbx -> {
					if(chbx.getBackground()==Color.BLUE) {
						chbx.setBackground(Color.RED);
						chbx.setForeground(Color.WHITE);
					}
				});
				view.tempList.forEach( stud -> {
					stud.setReason(JOptionPane.showInputDialog(stud.getStud().getName() + "의 결석사유를 입력하세요"));
					view.absentList.add(stud);
					});
				view.tempList.clear();
				view.absentsb = new StringBuilder();
				if(view.absentList.size() != 0)
					view.absentsb.append(" 결석 " + view.absentList.size());
	
			}
		});
	}
	
	public void tardyBTNAction() {
		this.view.AddListener_tardyBTN(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				view.checkBoxList.forEach( chbx -> {
					if(chbx.getBackground()==Color.BLUE) {
						chbx.setBackground(Color.YELLOW);
						chbx.setForeground(Color.BLACK);
					}
				});
				view.tempList.forEach( stud -> {
					stud.setReason(JOptionPane.showInputDialog(stud.getStud().getName() + "의 지각사유를 입력하세요"));
					view.tardyList.add(stud);
					});
				view.tempList.clear();
				view.tardysb = new StringBuilder();
				if(view.tardyList.size() != 0)
					view.tardysb.append(" 지각 " + view.tardyList.size());
			}
		});
	}
	
	public void etcBTNAction() {
		this.view.AddListener_etcBTN(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				view.checkBoxList.forEach( chbx -> {
					if(chbx.getBackground()==Color.BLUE) {
						chbx.setBackground(Color.GREEN);
						chbx.setForeground(Color.BLACK);
					}
				});
				view.tempList.forEach( stud -> {
					stud.setReason(JOptionPane.showInputDialog(stud.getStud().getName() + "의 기타사유를 입력하세요"));
					view.etcList.add(stud);
					});
				view.tempList.clear();
				view.etcsb = new StringBuilder();
				if(view.etcList.size() != 0)
					view.etcsb.append(" 기타 " + view.etcList.size());
			}
		});
	}
	
	public void reportBTNAction() {
		this.view.AddListener_reportBTN(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TextPopupForm pop = new TextPopupForm(470, 500);
				ScrollPane scroll = new ScrollPane();
				view.attendCheck2();
				JTextArea textarea = new JTextArea(view.attendsb.toString());
				textarea.setFont(new Font("굴림", Font.BOLD, 30));
				textarea.setEditable(false);
				scroll.add(textarea, BorderLayout.CENTER);
				pop.add(scroll);
				view.attendsb.delete(0, view.attendsb.length());
				view.attendCheck1();
			}
		});
	}
}
