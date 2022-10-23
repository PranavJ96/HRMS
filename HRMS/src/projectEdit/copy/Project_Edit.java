package projectEdit.copy;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;

import log.log;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Project_Edit {

	private JFrame frame;
	private JTextField txt_projectname;
	private JTextField txt_startdate;
	private JComboBox<String> cmb_projecttype;
	private JComboBox<String> cmb_department;
	private JComboBox<String> cmb_industry;
	private JComboBox<String> cmb_location;
	private JComboBox<String> cmb_client;
	private JComboBox<String> cmb_pmanager;
	public String designation="Project Manager";
	public String fname,lname,combine,rev_fname,rev_lname,rev_combine,rev_projecttype,rev_deptname,rev_industryname,rev_locationname,rev_clientname;
	int projectTypeId,projectManagerId,clientId,departmentId,locationId,industryId;
	public static int projectID;
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	log userdata = new log();
	
	public void fetchdefaults()
	{
		try {
			//projectID=id;
			//fetching project name
			pst = con.prepareStatement("Select project_name from project where project_id = ?");
			pst.setInt(1,projectID);
			rs = pst.executeQuery();
			while(rs.next())
			{
				txt_projectname.setText(rs.getString(1));
			}
			
			//fetching start date
			pst = con.prepareStatement("Select project_start_date from project where project_id = ?");
			pst.setInt(1,projectID);
			rs = pst.executeQuery();
			while(rs.next())
			{
				txt_startdate.setText(rs.getString(1));
			}
			
			
			//fetching project manager name
			pst = con.prepareStatement("select person.f_name, person.l_name from person inner join project on person.person_id = (SELECT CONCAT('EM',project.project_manager_id)) where project.project_id= ?");
			pst.setInt(1, projectID);
			rs = pst.executeQuery();
			while(rs.next())
			{
				rev_fname = rs.getString("f_name");
				//System.out.println("SEXY BOI's first name: "+rev_fname);
				rev_lname = rs.getString("l_name");
				//System.out.println("SEXY BOI's last name: "+rev_lname);
				rev_combine = (rev_fname+" "+rev_lname);
				//System.out.println(rev_combine);
				//cmb_pmanager.addItem(combine);
			}
			
			//fetching project type
			pst = con.prepareStatement("Select project_type.type_of_project from project_type inner join project on project.project_type_id = project_type.project_type_id where project.project_id = ?");
			pst.setInt(1, projectID);
			rs = pst.executeQuery();
			while(rs.next())
			{
				rev_projecttype=rs.getString(1);
			}
			
			//fetching department
			pst = con.prepareStatement("Select department.department_name from department inner join project on project.department_id = department.department_id where project.project_id=?");
			pst.setInt(1, projectID);
			rs = pst.executeQuery();
			while(rs.next())
			{
				rev_deptname=rs.getString(1);
			}
			
			
			//fetching industry
			pst = con.prepareStatement("Select industry.industry_name from industry inner join project on project.industry_id=industry.industry_id where project.project_id=?");
			pst.setInt(1, projectID);
			rs = pst.executeQuery();
			while(rs.next())
			{
				rev_industryname=rs.getString(1);
			}
			
			//fetching location
			pst = con.prepareStatement("Select location.location_name from location inner join project on project.location_id = location.location_id where project.project_id=?");
			pst.setInt(1, projectID);
			rs = pst.executeQuery();
			while(rs.next())
			{
				rev_locationname=rs.getString(1);
			}
			
			//fetching client
			pst = con.prepareStatement("Select client.client_name from client inner join project on project.client_id = client.client_id where project.project_id=?");
			pst.setInt(1, projectID);
			rs = pst.executeQuery();
			while(rs.next())
			{
				rev_clientname=rs.getString(1);
			}
			
		}
		catch(SQLException ex) {
			System.out.println(ex);
		}
	}
	
	
	
	public void fetchvariables()
	{
		try {
			//fetching project_type_id from type_of_project 
			pst = con.prepareStatement("select project_type_id from project_type where type_of_project = ?");
			pst.setString(1, cmb_projecttype.getSelectedItem().toString());
			rs = pst.executeQuery();
			while(rs.next())
			{
				projectTypeId = rs.getInt("project_type_id");
				//System.out.println(projectTypeId);
			}
			
			
			//fetching project_manager_id from project manager name
			combine=cmb_pmanager.getSelectedItem().toString();
			String[] output=combine.split("\\s+");
			pst = con.prepareStatement("select employee.employee_id from employee inner join person on person.person_id = (select concat('EM',employee.employee_id)) where person.f_name = ? and person.l_name = ?");
			pst.setString(1, output[0]);
			pst.setString(2, output[1]);
			rs = pst.executeQuery();
			while(rs.next())
			{
				projectManagerId = rs.getInt("employee_id");
				System.out.println(projectManagerId);
			}
			
			//fetching client_id from client_name
			pst = con.prepareStatement("select client_id from client where client_name=?");
			pst.setString(1, cmb_client.getSelectedItem().toString());
			rs = pst.executeQuery();
			while(rs.next())
			{
				clientId = rs.getInt("client_id");
				System.out.println(clientId);
			}
			
			//fetching department_id from department name
			pst = con.prepareStatement("select department_id from department where department_name=?");
			pst.setString(1, cmb_department.getSelectedItem().toString());
			rs = pst.executeQuery();
			while(rs.next())
			{
				departmentId = rs.getInt("department_id");
				System.out.println(departmentId);
			}
			
			//fetching location_id from location name
			pst = con.prepareStatement("select location_id from location where location_name=?");
			pst.setString(1, cmb_location.getSelectedItem().toString());
			rs = pst.executeQuery();
			while(rs.next())
			{
				locationId = rs.getInt("location_id");
				System.out.println(locationId);
			}
			
			//fetching industry_id from industry name
			pst = con.prepareStatement("select industry_id from industry where industry_name=?");
			pst.setString(1, cmb_industry.getSelectedItem().toString());
			rs = pst.executeQuery();
			while(rs.next())
			{
				industryId = rs.getInt("industry_id");
				System.out.println(industryId);
			}

		}
		catch(SQLException ex) {
			System.out.println(ex);
		}
	}
	
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
	
	public void fetchdata() {
		try {
			//fetching project manager name
			pst = con.prepareStatement("select person.f_name, person.l_name from person inner join employee on person.person_id = (SELECT CONCAT('EM',employee.employee_id)) where employee.designation= ?");
			pst.setString(1, designation);
			rs = pst.executeQuery();
			while(rs.next())
			{
				fname = rs.getString("f_name");
				lname = rs.getString("l_name");
				combine = (fname+" "+lname);
				cmb_pmanager.addItem(combine);
				
			}
			cmb_pmanager.setSelectedItem(rev_combine);
			
			//fetching project type
			pst = con.prepareStatement("Select type_of_project from project_type where is_active = 1");
			rs = pst.executeQuery();
			while(rs.next())
			{
				cmb_projecttype.addItem(rs.getString("type_of_project"));
				/*if()
				{
					cmb_projecttype.setSelectedItem("");
				}*/
			}
			cmb_projecttype.setSelectedItem(rev_projecttype);
			
			
			//fetching client
			pst = con.prepareStatement("Select client_name from client");
			rs = pst.executeQuery();
			while(rs.next())
			{
				cmb_client.addItem(rs.getString("client_name"));
			}
			cmb_client.setSelectedItem(rev_clientname);
			
			//fetching department
			pst = con.prepareStatement("Select department_name from department where is_active = 1");
			rs = pst.executeQuery();
			while(rs.next())
			{
				cmb_department.addItem(rs.getString("department_name"));
			}
			cmb_department.setSelectedItem(rev_deptname);
			
			//fetching industry
			pst = con.prepareStatement("Select industry_name from industry where is_active = 1");
			rs = pst.executeQuery();
			while(rs.next())
			{
				cmb_industry.addItem(rs.getString("industry_name"));
			}
			cmb_industry.setSelectedItem(rev_industryname);
			
			//fetching location
			pst = con.prepareStatement("Select location_name from location where is_active = 1");
			rs = pst.executeQuery();
			while(rs.next())
			{
				cmb_location.addItem(rs.getString("location_name"));
			}
			cmb_location.setSelectedItem(rev_locationname);
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		projectID = Integer.parseInt(args[0]);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Project_Edit window = new Project_Edit();
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
	public Project_Edit() {
		initialize();
		connect();
		fetchdefaults();
		fetchdata();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.activeCaption);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Project Name:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(33, 88, 105, 37);
		frame.getContentPane().add(lblNewLabel);
		
		txt_projectname = new JTextField();
		txt_projectname.setBounds(212, 99, 219, 26);
		frame.getContentPane().add(txt_projectname);
		txt_projectname.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("PROJECT");
		lblNewLabel_1.setFont(new Font("Serif", Font.BOLD, 23));
		lblNewLabel_1.setBounds(184, 22, 133, 48);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblProjectType = new JLabel("Project Type:");
		lblProjectType.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblProjectType.setBounds(33, 128, 105, 37);
		frame.getContentPane().add(lblProjectType);
		
		JLabel lblProjectManagerName = new JLabel("Project Manager Name:");
		lblProjectManagerName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblProjectManagerName.setBounds(33, 168, 156, 37);
		frame.getContentPane().add(lblProjectManagerName);
		
		JLabel lblDepartment = new JLabel("Department:");
		lblDepartment.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDepartment.setBounds(33, 208, 105, 37);
		frame.getContentPane().add(lblDepartment);
		
		JLabel lblNewLabel_5 = new JLabel("Industry:");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_5.setBounds(33, 248, 105, 37);
		frame.getContentPane().add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Location");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_6.setBounds(33, 288, 105, 37);
		frame.getContentPane().add(lblNewLabel_6);
		
		JLabel lblStartDate = new JLabel("Start Date");
		lblStartDate.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblStartDate.setBounds(33, 328, 105, 37);
		frame.getContentPane().add(lblStartDate);
		
		txt_startdate = new JTextField();
		txt_startdate.setToolTipText("yyyy-mm-dd");
		  txt_startdate.setText("yyyy-mm-dd");
	        txt_startdate.setForeground(Color.LIGHT_GRAY);
	        txt_startdate.addFocusListener(new FocusAdapter() {
	            @Override
	            public void focusGained(FocusEvent e) {
	                if(txt_startdate.getText().trim().equals("yyyy-mm-dd")) {
	                	txt_startdate.setText("");
	                }  
	                txt_startdate.setForeground(Color.BLACK);
	            }
	            @Override
	            public void focusLost(FocusEvent e) {
	                if(txt_startdate.getText().trim().equals("")) {
	                	txt_startdate.setText("yyyy-mm-dd");
	                }
	                txt_startdate.setForeground(Color.LIGHT_GRAY);
	            }
	        });
		txt_startdate.setColumns(10);
		txt_startdate.setBounds(212, 339, 219, 26);
		frame.getContentPane().add(txt_startdate);
		
		cmb_projecttype = new JComboBox();
		cmb_projecttype.setBounds(212, 138, 219, 21);
		frame.getContentPane().add(cmb_projecttype);
		
		cmb_department = new JComboBox();
		cmb_department.setBounds(212, 224, 219, 21);
		frame.getContentPane().add(cmb_department);
		
		cmb_industry = new JComboBox();
		cmb_industry.setBounds(212, 264, 219, 21);
		frame.getContentPane().add(cmb_industry);
		
		cmb_location = new JComboBox();
		cmb_location.setBounds(212, 304, 219, 21);
		frame.getContentPane().add(cmb_location);
		
		JButton btnsave = new JButton("Save Changes");
		btnsave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date currentDate = new Date(System.currentTimeMillis());
				try {
					fetchvariables();
					pst = con.prepareStatement("UPDATE project SET project_name = ?, project_type_id = ?,project_manager_id = ?, client_id = ?, department_id = ?, industry_id = ?, location_id = ?, project_start_date = ?, modified_date = ?, modified_by = ? where project_id = ?");
					pst.setString(1, txt_projectname.getText());
					pst.setInt(2, projectTypeId);
					pst.setInt(3, projectManagerId);
					pst.setInt(4, clientId);
					pst.setInt(5, departmentId);
					pst.setInt(6, industryId);
					pst.setInt(7, locationId);
					pst.setString(8, txt_startdate.getText());
					pst.setDate(9,currentDate);
					pst.setInt(10, log.userId);
					pst.setInt(11, projectID);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record Updated");
					txt_projectname.requestFocus();
					frame.dispose();
				}
				catch(SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnsave.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnsave.setBounds(85, 464, 138, 37);
		frame.getContentPane().add(btnsave);
		
		JButton btnNewButton = new JButton("Cancel");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setBounds(267, 464, 139, 37);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel_6_1 = new JLabel("Client:");
		lblNewLabel_6_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_6_1.setBounds(33, 371, 105, 37);
		frame.getContentPane().add(lblNewLabel_6_1);
		
		cmb_client = new JComboBox();
		cmb_client.setBounds(212, 380, 219, 21);
		frame.getContentPane().add(cmb_client);
		
		cmb_pmanager = new JComboBox();
		cmb_pmanager.setBounds(212, 177, 219, 21);
		frame.getContentPane().add(cmb_pmanager);
		frame.setBounds(100, 100, 508, 624);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
