package login;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;

import log.log;
import mainPage.MainPage;

import java.sql.*;

import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import javax.swing.JPasswordField;

public class login {

	private JFrame frame;
	private JTextField textID;
	private JPasswordField textPassword;
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	log test = new log();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login window = new login();
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
	public login() {
		initialize();
		connect();
	}

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
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.activeCaption);
		frame.getContentPane().setForeground(SystemColor.desktop);
		frame.setBounds(400, 200, 600, 353);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("HRMS Login");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 26));
		lblNewLabel.setBounds(182, 11, 177, 38);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Login Id");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 22));
		lblNewLabel_1.setBounds(71, 83, 132, 37);
		frame.getContentPane().add(lblNewLabel_1);
		
		textID = new JTextField();
		textID.setBounds(228, 83, 271, 31);
		frame.getContentPane().add(textID);
		textID.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Password");
		lblNewLabel_1_1.setFont(new Font("Arial", Font.PLAIN, 22));
		lblNewLabel_1_1.setBounds(71, 148, 132, 37);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String password = textPassword.getText();
				String id = textID.getText();
				if(password!=null && id!=null) {
					try {
						CallableStatement stmt =con.prepareCall("{call Login(?,?,?,?,?)}");
						stmt.setString(1, id);
						stmt.setString(2, password);
						stmt.registerOutParameter(3, java.sql.Types.BOOLEAN);
						stmt.registerOutParameter(4, java.sql.Types.INTEGER);
						stmt.registerOutParameter(5, java.sql.Types.INTEGER);
						boolean hadResults = stmt.execute();
						while (hadResults) {
							   rs = stmt.getResultSet();
							   hadResults = stmt.getMoreResults();
							}
						boolean valid = stmt.getBoolean(3);
						log.adminId = stmt.getInt(4);
						log.userId = stmt.getInt(5);
						if(valid == true) {
							frame.dispose();
							MainPage.main(null);
						}
						else {
							JOptionPane.showMessageDialog(null,"Invalid login details","Login Error",JOptionPane.ERROR_MESSAGE);
							textPassword.setText(null);
							textID.setText(null);
						}
						textPassword.setText(null);
						textID.setText(null);
					}
					catch(SQLException ex) {
						JOptionPane.showMessageDialog(null, ex);						
					}
				}
//				if(password.contains("king")&& id.contains("one")) {
//					textPassword.setText(null);
//					textID.setText(null);
//					javacrud.main(null);
//				}
//				else {
//					JOptionPane.showMessageDialog(null,"Invalid login details","Login Error",JOptionPane.ERROR_MESSAGE);
//					textPassword.setText(null);
//					textID.setText(null);
//				}
			}
		});
		btnLogin.setForeground(Color.BLACK);
		btnLogin.setBackground(SystemColor.control);
		btnLogin.setBounds(86, 228, 137, 38);
		frame.getContentPane().add(btnLogin);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textID.setText(null);
				textPassword.setText(null);
			}
		});
		btnReset.setForeground(Color.BLACK);
		btnReset.setBackground(SystemColor.control);
		btnReset.setBounds(340, 228, 122, 38);
		frame.getContentPane().add(btnReset);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(71, 202, 428, 2);
		frame.getContentPane().add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(71, 63, 428, 2);
		frame.getContentPane().add(separator_1);
		
		textPassword = new JPasswordField();
		textPassword.setBounds(228, 148, 271, 37);
		frame.getContentPane().add(textPassword);
	}
}
