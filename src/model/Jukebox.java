// Charles Dunbar, Mohammad Adlan Fauzi
package model;

import java.util.ArrayList;

import songplayer.EndOfSongEvent;
import songplayer.EndOfSongListener;
import songplayer.SongPlayer;

// This class facilitates the operations of a jukebox
// it utilizes other classes to do the heavy lifting, but
// this class orchestrates the operations
public class Jukebox {

	private JukeboxAccountCollection accounts;
	private JukeboxAccount tmpJukeboxAccount;
	private SongLibrary songs;
	private Song tmpSong;
	private TheDecider theDecider;
	private EndOfSongListener waitForSongEnd;
	private ArrayList<Song> songsStack;

	public EndOfSongListener codeCoverage;

	// constructor, initializes instance variables and calls to initialize accounts/songs
	public Jukebox() {
		accounts = new JukeboxAccountCollection();
		songs = new SongLibrary();
		waitForSongEnd = new WaitingForSongToEnd();
		codeCoverage = new WaitingForSongToEnd();
		songsStack = new ArrayList<Song>();
		theDecider = new TheDecider();
		initialize();
	}

	// initializes accounts/songs
	private void initialize() {
		accounts.add("Chris", "1");
		accounts.add("Devon", "22");
		accounts.add("River", "333");
		accounts.add("Ryan", "4444");

		songs.addSong(new Song(5.0, "Sun Microsystems", "Flute", ".aif"));
		songs.addSong(new Song(4.0, "Kevin MacLeod", "Loping Sting", ".mp3"));
	}

	// tries to login with given username and password, returns result
	public boolean login(String username, char[] password) {
		tmpJukeboxAccount = accounts.getAccount(username, password);

		if (tmpJukeboxAccount == null) {
			return false;
		} else {
			return true;
		}
	}

	// returns the logged in jukebox account
	public JukeboxAccount getAccount() {
		return tmpJukeboxAccount;
	}

	// this class listens for the end of a song, delays, then plays the next song in stack if any
	private class WaitingForSongToEnd implements EndOfSongListener {
		public void songFinishedPlaying(EndOfSongEvent eosEvent) {

			try {
				Thread.sleep((long) (1.5 * Math.pow(10, 3)));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			if (songsStack.size() != 1) {
				SongPlayer.playFile(waitForSongEnd, "./songfiles/" + songsStack.get(1).getName().replaceAll("\\s+", "")
						+ songsStack.get(1).getSongType());
				songsStack.remove(1);
			} else {
				songsStack.remove(0);
			}
		}
	}

	// tries to play the song at the given index of the songLibrary
	// because this is iteration 1 only values of 0 and 1 will ever be passed in
	public boolean tryPlay(int i) {
		if (tmpJukeboxAccount == null) {
			return false;

		}
		tmpSong = songs.getSongs()[i];

		if (theDecider.decide(tmpJukeboxAccount, tmpSong)) {
			tmpJukeboxAccount.play(tmpSong.getLength());
			tmpSong.play();

			if (songsStack.isEmpty()) {
				SongPlayer.playFile(waitForSongEnd, "./songfiles/"
						+ songs.getSongs()[i].getName().replaceAll("\\s+", "") + songs.getSongs()[i].getSongType());

			}

			songsStack.add(songs.getSongs()[i]);

			return true;
		}

		return false;
	}

	// returns whether or not an account is logged in
	public boolean loggedIn() {
		if (tmpJukeboxAccount == null)
			return false;

		return true;
	}

	// signs out, sets tmp variables to null
	public void signOut() {
		tmpJukeboxAccount = null;
		tmpSong = null;
	}

}
