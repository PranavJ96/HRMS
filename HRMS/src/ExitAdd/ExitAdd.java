package ExitAdd;

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
import java.awt.event.FocusListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.sql.Date;
public class ExitAdd {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	
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
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ExitAdd window = new ExitAdd();
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
	public ExitAdd() {
		initialize();
		connect();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.activeCaption);
		frame.setBounds(500, 200, 908, 586);
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
        textField.addFocusListener (new FocusAdapter() {
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
    	textField.setColumns(10);
		textField.setBounds(407, 105, 188, 26);
		frame.getContentPane().add(textField);
	
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(407, 162, 188, 26);
		frame.getContentPane().add(textField_1);
		
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
		
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				Date currentDate = new Date(System.currentTimeMillis());
				try {
					pst = con.prepareStatement("INSERT INTO employee_exit (resignation_date,notice_period,separation_date,employee_id ,no_dues, created_date, is_active, modified_by) VALUES (?,?,?,?,?,?,?,?)");
					pst.setString(1, textField.getText());
					pst.setString(2, textField_1.getText());
					pst.setString(3, textField_2.getText());
					pst.setString(4, textField_3.getText());
					pst.setString(5, textField_4.getText());
					pst.setDate(6,currentDate);
					pst.setBoolean(7, true);
					pst.setInt(8, log.userId);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record added");
					textField.setText("");
					textField.requestFocus();
					
					textField_1.setText("");
					textField_1.requestFocus();
					
					textField_2.setText("");
					textField_2.requestFocus();
					
					textField_3.setText("");
					textField_3.requestFocus();
					
					textField_4.setText("");
					textField_4.requestFocus();
					
				}
				catch(SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(188, 446, 91, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Exit");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnNewButton_1.setBounds(565, 446, 91, 23);
		frame.getContentPane().add(btnNewButton_1);
	}

}
