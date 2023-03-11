package studentManager.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.ParseException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Locale;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import studentManager.controller.StudentDB;
import studentManager.model.AttendCheck;
import studentManager.model.SeatBTNLayout;
import studentManager.model.Student;

public class MainView extends JFrame {
	private static final long serialVersionUID = 1L;
	
	InfoView infoView;
	MainView mainView;
	
	public void setMainView(MainView mainView) {
		this.mainView = mainView;
	}
	
	private ArrayList<AttendCheck> absentList = new ArrayList<AttendCheck>();
	private ArrayList<AttendCheck> tardyList = new ArrayList<AttendCheck>();
	private ArrayList<AttendCheck> etcList = new ArrayList<AttendCheck>();
	private ArrayList<AttendCheck> tempList = new ArrayList<AttendCheck>();
	private StringBuilder attendsb = new StringBuilder();
	private StringBuilder absentsb = new StringBuilder();
	private StringBuilder tardysb = new StringBuilder();
	private StringBuilder etcsb = new StringBuilder();
	private ArrayList<JCheckBox> checkBoxList = new ArrayList<JCheckBox>();
	private ArrayList<SeatBTNLayout> layoutList = new ArrayList<SeatBTNLayout>();
	private ArrayList<JPanel> layoutPanelList = new ArrayList<JPanel>();
	private LineBorder lb = new LineBorder(Color.black, 1, true);
	
	public MainView() {
		setTitle("학생관리 프로그램");
		setBounds(500, 150, 1626, 1020);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JPanel topPanel = new JPanel();
		getContentPane().add(topPanel, BorderLayout.NORTH);
		
		JLabel topLabel = new JLabel("CODEHOWS");
		topLabel.setFont(new Font("굴림", Font.BOLD, 50));
		topPanel.add(topLabel);
		
		JPanel sidePanel = new JPanel();
		getContentPane().add(sidePanel, BorderLayout.WEST);
		
		JInternalFrame internalFrame = new JInternalFrame("학생 목록");
		sidePanel.add(internalFrame);
		
		JPanel sidePanel_1 = new JPanel();
		sidePanel_1.setLayout(new GridLayout(0, 1, 0, 0));
		internalFrame.getContentPane().add(sidePanel_1, BorderLayout.SOUTH);
		
		JPanel sidePanel_2 = new JPanel();
		sidePanel_2.setLayout(new GridLayout(0, 1, 0, 0));
		showStudentList(internalFrame, sidePanel_2);
		
		JButton student_insert_Button = new JButton("학생 추가");
		student_insert_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Student student = new Student();
				student.setName(JOptionPane.showInputDialog("이름을 입력하세요"));
				try {
					java.util.Date d = new java.text.SimpleDateFormat("yyyyMMdd")
							.parse(JOptionPane.showInputDialog("생년월일을 입력하세요(8자리)"));
					student.setBirth(new java.sql.Date(d.getTime()));
				}catch (ParseException pe) {
					pe.printStackTrace();
				}
				student.setGender(JOptionPane.showInputDialog("성별을 입력하세요(남/여)"));
				student.setPhoneNumber(JOptionPane.showInputDialog("연락처를 입력하세요"));
				student.setEmail(JOptionPane.showInputDialog("이메일를 입력하세요"));
				
				StudentDB sdb = new StudentDB();
				sdb.insertStudent(student);
				
				internalFrame.setVisible(false);
				JCheckBox chckbxNewCheckBox = new JCheckBox(student.getName());
				chckbxNewCheckBox.addItemListener(new ItemListener() {
					@Override
					public void itemStateChanged(ItemEvent e) {
						if(chckbxNewCheckBox.isSelected()) {
							StudentDB sdb = new StudentDB();
							AttendCheck ac = new AttendCheck();
							ac = sdb.selectStudentByName(chckbxNewCheckBox.getText());
							chckbxNewCheckBox.setBackground(Color.BLUE);
							chckbxNewCheckBox.setForeground(Color.WHITE);
							tempList.add(ac);
						} else {
							tempList.removeIf(stud -> stud.getName().equals(chckbxNewCheckBox.getText()));
							chckbxNewCheckBox.setBackground(new Color(0xf0f0f0));
							chckbxNewCheckBox.setForeground(Color.BLACK);
						}
					}
				});
				checkBoxList.add(chckbxNewCheckBox);
				sidePanel_2.add(chckbxNewCheckBox);
				internalFrame.setVisible(true);
			}
		});
		student_insert_Button.setBorder(lb);
		student_insert_Button.setBackground(Color.GRAY);
		student_insert_Button.setForeground(Color.WHITE);
		sidePanel_1.add(student_insert_Button);
		
		JButton student_update_Button = new JButton("정보 수정");
		student_update_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tempList.forEach(stud->{
					infoView = new InfoView(stud, true);
					infoView.setMainView(mainView);
				});
				tempList.clear();
				checkBoxList.forEach( chbx -> {
					chbx.setBackground(new Color(0xf0f0f0));
					chbx.setForeground(Color.BLACK);
					chbx.setSelected(false);
				});
			}
		});
		student_update_Button.setBorder(lb);
		student_update_Button.setBackground(Color.GRAY);
		student_update_Button.setForeground(Color.WHITE);
		sidePanel_1.add(student_update_Button);
		
		JButton student_delete_Button = new JButton("학생 삭제");
		student_delete_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tempList.forEach(stud->{
					int confirm = JOptionPane.showConfirmDialog(null, stud.getName() + "을(를) 삭제하시겠습니까?", "confirm",JOptionPane.YES_NO_OPTION);
					if(confirm==0) {
						new StudentDB().deleteStudent(stud);
						internalFrame.setVisible(false);
						checkBoxList.forEach( chbx -> {
							if(chbx.getText().equals(stud.getName())) {
								sidePanel_2.remove(chbx);
							}
						});
						internalFrame.setVisible(true);
					}
				});
				tempList.clear();
				resetSeatLayout();
			}
		});
		student_delete_Button.setBorder(lb);
		student_delete_Button.setBackground(Color.GRAY);
		student_delete_Button.setForeground(Color.WHITE);
		sidePanel_1.add(student_delete_Button);
		
		JButton btnNewButton = new JButton("결석 처리");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkBoxList.forEach( chbx -> {
					if(chbx.getBackground()==Color.BLUE) {
						chbx.setBackground(Color.RED);
						chbx.setForeground(Color.WHITE);
					}
				});
				tempList.forEach( stud -> {
					stud.setReason(JOptionPane.showInputDialog(stud.getName() + "의 결석사유를 입력하세요"));
					absentList.add(stud);
					});
				tempList.clear();
				absentsb = new StringBuilder();
				if(absentList.size() != 0)
					absentsb.append(" 결석 " + absentList.size());

			}
		});
		sidePanel_1.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("지각 처리");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkBoxList.forEach( chbx -> {
					if(chbx.getBackground()==Color.BLUE) {
						chbx.setBackground(Color.YELLOW);
						chbx.setForeground(Color.BLACK);
					}
				});
				tempList.forEach( stud -> {
					stud.setReason(JOptionPane.showInputDialog(stud.getName() + "의 지각사유를 입력하세요"));
					tardyList.add(stud);
					});
				tempList.clear();
				tardysb = new StringBuilder();
				if(tardyList.size() != 0)
					tardysb.append(" 지각 " + tardyList.size());
			}
		});
		sidePanel_1.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("기타 처리");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkBoxList.forEach( chbx -> {
					if(chbx.getBackground()==Color.BLUE) {
						chbx.setBackground(Color.GREEN);
						chbx.setForeground(Color.BLACK);
					}
				});
				tempList.forEach( stud -> {
					stud.setReason(JOptionPane.showInputDialog(stud.getName() + "의 기타사유를 입력하세요"));
					etcList.add(stud);
					});
				tempList.clear();
				etcsb = new StringBuilder();
				if(etcList.size() != 0)
					etcsb.append(" 기타 " + etcList.size());
			}
		});
		sidePanel_1.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("보고");
		attendCheck1();
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TextPopupForm pop = new TextPopupForm(470, 500);
				ScrollPane scroll = new ScrollPane();
				attendCheck2();
				JTextArea textarea = new JTextArea(attendsb.toString());
				textarea.setFont(new Font("굴림", Font.BOLD, 30));
				textarea.setEditable(false);
				scroll.add(textarea, BorderLayout.CENTER);
				pop.add(scroll);
				attendsb.delete(0, attendsb.length());
				attendCheck1();
//				checkBoxList.forEach( chbx -> {
//					chbx.setBackground(new Color(0xf0f0f0));
//					chbx.setForeground(Color.BLACK);
//				});
			}
		});
		sidePanel_1.add(btnNewButton_3);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel mainPanel1 = new JPanel();
		tabbedPane.addTab("자리 배치도", null, mainPanel1, null);
		mainPanel1.setLayout(new GridLayout(4, 2, 500, 100));
		
		JPanel mainPanel1_1 = new JPanel();
		mainPanel1.add(mainPanel1_1);
		mainPanel1_1.setLayout(new GridLayout(2, 2, 0, 0));
		layoutPanelList.add(mainPanel1_1);
		
		JPanel mainPanel1_2 = new JPanel();
		mainPanel1.add(mainPanel1_2);
		mainPanel1_2.setLayout(new GridLayout(2, 2, 0, 0));
		layoutPanelList.add(mainPanel1_2);
		
		JPanel mainPanel1_3 = new JPanel();
		mainPanel1.add(mainPanel1_3);
		mainPanel1_3.setLayout(new GridLayout(2, 2, 0, 0));
		layoutPanelList.add(mainPanel1_3);
		
		JPanel mainPanel1_4 = new JPanel();
		mainPanel1.add(mainPanel1_4);
		mainPanel1_4.setLayout(new GridLayout(2, 2, 0, 0));
		layoutPanelList.add(mainPanel1_4);
		
		JPanel mainPanel1_5 = new JPanel();
		mainPanel1.add(mainPanel1_5);
		mainPanel1_5.setLayout(new GridLayout(2, 2, 0, 0));
		layoutPanelList.add(mainPanel1_5);
		
		JPanel mainPanel1_6 = new JPanel();
		mainPanel1.add(mainPanel1_6);
		mainPanel1_6.setLayout(new GridLayout(2, 2, 0, 0));
		layoutPanelList.add(mainPanel1_6);
		
		JPanel mainPanel1_7 = new JPanel();
		mainPanel1.add(mainPanel1_7);
		mainPanel1_7.setLayout(new GridLayout(2, 2, 0, 0));
		layoutPanelList.add(mainPanel1_7);
		
		JPanel mainPanel1_8 = new JPanel();
		mainPanel1.add(mainPanel1_8);
		mainPanel1_8.setLayout(new GridLayout(2, 2, 0, 0));
		layoutPanelList.add(mainPanel1_8);
		
		for(int i=0; i<8; i++) {
			for(int j=1; j<=4; j++) {
				layoutPanelList.get(i).add(setStudentSeats(j+i*4));
			}
		}
		
		JPanel panel_4 = new JPanel();
		tabbedPane.addTab("미정", null, panel_4, null);
		setVisible(true);
	}
	
	public void resetSeatLayout() {
		layoutList.forEach(seat->{
			String name = new StudentDB().selectStudentBySeat(seat.getSeatNumber()).getName();
			if(name==null) {
				seat.getBtn().setText("공석");
				seat.getBtn().setForeground(new Color(192, 192, 192));
			}else {
				seat.getBtn().setText(name);
			}
		});
	}
	
	public void resetStudentList() {
		ArrayList<Student> studs = new StudentDB().selectStudents();
		checkBoxList.forEach(chbx->{
			chbx.setText(null);
		});
		for(int i=0; i<studs.size(); i++) {
			checkBoxList.get(i).setText(studs.get(i).getName());
		}
	}
	
	private void showStudentList(JInternalFrame inter, JPanel pane) {
		inter.getContentPane().add(pane, BorderLayout.NORTH);
		new StudentDB().selectStudents().forEach(std -> {
			JCheckBox chckbxNewCheckBox = new JCheckBox(std.getName());
			chckbxNewCheckBox.addItemListener(new ItemListener() {
				@Override
				public void itemStateChanged(ItemEvent e) {
					if(chckbxNewCheckBox.isSelected()) {
						StudentDB sdb = new StudentDB();
						AttendCheck ac = new AttendCheck();
						ac = sdb.selectStudentByName(chckbxNewCheckBox.getText());
						chckbxNewCheckBox.setBackground(Color.BLUE);
						chckbxNewCheckBox.setForeground(Color.WHITE);
						tempList.add(ac);
					} else {
						tempList.removeIf(stud -> stud.getName().equals(chckbxNewCheckBox.getText()));
						absentList.removeIf(stud -> stud.getName().equals(chckbxNewCheckBox.getText()));
						tardyList.removeIf(stud -> stud.getName().equals(chckbxNewCheckBox.getText()));
						etcList.removeIf(stud -> stud.getName().equals(chckbxNewCheckBox.getText()));
						chckbxNewCheckBox.setBackground(new Color(0xf0f0f0));
						chckbxNewCheckBox.setForeground(Color.BLACK);
					}
				}
			});
			pane.add(chckbxNewCheckBox);
			checkBoxList.add(chckbxNewCheckBox);
			inter.setVisible(true);
			});
	}
	
	private JButton setStudentSeats(int seatNo) {
		StudentDB sdb = new StudentDB();
		Student std = sdb.selectStudentBySeat(seatNo);
		String name = std.getName();
		
		JButton seat = null;
		if(name!=null) {
			seat = new JButton(name);
			seat.setFont(new Font("굴림", Font.BOLD, 50));
		} else {
			seat = new JButton("공석");
			seat.setFont(new Font("굴림", Font.BOLD, 50));
			seat.setForeground(new Color(192, 192, 192));
		}
		layoutList.add(new SeatBTNLayout(seat, name, seatNo));
		seat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(((JButton)e.getSource()).getText().equals("공석")) {
					int confirm = JOptionPane.showConfirmDialog(null, "인원을 배치하시겠습니까?", "confirm",JOptionPane.YES_NO_OPTION );
					if(confirm==0) {
						String ChangedName = JOptionPane.showInputDialog("배치할 인원의 이름을 입력하세요.");
						layoutList.forEach(lol ->{
							if(lol.getBtn().getText().equals(ChangedName)) {
								lol.getBtn().setText("공석"); 
								lol.getBtn().setFont(new Font("굴림", Font.BOLD, 50));
								lol.getBtn().setForeground(new Color(192, 192, 192));
							}
						});
						layoutList.forEach(lol ->{
							if(lol.getBtn() == (JButton)e.getSource()) {
								new StudentDB().updateSeatNumber(ChangedName, lol.getSeatNumber());
								((JButton)e.getSource()).setText(ChangedName);
								((JButton)e.getSource()).setFont(new Font("굴림", Font.BOLD, 50));
								((JButton)e.getSource()).setForeground(new Color(0, 0, 0));
							}
						});
					}
				} else {
					Student stud = new StudentDB().selectStudentByName(((JButton)e.getSource()).getText());
					infoView = new InfoView(stud, false);
				}
				
			}
		});
		
		return seat;
	}
	
	private void attendCheck1() {
		LocalDate date = LocalDate.now();
		DayOfWeek dayOfWeek = date.getDayOfWeek();
		StringTokenizer st = new StringTokenizer(LocalDate.now().toString(), "-");
		st.nextToken();
		attendsb = new StringBuilder();
		attendsb.append("["+Integer.parseInt(st.nextToken()))
			.append("/"+st.nextToken()+"(")
			.append(dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.KOREAN)+")")
			.append(" 1차 교육생 출석현황]\n");
	}
	
	private void attendCheck2() {
		StudentDB sdb = new StudentDB();
		int stdCount = sdb.countStudents();
		attendCheck1();
		attendsb.append("출석 " + (stdCount 
					- absentList.size() - tardyList.size() - etcList.size()))
				.append(absentsb.toString())
				.append(tardysb.toString())
				.append(etcsb.toString()+"\n");
		if(absentList.size()>0) {
			attendsb.append("\n결석\n");
			absentList.forEach( std -> {
				attendsb.append(std.getName() + " - ");
				attendsb.append(std.getReason() + "\n");
				});
		}
		if(tardyList.size()>0) {
			attendsb.append("\n지각\n");
			tardyList.forEach( std -> {
				attendsb.append(std.getName() + " - ");
				attendsb.append(std.getReason() + "\n");
			});
		}
		if(etcList.size()>0) {
			attendsb.append("\n기타\n");
			etcList.forEach( std -> {
				attendsb.append(std.getName() + " - ");
				attendsb.append(std.getReason() + "\n");
			});
		}
	}
}
