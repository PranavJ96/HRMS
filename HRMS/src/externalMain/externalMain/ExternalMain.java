package externalMain.externalMain;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;

import externalAdd.externalAdd.*;
import externalEdit.copy.ExternalEdit;
import log.log;
import mainPage.MainPage;
import net.proteanit.sql.DbUtils;
import wTEdit.copy.WTEdit;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

public class ExternalMain {

	private JFrame frame;
	private JTextField txt_candidate_id;
	public String searchcandidate_id;
	public int accessLevel;
	public int userId;
	public String searchWorkId;
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	private JTable table_external;
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

	public void table_external() {
		try {
			pst = con.prepareStatement("Select candidate_id as Candidate_ID,f_name as First_Name, m_name as Middle_Name, l_name as Last_name, father_name as Father_Name, mother_name as Mother_Name, spouse_name as Spouse_Name,date_of_birth as DOB, qualification as Qualification ,blood_group as Blood_Group , address_id as Address_ID ,phone_number as Phone_Number ,personal_email_address as Personal_MailID ,location_id as Location_ID,primary_skills as Primary_Skills from candidate where is_active = 1");
			rs = pst.executeQuery();
			table_external.setModel(DbUtils.resultSetToTableModel(rs));
			table_external.getColumnModel().getColumn(0).setPreferredWidth(100);
			table_external.getColumnModel().getColumn(1).setPreferredWidth(100);
			table_external.getColumnModel().getColumn(2).setPreferredWidth(100);
			table_external.getColumnModel().getColumn(3).setPreferredWidth(100);
			table_external.getColumnModel().getColumn(4).setPreferredWidth(100);
			table_external.getColumnModel().getColumn(5).setPreferredWidth(100);
			table_external.getColumnModel().getColumn(6).setPreferredWidth(100);
			table_external.getColumnModel().getColumn(7).setPreferredWidth(100);
			table_external.getColumnModel().getColumn(8).setPreferredWidth(100);
			table_external.getColumnModel().getColumn(9).setPreferredWidth(100);
			table_external.getColumnModel().getColumn(10).setPreferredWidth(100);
			table_external.getColumnModel().getColumn(11).setPreferredWidth(100);
			table_external.getColumnModel().getColumn(12).setPreferredWidth(100);
			table_external.getColumnModel().getColumn(13).setPreferredWidth(100);
			table_external.getColumnModel().getColumn(14).setPreferredWidth(100);
			
			
			//table.getColumnModel().getColumn(0).setHeaderValue("test");
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ExternalMain window = new ExternalMain();
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
	public ExternalMain() {
		initialize();
		connect();
		table_external();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		userId= log.userId;
		accessLevel= log.adminId;
		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.activeCaption);
		frame.setBounds(150, 25, 1234, 777);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblHrms = new JLabel("HRMS");
		lblHrms.setHorizontalAlignment(SwingConstants.CENTER);
		lblHrms.setFont(new Font("Arial", Font.BOLD, 26));
		lblHrms.setBounds(508, 26, 177, 38);
		frame.getContentPane().add(lblHrms);
		
		JLabel lblExternal = new JLabel("External");
		lblExternal.setForeground(Color.BLACK);
		lblExternal.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblExternal.setBackground(Color.BLACK);
		lblExternal.setBounds(40, 88, 156, 38);
		frame.getContentPane().add(lblExternal);
		
		JLabel lblNewLabel_1 = new JLabel("Candidate Id");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(40, 156, 110, 28);
		frame.getContentPane().add(lblNewLabel_1);
		
		txt_candidate_id = new JTextField();
		txt_candidate_id.setColumns(10);
		txt_candidate_id.setBounds(145, 162, 140, 20);
		frame.getContentPane().add(txt_candidate_id);
		
		JButton btnNewButton_1_1 = new JButton("Edit");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] textValue = new String[]{txt_candidate_id.getText()};
				ExternalEdit.main(textValue);
			}
		});
		btnNewButton_1_1.setBounds(926, 692, 91, 23);
		frame.getContentPane().add(btnNewButton_1_1);
		
		JButton btnNewButton_1_2 = new JButton("Delete");
		btnNewButton_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchWorkId = txt_candidate_id.getText();
				try {
					pst = con.prepareStatement("Update candidate SET is_active = 0 where candidate_id =?");
		            pst.setString(1, txt_candidate_id.getText());
		            pst.executeUpdate();
		            JOptionPane.showMessageDialog(null, "Record Delete!!!!!");
		            table_external();
				}
				catch(SQLException ex) {
					ex.printStackTrace();
				}
			}
		});
		btnNewButton_1_2.setBounds(1088, 692, 91, 23);
		frame.getContentPane().add(btnNewButton_1_2);
		
		JButton btnHome = new JButton("Home");
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				MainPage.main(null);
			}
		});
		btnHome.setFont(new Font("Arial", Font.PLAIN, 13));
		btnHome.setBackground(SystemColor.menu);
		btnHome.setBounds(40, 41, 91, 23);
		frame.getContentPane().add(btnHome);
		
		JButton btn_refresh = new JButton("Refresh");
		btn_refresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table_external();
				txt_candidate_id.setText(null);
			}
		});
		btn_refresh.setBounds(445, 162, 85, 21);
		frame.getContentPane().add(btn_refresh);
		
		JButton btn_search = new JButton("Search");
		btn_search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchcandidate_id = txt_candidate_id.getText();
				try {
					if(searchcandidate_id.equals(""))
					{
						table_external();
					}
					else
					{
					pst = con.prepareStatement("Select candidate_id as Candidate_ID,f_name as First_Name, m_name as Middle_Name, l_name as Last_name, father_name as Father_Name, mother_name as Mother_Name, spouse_name as Spouse_Name,date_of_birth as DOB, qualification as Qualification ,blood_group as Blood_Group , address_id as Address_ID ,phone_number as Phone_Number ,personal_email_address as Personal_MailID ,location_id as Location_ID,primary_skills as Primary_Skills from candidate where candidate_id = "+searchcandidate_id);
					}
				rs = pst.executeQuery();
				}
				catch(SQLException ex) {
				ex.printStackTrace();
				}
				table_external.setModel(DbUtils.resultSetToTableModel(rs));
				table_external.getColumnModel().getColumn(0).setPreferredWidth(100);
				table_external.getColumnModel().getColumn(1).setPreferredWidth(100);
				table_external.getColumnModel().getColumn(2).setPreferredWidth(100);
				table_external.getColumnModel().getColumn(3).setPreferredWidth(100);
				table_external.getColumnModel().getColumn(4).setPreferredWidth(100);
				table_external.getColumnModel().getColumn(5).setPreferredWidth(100);
				table_external.getColumnModel().getColumn(6).setPreferredWidth(100);
				table_external.getColumnModel().getColumn(7).setPreferredWidth(100);
				table_external.getColumnModel().getColumn(8).setPreferredWidth(100);
				table_external.getColumnModel().getColumn(9).setPreferredWidth(100);
				table_external.getColumnModel().getColumn(10).setPreferredWidth(100);
				table_external.getColumnModel().getColumn(11).setPreferredWidth(100);
				table_external.getColumnModel().getColumn(12).setPreferredWidth(100);
				table_external.getColumnModel().getColumn(13).setPreferredWidth(100);
				table_external.getColumnModel().getColumn(14).setPreferredWidth(100);
				
				//table.getColumnModel().getColumn(0).setHeaderValue("test");
			}
		});
		btn_search.setBounds(295, 162, 85, 21);
		frame.getContentPane().add(btn_search);
		
		
		JButton btn_add = new JButton("Add Candidate");
		btn_add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ExternalAdd.main(null);
			}
		});
		btn_add.setBounds(987, 162, 167, 21);
		frame.getContentPane().add(btn_add);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 207, 1176, 472);
		frame.getContentPane().add(scrollPane);
		
		table_external = new JTable();
		table_external.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane.setViewportView(table_external);
	}
}
