package controller;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import model.JukeboxAccountCollection;
import model.JukeboxAccount;

@SuppressWarnings("serial")
public class JukeboxGUI1 extends JFrame {
	
	public static void main(String[] args) {
		JukeboxGUI1 g = new JukeboxGUI1();
		g.setVisible(true);
	}

	private JButton btnSong1, btnSong2, btnSignOut, btnLogin;
	private JLabel lblUsername, lblPassword, lblStatus, lblInfo;
	private JPasswordField password;
	private JTextField username;
	private JukeboxAccountCollection accounts;
	private JukeboxAccount tmpJukeboxAccount;
	
	public JukeboxGUI1() {
		setSize(new Dimension(360, 330));
		setLayout(new FlowLayout());
		initialize();
		setTheLayout();
		initializeAccounts();
	}
	
	private void initialize() {
		btnSong1 = new JButton("Select song 1");
		btnSong2 = new JButton("Select song 2");
		btnSignOut = new JButton("Sign Out");
		btnLogin = new JButton("Login");
		
		lblUsername = new JLabel("Account Name", SwingConstants.RIGHT);
		lblPassword = new JLabel("Password", SwingConstants.RIGHT);
		lblStatus = new JLabel("Status", SwingConstants.RIGHT);
		lblInfo = new JLabel("Login");
		
		password = new JPasswordField(15);
		
		username = new JTextField();
		
		accounts = new JukeboxAccountCollection();
	}
	
	private void setTheLayout() {
		setTitle("JukeboxGUI1");
		
		JPanel contentPanel = new JPanel();
		Border padding = BorderFactory.createEmptyBorder(20, 20, 20, 20);
		contentPanel.setBorder(padding);
		setContentPane(contentPanel);
		
		JPanel songButtons = new JPanel();
		songButtons.setLayout(new GridLayout(3, 2, 5, 10));
		
		btnSong1.addActionListener(new buttonListener());
		btnSong2.addActionListener(new buttonListener());
		
		songButtons.add(btnSong1);
		songButtons.add(new JPanel());
		songButtons.add(btnSong2);
		songButtons.add(new JPanel());
		songButtons.add(new JPanel());
		songButtons.add(new JPanel());
		
		songButtons.setPreferredSize(new Dimension(300, 90));
		
		JPanel login = new JPanel();
		login.setLayout(new GridLayout(4, 2, 5, 10));
		
		btnSignOut.addActionListener(new buttonListener());
		btnLogin.addActionListener(new buttonListener());
		
		login.add(lblUsername);
		login.add(username);
		login.add(lblPassword);
		login.add(password);
		login.add(btnSignOut);
		login.add(btnLogin);
		login.add(lblStatus);
		login.add(lblInfo);
		
		login.setPreferredSize(new Dimension(300, 130));
		login.setBackground(Color.WHITE);

		add(songButtons);
		add(login);
	}
	
	private void initializeAccounts() {
		accounts.add("Chris", "1");
		accounts.add("Devon", "22");
		accounts.add("River", "333");
		accounts.add("Ryan", "4444");
	}
	
	private void showError() {
        JOptionPane.showMessageDialog(null, "Invalid");
	}
	
	private class buttonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String text = e.getActionCommand();
			
			if (text.equals("Select song 1")) {
				
			} else if (text.equals("Select song 2")) {
				
			} else if (text.equals("Sign Out")) {
				
			} else if (text.equals("Login")) {
				tryLogin();
			} 
		}
		
	}
	
	public void tryLogin() {
		tmpJukeboxAccount = accounts.getAccount(username.getText(), password.getPassword());
		
		if (tmpJukeboxAccount == null) {
			showError();
		} else {
			lblInfo.setText(tmpJukeboxAccount.getID());
		}
	}
	
}
