// Charles Dunbar, Mohammad Adlan Fauzi
package model;

// this class serves as a rudimentary collection of songs
// for iteration 1
public class SongLibrary {
	private Song[] songs;	
	
	// constructor, initializes variables
	public SongLibrary() {
		songs = new Song[2];
	}
	
	// adds songs, simple for iteration 1
	public void addSong(Song song) {
		if (songs[0] == null) {
			songs[0] = song;
		} else {
			songs[1] = song;
		}
	}
	
	// returns the array of songs
	public Song[] getSongs() {
		return songs;
	}
}
