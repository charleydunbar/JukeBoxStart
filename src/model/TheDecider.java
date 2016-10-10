package model;

public class TheDecider {

	public TheDecider() {

	}

	public boolean decide(JukeboxAccount acc, Song song) {
		if (acc.getSeconds() >= song.getSeconds() && song.playable() && acc.playable()) {
			return true;
		}

		return false;
	}
}
