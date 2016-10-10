package model;

public class Jukebox {

	private JukeboxAccountCollection accounts;
	private JukeboxAccount tmpJukeboxAccount;

	public Jukebox() {
		accounts = new JukeboxAccountCollection();
		initialize();
	}

	private void initialize() {
		accounts.add("Chris", "1");
		accounts.add("Devon", "22");
		accounts.add("River", "333");
		accounts.add("Ryan", "4444");
	}

	public boolean login(String username, char[] password) {
		tmpJukeboxAccount = accounts.getAccount(username, password);

		if (tmpJukeboxAccount == null) {
			return false;
		} else {
			return true;
		}
	}

	public String getID() {
		return tmpJukeboxAccount.getID();
	}

}
