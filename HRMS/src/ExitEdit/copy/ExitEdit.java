package ExitEdit.copy;

import java.awt.Color;
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
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.awt.event.ActionEvent; 
import java.sql.Date;
public class ExitEdit {

	private JFrame frame;
	private JTextField textField;
	
	public static int ExitID;
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	log userdata = new log();
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
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
		ExitID = Integer.parseInt(args[0]);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ExitEdit window = new ExitEdit();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public void getData(int id) {
		 try {
			 		ExitID = id;
	                pst = con.prepareStatement("Select resignation_date from employee_exit where exit_id = ?");
	                pst.setInt(1, id);
	                ResultSet rs = pst.executeQuery();
	                if(rs.next()==true)
		            {
	                String resignationdate = rs.getString(1);
	                textField.setText(resignationdate);
		            }  
	         
	                ExitID = id;
	                pst = con.prepareStatement("Select notice_period from employee_exit where exit_id = ?");
	                pst.setInt(1, id);
	                ResultSet rs1 = pst.executeQuery();
	                if(rs1.next()==true)
		            {
	                String noticeperiod = rs1.getString(1);
	                textField_1.setText(noticeperiod);
		            }  
	                
	                ExitID = id;
	                pst = con.prepareStatement("Select separation_date from employee_exit where exit_id = ?");
	                pst.setInt(1, id);
	                ResultSet rs2 = pst.executeQuery();
	                if(rs2.next()==true)
		            {
	                String separationdate = rs2.getString(1);
	                textField_2.setText(separationdate);
		            } 
	           
	                ExitID = id;
	                pst = con.prepareStatement("Select employee_id from employee_exit where exit_id = ?");
	                pst.setInt(1, id);
	                ResultSet rs3 = pst.executeQuery();
	                if(rs3.next()==true)
		            {
	                String employeeid = rs3.getString(1);
	                textField_3.setText(employeeid);
		            } 
	     
	                ExitID = id;
	                pst = con.prepareStatement("Select no_dues from employee_exit where exit_id = ?");
	                pst.setInt(1, id);
	                ResultSet rs4 = pst.executeQuery();
	                if(rs4.next()==true)
		            {
	                String No_dues = rs4.getString(1);
	                textField_4.setText(No_dues);
		            } 
	        } 
		
		 catch (SQLException ex) {
	           
	        }
	}
	/**
	 * Create the application.
	 */
	public ExitEdit() {
		initialize();
		connect();
		getData(ExitID);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.activeCaption);
		frame.setBounds(500, 200, 916, 701);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblHrms = new JLabel("HRMS: Exit");
		lblHrms.setHorizontalAlignment(SwingConstants.CENTER);
		lblHrms.setFont(new Font("Arial", Font.BOLD, 26));
		lblHrms.setBounds(331, 11, 243, 38);
		frame.getContentPane().add(lblHrms);
		
		JLabel lblNewLabel = new JLabel("Resignation Date :");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel.setBounds(239, 97, 130, 38);
		frame.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
        textField.setToolTipText("yyyy-mm-dd");
        textField.setText("yyyy-mm-dd");
        textField.setForeground(Color.LIGHT_GRAY);
        textField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if(textField.getText().trim().equals("yyyy-mm-dd")) {
                    textField.setText("");
                }
               
                textField.setForeground(Color.BLACK);
            }
            @Override
            public void focusLost(FocusEvent e) {
                if(textField.getText().trim().equals("")) {
                    textField.setText("yyyy-mm-dd");
                }
               
                textField.setForeground(Color.LIGHT_GRAY);
            }
        });
		textField.setBounds(407, 105, 188, 26);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		
		JButton btnNewButton = new JButton("Update");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				Date currentDate = new Date(System.currentTimeMillis());
				try {
					pst = con.prepareStatement("UPDATE  employee_exit SET resignation_date = ?,notice_period = ?,separation_date = ?,employee_id = ?,no_dues = ?, modified_date = ?, modified_by = ? where  exit_id = ?");
					pst.setString(1, textField.getText());
					pst.setString(2, textField_1.getText());
					pst.setString(3, textField_2.getText());
					pst.setString(4, textField_3.getText());
					pst.setString(5, textField_4.getText());
					pst.setDate(6,currentDate);
					pst.setInt(7, log.userId);
					pst.setInt(8, ExitID);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record Updated");
					textField.requestFocus();
					textField_1.requestFocus();
					textField_2.requestFocus();
					textField_3.requestFocus();
					textField_4.requestFocus();
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
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(407, 162, 188, 26);
		frame.getContentPane().add(textField_1);
		
		textField_2 = new JTextField();
		textField_2 = new JTextField();
        textField_2.setToolTipText("yyyy-mm-dd");
        textField_2.setText("yyyy-mm-dd");
        textField_2.setForeground(Color.LIGHT_GRAY);
        textField_2.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if(textField_2.getText().trim().equals("yyyy-mm-dd")) {
                    textField_2.setText("");
                }  
                textField_2.setForeground(Color.BLACK);
            }
            @Override
            public void focusLost(FocusEvent e) {
                if(textField_2.getText().trim().equals("")) {
                    textField_2.setText("yyyy-mm-dd");
                }
                textField_2.setForeground(Color.LIGHT_GRAY);
            }
        });
		textField_2.setColumns(10);
		textField_2.setBounds(407, 221, 188, 26);
		frame.getContentPane().add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(407, 284, 188, 26);
		frame.getContentPane().add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(407, 340, 188, 26);
		frame.getContentPane().add(textField_4);
		
		JLabel lblResinoticePeriod = new JLabel("Notice Period:");
		lblResinoticePeriod.setFont(new Font("Arial", Font.PLAIN, 16));
		lblResinoticePeriod.setBounds(260, 154, 130, 38);
		frame.getContentPane().add(lblResinoticePeriod);
		
		JLabel lblSeparationDate = new JLabel("Separation Date:");
		lblSeparationDate.setFont(new Font("Arial", Font.PLAIN, 16));
		lblSeparationDate.setBounds(249, 213, 130, 38);
		frame.getContentPane().add(lblSeparationDate);
		
		JLabel lblEmployee = new JLabel("Employee id:");
		lblEmployee.setFont(new Font("Arial", Font.PLAIN, 16));
		lblEmployee.setBounds(273, 276, 96, 38);
		frame.getContentPane().add(lblEmployee);
		
		JLabel lblNoDues = new JLabel("No Dues:");
		lblNoDues.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNoDues.setBounds(298, 332, 80, 38);
		frame.getContentPane().add(lblNoDues);
		
	}
}
