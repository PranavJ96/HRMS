package tables;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;

import AddressEdit.copy.AddressEdit;
import ClientAdd.ClientAdd;
import ClientEdit.copy.ClientEdit;
import ExitAdd.ExitAdd;
import ExitEdit.copy.ExitEdit;
import IndusEdit.copy.IndusEdit;
import deptEdit.copy.DeptEdit;
import log.log;
import mainPage.MainPage;
import net.proteanit.sql.DbUtils;
import wTEdit.copy.WTEdit;
import wTadd.WTAdd;

import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import payrollEdit.PayrollEdit;
import projectAdd.Project_Add;
import projectEdit.copy.Project_Edit;
import attendanceEdit.AttendanceEdit;

public class Tables {

	private JFrame frame;
	private JTextField textFieldWork;
	private JTextField textFieldDeparment;
	private JTextField textFieldProject;
	private JTextField textFieldAddress;
	private JTextField textFieldIndustry;
	private JTextField textFieldClients;
	private JTextField textFieldAttendance;
	private JTextField textFieldPayroll;
	private JTextField textFieldEquipment;
	private JTextField textFieldExit;
	

	public int accessLevel;
	public int userId;
	private JTable table;
	public String searchWorkId ;
	public String searchDepartmentId;
	public String searchAddressID;
	public String searchIndustryId;
	public String searchPersonId;
	public String searchClientId;
	public String searchExitId;
	public String searchProjectId,searchAttendanceId;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tables window = new Tables();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Tables() {
		initialize();
		connect();
		table_load();
		table_load1();
		table_load2();
		table_load3();
		table_load4();
		table_load5();
		table_load6();
		table_projectload();
		table_attendanceload();
		table_payrollload();
	}
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	private JTable table_1;
	private JTable table_2;
	private JTable table_3;
	private JTable table_5;
	private JTable table_4;
	private JTable table_6;
	private JTable table_project;
	private JTable table_attendance;
	private JTable table_payroll;
	public void connect()
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/hrms", "root", "");
		}
		catch(ClassNotFoundException ex) {
			System.out.println(ex);
		}
		catch(SQLException ex) {
			System.out.println(ex);
		}
	}
	public void table_load() {
		try {
			pst = con.prepareStatement("Select wt_id as WT_ID,work_type_name as Work_Type from work_type where is_active = 1");
			rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			//table.getColumnModel().getColumn(0).setHeaderValue("test");
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public void table_load1() {
		try {
			pst = con.prepareStatement("Select department_id as Department_ID,department_name as Department_Type,department_head_id as Department_Head_ID from department where is_active = 1");
			rs = pst.executeQuery();
			table_1.setModel(DbUtils.resultSetToTableModel(rs));
			//table.getColumnModel().getColumn(0).setHeaderValue("test");
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void table_load2() {
		try {
			pst = con.prepareStatement("Select address_id as Address_ID,door_number as Door_Number,street_name as Street_Name,area as Area,city as City,postcode as Postcode,country as Country from address where is_active = 1");
			rs = pst.executeQuery();
			table_2.setModel(DbUtils.resultSetToTableModel(rs));
			//table.getColumnModel().getColumn(0).setHeaderValue("test");
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void table_load3() {
		try {
			pst = con.prepareStatement("Select industry_id as Industry_ID, industry_name as Industry_Name ,industry_head_id as Industry_Head_ID from industry where is_active = 1");
			rs = pst.executeQuery();
			table_3.setModel(DbUtils.resultSetToTableModel(rs));
			//table.getColumnModel().getColumn(0).setHeaderValue("test");
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public void table_load4() {
		try {
			pst = con.prepareStatement("Select equipment_id  as Equipment_ID, asset_id as Asset_ID , asset_name as Asset_Name , assigned_date as Assigned_Date, location_id as Location_ID ,person_id as Person_ID from equipment where is_active = 1");
			rs = pst.executeQuery();
			table_5.setModel(DbUtils.resultSetToTableModel(rs));
			//table.getColumnModel().getColumn(0).setHeaderValue("test");
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void table_load5() {
		try {
			pst = con.prepareStatement("Select client_id  as Client_ID, client_name as Client_Name,location_id as Location_ID from client where is_active = 1");
			rs = pst.executeQuery();
			table_4.setModel(DbUtils.resultSetToTableModel(rs));
			//table.getColumnModel().getColumn(0).setHeaderValue("test");
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public void table_load6() {
		try {
			pst = con.prepareStatement("Select exit_id  as Exit_ID, resignation_date as Resignation_Date, notice_period as Notice_Period, separation_date as Separation_Date, employee_id as Employee_ID, no_dues as No_Dues from employee_exit where is_active = 1 ");
			rs = pst.executeQuery();
			table_6.setModel(DbUtils.resultSetToTableModel(rs));
			//table.getColumnModel().getColumn(0).setHeaderValue("test");
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void table_attendanceload() {
		try {
			pst = con.prepareStatement("Select attendance_id as Attendance_ID,work_duration_h as Work_Duration_Hours,default_work_duration_h as Default_Work_Duration_Hours, overtime_duration_h as Overtime_Hours, total_leaves as Total_Leaves, used_leaves as Used_Leaves, (total_leaves-used_leaves) as Leftover_Leaves from attendance where is_active = 1");
			rs = pst.executeQuery();
			table_attendance.setModel(DbUtils.resultSetToTableModel(rs));
			//table.getColumnModel().getColumn(0).setHeaderValue("test");
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void table_payrollload() {
		try {
			pst = con.prepareStatement("Select person_id as Person_ID, salary as Salary, bonus as Bonus, compensation as Compensation, (salary+bonus+compensation) as Net_Salary from payroll where is_active = 1");
			rs = pst.executeQuery();
			table_payroll.setModel(DbUtils.resultSetToTableModel(rs));
			//table.getColumnModel().getColumn(0).setHeaderValue("test");
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void table_projectload() {
		try {
			pst = con.prepareStatement("Select project_id as PROJECT_ID,project_name as PROJECT_NAME, project_type_id AS PROJECT_TYPE_ID, project_manager_id AS PROJECT_MANAGER_ID, client_id AS CLIENT_ID, department_id AS DEPARTMENT_ID, industry_id AS INDUSTRY_ID, location_id AS LOCATION_ID, project_start_date AS PROJECT_START_DATE from project where is_active = 1");
			rs = pst.executeQuery();
			table_project.setModel(DbUtils.resultSetToTableModel(rs));
			//table.getColumnModel().getColumn(0).setHeaderValue("test");
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		userId= log.userId;
		accessLevel= log.adminId;
		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.activeCaption);
		frame.getContentPane().setLayout(null);
		
		JLabel lblHrms = new JLabel("HRMS");
		lblHrms.setHorizontalAlignment(SwingConstants.CENTER);
		lblHrms.setFont(new Font("Arial", Font.BOLD, 26));
		lblHrms.setBounds(465, 11, 177, 38);
		frame.getContentPane().add(lblHrms);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(38, 91, 1151, 614);
		frame.getContentPane().add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Work Type", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Work Id");
		lblNewLabel_1.setBounds(8, 16, 110, 28);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel.add(lblNewLabel_1);
		
		textFieldWork = new JTextField();
		textFieldWork.setBounds(101, 22, 140, 20);
		textFieldWork.setColumns(10);
		panel.add(textFieldWork);
		
		JButton btnNewButton_1 = new JButton("Add");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WTAdd.main(null);
			}
		});
		btnNewButton_1.setBounds(988, 21, 148, 23);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("Edit");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] textValue = new String[]{textFieldWork.getText()};
				WTEdit.main(textValue);
			}
		});
		btnNewButton_1_1.setBounds(894, 552, 91, 23);
		panel.add(btnNewButton_1_1);
		
		JButton btnNewButton_1_2 = new JButton("Delete");
		btnNewButton_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchWorkId = textFieldWork.getText();
				try {
					pst = con.prepareStatement("Update Work_Type SET is_active = ? where wt_id =?");
		            pst.setInt(1, 0);
		            pst.setString(2, searchWorkId);
		            pst.executeUpdate();
		            JOptionPane.showMessageDialog(null, "Record Delete!!!!!");
		            table_load();
				}
				catch(SQLException ex) {
					ex.printStackTrace();
				}
			}
		});
		btnNewButton_1_2.setBounds(1045, 552, 91, 23);
		panel.add(btnNewButton_1_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 84, 1126, 449);
		panel.add(scrollPane);
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchWorkId  = textFieldWork.getText();
			    try {
				    	if(searchWorkId.equals(""))
				    	{
				    		table_load();   
				    	}
				    	else
				    	{
						    pst = con.prepareStatement("Select wt_id as WT_ID,work_type_name as Work_Type from work_type where is_active = 1 && wt_id = "+searchWorkId);
				    	}
					rs = pst.executeQuery();
			    }
				catch(SQLException ex) {
					ex.printStackTrace();
				}
				
				table.setModel(DbUtils.resultSetToTableModel(rs));
				//table.getColumnModel().getColumn(0).setHeaderValue("test");
			}
		});
		btnNewButton.setBounds(267, 21, 91, 23);
		panel.add(btnNewButton);
		
		JButton btnNewButton_2 = new JButton("Refresh");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table_load();
				textFieldWork.setText(" ");	
			}
		});
		btnNewButton_2.setBounds(394, 21, 91, 23);
		panel.add(btnNewButton_2);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Department", null, panel_1, null);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1_1 = new JLabel("Department Id");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1.setBounds(8, 11, 110, 28);
		panel_1.add(lblNewLabel_1_1);
		
		textFieldDeparment = new JTextField();
		textFieldDeparment.setColumns(10);
		textFieldDeparment.setBounds(117, 17, 140, 20);
		panel_1.add(textFieldDeparment);
		
		JButton btnNewButton_1_1_1 = new JButton("Edit");
		btnNewButton_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] textValue = new String[]{textFieldDeparment.getText()};
			DeptEdit.main(textValue);
			}
		});
		btnNewButton_1_1_1.setBounds(1045, 547, 91, 23);
		panel_1.add(btnNewButton_1_1_1);
		
		JButton btnNewButton_3 = new JButton("Search");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchDepartmentId  = textFieldDeparment.getText();
			    try {
				    	if(searchDepartmentId.equals(""))
				    	{
				    		table_load1();
				    	}
				    	else
				    	{
						    pst = con.prepareStatement("Select department_id as Department_ID,department_name as Department_Type,department_head_id as Department_Head_ID from department where is_active = 1 && department_id = "+searchDepartmentId);
				    	}
					rs = pst.executeQuery();
			    }
				catch(SQLException ex) {
					ex.printStackTrace();
				}
				
				table_1.setModel(DbUtils.resultSetToTableModel(rs));
				//table.getColumnModel().getColumn(0).setHeaderValue("test");
			}
		});
		btnNewButton_3.setBounds(281, 16, 91, 23);
		panel_1.add(btnNewButton_3);
		
		JScrollPane scrollPane_10 = new JScrollPane();
		scrollPane_10.setBounds(28, 63, 1092, 459);
		panel_1.add(scrollPane_10);
		
		table_1 = new JTable();
		scrollPane_10.setViewportView(table_1);
		
		JButton btnNewButton_2_1 = new JButton("Refresh");
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table_load1();
				textFieldDeparment.setText(" ");
			}
		});
		btnNewButton_2_1.setBounds(393, 16, 91, 23);
		panel_1.add(btnNewButton_2_1);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Project", null, panel_2, null);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_1_2 = new JLabel("Project Id");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_2.setBounds(8, 16, 110, 28);
		panel_2.add(lblNewLabel_1_2);
		
		textFieldProject = new JTextField();
		textFieldProject.setColumns(10);
		textFieldProject.setBounds(101, 22, 140, 20);
		panel_2.add(textFieldProject);
		
		JButton btnNewButton_1_3 = new JButton("Add");
		btnNewButton_1_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Project_Add.main(null);
			}
		});
		btnNewButton_1_3.setBounds(988, 21, 148, 23);
		panel_2.add(btnNewButton_1_3);
		
		JButton btn_edit = new JButton("Edit");
		btn_edit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//project edit button code here
				String[] projectId = new String[]{textFieldProject.getText().trim()};
				Project_Edit.main(projectId);
			}
		});
		btn_edit.setBounds(915, 552, 91, 23);
		panel_2.add(btn_edit);
		
		JButton btndelete = new JButton("Delete");
		btndelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchProjectId = textFieldProject.getText();
				try {
					pst = con.prepareStatement("Update project SET is_active = ? where project_id =?");
		            pst.setInt(1, 0);
		            pst.setString(2, searchProjectId);
		            pst.executeUpdate();
		            JOptionPane.showMessageDialog(null, "Record Delete!!!!!");
		            table_projectload();
				}
				catch(SQLException ex) {
					ex.printStackTrace();
				}
			}
		});
		btndelete.setBounds(1045, 552, 91, 23);
		panel_2.add(btndelete);
		
		JButton btnprojectsearch = new JButton("Search");
		btnprojectsearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				//project search code here
				searchProjectId  = textFieldProject.getText();
			    try {
			    		if(searchProjectId.equals(""))
				    	{
						    pst = con.prepareStatement("Select project_id as Project_ID,project_name, project_type_id, project_manager_id, client_id, department_id, industry_id, location_id, project_start_date as Project from project where is_active = 1");
				    	}
				    	else
				    	{
						    pst = con.prepareStatement("Select project_id as Project_ID,project_name, project_type_id, project_manager_id, client_id, department_id, industry_id, location_id, project_start_date as Project from project where project_id = "+searchProjectId);
				    	}
					rs = pst.executeQuery();
			    }
				catch(SQLException ex) {
					ex.printStackTrace();
				}
				
				table_project.setModel(DbUtils.resultSetToTableModel(rs));
				//table.getColumnModel().getColumn(0).setHeaderValue("test");
			}
		});
		btnprojectsearch.setBounds(269, 22, 85, 21);
		panel_2.add(btnprojectsearch);
		
		JScrollPane scrollPane_project = new JScrollPane();
		scrollPane_project.setBounds(8, 66, 1128, 465);
		panel_2.add(scrollPane_project);
		
		table_project = new JTable();
		scrollPane_project.setViewportView(table_project);
		
		JButton btn_projectrefresh = new JButton("Refresh");
		btn_projectrefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table_projectload();
				textFieldProject.setText(" ");
			}
		});
		btn_projectrefresh.setBounds(377, 22, 85, 20);
		panel_2.add(btn_projectrefresh);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Address", null, panel_3, null);
		panel_3.setLayout(null);
		
		JLabel lblNewLabel_1_3 = new JLabel("Address ID");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_3.setBounds(8, 16, 110, 28);
		panel_3.add(lblNewLabel_1_3);
		
		textFieldAddress = new JTextField();
		textFieldAddress.setColumns(10);
		textFieldAddress.setBounds(101, 22, 140, 20);
		panel_3.add(textFieldAddress);
		
		JButton btnNewButton_3_1 = new JButton("Search");
		btnNewButton_3_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchAddressID  = textFieldAddress.getText();
			    try {
				    	if(searchAddressID.equals(""))
				    	{
                       table_load2();
				    	}
				    	else
				    	{
						    pst = con.prepareStatement("Select address_id as Address_ID,door_number as Door_Number,street_name as Street_Name,area as Area,city as City,postcode as Postcode,country as Country from address where is_active = 1 && address_id = "+searchAddressID);
				    	}
					rs = pst.executeQuery();
			    }
				catch(SQLException ex) {
					ex.printStackTrace();
				}
				
				table_2.setModel(DbUtils.resultSetToTableModel(rs));
				//table.getColumnModel().getColumn(0).setHeaderValue("test");
			}
		});
						
		btnNewButton_3_1.setBounds(278, 21, 91, 23);
		panel_3.add(btnNewButton_3_1);
		
		JButton btnNewButton_2_1_1 = new JButton("Refresh");
		btnNewButton_2_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table_load2();
				textFieldAddress.setText(" ");
			}
		});
		btnNewButton_2_1_1.setBounds(390, 21, 91, 23);
		panel_3.add(btnNewButton_2_1_1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(30, 66, 1083, 475);
		panel_3.add(scrollPane_1);
		
		table_2 = new JTable();
		scrollPane_1.setViewportView(table_2);
		
		JButton btnNewButton_1_1_8 = new JButton("Edit");
		btnNewButton_1_1_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] textValue = new String[]{textFieldAddress.getText()};
				AddressEdit.main(textValue);
			}
		});
		btnNewButton_1_1_8.setBounds(1022, 552, 91, 23);
		panel_3.add(btnNewButton_1_1_8);
		
		JPanel panel_4 = new JPanel();
		tabbedPane.addTab("Industry", null, panel_4, null);
		panel_4.setLayout(null);
		
		JLabel lblNewLabel_1_4 = new JLabel("Industry Id");
		lblNewLabel_1_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_4.setBounds(8, 11, 110, 28);
		panel_4.add(lblNewLabel_1_4);
		
		textFieldIndustry = new JTextField();
		textFieldIndustry.setColumns(10);
		textFieldIndustry.setBounds(101, 17, 140, 20);
		panel_4.add(textFieldIndustry);
		
		JButton btnNewButton_1_1_3 = new JButton("Edit");
		btnNewButton_1_1_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] textValue = new String[]{textFieldIndustry.getText()};
				IndusEdit.main(textValue);
			}
		});
		btnNewButton_1_1_3.setBounds(1033, 552, 91, 23);
		panel_4.add(btnNewButton_1_1_3);
		
		JButton btnNewButton_3_1_1 = new JButton("Search");
		btnNewButton_3_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchIndustryId  = textFieldIndustry.getText();
			    try {
				    	if(searchIndustryId.equals(""))
				    	{
				    		table_load3();
				    	}
				    	else
				    	{
						    pst = con.prepareStatement("Select industry_id as Industry_ID, industry_name as Industry_Name ,industry_head_id as Industry_Head_ID from industry where is_active = 1 && industry_id =" +searchIndustryId);
				    	}
					rs = pst.executeQuery();
			    }
				catch(SQLException ex) {
					ex.printStackTrace();
				}
				
				table_3.setModel(DbUtils.resultSetToTableModel(rs));
				//table.getColumnModel().getColumn(0).setHeaderValue("test");
			}
		});
		btnNewButton_3_1_1.setBounds(265, 16, 91, 23);
		panel_4.add(btnNewButton_3_1_1);
		
		JButton btnNewButton_2_1_1_1 = new JButton("Refresh");
		btnNewButton_2_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table_load3 ();
				textFieldIndustry.setText(" ");
			}
		});
		btnNewButton_2_1_1_1.setBounds(378, 16, 91, 23);
		panel_4.add(btnNewButton_2_1_1_1);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(18, 50, 1106, 491);
		panel_4.add(scrollPane_3);
		
		table_3 = new JTable();
		scrollPane_3.setViewportView(table_3);
		
		JPanel panel_5 = new JPanel();
		tabbedPane.addTab("Clients", null, panel_5, null);
		panel_5.setLayout(null);
		
		JLabel lblNewLabel_1_5 = new JLabel("Client Id");
		lblNewLabel_1_5.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_5.setBounds(8, 11, 110, 28);
		panel_5.add(lblNewLabel_1_5);
		
		textFieldClients = new JTextField();
		textFieldClients.setColumns(10);
		textFieldClients.setBounds(101, 17, 140, 20);
		panel_5.add(textFieldClients);
		
		JButton btnNewButton_1_4 = new JButton("Add");
		btnNewButton_1_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClientAdd.main(null);
			}
		});
		btnNewButton_1_4.setBounds(988, 16, 148, 23);
		panel_5.add(btnNewButton_1_4);
		
		JButton btnNewButton_1_1_4 = new JButton("Edit");
		btnNewButton_1_1_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] textValue = new String[]{textFieldClients.getText()};
				ClientEdit.main(textValue);
			}
		});
		btnNewButton_1_1_4.setBounds(894, 547, 91, 23);
		panel_5.add(btnNewButton_1_1_4);
		
		JButton btnNewButton_1_2_2 = new JButton("Delete");
		btnNewButton_1_2_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				searchClientId = textFieldClients.getText();
				try {
					pst = con.prepareStatement("Update client SET is_active = ? where client_id =?");
		            pst.setInt(1, 0);
		            pst.setString(2, searchClientId);
		            pst.executeUpdate();
		            JOptionPane.showMessageDialog(null, "Record Delete!!!!!");
		            table_load5();
				}
				catch(SQLException ex) {
					ex.printStackTrace();
				}
			}
		});
				
		btnNewButton_1_2_2.setBounds(1045, 547, 91, 23);
		panel_5.add(btnNewButton_1_2_2);
		
		JButton btnNewButton_5 = new JButton("Search");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				searchClientId  = textFieldClients.getText();
			    try {
				    	if(searchClientId.equals(""))
				    	{
                      table_load5();
				    	}
				    	else
				    	{
						    pst = con.prepareStatement("Select client_id  as Client_ID, client_name as Client_Name,location_id as Location_ID from client where is_active = 1 && client_id = "+searchClientId);
				    	}
					rs = pst.executeQuery();
			    }
				catch(SQLException ex) {
					ex.printStackTrace();
				}
				
				table_4.setModel(DbUtils.resultSetToTableModel(rs));
				//table.getColumnModel().getColumn(0).setHeaderValue("test");	
			}
		});
		btnNewButton_5.setBounds(266, 16, 91, 23);
		panel_5.add(btnNewButton_5);
		
		JButton btnNewButton_2_3 = new JButton("Refresh");
		btnNewButton_2_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table_load5();
				textFieldClients.setText(" ");
			}
		});
		btnNewButton_2_3.setBounds(378, 16, 91, 23);
		panel_5.add(btnNewButton_2_3);
		
		JScrollPane scrollPane_5 = new JScrollPane();
		scrollPane_5.setBounds(35, 73, 1082, 457);
		panel_5.add(scrollPane_5);
		
		table_4 = new JTable();
		scrollPane_5.setViewportView(table_4);
		
		JPanel panel_6 = new JPanel();
		tabbedPane.addTab("Attendance", null, panel_6, null);
		panel_6.setLayout(null);
		
		JLabel lblNewLabel_1_6 = new JLabel("Attendance Id");
		lblNewLabel_1_6.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_6.setBounds(8, 11, 110, 28);
		panel_6.add(lblNewLabel_1_6);
		
		textFieldAttendance = new JTextField();
		textFieldAttendance.setColumns(10);
		textFieldAttendance.setBounds(118, 17, 140, 20);
		panel_6.add(textFieldAttendance);
		
		JButton btnNewButton_1_1_5 = new JButton("Edit");
		btnNewButton_1_1_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] attendanceId = new String[]{textFieldAttendance.getText().trim()};
				AttendanceEdit.main(attendanceId);
			}
		});
		btnNewButton_1_1_5.setBounds(1045, 547, 91, 23);
		panel_6.add(btnNewButton_1_1_5);
		
		JButton btn_attsearch = new JButton("Search");
		btn_attsearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				searchAttendanceId  = textFieldAttendance.getText();
				//System.out.println(searchAttendanceId);
			    try {
			    		if(searchAttendanceId.equals(""))
				    	{
						    pst = con.prepareStatement("Select attendance_id as Attendance_ID,work_duration_h as Work_Duration_Hours,default_work_duration_h as Default_Work_Duration_Hours, overtime_duration_h as Overtime_Hours, total_leaves as Total_Leaves, used_leaves as Used_Leaves, (total_leaves-used_leaves) as Leftover_Leaves from attendance where is_active = 1");
				    	}
				    	else
				    	{
						    pst = con.prepareStatement("Select attendance_id as Attendance_ID,work_duration_h as Work_Duration_Hours,default_work_duration_h as Default_Work_Duration_Hours, overtime_duration_h as Overtime_Hours, total_leaves as Total_Leaves, used_leaves as Used_Leaves, (total_leaves-used_leaves) as Leftover_Leaves from attendance where attendance_id = "+searchAttendanceId);
				    	}
					rs = pst.executeQuery();
			    }
				catch(SQLException ex) {
					ex.printStackTrace();
				}
			    table_attendance.setModel(DbUtils.resultSetToTableModel(rs));
			}
		});
		btn_attsearch.setBounds(279, 17, 85, 21);
		panel_6.add(btn_attsearch);
		
		JButton btn_attrefresh = new JButton("Refresh");
		btn_attrefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table_attendanceload();
				textFieldAttendance.setText(" ");
			}
		});
		btn_attrefresh.setBounds(382, 17, 85, 21);
		panel_6.add(btn_attrefresh);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(8, 59, 1128, 478);
		panel_6.add(scrollPane_2);
		
		table_attendance = new JTable();
		scrollPane_2.setViewportView(table_attendance);
		
		JPanel panel_7 = new JPanel();
		tabbedPane.addTab("Payroll", null, panel_7, null);
		panel_7.setLayout(null);
		
		JLabel lblNewLabel_1_7 = new JLabel("Person Id:");
		lblNewLabel_1_7.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_7.setBounds(8, 16, 110, 28);
		panel_7.add(lblNewLabel_1_7);
		
		textFieldPayroll = new JTextField();
		textFieldPayroll.setColumns(10);
		textFieldPayroll.setBounds(101, 22, 140, 20);
		panel_7.add(textFieldPayroll);
		
		JButton btnNewButton_1_1_6 = new JButton("Edit");
		btnNewButton_1_1_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] personId = new String[]{textFieldPayroll.getText().trim()};
				PayrollEdit.main(personId);
			}
		});
		btnNewButton_1_1_6.setBounds(1032, 552, 91, 23);
		panel_7.add(btnNewButton_1_1_6);
		
		JButton btn_payrollsearch = new JButton("Search");
		btn_payrollsearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchPersonId  = textFieldPayroll.getText();
				//System.out.println(searchAttendanceId);
			    try {
			    		if(searchPersonId.equals(""))
				    	{
						    pst = con.prepareStatement("Select person_id as Person_ID,  salary as Salary, bonus as Bonus, compensation as Compensation, (salary+bonus+compensation) as Net_Salary from payroll where is_active = 1");
				    	}
				    	else
				    	{
						    pst = con.prepareStatement("Select person_id as Person_ID,  salary as Salary, bonus as Bonus, compensation as Compensation, (salary+bonus+compensation) as Net_Salary from payroll where person_id = ?");
						    pst.setString(1,searchPersonId);
				    	}
					rs = pst.executeQuery();
			    }
				catch(SQLException ex) {
					ex.printStackTrace();
				}
			    table_payroll.setModel(DbUtils.resultSetToTableModel(rs));
			}
		});
		btn_payrollsearch.setBounds(261, 22, 85, 21);
		panel_7.add(btn_payrollsearch);
		
		JButton btn_payrollrefresh = new JButton("Refresh");
		btn_payrollrefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table_payrollload();
				textFieldPayroll.setText(" ");
			}
		});
		btn_payrollrefresh.setBounds(356, 22, 85, 21);
		panel_7.add(btn_payrollrefresh);
		
		JScrollPane scrollPane_6 = new JScrollPane();
		scrollPane_6.setBounds(8, 68, 1128, 461);
		panel_7.add(scrollPane_6);
		
		table_payroll = new JTable();
		scrollPane_6.setViewportView(table_payroll);
		
		JPanel panel_8 = new JPanel();
		tabbedPane.addTab("Equipment", null, panel_8, null);
		panel_8.setLayout(null);
		
		JLabel lblNewLabel_1_8 = new JLabel("Person Id");
		lblNewLabel_1_8.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_8.setBounds(8, 11, 110, 28);
		panel_8.add(lblNewLabel_1_8);
		
		textFieldEquipment = new JTextField();
		textFieldEquipment.setColumns(10);
		textFieldEquipment.setBounds(101, 17, 140, 20);
		panel_8.add(textFieldEquipment);
		
		JButton btnNewButton_4 = new JButton("Search");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				searchPersonId  = textFieldEquipment.getText();
			    try {
				    	if(searchPersonId.equals(""))
				    	{
				    		table_load4();
				    	}
				    	else
				    	{
						    pst = con.prepareStatement("Select equipment_id  as Equipment_ID, asset_id as Asset_ID , asset_name as Asset_Name , assigned_date as Assigned_Date, location_id as Location_ID ,person_id as Person_ID from equipment where is_active=1 && person_id = ?");
						    pst.setString(1, searchPersonId);
				    }
					rs = pst.executeQuery();
			    }
				catch(SQLException ex) {
					ex.printStackTrace();
				}
				
				table_5.setModel(DbUtils.resultSetToTableModel(rs));
				//table.getColumnModel().getColumn(0).setHeaderValue("test");
			}
		});
		btnNewButton_4.setBounds(262, 16, 91, 23);
		panel_8.add(btnNewButton_4);
		JButton btnNewButton_2_2 = new JButton("Refresh");
		btnNewButton_2_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table_load4();
				textFieldEquipment.setText(" ");
			}
		});
		btnNewButton_2_2.setBounds(376, 16, 91, 23);
		panel_8.add(btnNewButton_2_2);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(39, 62, 1064, 495);
		panel_8.add(scrollPane_4);
		
		table_5 = new JTable();
		scrollPane_4.setViewportView(table_5);
		
		JPanel panel_9 = new JPanel();
		tabbedPane.addTab("Exit", null, panel_9, null);
		panel_9.setLayout(null);
		
		JLabel lblNewLabel_1_9 = new JLabel("Exit Id");
		lblNewLabel_1_9.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_9.setBounds(40, 16, 110, 28);
		panel_9.add(lblNewLabel_1_9);
		
		textFieldExit = new JTextField();
		textFieldExit.setColumns(10);
		textFieldExit.setBounds(139, 22, 140, 20);
		panel_9.add(textFieldExit);
		
		JButton btnNewButton_1_7 = new JButton("Add");
		btnNewButton_1_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ExitAdd.main(null);
			}
		});
		btnNewButton_1_7.setBounds(966, 21, 129, 23);
		panel_9.add(btnNewButton_1_7);
		
		JButton btnNewButton_1_1_7 = new JButton("Edit");
		btnNewButton_1_1_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] textValue = new String[]{textFieldExit.getText()};
				ExitEdit.main(textValue);
			}
		});
		btnNewButton_1_1_7.setBounds(844, 552, 91, 23);
		panel_9.add(btnNewButton_1_1_7);
		
		JButton btnNewButton_1_2_4 = new JButton("Delete");
		btnNewButton_1_2_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchExitId = textFieldExit.getText();
				try {
					pst = con.prepareStatement("Update employee_exit SET is_active = ? where exit_id =?");
		            pst.setInt(1, 0);
		            pst.setString(2, searchExitId);
		            pst.executeUpdate();
		            JOptionPane.showMessageDialog(null, "Record Delete!!!!!");
		            table_load6();
				}
				catch(SQLException ex) {
					ex.printStackTrace();
				}
			}
		});
				
		btnNewButton_1_2_4.setBounds(1004, 552, 91, 23);
		panel_9.add(btnNewButton_1_2_4);
		
		JScrollPane scrollPane_8 = new JScrollPane();
		scrollPane_8.setBounds(43, 55, 1052, 481);
		panel_9.add(scrollPane_8);
		
		table_6 = new JTable();
		scrollPane_8.setViewportView(table_6);
		
		JButton btnNewButton_4_1 = new JButton("Search");
		btnNewButton_4_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				searchExitId  = textFieldExit.getText();
			    try {
				    	if(searchExitId.equals(""))
				    	{
				    		table_load6();
				    	}
				    	else
				    	{
						    pst = con.prepareStatement("Select exit_id  as Exit_ID, resignation_date as Resignation_Date, notice_period as Notice_Period, separation_date as Separation_Date, employee_id as Employee_ID, no_dues as No_Dues from employee_exit where is_active = 1 && exit_id = "+searchExitId);
				    	}
					rs = pst.executeQuery();
			    }
				catch(SQLException ex) {
					ex.printStackTrace();
				}
				
				table_6.setModel(DbUtils.resultSetToTableModel(rs));
				//table.getColumnModel().getColumn(0).setHeaderValue("test");
			}
		});
	
		btnNewButton_4_1.setBounds(304, 21, 91, 23);
		panel_9.add(btnNewButton_4_1);
		
		JButton btnNewButton_4_1_1 = new JButton("Refresh");
		btnNewButton_4_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table_load6 ();
				textFieldExit.setText(" ");
			}
		});
		btnNewButton_4_1_1.setBounds(409, 21, 91, 23);
		panel_9.add(btnNewButton_4_1_1);
		
		JButton btnHome = new JButton("Home");
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				MainPage.main(null);
			}
		});
		btnHome.setFont(new Font("Arial", Font.PLAIN, 13));
		btnHome.setBackground(SystemColor.menu);
		btnHome.setBounds(38, 24, 91, 23);
		frame.getContentPane().add(btnHome);
		frame.setBounds(150, 25, 1234, 777);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
