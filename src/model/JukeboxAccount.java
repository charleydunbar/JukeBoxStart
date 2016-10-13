// Charles Dunbar, Mohammad Adlan Fauzi
package model;

import java.time.LocalDate;

// this class facilitates jukebox accounts, it remembers
// information about itself:
// plays via LimitCounter, password and id, and the seconds of
// credits it has to play
public class JukeboxAccount {
	private String id;
	private char[] password;
	private LimitCounter plays;
	private int seconds;

	// constructor, initializes values
	public JukeboxAccount(String id, char[] password) {
		this.id = id;
		this.password = password;
		plays = new LimitCounter(3);
		seconds = 90000;
	}

	// returns id
	public String getID() {
		return id;
	}
	
	// returns seconds remaining on account
	public int getSeconds() {
		return seconds;
	}

	// returns password for account
	public char[] getPassword() {
		return password;
	}

	// returns whether or not a song can be played on this account
	// (dependent on 3 a day rule)
	public boolean playable() {
		return plays.playable(LocalDate.now());
	}

	// "plays" a song, decrements seconds credits by given length
	public boolean play(double length) {
		if (length <= seconds && plays.performNew(LocalDate.now())) {
			seconds -= length;
			return true;
		} else {
			return false;
		}
	}

	// returns how many songs have been played on requested date
	public int getPlays(LocalDate now) {
		return plays.getPlays(now);
	}
}
