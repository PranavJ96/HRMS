package internalEdit;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.SwingConstants;

import log.log;
import net.proteanit.sql.DbUtils;

import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.sql.Date;
import javax.swing.JComboBox;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
public class InternalEdit {

	private JFrame frame;

	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	log userdata = new log();
	List<Object> lstObject = new ArrayList<Object>();
	List<Object> lstLocation= new ArrayList<Object>();
	List<Object> lstProject = new ArrayList<Object>();
	List<Object> lstDepartment= new ArrayList<Object>();
	private JTextField textFName;
	private JTextField textMName;
	private JTextField textLName;
	private JTextField textFather;
	private JTextField textMother;
	private JTextField textSpouse;
	private JTextField textDOB;
	private JTextField textQualification;
	private JTextField textBlood;
	private JTextField textPhone;
	private JTextField textField_16;
	private JTextField textField_17;
	private JTextField textField_18;
	private JTextField textField_19;
	private JTextField textField_20;
	private JTextField textField_23;
	private JTextField textField_24;
	private JTextField textField_25;
	private JTextField textField_26;
	private JTextField textField_29;
	private JTextField textField_30;
	public static String employee_Id;
	private JTextField textGender;
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
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		employee_Id = args[0];
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InternalEdit window = new InternalEdit();
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
	public InternalEdit() {
		connect();
		getLocation();
		getDepartment();
		getProject();
		getWorkType();
		initialize();
		getData();
	}

	public void getData() {
		try {			
	 			CallableStatement stmt =con.prepareCall("{call GetSinglePersonDetails(?)}");
				stmt.setString(1, employee_Id);
				rs = stmt.executeQuery();
                if(rs.next()==true) {
                	textFName.setText(rs.getString(1));
					textMName.setText(rs.getString(2));
					textLName.setText(rs.getString(3));
					textFather.setText(rs.getString(4));
					textMother.setText(rs.getString(5));
					textSpouse.setText(rs.getString(6));
					textDOB.setText(rs.getString(7));
					textQualification.setText(rs.getString(8));
					textBlood.setText(rs.getString(9));
					textPhone.setText(rs.getString(10));
					textField_16.setText(rs.getString(11));
					textField_17.setText(rs.getString(12));
					textField_18.setText(rs.getString(13));
					textField_19.setText(rs.getString(14));
					textField_20.setText(rs.getString(15));
					textField_23.setText(rs.getString(18));
					textField_25.setText(rs.getString(20));
					textField_26.setText(rs.getString(21));
					textField_29.setText(rs.getString(24));
					textField_30.setText(rs.getString(25));
					textField_24.setText(rs.getString(19));
					textGender.setText(rs.getString(27));
                }
//	                textEmail.setText(rs.getString(1));
//	                textPassword.setText(rs.getString(2));
//	                role_id = rs.getInt(2);
	                
	        } 
		
		 catch (SQLException ex) {
				ex.printStackTrace();
	        }
		 catch (Exception ex) {
	           
	        }
	}
	public void getDepartment() {
		try {
			pst = con.prepareStatement("select department_id, department_name from department");
	        ResultSet rs = pst.executeQuery();
		    while(rs.next()==true)
		    {

		    	List<Object> test = new ArrayList<Object>();
		        test.add(rs.getInt(1));
		        test.add(rs.getString(2));
		        lstDepartment.add(test);
		    } 
		}  
		catch(SQLException ex)
		{
			JOptionPane.showMessageDialog(null, ex);	
		}
	}
	public void getProject() {
		try {
			pst = con.prepareStatement("select project_id, project_name from project");
	        ResultSet rs = pst.executeQuery();
		    while(rs.next()==true)
		    {

		    	List<Object> test = new ArrayList<Object>();
		        test.add(rs.getInt(1));
		        test.add(rs.getString(2));
		        lstProject.add(test);
		    } 
		}  
		catch(SQLException ex)
		{
			JOptionPane.showMessageDialog(null, ex);	
		}
	}
	public void getLocation() {
		try {
			pst = con.prepareStatement("select location_id, location_name from location");
	        ResultSet rs = pst.executeQuery();
		    while(rs.next()==true)
		    {

		    	List<Object> test = new ArrayList<Object>();
		        test.add(rs.getInt(1));
		        test.add(rs.getString(2));
		        lstLocation.add(test);
		    } 
		}  
		catch(SQLException ex)
		{
			JOptionPane.showMessageDialog(null, ex);	
		}
	}
	public void getWorkType() {
		try {
			pst = con.prepareStatement("select wt_id,work_type_name from work_type");
	        ResultSet rs = pst.executeQuery();
		    while(rs.next()==true)
		    {

		    	List<Object> test = new ArrayList<Object>();
		        test.add(rs.getInt(1));
		        test.add(rs.getString(2));
		    	lstObject.add(test);
		    } 
		}  
		catch(SQLException ex)
		{
			JOptionPane.showMessageDialog(null, ex);	
		}
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.activeCaption);
		frame.setBounds(100, 50, 1360, 696);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(40, 87, 1281, 563);
		frame.getContentPane().add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Personal Details", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("First Name");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel.setBounds(161, 11, 130, 51);
		panel.add(lblNewLabel);
		
		textFName = new JTextField();
		textFName.setColumns(10);
		textFName.setBounds(301, 20, 233, 36);
		panel.add(textFName);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnExit.setBounds(1143, 501, 91, 23);
		panel.add(btnExit);
		
		JLabel lblMiddleName = new JLabel("Middle Name");
		lblMiddleName.setFont(new Font("Arial", Font.PLAIN, 18));
		lblMiddleName.setBounds(161, 56, 130, 51);
		panel.add(lblMiddleName);
		
		textMName = new JTextField();
		textMName.setColumns(10);
		textMName.setBounds(301, 65, 233, 36);
		panel.add(textMName);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setFont(new Font("Arial", Font.PLAIN, 18));
		lblLastName.setBounds(161, 98, 130, 51);
		panel.add(lblLastName);
		
		textLName = new JTextField();
		textLName.setColumns(10);
		textLName.setBounds(301, 107, 233, 36);
		panel.add(textLName);
		
		JLabel lblFatherName = new JLabel("Father Name");
		lblFatherName.setFont(new Font("Arial", Font.PLAIN, 18));
		lblFatherName.setBounds(161, 141, 130, 51);
		panel.add(lblFatherName);
		
		textFather = new JTextField();
		textFather.setColumns(10);
		textFather.setBounds(301, 150, 233, 36);
		panel.add(textFather);
		
		JLabel lblMotherName = new JLabel("Mother Name");
		lblMotherName.setFont(new Font("Arial", Font.PLAIN, 18));
		lblMotherName.setBounds(161, 183, 130, 51);
		panel.add(lblMotherName);
		
		textMother = new JTextField();
		textMother.setColumns(10);
		textMother.setBounds(301, 192, 233, 36);
		panel.add(textMother);
		
		JLabel lblSpouseName = new JLabel("Spouse Name");
		lblSpouseName.setFont(new Font("Arial", Font.PLAIN, 18));
		lblSpouseName.setBounds(161, 226, 130, 51);
		panel.add(lblSpouseName);
		
		textSpouse = new JTextField();
		textSpouse.setColumns(10);
		textSpouse.setBounds(301, 235, 233, 36);
		panel.add(textSpouse);
		
		JLabel lblDob = new JLabel("DOB");
		lblDob.setFont(new Font("Arial", Font.PLAIN, 18));
		lblDob.setBounds(161, 269, 130, 51);
		panel.add(lblDob);
		
		textDOB = new JTextField();
		textDOB.setColumns(10);
		textDOB.setToolTipText("yyyy-mm-dd");
		textDOB.setText("yyyy-mm-dd");
		textDOB.setForeground(Color.LIGHT_GRAY);
		textDOB.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(textDOB.getText().trim().equals("yyyy-mm-dd")) {
					textDOB.setText("");
				}
				
				textDOB.setForeground(Color.BLACK);
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(textDOB.getText().trim().equals("")) {
					textDOB.setText("yyyy-mm-dd");
				}
				
				textDOB.setForeground(Color.LIGHT_GRAY);
			}
		});
		textDOB.setBounds(301, 278, 233, 36);
		panel.add(textDOB);
		
		JLabel lblQualification = new JLabel("Qualification");
		lblQualification.setFont(new Font("Arial", Font.PLAIN, 18));
		lblQualification.setBounds(161, 312, 130, 51);
		panel.add(lblQualification);
		
		textQualification = new JTextField();
		textQualification.setColumns(10);
		textQualification.setBounds(301, 321, 233, 36);
		panel.add(textQualification);
		
		JLabel lblBloodGroup = new JLabel("Blood Group");
		lblBloodGroup.setFont(new Font("Arial", Font.PLAIN, 18));
		lblBloodGroup.setBounds(161, 358, 130, 51);
		panel.add(lblBloodGroup);
		
		textBlood = new JTextField();
		textBlood.setColumns(10);
		textBlood.setBounds(301, 367, 233, 36);
		panel.add(textBlood);
		
		JLabel lblAddressId = new JLabel("Phone Number");
		lblAddressId.setFont(new Font("Arial", Font.PLAIN, 18));
		lblAddressId.setBounds(161, 403, 130, 51);
		panel.add(lblAddressId);
		
		textPhone = new JTextField();
		textPhone.setColumns(10);
		textPhone.setBounds(301, 412, 233, 36);
		panel.add(textPhone);
		
		JLabel lblPersonalEmailId = new JLabel("Personal Email ID");
		lblPersonalEmailId.setFont(new Font("Arial", Font.PLAIN, 18));
		lblPersonalEmailId.setBounds(736, 75, 151, 51);
		panel.add(lblPersonalEmailId);
		
		textField_16 = new JTextField();
		textField_16.setColumns(10);
		textField_16.setBounds(925, 84, 233, 36);
		panel.add(textField_16);
		
		JLabel lblNationalId = new JLabel("National ID");
		lblNationalId.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNationalId.setBounds(736, 118, 130, 51);
		panel.add(lblNationalId);
		
		textField_17 = new JTextField();
		textField_17.setColumns(10);
		textField_17.setBounds(925, 127, 233, 36);
		panel.add(textField_17);
		
		JLabel lblBankAccountNumber = new JLabel("Bank Account No");
		lblBankAccountNumber.setFont(new Font("Arial", Font.PLAIN, 18));
		lblBankAccountNumber.setBounds(736, 164, 166, 51);
		panel.add(lblBankAccountNumber);
		
		textField_18 = new JTextField();
		textField_18.setColumns(10);
		textField_18.setBounds(925, 173, 233, 36);
		panel.add(textField_18);
		
		JLabel lblCandidateId = new JLabel("Candidate ID");
		lblCandidateId.setFont(new Font("Arial", Font.PLAIN, 18));
		lblCandidateId.setBounds(736, 209, 130, 51);
		panel.add(lblCandidateId);
		
		textField_19 = new JTextField();
		textField_19.setColumns(10);
		textField_19.setBounds(925, 218, 233, 36);
		panel.add(textField_19);
		
		JLabel lblGender = new JLabel("Gender");
		lblGender.setFont(new Font("Arial", Font.PLAIN, 18));
		lblGender.setBounds(736, 20, 130, 51);
		panel.add(lblGender);
		
		textGender = new JTextField();
		textGender.setColumns(10);
		textGender.setBounds(926, 29, 233, 36);
		panel.add(textGender);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Professional Details", null, panel_1, null);
		panel_1.setLayout(null);

		JComboBox comboBox = new JComboBox();
		for(int i=0;i<lstObject.size();i++)
		{
			List<Object> comboTemp = (List<Object>) lstObject.get(i);
			comboBox.addItem((String)comboTemp.get(1));
		}
		comboBox.setSelectedIndex(0);
		comboBox.setBounds(292, 387, 233, 38);
		panel_1.add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		for(int i=0;i<lstLocation.size();i++)
		{
			List<Object> comboTemp2 = (List<Object>) lstLocation.get(i);
			comboBox_1.addItem((String)comboTemp2.get(1));
		}
		comboBox_1.setSelectedIndex(0);
		comboBox_1.setBounds(932, 110, 233, 38);
		panel_1.add(comboBox_1);

		JComboBox comboBox_2 = new JComboBox();
		for(int i=0;i<lstProject.size();i++)
		{
			List<Object> comboTemp2 = (List<Object>) lstProject.get(i);
			comboBox_2.addItem((String)comboTemp2.get(1));
		}
		comboBox_2.setSelectedIndex(0);
		comboBox_2.setBounds(292, 97, 233, 38);
		panel_1.add(comboBox_2);
		
		JComboBox comboBox_3 = new JComboBox();
		for(int i=0;i<lstDepartment.size();i++)
		{
			List<Object> comboTemp2 = (List<Object>) lstDepartment.get(i);
			comboBox_3.addItem((String)comboTemp2.get(1));
		}
		comboBox_3.setSelectedIndex(0);
		comboBox_3.setBounds(292, 159, 233, 38);
		panel_1.add(comboBox_3);
		
		JLabel lblHrms = new JLabel("HRMS");
		lblHrms.setHorizontalAlignment(SwingConstants.CENTER);
		lblHrms.setFont(new Font("Arial", Font.BOLD, 26));
		lblHrms.setBounds(513, 11, 177, 38);
		frame.getContentPane().add(lblHrms);
		JLabel lblOfficialEmailId = new JLabel("Official Email ID");
		lblOfficialEmailId.setFont(new Font("Arial", Font.PLAIN, 18));
		lblOfficialEmailId.setBounds(67, 26, 130, 51);
		panel_1.add(lblOfficialEmailId);
		
		textField_20 = new JTextField();
		textField_20.setColumns(10);
		textField_20.setBounds(292, 35, 233, 36);
		panel_1.add(textField_20);
		
		JButton btnAddUser = new JButton("Update");
		btnAddUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String selectedWorkType = (String) comboBox.getSelectedItem();
				String selectedLocation = (String) comboBox_1.getSelectedItem();
				String selectedProject = (String) comboBox_2.getSelectedItem();
				String selectedDepartment = (String) comboBox_3.getSelectedItem();
				int selectedWorkTypeValue=0;
				int selectedLocationValue=0;
				int selectedProjectValue=0;
				int selectedDepartmentValue=0;
				for(int i=0;i<lstObject.size();i++)
				{
					List<Object> comboTemp = (List<Object>) lstObject.get(i);
					String tempValue = (String)comboTemp.get(1);
					if(tempValue.equals(selectedWorkType))
					{
						selectedWorkTypeValue = (int)comboTemp.get(0);
						break;
					}
				}
				for(int i=0;i<lstLocation.size();i++)
				{
					List<Object> comboTemp1 = (List<Object>) lstLocation.get(i);
					String tempValue = (String)comboTemp1.get(1);
					if(tempValue.equals(selectedLocation))
					{
						selectedLocationValue = (int)comboTemp1.get(0);
						break;
					}
				}
				for(int i=0;i<lstProject.size();i++)
				{
					List<Object> comboTemp2 = (List<Object>) lstProject.get(i);
					String tempValue = (String)comboTemp2.get(1);
					if(tempValue.equals(selectedProject))
					{
						selectedProjectValue = (int)comboTemp2.get(0);
						break;
					}
				}
				for(int i=0;i<lstDepartment.size();i++)
				{
					List<Object> comboTemp3 = (List<Object>) lstDepartment.get(i);
					String tempValue = (String)comboTemp3.get(1);
					if(tempValue.equals(selectedDepartment))
					{
						selectedDepartmentValue = (int)comboTemp3.get(0);
						break;
					}
				}
				try {
					CallableStatement stmt =con.prepareCall("{call UpdatePerson(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
					stmt.setInt(1, log.userId);
					stmt.setString(2, textFName.getText());
					stmt.setString(3, textMName.getText());
					stmt.setString(4, textLName.getText());
					stmt.setString(5, textFather.getText());
					stmt.setString(6, textMother.getText());
					stmt.setString(7, textSpouse.getText());
					stmt.setString(8, textDOB.getText());
					stmt.setString(9, textQualification.getText());
					stmt.setString(10, textBlood.getText());
					stmt.setString(11, textPhone.getText());
					stmt.setString(12, textField_16.getText());
					stmt.setString(13, textField_17.getText());
					stmt.setString(14, textField_18.getText());
					stmt.setString(15, textField_19.getText());
					stmt.setString(16, textField_20.getText());
					stmt.setInt(17, selectedProjectValue);
					stmt.setInt(18, selectedDepartmentValue);
					stmt.setString(19, textField_23.getText());
					stmt.setString(20, textField_25.getText());
					stmt.setString(21, textField_26.getText());
					stmt.setInt(22, selectedWorkTypeValue);
					stmt.setString(23, textField_29.getText());
					stmt.setInt(24, selectedLocationValue);
					stmt.setString(25, textField_30.getText());
					if(textField_24.getText().contains("yyyy-mm-dd"))
					{
						stmt.setString(26, "2020-11-11");
					}
					else if(textField_24.getText().contains(""))
					{
						stmt.setString(26, "2020-11-11");
					}
					else if(textField_24.getText().contains(" "))
					{
						stmt.setString(26, "2020-11-11");
					}
					else
					{
						stmt.setString(26, textField_24.getText());
					}
					stmt.setString(27, employee_Id);
					stmt.setString(28, textGender.getText());
					rs = stmt.executeQuery();
					JOptionPane.showMessageDialog(null, "Record Updated");
					frame.dispose();
				}
				catch(SQLException ex) {
					ex.printStackTrace();
				}
			}
		});
		btnAddUser.setBounds(434, 494, 91, 23);
		panel_1.add(btnAddUser);
		
		JButton btnExit_1 = new JButton("Exit");
		btnExit_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnExit_1.setBounds(785, 494, 91, 23);
		panel_1.add(btnExit_1);
		
		JLabel lblProjectId = new JLabel("Project");
		lblProjectId.setFont(new Font("Arial", Font.PLAIN, 18));
		lblProjectId.setBounds(67, 82, 130, 51);
		panel_1.add(lblProjectId);
		
		JLabel lblDepartmentId = new JLabel("Department ");
		lblDepartmentId.setFont(new Font("Arial", Font.PLAIN, 18));
		lblDepartmentId.setBounds(67, 144, 130, 51);
		panel_1.add(lblDepartmentId);
		
		JLabel lblDoj = new JLabel("DOJ");
		lblDoj.setFont(new Font("Arial", Font.PLAIN, 18));
		lblDoj.setBounds(67, 211, 130, 51);
		panel_1.add(lblDoj);
		
		textField_23 = new JTextField();
		textField_23.setColumns(10);
		textField_23.setToolTipText("yyyy-mm-dd");
		textField_23.setText("yyyy-mm-dd");
		textField_23.setForeground(Color.LIGHT_GRAY);
		textField_23.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(textField_23.getText().trim().equals("yyyy-mm-dd")) {
					textField_23.setText("");
				}
				
				textField_23.setForeground(Color.BLACK);
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(textField_23.getText().trim().equals("")) {
					textField_23.setText("yyyy-mm-dd");
				}
				
				textField_23.setForeground(Color.LIGHT_GRAY);
			}
		});
		textField_23.setBounds(292, 220, 233, 36);
		panel_1.add(textField_23);
		
		JLabel lblContractEndDate = new JLabel("Contract End Date");
		lblContractEndDate.setFont(new Font("Arial", Font.PLAIN, 18));
		lblContractEndDate.setBounds(742, 239, 166, 51);
		panel_1.add(lblContractEndDate);
		
		textField_24 = new JTextField();
		textField_24.setToolTipText("yyyy-mm-dd");
		textField_24.setText("yyyy-mm-dd");
		textField_24.setForeground(Color.LIGHT_GRAY);
		textField_24.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(textField_24.getText().trim().equals("yyyy-mm-dd")) {
					textField_24.setText("");
				}
				
				textField_24.setForeground(Color.BLACK);
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(textField_24.getText().trim().equals("")) {
					textField_24.setText("yyyy-mm-dd");
				}
				
				textField_24.setForeground(Color.LIGHT_GRAY);
			}
		});
		textField_24.setColumns(10);
		textField_24.setBounds(932, 248, 233, 36);
		panel_1.add(textField_24);
		
		JLabel lblRoleLevel = new JLabel("Role Level");
		lblRoleLevel.setFont(new Font("Arial", Font.PLAIN, 18));
		lblRoleLevel.setBounds(67, 273, 130, 51);
		panel_1.add(lblRoleLevel);
		
		textField_25 = new JTextField();
		textField_25.setColumns(10);
		textField_25.setBounds(292, 282, 233, 36);
		panel_1.add(textField_25);
		
		JLabel lblDesignation = new JLabel("Designation");
		lblDesignation.setFont(new Font("Arial", Font.PLAIN, 18));
		lblDesignation.setBounds(67, 329, 130, 51);
		panel_1.add(lblDesignation);
		
		textField_26 = new JTextField();
		textField_26.setColumns(10);
		textField_26.setBounds(292, 338, 233, 36);
		panel_1.add(textField_26);
		
		JLabel lblWorkType = new JLabel("Work Type");
		lblWorkType.setFont(new Font("Arial", Font.PLAIN, 18));
		lblWorkType.setBounds(67, 379, 130, 51);
		panel_1.add(lblWorkType);
		
		JLabel lblTaxId = new JLabel("Tax ID");
		lblTaxId.setFont(new Font("Arial", Font.PLAIN, 18));
		lblTaxId.setBounds(742, 36, 130, 51);
		panel_1.add(lblTaxId);
		
		textField_29 = new JTextField();
		textField_29.setColumns(10);
		textField_29.setBounds(932, 45, 233, 36);
		panel_1.add(textField_29);
		
		JLabel lblCompanyName = new JLabel("Company Name");
		lblCompanyName.setFont(new Font("Arial", Font.PLAIN, 18));
		lblCompanyName.setBounds(742, 175, 130, 51);
		panel_1.add(lblCompanyName);
		
		textField_30 = new JTextField();
		textField_30.setColumns(10);
		textField_30.setBounds(930, 184, 233, 36);
		panel_1.add(textField_30);
		
		JLabel lblLocation = new JLabel("Location");
		lblLocation.setFont(new Font("Arial", Font.PLAIN, 18));
		lblLocation.setBounds(742, 97, 130, 51);
		panel_1.add(lblLocation);
		
		
		
		
	}
}
