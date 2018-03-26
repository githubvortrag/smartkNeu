package comparator;

import lebensmittel.Lebensmittel;
import java.util.Comparator;

public class IDComperator implements Comparator<Lebensmittel>{
	
	/*
	 * ID Vergleich, von groﬂ nach klein
	 * */

	public int compare(Lebensmittel l1, Lebensmittel l2) {
		if(l1.getLebensmittelID()<l2.getLebensmittelID()) {
			return -1;
		}
		if(l1.getLebensmittelID()==l2.getLebensmittelID()) {
			return 0;
		}
		return 1;
	}
	
}
