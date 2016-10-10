package model;

import java.util.LinkedList;
import java.util.Queue;

public class Jukebox {

	private JukeboxAccountCollection accounts;
	private JukeboxAccount tmpJukeboxAccount;
	private Song flute, spacemusic, tmpSong;
	private TheDecider theDecider;

	public Jukebox() {
		accounts = new JukeboxAccountCollection();
		theDecider = new TheDecider();
		initialize();
	}

	private void initialize() {
		accounts.add("Chris", "1");
		accounts.add("Devon", "22");
		accounts.add("River", "333");
		accounts.add("Ryan", "4444");

		flute = new Song("flute.aif", 6);
		spacemusic = new Song("spacemusic.au", 6);
	}

	public boolean login(String username, char[] password) {
		tmpJukeboxAccount = accounts.getAccount(username, password);

		if (tmpJukeboxAccount == null) {
			return false;
		} else {
			return true;
		}
	}

	public JukeboxAccount getAccount() {
		return tmpJukeboxAccount;
	}

	public boolean tryPlay(String song) {
		if (tmpJukeboxAccount == null) {
			return false;

		} else {
			if (song.equals("flute.aif")) {
				tmpSong = flute;
			} else if (song.equals("spacemusic.au")) {
				tmpSong = spacemusic;
			} else {
				return false;
			}

			if (theDecider.decide(tmpJukeboxAccount, tmpSong)) {
				tmpJukeboxAccount.play(tmpSong.getSeconds());
				tmpSong.play();
				return true;
			}

			return false;
		}
	}

}
