package ClientEdit.copy;

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
import java.awt.event.ActionEvent; 
import java.sql.Date;
public class ClientEdit{

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	public static int ClientID;
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	log userdata = new log();
	
	
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
		ClientID = Integer.parseInt(args[0]);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientEdit window = new ClientEdit();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public void getData(int id) {
		 try {
			 		ClientID = id;
	                pst = con.prepareStatement("Select client_name from client where client_id = ?");
	                pst.setInt(1, id);
	                ResultSet rs = pst.executeQuery();
	                if(rs.next()==true)
		            {
	                String ClientName = rs.getString(1);
	                textField.setText(ClientName);
		            }  
	                
	                ClientID = id;
	                pst = con.prepareStatement("Select location_id from client where client_id = ?");
	                pst.setInt(1, id);
	                ResultSet rs1 = pst.executeQuery();
	                if(rs1.next()==true)
		            {
	                String locationId = rs1.getString(1);
	                textField_1.setText(locationId);
		            }  
	        } 
		
		 catch (SQLException ex) {
	           
     }
	}
	/**
	 * Create the application.
	 */
	public ClientEdit() {
		initialize();
		connect();
		getData(ClientID);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.activeCaption);
		frame.setBounds(500, 200, 523, 357);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblHrms = new JLabel("HRMS");
		lblHrms.setHorizontalAlignment(SwingConstants.CENTER);
		lblHrms.setFont(new Font("Arial", Font.BOLD, 26));
		lblHrms.setBounds(129, 11, 177, 38);
		frame.getContentPane().add(lblHrms);
		
		JLabel lblNewLabel = new JLabel("   Client Name");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel.setBounds(73, 93, 102, 38);
		frame.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(209, 101, 188, 26);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		

		JLabel lblLocationId = new JLabel("Location ID");
		lblLocationId.setFont(new Font("Arial", Font.PLAIN, 16));
		lblLocationId.setBounds(95, 142, 91, 38);
		frame.getContentPane().add(lblLocationId);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(209, 150, 188, 26);
		frame.getContentPane().add(textField_1);
		
		JButton btnNewButton = new JButton("Update");
		btnNewButton.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent e) { 
				Date currentDate = new Date(System.currentTimeMillis());
				try {
					pst = con.prepareStatement("UPDATE  client SET client_name = ?,location_id = ?, modified_date = ?, modified_by = ? where client_id = ?");
					pst.setString(1, textField.getText());
					pst.setString(2, textField_1.getText());
					pst.setDate(3,currentDate);
					pst.setInt(4, log.userId);
					pst.setInt(5, ClientID);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record Updated");
					textField.requestFocus();
					textField_1.requestFocus();
				}
				catch(SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(84, 235, 91, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Exit");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnNewButton_1.setBounds(306, 235, 91, 23);
		frame.getContentPane().add(btnNewButton_1);	
		
	}
}
