package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import model.Jukebox;

@SuppressWarnings("serial")
public class JukeboxView extends JPanel {

	private Jukebox jukebox;
	private JButton btnSong1, btnSong2, btnSignOut, btnLogin;
	private JLabel lblUsername, lblPassword, lblStatus, lblInfo;
	private JPasswordField password;
	private JTextField username;

	public JukeboxView(Jukebox jukebox) {
		this.jukebox = jukebox;
		initialize();
		setLayout();
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
	}

	private void setLayout() {
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

		this.add(songButtons);
		this.add(login);
	}

	private void showError() {
		JOptionPane.showMessageDialog(null, "Invalid");
	}

	private class buttonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String text = e.getActionCommand();

			if (text.equals("Select song 1")) {
				tryPlay("flute.aif");
			} else if (text.equals("Select song 2")) {
				tryPlay("spacemusic.au");
			} else if (text.equals("Sign Out")) {

			} else if (text.equals("Login")) {
				tryLogin();
			}
		}

	}

	private void tryPlay(String name) {
		if (jukebox.tryPlay(name)) {
			lblInfo.setText(jukebox.getAccount().getID() + " " + jukebox.getAccount().getSeconds());
		} else {
			showError();
		}
	}

	private void tryLogin() {
		if (jukebox.login(username.getText(), password.getPassword())) {
			lblInfo.setText(jukebox.getAccount().getID() + " " + jukebox.getAccount().getSeconds());
		} else {
			showError();
		}
	}

}
