package daten;

import java.io.Serializable;
import java.util.Calendar;


public class  Datumsangaben implements Serializable{
	
	/**
	 * Sammlung der Datumsangaben Öffnung, Mindesthaltbarkeitsdatum, Eingelagert
	 */
	private static final long serialVersionUID = 1L;
	private Calendar eingelagert;
	private Calendar mhd;
	private Calendar öffnung;
	
	public Datumsangaben(Calendar mhd){
		this.eingelagert = Calendar.getInstance();
		this.mhd = mhd;
	}


	public void öffnen(){
		
		Calendar neu = Calendar.getInstance();
		neu.setTime(neu.getTime());
		
		this.öffnung = neu;
	}
	
	//Getter und Setter

	public Calendar getEingelagert() {
		return eingelagert;
	}

	public void setEingelagert(Calendar eingelagert) {
		this.eingelagert = eingelagert;
	}

	public Calendar getMhd() {
		return mhd;
	}

	public void setMhd(Calendar mhd) {
		this.mhd = mhd;
	}

	public Calendar getÖffnung() {
		return öffnung;
	}

	public void setÖffnung(Calendar öffnung) {
		this.öffnung = öffnung;
	}
	
	
}
