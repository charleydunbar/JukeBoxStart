package model;

import java.time.LocalDate;

public class Song {

	private String id;
	private LimitCounter plays;
	private int seconds;

	public Song(String id, int seconds) {
		this.id = id;
		plays = new LimitCounter(3);
		this.seconds = seconds;
	}

	public String getName() {
		return id;
	}

	public int getSeconds() {
		return seconds;
	}

	public boolean playable() {
		return plays.playable(LocalDate.now());
	}

	public boolean play() {
		if (plays.performNew(LocalDate.now())) {
			return true;
		} else {
			return false;
		}
	}
}
