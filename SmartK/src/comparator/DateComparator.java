package comparator;

import java.util.Comparator;

import daten.Datumsangaben;


public class DateComparator implements Comparator<Datumsangaben>{
	/*
	 * Vergleich von Daten, Sortieren von spät nach früh
	 * */
	
	public int compare(Datumsangaben date1, Datumsangaben date2) {
		if(date1.getMhd().getTimeInMillis()<date2.getMhd().getTimeInMillis()) {
			return -1;
		}
		if(date1.getMhd().getTimeInMillis()==date2.getMhd().getTimeInMillis()) {
			return 0;
		}
		return 1;
	}
	
	public int return1(){
		return 1;
	}

}