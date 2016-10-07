package model;

public class JukeboxAccount {
	private String id;
	private char[] password;
	
	public JukeboxAccount(String id, char[] password) {
		this.id = id;
		this.password = password;
	}
	
	public String getID() {
		return id;
	}
	
	public char[] getPassword() {
		return password;
	}
}
