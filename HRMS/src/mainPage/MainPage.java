package mainPage;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.SystemColor;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

import User.copy.User;
import log.log;
import externalMain.externalMain.ExternalMain;
import internalMain.InternalMain;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainPage {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainPage window = new MainPage();
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
	public MainPage() {
		initialize();
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
		lblHrms.setBounds(176, 11, 177, 38);
		frame.getContentPane().add(lblHrms);
		
		JButton btnExternal = new JButton("External");
		btnExternal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				ExternalMain.main(null);
			}
		});
		btnExternal.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnExternal.setBackground(SystemColor.menu);
		btnExternal.setBounds(342, 106, 166, 63);
		frame.getContentPane().add(btnExternal);
		
		JButton btnInternal = new JButton("Internal");
		btnInternal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				InternalMain.main(null);
			}
		});
		btnInternal.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnInternal.setBackground(SystemColor.menu);
		btnInternal.setBounds(37, 106, 166, 63);
		frame.getContentPane().add(btnInternal);
		
		if(log.adminId == 1)
		{
			JButton btnAddUser = new JButton("Add User");
			btnAddUser.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					frame.dispose();
					User.main(null);
				}
			});
			btnAddUser.setFont(new Font("Tahoma", Font.PLAIN, 20));
			btnAddUser.setBackground(SystemColor.menu);
			btnAddUser.setBounds(176, 224, 166, 63);
			frame.getContentPane().add(btnAddUser);

		}
			frame.setBounds(400, 200, 582, 353);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
