package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class LimitCounter {
	private int limit;
	private ArrayList<LocalDate> dates;

	public LimitCounter(int limit) {
		this.limit = limit;
		dates = new ArrayList<>();
	}

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

}
