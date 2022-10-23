package ClientAdd;

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
public class ClientAdd {

	private JFrame frame;
	private JTextField textField;

	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	log userdata = new log();
	private JTextField textField_1;
	
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
					ClientAdd window = new ClientAdd();
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
	public ClientAdd() {
		initialize();
		connect();
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
		
		JLabel lblHrms = new JLabel("HRMS: Clients");
		lblHrms.setHorizontalAlignment(SwingConstants.CENTER);
		lblHrms.setFont(new Font("Arial", Font.BOLD, 26));
		lblHrms.setBounds(154, 11, 202, 26);
		frame.getContentPane().add(lblHrms);
		
		JLabel lblNewLabel = new JLabel("Client Name");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel.setBounds(79, 93, 130, 38);
		frame.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(247, 101, 188, 26);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblLocationId = new JLabel("Location ID");
		lblLocationId.setFont(new Font("Arial", Font.PLAIN, 16));
		lblLocationId.setBounds(84, 153, 91, 38);
		frame.getContentPane().add(lblLocationId);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(247, 161, 188, 26);
		frame.getContentPane().add(textField_1);
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				Date currentDate = new Date(System.currentTimeMillis());
				try {
					pst = con.prepareStatement("INSERT INTO client(client_name,location_id,created_date, is_active, modified_by) VALUES (?,?,?,?,?)");
					pst.setString(1, textField.getText());
					pst.setString(2, textField_1.getText());
					pst.setDate(3,currentDate);
					pst.setBoolean(4, true);
					pst.setInt(5, log.userId);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record added");
					frame.dispose();
				}
				catch(SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(100, 249, 91, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Exit");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnNewButton_1.setBounds(344, 249, 91, 23);
		frame.getContentPane().add(btnNewButton_1);
		
	}

}
