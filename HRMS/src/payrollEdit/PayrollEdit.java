package payrollEdit;

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
public class PayrollEdit {

	private JFrame frame;
	private JTextField txt_bonus;
	public static String personID;
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	log userdata = new log();
	private JTextField txt_compensation;
	private JTextField textSalary;
	
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
		personID = args[0];
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PayrollEdit window = new PayrollEdit();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public void getData() {
		 try {
			 		//personID = id;
	                pst = con.prepareStatement("Select salary, bonus,compensation from payroll where person_id = ?");
	                pst.setString(1,personID);
	                ResultSet rs = pst.executeQuery();
	                if(rs.next()==true)
		            {
	                Integer bonus = rs.getInt("bonus");
	                Integer compensation = rs.getInt("compensation");
	                Integer salary = rs.getInt("salary");
	                //System.out.println("Total: "+totLeaves);
	                //System.out.println("Used: "+usedLeaves);
	                txt_bonus.setText(bonus.toString());
	                txt_compensation.setText(compensation.toString());
	                textSalary.setText(salary.toString());
		            }  
	        } 
		
		 catch (SQLException ex) {
	           
	        }
	}
	/**
	 * Create the application.
	 */
	public PayrollEdit() {
		initialize();
		connect();
		getData();
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
		
		JLabel lblNewLabel = new JLabel("Bonus:");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel.setBounds(59, 122, 130, 38);
		frame.getContentPane().add(lblNewLabel);
		
		txt_bonus = new JTextField();
		txt_bonus.setBounds(205, 130, 188, 26);
		frame.getContentPane().add(txt_bonus);
		txt_bonus.setColumns(10);
		
		JButton btnNewButton = new JButton("Update");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				Date currentDate = new Date(System.currentTimeMillis());
				try {
					pst = con.prepareStatement("UPDATE  payroll SET salary = ?, bonus = ?, compensation = ?,modified_date = ?, modified_by = ? where person_id = ?");
					pst.setString(1, textSalary.getText());
					pst.setInt(2, Integer.parseInt(txt_bonus.getText()));
					pst.setInt(3, Integer.parseInt(txt_compensation.getText()));
					pst.setDate(4,currentDate);
					pst.setInt(5, log.userId);
					pst.setString(6, personID);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record Updated");
					txt_bonus.requestFocus();
					frame.dispose();
				}
				catch(SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(70, 249, 91, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Exit");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnNewButton_1.setBounds(302, 249, 91, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		JLabel lblLeavesUsed = new JLabel("Compensation:");
		lblLeavesUsed.setFont(new Font("Arial", Font.PLAIN, 16));
		lblLeavesUsed.setBounds(59, 174, 130, 38);
		frame.getContentPane().add(lblLeavesUsed);
		
		txt_compensation = new JTextField();
		txt_compensation.setColumns(10);
		txt_compensation.setBounds(205, 183, 188, 26);
		frame.getContentPane().add(txt_compensation);
		
		JLabel lblSalary = new JLabel("Salary:");
		lblSalary.setFont(new Font("Arial", Font.PLAIN, 16));
		lblSalary.setBounds(59, 73, 130, 38);
		frame.getContentPane().add(lblSalary);
		
		textSalary = new JTextField();
		textSalary.setColumns(10);
		textSalary.setBounds(205, 81, 188, 26);
		frame.getContentPane().add(textSalary);
	}

}
