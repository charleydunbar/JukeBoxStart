// Charles Dunbar, Mohammad Adlan Fauzi
package model;

import java.time.LocalDate;

// this class serves as a Song, it remembers misc information about itself:
// playcount via LimitCounter, length, artist, name, songType
public class Song {
	private double length;
	private String artist;
	private String name;
	private String songType;
	private LimitCounter plays;
	
	// constructor, initializes variables
	public Song(double length, String artist, String name, String songType){
		this.length = length;
		plays = new LimitCounter(3);
		this.artist = artist;
		this.name = name;
		this.songType = songType;
	}
	
	// "plays" a song if possible, returns if operation was successful
	public boolean play() {
		if (plays.performNew(LocalDate.now()))
			return true;
		
		return false;
	}
	
	// returns whether this song can be played again at current time
	public boolean playable() {
		return plays.playable(LocalDate.now());
	}
	
	// returns songType (extension)
	public String getSongType(){
		return songType;
	}
	
	// returns name
	public String getName(){
		return name;
	}
	
	// returns artist
	public String getArtist(){
		return artist;
	}
	
	// returns length
	public double getLength(){
		return length;
	}
}
