package userAdd;

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
public class UserAdd {

	private JFrame frame;
	private JTextField textEmail;

	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	log userdata = new log();
	private JTextField textPassword;
	private JTextField textEmployeeID;
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
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserAdd window = new UserAdd();
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
	public UserAdd() {
		connect();
		getRoles();
		initialize();
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
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.activeCaption);
		frame.setBounds(500, 200, 576, 412);
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
		comboBox.setBounds(237, 212, 233, 38);
		frame.getContentPane().add(comboBox);

		
		JButton btnAddUser = new JButton("Add");
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
					pst = con.prepareStatement("INSERT INTO user(email_address,password,employee_id,role_id, created_date, is_active, modified_by) VALUES (?,?,?,?,?,?,?)");
					pst.setString(1, textEmail.getText());
					pst.setString(2, textPassword.getText());
					pst.setString(3, textEmployeeID.getText());
					pst.setInt(4, selectedRoleId);
					pst.setDate(5,currentDate);
					pst.setBoolean(6, true);
					pst.setInt(7, log.userId);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record added");
					textEmail.setText("");
					textEmail.requestFocus();
				}
				catch(SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnAddUser.setBounds(97, 302, 91, 23);
		frame.getContentPane().add(btnAddUser);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnExit.setBounds(379, 302, 91, 23);
		frame.getContentPane().add(btnExit);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Arial", Font.PLAIN, 18));
		lblPassword.setBounds(97, 113, 130, 38);
		frame.getContentPane().add(lblPassword);
		
		textPassword = new JTextField();
		textPassword.setColumns(10);
		textPassword.setBounds(237, 111, 233, 36);
		frame.getContentPane().add(textPassword);
		
		JLabel lblEmployeeId = new JLabel("Employee ID");
		lblEmployeeId.setFont(new Font("Arial", Font.PLAIN, 18));
		lblEmployeeId.setBounds(97, 164, 130, 38);
		frame.getContentPane().add(lblEmployeeId);
		
		textEmployeeID = new JTextField();
		textEmployeeID.setColumns(10);
		textEmployeeID.setBounds(237, 162, 233, 36);
		frame.getContentPane().add(textEmployeeID);
		
		JLabel lblAccessLevel = new JLabel("Access Level");
		lblAccessLevel.setFont(new Font("Arial", Font.PLAIN, 18));
		lblAccessLevel.setBounds(97, 213, 130, 38);
		frame.getContentPane().add(lblAccessLevel);
		
		
	}
}
