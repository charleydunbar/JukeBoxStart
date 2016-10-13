// Charles Dunbar, Mohammad Adlan Fauzi
package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import model.Jukebox;

// this class is used by the GUI as a view, it provides the
// interface for users to login and play one of two songs. It
// displays information and error messages per the spec of this project
@SuppressWarnings("serial")
public class JukeboxView extends JPanel {

	private Jukebox jukebox;
	private JButton btnSong1, btnSong2, btnSignOut, btnLogin;
	private JLabel lblUsername, lblPassword, lblStatus, lblInfo;
	private JPasswordField password;
	private JTextField username;

	// constructor, calls to initialize variables
	public JukeboxView(Jukebox jukebox) {
		this.jukebox = jukebox;
		initialize();
		setLayout();
	}

	// initializes variables
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

	// sets the layout of view, adds listeners
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

	// shows error message with passed in message
	private void showError(String message) {
		JOptionPane.showMessageDialog(null, message);
	}

	// this class serves to listen to buttons and determine which was pressed,
	// invokes respective operations
	private class buttonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String text = e.getActionCommand();

			if (text.equals("Select song 1")) {
				tryPlay(0);
			} else if (text.equals("Select song 2")) {
				tryPlay(1);
			} else if (text.equals("Sign Out")) {
				signOut();
			} else if (text.equals("Login")) {
				tryLogin();
			}
		}

	}
	
	// signs out, clears GUI output
	private void signOut() {
		jukebox.signOut();
		lblInfo.setText("Login");
		username.setText("");
		password.setText("");
	}
	
	// tries to play song 0, or 1 (1 or 2)
	// if an error should arise calls to display message
	private void tryPlay(int i) {
		if (jukebox.loggedIn()) {
		if (jukebox.tryPlay(i)) {
			updateLabel();
		} else {
			showError("You cannot play this song for one or more of the following reasons:\n"
					+ "\t1. This song has been played three times today\n"
					+ "\t2. Your account has played three songs today\n"
					+ "\t3. Your account has insufficient credits");
		}
		} else {
			showError("You must login to play a song");
		}
	}
	
	// updates label (account info), formats time remaining on account to hours, minutes, seconds
	// lists plays selected today
	private void updateLabel() {
		int plays = jukebox.getAccount().getPlays(LocalDate.now());
		int seconds = jukebox.getAccount().getSeconds();
		String hours = Integer.toString(seconds / 3600);
		seconds = seconds % 3600;
		String minutes = Integer.toString(seconds / 60);
		seconds = seconds % 60;
		String secs = Integer.toString(seconds);
		
		if (hours.length() < 2)
			hours = "0" + hours;
		
		if (minutes.length() < 2)
			minutes = "0" + minutes;
		
		if (secs.length() < 2)
			secs = "0" + secs;
		
		lblInfo.setText(plays + " selected, " + hours + ":" + minutes + ":" + secs);
	}

	// tries to login, if error occurs shows message
	private void tryLogin() {
		if (jukebox.login(username.getText(), password.getPassword())) {
			updateLabel();
		} else {
			showError("Invalid login");
		}
	}

}
