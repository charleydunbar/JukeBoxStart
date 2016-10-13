// Charles Dunbar, Mohammad Adlan Fauzi
package model;

import java.util.HashMap;

// this class serves as a collection of JukeboxAccount
// it facilitates adding new accounts and getting accounts
public class JukeboxAccountCollection {
	private HashMap<String, JukeboxAccount> accounts;
	private JukeboxAccount tmpAcc;

	// constructor, initializes variables
	public JukeboxAccountCollection() {
		accounts = new HashMap<>();
	}

	// adds an account to the collection
	public void add(String id, String password) {
		char[] tmpPass = new char[password.length()];

		for (int i = 0; i < password.length(); i++)
			tmpPass[i] = password.charAt(i);

		JukeboxAccount tmpAcc = new JukeboxAccount(id, tmpPass);

		accounts.put(id, tmpAcc);
	}

	// retrieves and account with given information, returns null if username/password is incorrect
	public JukeboxAccount getAccount(String id, char[] password) {
		tmpAcc = accounts.get(id);

		if (tmpAcc == null || password == null || password.length == 0)
			return null;

		if (password.length != tmpAcc.getPassword().length)
			return null;

		for (int i = 0; i < password.length; i++) {
			if (password[i] != tmpAcc.getPassword()[i])
				return null;
		}

		return tmpAcc;
	}

}
