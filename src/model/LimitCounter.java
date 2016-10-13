// Charles Dunbar, Mohammad Adlan Fauzi
package model;

import java.time.LocalDate;
import java.util.ArrayList;

// This class utilizes LocalDate to "remember" how many
// "plays" and object has done, it has methods to convey this
// functionality to other classes
public class LimitCounter {
	private int limit;
	private ArrayList<LocalDate> dates;

	// constructor, initializes variables
	public LimitCounter(int limit) {
		this.limit = limit;
		dates = new ArrayList<>();
	}

	// "performs" new play if possible, returns whether or not a play was successful
	public boolean performNew(LocalDate ld) {
		if (dates.size() < limit) {
			dates.add(ld);
			return true;
		} else {
			if (ld.isAfter(dates.get(0))) {
				dates.add(ld);
				dates.remove(0);
				return true;
			} else {
				return false;
			}
		}
	}

	// returns whether there are any plays left on requested day
	public boolean playable(LocalDate ld) {
		if (dates.size() < limit) {
			return true;
		} else {
			if (ld.isAfter(dates.get(0))) {
				return true;
			} else {
				return false;
			}
		}
	}

	// returns how many times play has been invoked on requested day
	public int getPlays(LocalDate now) {
		int count = 0;
		
		for (int i = 0; i < dates.size(); i++) {
			if (now.compareTo(dates.get(i)) == 0)
				count += 1;
		}
		
		return count;
	}

}
