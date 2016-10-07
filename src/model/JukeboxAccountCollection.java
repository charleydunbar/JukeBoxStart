package model;

import java.util.HashMap;

public class JukeboxAccountCollection {
	HashMap<String, JukeboxAccount> accounts;
	JukeboxAccount tmpAcc;

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

		if (tmpAcc == null)
			return null;

		try {
			for (int i = 0; i < password.length; i++) {
				if (password[i] != tmpAcc.getPassword()[i])
					return null;
			}
		} catch (Exception e) {
			return null;
		}
		
		return tmpAcc;
	}

}
