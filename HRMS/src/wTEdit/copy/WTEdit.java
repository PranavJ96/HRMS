package wTEdit.copy;

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
public class WTEdit {

	private JFrame frame;
	private JTextField textField;
	public static int wTID;
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
		wTID = Integer.parseInt(args[0]);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WTEdit window = new WTEdit();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public void getData(int id) {
		 try {
			 		wTID = id;
	                pst = con.prepareStatement("Select work_type_name from work_type where wt_id = ?");
	                pst.setInt(1, id);
	                ResultSet rs = pst.executeQuery();
	                if(rs.next()==true)
		            {
	                String workTypeName = rs.getString(1);
	                textField.setText(workTypeName);
		            }  
	        } 
		
		 catch (SQLException ex) {
	           
	        }
	}
	/**
	 * Create the application.
	 */
	public WTEdit() {
		initialize();
		connect();
		getData(wTID);
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
		
		JLabel lblNewLabel = new JLabel("Work Type");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel.setBounds(63, 93, 130, 38);
		frame.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(209, 101, 188, 26);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Update");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				Date currentDate = new Date(System.currentTimeMillis());
				try {
					pst = con.prepareStatement("UPDATE  work_type SET work_type_name = ?, modified_date = ?, modified_by = ? where wt_id = ?");
					pst.setString(1, textField.getText());
					pst.setDate(2,currentDate);
					pst.setInt(3, log.userId);
					pst.setInt(4, wTID);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record Updated");
					textField.requestFocus();
				}
				catch(SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(73, 188, 91, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Exit");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnNewButton_1.setBounds(301, 188, 91, 23);
		frame.getContentPane().add(btnNewButton_1);
	}

}
