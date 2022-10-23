package userEdit;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.SwingConstants;

import log.log;

import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
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
public class UserEdit {

	private JFrame frame;
	private JTextField textEmail;
	public static int employee_Id;
	public int role_id;
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	log userdata = new log();
	private JTextField textPassword;
	List<Object> lstObject = new ArrayList<Object>();
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
		employee_Id = Integer.parseInt(args[0]);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserEdit window = new UserEdit();
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
	public UserEdit() {
		connect();
		getRoles();
		initialize();
		getData();
	}

	public void getRoles() {
		try {
			pst = con.prepareStatement("select role_id,user_role_name from access_level");
	        ResultSet rs = pst.executeQuery();
		    while(rs.next()==true)
		    {

		    	List<Object> test = new ArrayList<Object>();
		        int roleID = rs.getInt(1);
		        String roleName = rs.getString(2);
		        test.add(roleID);
		        test.add(roleName);
		    	lstObject.add(test);
		    } 
		}  
		catch(SQLException ex)
		{
			JOptionPane.showMessageDialog(null, ex);	
		}
	}
	public void getData() {
		 try {
	                pst = con.prepareStatement("Select email_address as Email_ID, password as Password, role_id from user where employee_id = ? && is_active = 1");
	                pst.setInt(1, employee_Id);
	                ResultSet rs = pst.executeQuery();
	                if(rs.next()==true)
		            {
	                textEmail.setText(rs.getString(1));
	                textPassword.setText(rs.getString(2));
	                role_id = rs.getInt(2);
	                
		            }  
	        } 
		
		 catch (SQLException ex) {
	           
	        }
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.activeCaption);
		frame.setBounds(500, 200, 576, 360);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblHrms = new JLabel("HRMS");
		lblHrms.setHorizontalAlignment(SwingConstants.CENTER);
		lblHrms.setFont(new Font("Arial", Font.BOLD, 26));
		lblHrms.setBounds(147, 11, 177, 38);
		frame.getContentPane().add(lblHrms);
		
		JLabel lblNewLabel = new JLabel("Email ID");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel.setBounds(97, 62, 130, 51);
		frame.getContentPane().add(lblNewLabel);
		
		textEmail = new JTextField();
		textEmail.setBounds(237, 60, 233, 36);
		frame.getContentPane().add(textEmail);
		textEmail.setColumns(10);

		JComboBox comboBox = new JComboBox();
		for(int i=0;i<lstObject.size();i++)
		{
			List<Object> comboTemp = (List<Object>) lstObject.get(i);
			comboBox.addItem((String)comboTemp.get(1));
		}
		comboBox.setSelectedIndex(1);
		comboBox.setBounds(237, 176, 233, 38);
		frame.getContentPane().add(comboBox);

		
		JButton btnAddUser = new JButton("Update");
		btnAddUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				String selectedValue = (String) comboBox.getSelectedItem();
				int selectedRoleId=0;
				for(int i=0;i<lstObject.size();i++)
				{
					List<Object> comboTemp = (List<Object>) lstObject.get(i);
					String tempValue = (String)comboTemp.get(1);
					if(tempValue.equals(selectedValue))
					{
						selectedRoleId = (int)comboTemp.get(0);
						break;
					}
				}
				Date currentDate = new Date(System.currentTimeMillis());
				try {
					pst = con.prepareStatement("Update user Set email_address = ?,password = ?,role_id = ?, modified_date = ?, modified_by = ? where employee_id= ?");
					pst.setString(1, textEmail.getText());
					pst.setString(2, textPassword.getText());
					pst.setInt(3, selectedRoleId);
					pst.setDate(4, currentDate);
					pst.setInt(5, log.userId);
					pst.setInt(6, employee_Id);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record Updated");
				}
				catch(SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnAddUser.setBounds(97, 259, 91, 23);
		frame.getContentPane().add(btnAddUser);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnExit.setBounds(376, 259, 91, 23);
		frame.getContentPane().add(btnExit);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Arial", Font.PLAIN, 18));
		lblPassword.setBounds(97, 113, 130, 38);
		frame.getContentPane().add(lblPassword);
		
		textPassword = new JTextField();
		textPassword.setColumns(10);
		textPassword.setBounds(237, 111, 233, 36);
		frame.getContentPane().add(textPassword);
		
		JLabel lblAccessLevel = new JLabel("Access Level");
		lblAccessLevel.setFont(new Font("Arial", Font.PLAIN, 18));
		lblAccessLevel.setBounds(97, 177, 130, 38);
		frame.getContentPane().add(lblAccessLevel);
		
		
	}
}
