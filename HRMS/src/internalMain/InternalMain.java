package internalMain;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;

import internalAdd.InternalAdd;
import internalEdit.InternalEdit;
import log.log;
import mainPage.MainPage;
import net.proteanit.sql.DbUtils;
import tables.Tables;

import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

public class InternalMain {

	private JFrame frame;
	private JTextField textField;

	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InternalMain window = new InternalMain();
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
	public InternalMain() {
		connect();
		initialize();
		getData();
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
	public void getData() {
		try {
			CallableStatement stmt =con.prepareCall("{call GetPersonDetails(?,?)}");
			stmt.setInt(1, log.adminId);
			stmt.setInt(2, log.userId);
			boolean hadResults = stmt.execute();
			while (hadResults) {
				   rs = stmt.getResultSet();
				   hadResults = stmt.getMoreResults();
				}
			rs = stmt.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			table.getColumnModel().getColumn(0).setPreferredWidth(100);
			table.getColumnModel().getColumn(1).setPreferredWidth(100);
			table.getColumnModel().getColumn(2).setPreferredWidth(100);
			table.getColumnModel().getColumn(3).setPreferredWidth(100);
			table.getColumnModel().getColumn(4).setPreferredWidth(100);
			table.getColumnModel().getColumn(5).setPreferredWidth(100);
			table.getColumnModel().getColumn(6).setPreferredWidth(100);
			table.getColumnModel().getColumn(7).setPreferredWidth(100);
			table.getColumnModel().getColumn(8).setPreferredWidth(100);
			table.getColumnModel().getColumn(9).setPreferredWidth(100);
			table.getColumnModel().getColumn(10).setPreferredWidth(100);
			table.getColumnModel().getColumn(11).setPreferredWidth(100);
			table.getColumnModel().getColumn(12).setPreferredWidth(100);
			table.getColumnModel().getColumn(13).setPreferredWidth(100);
			table.getColumnModel().getColumn(14).setPreferredWidth(100);
			table.getColumnModel().getColumn(15).setPreferredWidth(100);
			table.getColumnModel().getColumn(16).setPreferredWidth(100);
			table.getColumnModel().getColumn(17).setPreferredWidth(100);
			table.getColumnModel().getColumn(18).setPreferredWidth(100);
			table.getColumnModel().getColumn(19).setPreferredWidth(100);
			table.getColumnModel().getColumn(20).setPreferredWidth(100);
			table.getColumnModel().getColumn(21).setPreferredWidth(100);
			table.getColumnModel().getColumn(22).setPreferredWidth(100);
			table.getColumnModel().getColumn(23).setPreferredWidth(100);
			table.getColumnModel().getColumn(24).setPreferredWidth(100);
			table.getColumnModel().getColumn(25).setPreferredWidth(100);
			table.getColumnModel().getColumn(26).setPreferredWidth(100);
			table.getColumnModel().getColumn(27).setPreferredWidth(100);
			table.getColumnModel().getColumn(28).setPreferredWidth(100);
			
			
			//table.getColumnModel().getColumn(0).setHeaderValue("test");
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.activeCaption);
		frame.getContentPane().setLayout(null);
		
		JLabel lblHrms = new JLabel("HRMS");
		lblHrms.setHorizontalAlignment(SwingConstants.CENTER);
		lblHrms.setFont(new Font("Arial", Font.BOLD, 26));
		lblHrms.setBounds(518, 37, 177, 38);
		frame.getContentPane().add(lblHrms);
		
		JLabel lblNewLabel = new JLabel("Internal");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setBackground(new Color(0, 0, 0));
		lblNewLabel.setBounds(50, 99, 156, 38);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnTables = new JButton("Tables");
		btnTables.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Tables.main(null);
			}
		});
		btnTables.setFont(new Font("Arial", Font.PLAIN, 13));
		btnTables.setBackground(SystemColor.menu);
		btnTables.setBounds(192, 50, 76, 23);
		frame.getContentPane().add(btnTables);
		
		JLabel lblNewLabel_1 = new JLabel("Person Id");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(50, 167, 110, 28);
		frame.getContentPane().add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(143, 173, 140, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Add Person");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InternalAdd.main(null);
			}
		});
		btnNewButton_1.setBounds(1041, 172, 148, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("Edit");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String[] textValue = new String[]{textField.getText()};
				InternalEdit.main(textValue);
			}
		});
		btnNewButton_1_1.setBounds(936, 703, 91, 23);
		frame.getContentPane().add(btnNewButton_1_1);
		
		JButton btnNewButton_1_2 = new JButton("Delete");
		btnNewButton_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

				Date currentDate = new Date(System.currentTimeMillis());
				CallableStatement stmt =con.prepareCall("{call DeletePersonDetails(?,?,?)}");
				stmt.setInt(1, log.userId);
				stmt.setString(2, textField.getText());
				stmt.setDate(3, currentDate);
				boolean hadResults = stmt.execute();
				JOptionPane.showMessageDialog(null, "Record Deleted");
				getData();
				}
				catch(SQLException ex) {
					ex.printStackTrace();
				}
			}
		});
		btnNewButton_1_2.setBounds(1098, 703, 91, 23);
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
		btnHome.setBounds(50, 52, 91, 23);
		frame.getContentPane().add(btnHome);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(50, 232, 1128, 401);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane.setViewportView(table);
		
		JButton btn_search = new JButton("Search");
		btn_search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					CallableStatement stmt =con.prepareCall("{call GetSinglePersonDetails(?)}");
					stmt.setString(1, textField.getText());
					boolean hadResults = stmt.execute();
					while (hadResults) {
						   rs = stmt.getResultSet();
						   hadResults = stmt.getMoreResults();
						}
					rs = stmt.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					table.getColumnModel().getColumn(0).setPreferredWidth(100);
					table.getColumnModel().getColumn(1).setPreferredWidth(100);
					table.getColumnModel().getColumn(2).setPreferredWidth(100);
					table.getColumnModel().getColumn(3).setPreferredWidth(100);
					table.getColumnModel().getColumn(4).setPreferredWidth(100);
					table.getColumnModel().getColumn(5).setPreferredWidth(100);
					table.getColumnModel().getColumn(6).setPreferredWidth(100);
					table.getColumnModel().getColumn(7).setPreferredWidth(100);
					table.getColumnModel().getColumn(8).setPreferredWidth(100);
					table.getColumnModel().getColumn(9).setPreferredWidth(100);
					table.getColumnModel().getColumn(10).setPreferredWidth(100);
					table.getColumnModel().getColumn(11).setPreferredWidth(100);
					table.getColumnModel().getColumn(12).setPreferredWidth(100);
					table.getColumnModel().getColumn(13).setPreferredWidth(100);
					table.getColumnModel().getColumn(14).setPreferredWidth(100);
					table.getColumnModel().getColumn(15).setPreferredWidth(100);
					table.getColumnModel().getColumn(16).setPreferredWidth(100);
					table.getColumnModel().getColumn(17).setPreferredWidth(100);
					table.getColumnModel().getColumn(18).setPreferredWidth(100);
					table.getColumnModel().getColumn(19).setPreferredWidth(100);
					table.getColumnModel().getColumn(20).setPreferredWidth(100);
					table.getColumnModel().getColumn(21).setPreferredWidth(100);
					table.getColumnModel().getColumn(22).setPreferredWidth(100);
					table.getColumnModel().getColumn(23).setPreferredWidth(100);
					table.getColumnModel().getColumn(24).setPreferredWidth(100);
					table.getColumnModel().getColumn(25).setPreferredWidth(100);
					table.getColumnModel().getColumn(26).setPreferredWidth(100);
					
					
					//table.getColumnModel().getColumn(0).setHeaderValue("test");
				}
				catch(SQLException ex) {
					ex.printStackTrace();
				}
			}
		});
		btn_search.setBounds(310, 173, 85, 21);
		frame.getContentPane().add(btn_search);
		
		JButton btn_refresh = new JButton("Refresh");
		btn_refresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getData();
			}
		});
		btn_refresh.setBounds(420, 173, 85, 21);
		frame.getContentPane().add(btn_refresh);
		frame.setBounds(150, 25, 1234, 777);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
