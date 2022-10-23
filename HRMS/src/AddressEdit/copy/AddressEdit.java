package AddressEdit.copy;
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
public class AddressEdit {

	private JFrame frame;
	private JTextField textField;
	
	public static int AddressID;
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	log userdata = new log();
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
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
		AddressID = Integer.parseInt(args[0]);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddressEdit window = new AddressEdit();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public void getData(int id) {
		 try {
			 		AddressID = id;
	                pst = con.prepareStatement("Select door_number from address where address_id = ?");
	                pst.setInt(1, id);
	                ResultSet rs = pst.executeQuery();
	                if(rs.next()==true)
		            {
	                String doornumber = rs.getString(1);
	                textField.setText(doornumber);
		            }  
	     
	                AddressID = id;
	                pst = con.prepareStatement("Select street_name from address where address_id = ?");
	                pst.setInt(1, id);
	                ResultSet rs1 = pst.executeQuery();
	                if(rs1.next()==true)
		            {
	                String streetName = rs1.getString(1);
					textField_1.setText(streetName);
		            }  
	                
	                AddressID = id;
	                pst = con.prepareStatement("Select area from address where address_id = ?");
	                pst.setInt(1, id);
	                ResultSet rs2 = pst.executeQuery();
	                if(rs2.next()==true)
		            {
	                String Area = rs2.getString(1);
	                textField_2.setText(Area);
		            }  
	                
	                AddressID = id;
	                pst = con.prepareStatement("Select city from address where address_id = ?");
	                pst.setInt(1, id);
	                ResultSet rs3 = pst.executeQuery();
	                if(rs3.next()==true)
		            {
	                String city = rs3.getString(1);
	                textField_3.setText(city);
		            }  
	                
	                AddressID = id;
	                pst = con.prepareStatement("Select postcode from address where address_id = ?");
	                pst.setInt(1, id);
	                ResultSet rs4 = pst.executeQuery();
	                if(rs4.next()==true)
		            {
	                String postcode = rs4.getString(1);
	                textField_4.setText(postcode);
		            }  
	                
	                AddressID = id;
	                pst = con.prepareStatement("Select country from address where address_id = ?");
	                pst.setInt(1, id);
	                ResultSet rs5 = pst.executeQuery();
	                if(rs5.next()==true)
		            {
	                String country = rs5.getString(1);
	                textField_5.setText(country);
		            }  
	        } 
		
		 catch (SQLException ex) {
	           
	        }
	}
	/**
	 * Create the application.
	 */
	public AddressEdit() {
		initialize();
		connect();
		getData(AddressID);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.activeCaption);
		frame.setBounds(280, 50, 916, 701);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblHrms = new JLabel("HRMS: ADDRESS");
		lblHrms.setHorizontalAlignment(SwingConstants.CENTER);
		lblHrms.setFont(new Font("Arial", Font.BOLD, 26));
		lblHrms.setBounds(331, 11, 243, 38);
		frame.getContentPane().add(lblHrms);
		
		JLabel lblNewLabel = new JLabel("Door_Number :");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel.setBounds(239, 97, 130, 38);
		frame.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(407, 105, 188, 26);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblStreetName = new JLabel("Street Name :");
		lblStreetName.setFont(new Font("Arial", Font.PLAIN, 16));
		lblStreetName.setBounds(249, 158, 130, 26);
		frame.getContentPane().add(lblStreetName);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(407, 158, 188, 26);
		frame.getContentPane().add(textField_1);
		
		JLabel lblArea = new JLabel("Area :");
		lblArea.setFont(new Font("Arial", Font.PLAIN, 16));
		lblArea.setBounds(301, 199, 68, 38);
		frame.getContentPane().add(lblArea);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(407, 207, 188, 26);
		frame.getContentPane().add(textField_2);
		
		JLabel lblCity = new JLabel("City :");
		lblCity.setFont(new Font("Arial", Font.PLAIN, 16));
		lblCity.setBounds(311, 251, 48, 38);
		frame.getContentPane().add(lblCity);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(407, 259, 188, 26);
		frame.getContentPane().add(textField_3);
		
		JLabel lblPostCode = new JLabel("Post code :");
		lblPostCode.setFont(new Font("Arial", Font.PLAIN, 16));
		lblPostCode.setBounds(270, 303, 130, 26);
		frame.getContentPane().add(lblPostCode);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(407, 305, 188, 26);
		frame.getContentPane().add(textField_4);
		
		JLabel lblCountry = new JLabel("Country :");
		lblCountry.setFont(new Font("Arial", Font.PLAIN, 16));
		lblCountry.setBounds(290, 359, 79, 26);
		frame.getContentPane().add(lblCountry);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(407, 361, 188, 26);
		frame.getContentPane().add(textField_5);
		
		JButton btnNewButton = new JButton("Update");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				Date currentDate = new Date(System.currentTimeMillis());
				try {
					pst = con.prepareStatement("UPDATE  address SET door_number = ?,street_name = ?,area = ?,city = ?,postcode = ?,country = ?, modified_date = ?, modified_by = ? where  address_id = ?");
					pst.setString(1, textField.getText());
					pst.setString(2, textField_1.getText());
					pst.setString(3, textField_2.getText());
					pst.setString(4, textField_3.getText());
					pst.setString(5, textField_4.getText());
					pst.setString(6, textField_5.getText());
					pst.setDate(7,currentDate);
					pst.setInt(8, log.userId);
					pst.setInt(9, AddressID);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record Updated");
					frame.dispose();
				}
				catch(SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(210, 470, 91, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Exit");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnNewButton_1.setBounds(576, 470, 91, 23);
		frame.getContentPane().add(btnNewButton_1);
		
	}
}
