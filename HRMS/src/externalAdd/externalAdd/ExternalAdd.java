package externalAdd.externalAdd;

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
import java.util.List;
import java.awt.event.ActionEvent;
import java.sql.Date;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
public class ExternalAdd {

	private JFrame frame;

	
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
	private JTextField txtdoorno;
	private JTextField txtphno;
	private JTextField txtmailid;
	private JTextField txtprimaryskills;
	private JTextField txtgender;
	private JComboBox cboxlocation;
	private JTextField txtstreet;
	private JTextField txtarea;
	private JTextField txtcity;
	private JTextField txtpostalcode;
	private JTextField txtcountry;
	public int address_id;
	public int location_id;
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
	
	public void fetchLocation()
	{
		try {
			pst = con.prepareStatement("Select location_name from location where is_active = 1");
			rs = pst.executeQuery();
			while(rs.next())
			{
			//JComboBox cboxlocation;
			cboxlocation.addItem(rs.getString("location_name"));
			}
			
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
					ExternalAdd window = new ExternalAdd();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void insertAddress()
	{
		Date currentDate = new Date(System.currentTimeMillis());
		try {
			pst = con.prepareStatement("INSERT INTO address (door_number,street_name,area,city,postcode,country,is_active,created_date,modified_by) VALUES (?,?,?,?,?,?,?,?,?)");
			pst.setString(1, txtdoorno.getText());
			pst.setString(2, txtstreet.getText());
			pst.setString(3, txtarea.getText());
			pst.setString(4, txtcity.getText());
			pst.setString(5, txtpostalcode.getText());
			pst.setString(6, txtcountry.getText());
			pst.setBoolean(7, true);
			pst.setDate(8,currentDate);
			pst.setInt(9, log.userId);
			pst.executeUpdate();
			/*txtdoorno.setText("");
			txtstreet.setText("");
			txtarea.setText("");
			txtcity.setText("");
			txtpostalcode.setText("");
			txtmother.setText("");
			txtcountry.setText("");
			txtdoorno.requestFocus();*/
			
			
			
		}
		
		catch(SQLException e1) {
			e1.printStackTrace();
		}
	}
	

	    public void infoBox(String infoMessage, String titleBar){
	        JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
	    }

	/**
	 * Create the application.
	 */
	public ExternalAdd() {
		initialize();
		connect();
		fetchLocation();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.activeCaption);
		frame.setBounds(150, 10, 1160, 852);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblHrms = new JLabel("HRMS");
		lblHrms.setHorizontalAlignment(SwingConstants.CENTER);
		lblHrms.setFont(new Font("Arial", Font.BOLD, 26));
		lblHrms.setBounds(423, 10, 177, 38);
		frame.getContentPane().add(lblHrms);
		
		txtfname = new JTextField();
		txtfname.setBounds(190, 109, 192, 38);
		frame.getContentPane().add(txtfname);
		txtfname.setColumns(10);
		
		txtmname = new JTextField();
		txtmname.setColumns(10);
		txtmname.setBounds(190, 171, 192, 38);
		frame.getContentPane().add(txtmname);
		
		txtlname = new JTextField();
		txtlname.setColumns(10);
		txtlname.setBounds(190, 232, 192, 38);
		frame.getContentPane().add(txtlname);
		
		txtfather = new JTextField();
		txtfather.setColumns(10);
		txtfather.setBounds(190, 358, 192, 38);
		frame.getContentPane().add(txtfather);
		
		txtmother = new JTextField();
		txtmother.setColumns(10);
		txtmother.setBounds(190, 418, 192, 38);
		frame.getContentPane().add(txtmother);
		
		txtspouse = new JTextField();
		txtspouse.setColumns(10);
		txtspouse.setBounds(190, 478, 192, 38);
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
		txtdob.setBounds(190, 543, 192, 38);
		frame.getContentPane().add(txtdob);
		
		txtqualification = new JTextField();
		txtqualification.setColumns(10);
		txtqualification.setBounds(749, 599, 192, 38);
		frame.getContentPane().add(txtqualification);
		
		txtbloodgroup = new JTextField();
		txtbloodgroup.setColumns(10);
		txtbloodgroup.setBounds(190, 599, 192, 38);
		frame.getContentPane().add(txtbloodgroup);
		
		txtdoorno = new JTextField();
		txtdoorno.setColumns(10);
		txtdoorno.setBounds(749, 109, 192, 38);
		frame.getContentPane().add(txtdoorno);
		
		txtphno = new JTextField();
		txtphno.setColumns(10);
		txtphno.setBounds(190, 657, 192, 38);
		frame.getContentPane().add(txtphno);
		
		txtmailid = new JTextField();
		txtmailid.setColumns(10);
		txtmailid.setBounds(190, 717, 192, 38);
		frame.getContentPane().add(txtmailid);
		
		txtprimaryskills = new JTextField();
		txtprimaryskills.setColumns(10);
		txtprimaryskills.setBounds(749, 540, 192, 38);
		frame.getContentPane().add(txtprimaryskills);
		
		JLabel lblNewLabel = new JLabel("First Name");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(47, 121, 145, 26);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblMiddleName = new JLabel("Middle Name");
		lblMiddleName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMiddleName.setBounds(47, 183, 145, 26);
		frame.getContentPane().add(lblMiddleName);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblLastName.setBounds(47, 244, 145, 26);
		frame.getContentPane().add(lblLastName);
		
		JLabel lblFathersName = new JLabel("Father's Name");
		lblFathersName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFathersName.setBounds(47, 370, 145, 26);
		frame.getContentPane().add(lblFathersName);
		
		JLabel lblMothersName = new JLabel("Mother's Name");
		lblMothersName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMothersName.setBounds(47, 430, 145, 26);
		frame.getContentPane().add(lblMothersName);
		
		JLabel lblSpousesName = new JLabel("Spouse's Name");
		lblSpousesName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSpousesName.setBounds(47, 490, 145, 26);
		frame.getContentPane().add(lblSpousesName);
		
		JLabel lblDateOfBirth = new JLabel("Date Of Birth");
		lblDateOfBirth.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDateOfBirth.setBounds(47, 555, 145, 26);
		frame.getContentPane().add(lblDateOfBirth);
		
		JLabel lblQualification = new JLabel("Highest Qualification");
		lblQualification.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblQualification.setBounds(606, 611, 145, 26);
		frame.getContentPane().add(lblQualification);
		
		JLabel lblBloodGroup = new JLabel("Blood Group");
		lblBloodGroup.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblBloodGroup.setBounds(47, 611, 145, 26);
		frame.getContentPane().add(lblBloodGroup);
		
		JLabel lblAddressId = new JLabel("Door No.");
		lblAddressId.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAddressId.setBounds(606, 121, 145, 26);
		frame.getContentPane().add(lblAddressId);
		
		JLabel lblPhoneNumber = new JLabel("Phone Number");
		lblPhoneNumber.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPhoneNumber.setBounds(47, 669, 145, 26);
		frame.getContentPane().add(lblPhoneNumber);
		
		JLabel lblPersonalMailAddress = new JLabel("Personal Mail Address");
		lblPersonalMailAddress.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPersonalMailAddress.setBounds(47, 729, 145, 26);
		frame.getContentPane().add(lblPersonalMailAddress);
		
		JLabel lblLocationId = new JLabel("Location ID");
		lblLocationId.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblLocationId.setBounds(606, 489, 145, 26);
		frame.getContentPane().add(lblLocationId);
		
		JLabel lblPrimarySkills = new JLabel("Primary Skills");
		lblPrimarySkills.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPrimarySkills.setBounds(606, 552, 145, 26);
		frame.getContentPane().add(lblPrimarySkills);
		
		txtgender = new JTextField();
		txtgender.setColumns(10);
		txtgender.setBounds(190, 298, 192, 38);
		frame.getContentPane().add(txtgender);
		
		JLabel lblGender = new JLabel("Gender");
		lblGender.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblGender.setBounds(47, 310, 145, 26);
		frame.getContentPane().add(lblGender);
		
		JButton btnadd = new JButton("Add ");
		btnadd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date currentDate = new Date(System.currentTimeMillis());
				try {
					insertAddress();
					//infoBox("Hello", "Recent address value");
					pst = con.prepareStatement("select address_id from address where address_id=(select max(address_id) from address)");
					rs=pst.executeQuery();
					
					while(rs.next())
					{
						address_id = rs.getInt(1);
					}
					//System.out.println(address_id);
					//add_id=rs.getInt("address_id");
					
					String location_name=cboxlocation.getSelectedItem().toString();
					pst=con.prepareStatement("select location_id from location where location_name= ?");
					pst.setString(1, location_name);
					rs=pst.executeQuery();
					while(rs.next())
					{
						location_id = rs.getInt(1);
					}
					//System.out.println(address_id);
					pst = con.prepareStatement("INSERT INTO candidate (f_name,m_name,l_name, gender,father_name,mother_name,spouse_name,date_of_birth,qualification,blood_group,address_id,phone_number,personal_email_address,location_id,primary_skills, is_active,created_date, modified_by) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
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
					
					pst.setInt(11, address_id);
					pst.setString(12, txtphno.getText());
					pst.setString(13, txtmailid.getText());
					pst.setInt(14, location_id);
					pst.setString(15, txtprimaryskills.getText());
					pst.setBoolean(16, true);
					pst.setDate(17,currentDate);
					pst.setInt(18, log.userId);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record added");
					txtfname.setText("");
					txtmname.setText("");
					txtlname.setText("");
					txtgender.setText("");
					txtfather.setText("");
					txtmother.setText("");
					txtspouse.setText("");
					txtdob.setText("");
					txtqualification.setText("");
					txtbloodgroup.setText("");
					txtdoorno.setText("");
					txtstreet.setText("");
					txtarea.setText("");
					txtcity.setText("");
					txtpostalcode.setText("");
					txtmother.setText("");
					txtcountry.setText("");
					txtphno.setText("");
					txtmailid.setText("");
					//cboxlocation.setText("");
					txtprimaryskills.setText("");
					txtfname.requestFocus();
				}
				catch(Exception e1) {
					//e1.printStackTrace();
					System.out.println(e1);
				}
			}
		});
		btnadd.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnadd.setBounds(758, 752, 118, 38);
		frame.getContentPane().add(btnadd);
		
		JButton btnclose = new JButton("Close");
		btnclose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnclose.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnclose.setBounds(942, 752, 118, 38);
		frame.getContentPane().add(btnclose);
		
		cboxlocation = new JComboBox();
		//cboxlocation.setSelectedIndex(1);
		cboxlocation.setBounds(749, 478, 192, 38);
		frame.getContentPane().add(cboxlocation);
		
		txtstreet = new JTextField();
		txtstreet.setColumns(10);
		txtstreet.setBounds(749, 171, 192, 38);
		frame.getContentPane().add(txtstreet);
		
		JLabel lblStreetName = new JLabel("Street Name");
		lblStreetName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblStreetName.setBounds(606, 183, 145, 26);
		frame.getContentPane().add(lblStreetName);
		
		txtarea = new JTextField();
		txtarea.setColumns(10);
		txtarea.setBounds(749, 232, 192, 38);
		frame.getContentPane().add(txtarea);
		
		JLabel lblArea = new JLabel("Area");
		lblArea.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblArea.setBounds(606, 244, 145, 26);
		frame.getContentPane().add(lblArea);
		
		txtcity = new JTextField();
		txtcity.setColumns(10);
		txtcity.setBounds(749, 298, 192, 38);
		frame.getContentPane().add(txtcity);
		
		JLabel lblCity = new JLabel("City");
		lblCity.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCity.setBounds(606, 310, 145, 26);
		frame.getContentPane().add(lblCity);
		
		txtpostalcode = new JTextField();
		txtpostalcode.setColumns(10);
		txtpostalcode.setBounds(749, 358, 192, 38);
		frame.getContentPane().add(txtpostalcode);
		
		JLabel lblPostalCode = new JLabel("Postal Code");
		lblPostalCode.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPostalCode.setBounds(606, 370, 145, 26);
		frame.getContentPane().add(lblPostalCode);
		
		txtcountry = new JTextField();
		txtcountry.setColumns(10);
		txtcountry.setBounds(749, 418, 192, 38);
		frame.getContentPane().add(txtcountry);
		
		JLabel lblAddressId_5 = new JLabel("Country");
		lblAddressId_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAddressId_5.setBounds(606, 430, 145, 26);
		frame.getContentPane().add(lblAddressId_5);
	}
}
