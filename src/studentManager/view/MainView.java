package studentManager.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.ScrollPane;
import java.awt.Toolkit;
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

import studentManager.model.AttendCheck;
import studentManager.model.SeatBTNLayout;
import studentManager.model.StudentDAO;
import studentManager.model.StudentDTO;

public class MainView extends JFrame {
	private static final long serialVersionUID = 1L;
	
	InfoView infoView;
	MainView mainView;
	
	final double w = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	final double h = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	
	public ArrayList<AttendCheck> absentList = new ArrayList<AttendCheck>();
	public ArrayList<AttendCheck> tardyList = new ArrayList<AttendCheck>();
	public ArrayList<AttendCheck> etcList = new ArrayList<AttendCheck>();
	public ArrayList<AttendCheck> tempList = new ArrayList<AttendCheck>();
	public StringBuilder attendsb = new StringBuilder();
	public StringBuilder absentsb = new StringBuilder();
	public StringBuilder tardysb = new StringBuilder();
	public StringBuilder etcsb = new StringBuilder();
	public ArrayList<JCheckBox> checkBoxList = new ArrayList<JCheckBox>();
	public ArrayList<SeatBTNLayout> layoutList = new ArrayList<SeatBTNLayout>();
	public ArrayList<JPanel> layoutPanelList = new ArrayList<JPanel>();
	public LineBorder lb = new LineBorder(Color.black, 1, true);
	
	public JPanel topPanel;
	public JLabel topLabel;
	public JPanel sidePanel;
	public JPanel sidePanel_1;
	public JPanel sidePanel_2;
	public JButton student_insert_Button;
	public JButton student_update_Button;
	public JButton student_delete_Button;
	public JButton absentBTN;
	public JButton tardyBTN;
	public JButton etcBTN;
	public JButton reportBTN;
	public JTabbedPane tabbedPane;
	public JInternalFrame internalFrame;
	public JPanel seatPannel1;
	public JPanel seatPannel1_1;
	public JPanel seatPannel1_2;
	public JPanel seatPannel1_3;
	public JPanel seatPannel1_4;
	public JPanel seatPannel1_5;
	public JPanel seatPannel1_6;
	public JPanel seatPannel1_7;
	public JPanel seatPannel1_8;
	public JPanel panel_4;
	
	public void setMainView(MainView mainView) {
		this.mainView = mainView;
	}
	
	public MainView() {
		setTitle("학생관리 프로그램");
		setBounds(100, 20, (int)w-200, (int)h-100);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		topPanel = new JPanel();
		topLabel = new JLabel("CODEHOWS");
		sidePanel = new JPanel();
		sidePanel_1 = new JPanel();
		sidePanel_2 = new JPanel();
		internalFrame = new JInternalFrame("학생 목록");
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		topLabel.setFont(new Font("굴림", Font.BOLD, (int)h/40));
		sidePanel_1.setLayout(new GridLayout(0, 1, 0, 0));
		sidePanel_2.setLayout(new GridLayout(0, 1, 0, 0));
		
		topPanel.add(topLabel);
		internalFrame.getContentPane().add(sidePanel_1, BorderLayout.SOUTH);
		sidePanel.add(internalFrame);
		getContentPane().add(topPanel, BorderLayout.NORTH);
		getContentPane().add(sidePanel, BorderLayout.WEST);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		showStudentList(internalFrame, sidePanel_2);
		
		student_insert_Button = new JButton("학생 추가");
		student_insert_Button.setBorder(lb);
		student_insert_Button.setBackground(Color.GRAY);
		student_insert_Button.setForeground(Color.WHITE);
		sidePanel_1.add(student_insert_Button);
		
		student_update_Button = new JButton("정보 수정");
		student_update_Button.setBorder(lb);
		student_update_Button.setBackground(Color.GRAY);
		student_update_Button.setForeground(Color.WHITE);
		sidePanel_1.add(student_update_Button);
		
		student_delete_Button = new JButton("학생 삭제");
		student_delete_Button.setBorder(lb);
		student_delete_Button.setBackground(Color.GRAY);
		student_delete_Button.setForeground(Color.WHITE);
		sidePanel_1.add(student_delete_Button);
		
		absentBTN = new JButton("결석 처리");
		tardyBTN = new JButton("지각 처리");
		etcBTN = new JButton("기타 처리");
		reportBTN = new JButton("보고");
		sidePanel_1.add(absentBTN);
		sidePanel_1.add(tardyBTN);
		sidePanel_1.add(etcBTN);
		sidePanel_1.add(reportBTN);
		
		attendCheck1();
		
		seatPannel1 = new JPanel();
		seatPannel1_1 = new JPanel();
		seatPannel1_2 = new JPanel();
		seatPannel1_3 = new JPanel();
		seatPannel1_4 = new JPanel();
		seatPannel1_5 = new JPanel();
		seatPannel1_6 = new JPanel();
		seatPannel1_7 = new JPanel();
		seatPannel1_8 = new JPanel();
		layoutPanelList.add(seatPannel1_1);
		layoutPanelList.add(seatPannel1_2);
		layoutPanelList.add(seatPannel1_3);
		layoutPanelList.add(seatPannel1_4);
		layoutPanelList.add(seatPannel1_5);
		layoutPanelList.add(seatPannel1_6);
		layoutPanelList.add(seatPannel1_7);
		layoutPanelList.add(seatPannel1_8);
		seatPannel1.setLayout(new GridLayout(4, 2, (int)w/10, (int)h/30));
		seatPannel1_1.setLayout(new GridLayout(2, 2, 0, 0));
		seatPannel1_2.setLayout(new GridLayout(2, 2, 0, 0));
		seatPannel1_3.setLayout(new GridLayout(2, 2, 0, 0));
		seatPannel1_4.setLayout(new GridLayout(2, 2, 0, 0));
		seatPannel1_5.setLayout(new GridLayout(2, 2, 0, 0));
		seatPannel1_6.setLayout(new GridLayout(2, 2, 0, 0));
		seatPannel1_7.setLayout(new GridLayout(2, 2, 0, 0));
		seatPannel1_8.setLayout(new GridLayout(2, 2, 0, 0));
		seatPannel1.add(seatPannel1_1);
		seatPannel1.add(seatPannel1_2);
		seatPannel1.add(seatPannel1_3);
		seatPannel1.add(seatPannel1_4);
		seatPannel1.add(seatPannel1_5);
		seatPannel1.add(seatPannel1_6);
		seatPannel1.add(seatPannel1_7);
		seatPannel1.add(seatPannel1_8);
		tabbedPane.addTab("자리 배치도", null, seatPannel1, null);
		for(int i=0; i<8; i++) {
			for(int j=1; j<=4; j++) {
				layoutPanelList.get(i).add(setStudentSeats(j+i*4));
			}
		}
		
		panel_4 = new JPanel();
		tabbedPane.addTab("미정", null, panel_4, null);
		setVisible(true);
	}
	
	public void AddListener_StudentInsertBTN(ActionListener listener) {
		student_insert_Button.addActionListener(listener);
	}
	
	public void AddListener_StudentUpdateBTN(ActionListener listener) {
		student_update_Button.addActionListener(listener);
	}
	
	public void AddListener_StudentDeleteBTN(ActionListener listener) {
		student_delete_Button.addActionListener(listener);
	}
	
	public void AddListener_absentBTN(ActionListener listener) {
		absentBTN.addActionListener(listener);
	}
	
	public void AddListener_tardyBTN(ActionListener listener) {
		tardyBTN.addActionListener(listener);
	}
	
	public void AddListener_etcBTN(ActionListener listener) {
		etcBTN.addActionListener(listener);
	}
	
	public void AddListener_reportBTN(ActionListener listener) {
		reportBTN.addActionListener(listener);
	}
	
	public void resetSeatLayout() {
		layoutList.forEach(seat->{
			String name = StudentDAO.getInstance().selectStudentBySeat(seat.getSeatNumber()).getName();
			if(name==null) {
				seat.getBtn().setText("공석");
				seat.getBtn().setForeground(new Color(192, 192, 192));
			}else {
				seat.getBtn().setText(name);
			}
		});
	}
	
	public void resetStudentList() {
		ArrayList<StudentDTO> studs = StudentDAO.getInstance().selectStudents();
		checkBoxList.forEach(chbx->{
			chbx.setText(null);
		});
		for(int i=0; i<studs.size(); i++) {
			checkBoxList.get(i).setText(studs.get(i).getName());
		}
	}
	
	private void showStudentList(JInternalFrame inter, JPanel pane) {
		inter.getContentPane().add(pane, BorderLayout.NORTH);
		StudentDAO.getInstance().selectStudents().forEach(std -> {
			JCheckBox chckbxNewCheckBox = new JCheckBox(std.getName());
			chckbxNewCheckBox.setFont(new Font("굴림", Font.BOLD, (int)h/80));
			chckbxNewCheckBox.addItemListener(new ItemListener() {
				@Override
				public void itemStateChanged(ItemEvent e) {
					if(chckbxNewCheckBox.isSelected()) {
						AttendCheck ac = new AttendCheck(StudentDAO.getInstance().selectStudentByName(chckbxNewCheckBox.getText()), "");
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
		StudentDAO sdb = StudentDAO.getInstance();
		StudentDTO std = sdb.selectStudentBySeat(seatNo);
		String name = std.getName();
		
		JButton seat = null;
		if(name!=null) {
			seat = new JButton(name);
			seat.setFont(new Font("굴림", Font.BOLD, (int)h/20));
		} else {
			seat = new JButton("공석");
			seat.setFont(new Font("굴림", Font.BOLD, (int)h/20));
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
								lol.getBtn().setFont(new Font("굴림", Font.BOLD, (int)h/20));
								lol.getBtn().setForeground(new Color(192, 192, 192));
							}
						});
						layoutList.forEach(lol ->{
							if(lol.getBtn() == (JButton)e.getSource()) {
								StudentDAO.getInstance().updateSeatNumber(ChangedName, lol.getSeatNumber());
								((JButton)e.getSource()).setText(ChangedName);
								((JButton)e.getSource()).setFont(new Font("굴림", Font.BOLD, (int)h/20));
								((JButton)e.getSource()).setForeground(new Color(0, 0, 0));
							}
						});
					}
				} else {
					StudentDTO stud = StudentDAO.getInstance().selectStudentByName(((JButton)e.getSource()).getText());
					infoView = new InfoView(stud, false);
				}
				
			}
		});
		
		return seat;
	}
	
	public void attendCheck1() {
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
	
	public void attendCheck2() {
		StudentDAO sdb = StudentDAO.getInstance();
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
