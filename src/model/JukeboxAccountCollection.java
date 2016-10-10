package model;

import java.util.HashMap;

public class JukeboxAccountCollection {
	private HashMap<String, JukeboxAccount> accounts;
	private JukeboxAccount tmpAcc;

	public JukeboxAccountCollection() {
		accounts = new HashMap<>();
	}

	public void add(String id, String password) {
		char[] tmpPass = new char[password.length()];

		for (int i = 0; i < password.length(); i++)
			tmpPass[i] = password.charAt(i);

		JukeboxAccount tmpAcc = new JukeboxAccount(id, tmpPass);

		accounts.put(id, tmpAcc);
	}

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
