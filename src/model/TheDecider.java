// Charles Dunbar, Mohammad Adlan Fauzi
package model;

// this class returns whether or not the given
// account and song combo may play a song
public class TheDecider {

	// constructor, placeholder for comfort
	public TheDecider() {

	}

	// returns whether or not the given
	// account and song combo may play a song
	public boolean decide(JukeboxAccount acc, Song song) {
		if (acc.getSeconds() >= song.getLength() && song.playable() && acc.playable()) {
			return true;
		}

		return false;
	}
}
