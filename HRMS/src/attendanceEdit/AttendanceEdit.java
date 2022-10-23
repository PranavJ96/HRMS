package attendanceEdit;

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
public class AttendanceEdit {

	private JFrame frame;
	private JTextField txt_totleave;
	public static int attendanceID;
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	log userdata = new log();
	private JTextField txt_usedleave;
	
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
		attendanceID = Integer.parseInt(args[0]);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AttendanceEdit window = new AttendanceEdit();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public void getData(int id) {
		 try {
			 		attendanceID = id;
	                pst = con.prepareStatement("Select total_leaves,used_leaves from attendance where attendance_id = ?");
	                pst.setInt(1,attendanceID);
	                ResultSet rs = pst.executeQuery();
	                if(rs.next()==true)
		            {
	                Integer totLeaves = rs.getInt("total_leaves");
	                Integer usedLeaves = rs.getInt("used_leaves");
	                txt_totleave.setText(totLeaves.toString());
	                txt_usedleave.setText(usedLeaves.toString());
		            }  
	        } 
		
		 catch (SQLException ex) {
	           
	        }
	}
	/**
	 * Create the application.
	 */
	public AttendanceEdit() {
		initialize();
		connect();
		getData(attendanceID);
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
		
		JLabel lblNewLabel = new JLabel("Total Leaves:");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel.setBounds(63, 93, 130, 38);
		frame.getContentPane().add(lblNewLabel);
		
		txt_totleave = new JTextField();
		txt_totleave.setBounds(209, 101, 188, 26);
		frame.getContentPane().add(txt_totleave);
		txt_totleave.setColumns(10);
		
		JButton btnNewButton = new JButton("Update");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				Date currentDate = new Date(System.currentTimeMillis());
				try {
					pst = con.prepareStatement("UPDATE  attendance SET total_leaves = ?, used_leaves = ?, modified_date = ?,modified_by = ? where attendance_id = ?");
					pst.setString(1,txt_totleave.getText());
					pst.setString(2,txt_usedleave.getText());
					pst.setDate(3,currentDate);
					pst.setInt(4, log.userId);
					pst.setInt(5, attendanceID);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record Updated");
					frame.dispose();
				}
				catch(SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(74, 220, 91, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Exit");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnNewButton_1.setBounds(306, 220, 91, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		JLabel lblLeavesUsed = new JLabel("Leaves Used:");
		lblLeavesUsed.setFont(new Font("Arial", Font.PLAIN, 16));
		lblLeavesUsed.setBounds(63, 145, 130, 38);
		frame.getContentPane().add(lblLeavesUsed);
		
		txt_usedleave = new JTextField();
		txt_usedleave.setColumns(10);
		txt_usedleave.setBounds(209, 154, 188, 26);
		frame.getContentPane().add(txt_usedleave);
	}

}
