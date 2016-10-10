package model;

import java.time.LocalDate;

public class JukeboxAccount {
	private String id;
	private char[] password;
	private LimitCounter plays;
	private int seconds;
	
	public JukeboxAccount(String id, char[] password) {
		this.id = id;
		this.password = password;
		plays = new LimitCounter(3);
		seconds = 90000;
	}
	
	public String getID() {
		return id;
	}
	
	public char[] getPassword() {
		return password;
	}
	
	public boolean play(int length) {
		if (length < seconds && plays.performNew(LocalDate.now())) {
			seconds -= length;
			return true;
		} else {
			return false;
		}
	}
}
