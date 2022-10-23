package User.copy;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.SwingConstants;

import log.log;
import mainPage.MainPage;
import net.proteanit.sql.DbUtils;
import userAdd.UserAdd;
import userEdit.UserEdit;
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
import java.sql.Date;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionEvent;
public class User {

	private JFrame frame;

	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	log userdata = new log();
	List<Object> lstObject = new ArrayList<Object>();
	private JTextField textUserID;
	private JTable table;
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
					User window = new User();
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
	public User() {
		connect();
		initialize();
		getUsers();
	}
	public void getUsers() {
		try {
			pst = con.prepareStatement("select employee_id as Employee_ID,email_address as Email_ID,password as Password, role_id as ROLE_ID from user where is_active=1");
	        ResultSet rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
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
		frame.setBounds(200, 50, 1172, 751);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblHrms = new JLabel("HRMS");
		lblHrms.setHorizontalAlignment(SwingConstants.CENTER);
		lblHrms.setFont(new Font("Arial", Font.BOLD, 26));
		lblHrms.setBounds(456, 11, 177, 38);
		frame.getContentPane().add(lblHrms);
		
		JLabel lblNewLabel_1 = new JLabel("Employee ID");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(48, 105, 110, 28);
		frame.getContentPane().add(lblNewLabel_1);
		
		textUserID = new JTextField();
		textUserID.setColumns(10);
		textUserID.setBounds(197, 110, 148, 23);
		frame.getContentPane().add(textUserID);
		
		JButton btnNewButton_1 = new JButton("Add");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserAdd.main(null);
			}
		});
		btnNewButton_1.setBounds(990, 112, 148, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("Edit");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] textValue = new String[]{textUserID.getText()};
				UserEdit.main(textValue);
			}
		});
		btnNewButton_1_1.setBounds(896, 643, 91, 23);
		frame.getContentPane().add(btnNewButton_1_1);
		
		JButton btnNewButton_1_2 = new JButton("Delete");
		btnNewButton_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					pst = con.prepareStatement("Update user SET is_active = ? where employee_id =?");
		            pst.setInt(1, 0);
		            pst.setString(2, textUserID.getText());
		            pst.executeUpdate();
		            JOptionPane.showMessageDialog(null, "Record Delete!!!!!");
		            getUsers();
				}
				catch(SQLException ex) {
					ex.printStackTrace();
				}
			}
		});
		btnNewButton_1_2.setBounds(1047, 643, 91, 23);
		frame.getContentPane().add(btnNewButton_1_2);
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
			    	if(textUserID.getText().equals(""))
			    	{
					    pst = con.prepareStatement("select employee_id as Employee_ID,email_address as Email_ID,password as Password, role_id as ROLE_ID from user where is_active=1 ");
			    	}
			    	else
			    	{
					    pst = con.prepareStatement("select employee_id as Employee_ID,email_address as Email_ID,password as Password, role_id as ROLE_ID from user where is_active=1 && employee_id = ?");
					    pst.setString(1, textUserID.getText());
			    	}
					
			        ResultSet rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
				}  
				catch(SQLException ex)
				{
					JOptionPane.showMessageDialog(null, ex);	
				}
			}
		});
		btnNewButton.setBounds(381, 110, 91, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_2 = new JButton("Refresh");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getUsers();
			}
		});
		btnNewButton_2.setBounds(505, 110, 91, 23);
		frame.getContentPane().add(btnNewButton_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(37, 167, 1098, 454);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnHome = new JButton("Home");
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				MainPage.main(null);
			}
		});
		btnHome.setFont(new Font("Arial", Font.PLAIN, 13));
		btnHome.setBackground(SystemColor.menu);
		btnHome.setBounds(48, 24, 91, 23);
		frame.getContentPane().add(btnHome);
		
		
	}
}
