package externalEdit.copy;

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
import java.awt.event.ActionEvent; 
import java.sql.Date;
import javax.swing.JComboBox;
import javax.swing.JTextPane;
public class ExternalEdit{

	private JFrame frame;
	public static int externalID;
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	log userdata = new log();
	private JTextField txtfname;
	private JTextField txtmname;
	private JTextField txtlname;
	private JTextField txtfather;
	private JTextField txtmother;
	private JTextField txtspouse;
	private JTextField txtdob;
	private JTextField txtqualification;
	private JTextField txtbloodgroup;
	private JTextField txtphno;
	private JTextField txtmailid;
	private JTextField txtprimaryskills;
	private JTextField txtgender;
	
	private JTextField txtaddress;
	private JTextField txtlocation;
	
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
		externalID = Integer.parseInt(args[0]);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ExternalEdit window = new ExternalEdit();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
		
	public void getData(int id) {
		 try {
			 		//externalID = id;
	                pst = con.prepareStatement("Select  f_name ,m_name ,l_name , gender ,father_name ,mother_name ,spouse_name,date_of_birth ,qualification,blood_group,address_id ,phone_number ,personal_email_address ,location_id ,primary_skills from candidate where candidate_id=? ");
	                pst.setInt(1, id);
	                ResultSet rs = pst.executeQuery();
	                if(rs.next()==true)
		            {
	                //String workTypeName = rs.getString(1);
	                //txtfname.setText(workTypeName);
	                txtfname.setText(rs.getString(1));
					txtmname.setText(rs.getString(2));
					txtlname.setText(rs.getString(3));
					txtgender.setText(rs.getString(4));
					txtfather.setText(rs.getString(5));
					txtmother.setText(rs.getString(6));
					txtspouse.setText(rs.getString(7));
					txtdob.setText(rs.getString(8));
					txtqualification.setText(rs.getString(9));
					txtbloodgroup.setText(rs.getString(10));
					txtaddress.setText(rs.getString(11));
					txtphno.setText(rs.getString(12));
					txtmailid.setText(rs.getString(13));
					txtlocation.setText(rs.getString(14));
					txtprimaryskills.setText(rs.getString(15));
		            }  
	        } 
		
		 catch (SQLException ex) {
	           
	        }
	}
	/**
	 * Create the application.
	 */
	public ExternalEdit() {
		initialize();
		connect();
		getData(externalID);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.activeCaption);
		frame.setBounds(170, 10, 1193, 842);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblHrms = new JLabel("HRMS");
		lblHrms.setHorizontalAlignment(SwingConstants.CENTER);
		lblHrms.setFont(new Font("Arial", Font.BOLD, 26));
		lblHrms.setBounds(418, 10, 177, 38);
		frame.getContentPane().add(lblHrms);
		
		txtfname = new JTextField();
		txtfname.setColumns(10);
		txtfname.setBounds(202, 94, 192, 38);
		frame.getContentPane().add(txtfname);
		
		txtmname = new JTextField();
		txtmname.setColumns(10);
		txtmname.setBounds(202, 156, 192, 38);
		frame.getContentPane().add(txtmname);
		
		txtlname = new JTextField();
		txtlname.setColumns(10);
		txtlname.setBounds(202, 217, 192, 38);
		frame.getContentPane().add(txtlname);
		
		txtfather = new JTextField();
		txtfather.setColumns(10);
		txtfather.setBounds(202, 343, 192, 38);
		frame.getContentPane().add(txtfather);
		
		txtmother = new JTextField();
		txtmother.setColumns(10);
		txtmother.setBounds(202, 403, 192, 38);
		frame.getContentPane().add(txtmother);
		
		txtspouse = new JTextField();
		txtspouse.setColumns(10);
		txtspouse.setBounds(202, 463, 192, 38);
		frame.getContentPane().add(txtspouse);
		
		txtdob = new JTextField();
		txtdob.setText("yyyy-mm-dd");
		txtdob.setToolTipText("yyyy-mm-dd");
		txtdob.setForeground(Color.LIGHT_GRAY);
		txtdob.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(txtdob.getText().trim().equals("yyyy-mm-dd")) {
					txtdob.setText("");
				}
				
				txtdob.setForeground(Color.BLACK);
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(txtdob.getText().trim().equals("")) {
					txtdob.setText("yyyy-mm-dd");
				}
				
				txtdob.setForeground(Color.LIGHT_GRAY);
			}
		});
		txtdob.setColumns(10);
		txtdob.setBounds(202, 527, 192, 38);
		frame.getContentPane().add(txtdob);
		
		txtqualification = new JTextField();
		txtqualification.setColumns(10);
		txtqualification.setBounds(782, 457, 192, 38);
		frame.getContentPane().add(txtqualification);
		
		txtbloodgroup = new JTextField();
		txtbloodgroup.setColumns(10);
		txtbloodgroup.setBounds(782, 229, 192, 38);
		frame.getContentPane().add(txtbloodgroup);
		
		txtphno = new JTextField();
		txtphno.setColumns(10);
		txtphno.setBounds(782, 343, 192, 38);
		frame.getContentPane().add(txtphno);
		
		txtmailid = new JTextField();
		txtmailid.setColumns(10);
		txtmailid.setBounds(782, 288, 192, 38);
		frame.getContentPane().add(txtmailid);
		
		txtprimaryskills = new JTextField();
		txtprimaryskills.setColumns(10);
		txtprimaryskills.setBounds(782, 403, 192, 38);
		frame.getContentPane().add(txtprimaryskills);
		
		JLabel lblNewLabel = new JLabel("First Name");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(59, 106, 145, 26);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblMiddleName = new JLabel("Middle Name");
		lblMiddleName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMiddleName.setBounds(59, 168, 145, 26);
		frame.getContentPane().add(lblMiddleName);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblLastName.setBounds(59, 229, 145, 26);
		frame.getContentPane().add(lblLastName);
		
		JLabel lblFathersName = new JLabel("Father's Name");
		lblFathersName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFathersName.setBounds(59, 355, 145, 26);
		frame.getContentPane().add(lblFathersName);
		
		JLabel lblMothersName = new JLabel("Mother's Name");
		lblMothersName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMothersName.setBounds(59, 415, 145, 26);
		frame.getContentPane().add(lblMothersName);
		
		JLabel lblSpousesName = new JLabel("Spouse's Name");
		lblSpousesName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSpousesName.setBounds(59, 475, 145, 26);
		frame.getContentPane().add(lblSpousesName);
		
		JLabel lblDateOfBirth = new JLabel("Date Of Birth");
		lblDateOfBirth.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDateOfBirth.setBounds(59, 539, 145, 26);
		frame.getContentPane().add(lblDateOfBirth);
		
		JLabel lblQualification = new JLabel("Highest Qualification");
		lblQualification.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblQualification.setBounds(639, 469, 145, 26);
		frame.getContentPane().add(lblQualification);
		
		JLabel lblBloodGroup = new JLabel("Blood Group");
		lblBloodGroup.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblBloodGroup.setBounds(639, 241, 145, 26);
		frame.getContentPane().add(lblBloodGroup);
		
		JLabel lblPhoneNumber = new JLabel("Phone Number");
		lblPhoneNumber.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPhoneNumber.setBounds(639, 355, 145, 26);
		frame.getContentPane().add(lblPhoneNumber);
		
		JLabel lblPersonalMailAddress = new JLabel("Personal Mail Address");
		lblPersonalMailAddress.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPersonalMailAddress.setBounds(639, 300, 145, 26);
		frame.getContentPane().add(lblPersonalMailAddress);
		
		JLabel lblLocationId = new JLabel("Location ID");
		lblLocationId.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblLocationId.setBounds(639, 172, 145, 26);
		frame.getContentPane().add(lblLocationId);
		
		JLabel lblPrimarySkills = new JLabel("Primary Skills");
		lblPrimarySkills.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPrimarySkills.setBounds(639, 415, 145, 26);
		frame.getContentPane().add(lblPrimarySkills);
		
		txtgender = new JTextField();
		txtgender.setColumns(10);
		txtgender.setBounds(202, 283, 192, 38);
		frame.getContentPane().add(txtgender);
		
		JLabel lblGender = new JLabel("Gender");
		lblGender.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblGender.setBounds(59, 295, 145, 26);
		frame.getContentPane().add(lblGender);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date currentDate = new Date(System.currentTimeMillis());
				try {
					pst = con.prepareStatement("UPDATE candidate SET f_name = ?,m_name = ?,l_name = ?, gender = ?,father_name = ?,mother_name = ?,spouse_name = ?,date_of_birth = ?,qualification = ?,blood_group = ?,address_id = ?,phone_number = ?,personal_email_address = ?,location_id = ?,primary_skills = ?,modified_date = ?, modified_by = ? where candidate_id= ?");
					pst.setString(1, txtfname.getText());
					pst.setString(2, txtmname.getText());
					pst.setString(3, txtlname.getText());
					pst.setString(4, txtgender.getText());
					pst.setString(5, txtfather.getText());
					pst.setString(6, txtmother.getText());
					pst.setString(7, txtspouse.getText());
					pst.setString(8, txtdob.getText());
					pst.setString(9, txtqualification.getText());
					pst.setString(10, txtbloodgroup.getText());
					pst.setString(11, txtaddress.getText());
					pst.setString(12, txtphno.getText());
					pst.setString(13, txtmailid.getText());
					pst.setString(14, txtlocation.getText());
					pst.setString(15, txtprimaryskills.getText());
					//pst.setBoolean(16, true);
					pst.setDate(16,currentDate);
					pst.setInt(17, log.userId);
					pst.setInt(18, externalID);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record Updated");
					txtfname.requestFocus();
				}
				catch(SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnUpdate.setBounds(578, 637, 118, 38);
		frame.getContentPane().add(btnUpdate);
		
		JButton btnclose = new JButton("Close");
		btnclose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnclose.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnclose.setBounds(816, 637, 118, 38);
		frame.getContentPane().add(btnclose);
		
		txtaddress = new JTextField();
		txtaddress.setColumns(10);
		txtaddress.setBounds(782, 106, 192, 38);
		frame.getContentPane().add(txtaddress);
		
		JLabel lblAddress = new JLabel("Address ID");
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAddress.setBounds(639, 118, 145, 26);
		frame.getContentPane().add(lblAddress);
		
		txtlocation = new JTextField();
		txtlocation.setColumns(10);
		txtlocation.setBounds(782, 165, 192, 38);
		frame.getContentPane().add(txtlocation);
	}
}
