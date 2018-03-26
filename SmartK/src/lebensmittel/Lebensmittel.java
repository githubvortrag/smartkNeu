package lebensmittel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;


import daten.Datumsangaben;
import inhaltsstoffe.Inhaltsstoffe;

abstract public class Lebensmittel implements Serializable, Cloneable{
	
	
	private static final long serialVersionUID = 7112797613685533841L;
	private long lebensmittelID; // Nummer
	private String name;
	private String typ;
	private ArrayList <Datumsangaben> daten;
	private Inhaltsstoffe inhaltsstoffe; 
	private int anzahl; // Anzahl Packungen
	private boolean levelIsLow; // Ausgangspunkt für Einkaufsliste
	
	// Konstruktor
	
	public Lebensmittel(String name, Calendar mhd, String typ){
	//	this.lebensmittelID = ID;
		this.name = name;
		this.typ = typ;
		//this.inhaltsstoffe = new Inhaltsstoffe(ihst);
		this.daten = new ArrayList<Datumsangaben>();
		this.daten.add(new Datumsangaben(mhd));
		this.anzahl++;
	}
	
	abstract public void displayUI();
	
	abstract public String toLittleString();

	
	
	
	
	
	
	// Getter und Setter:
	
	
	public long getLebensmittelID() {
		return lebensmittelID;
	}

	public void setLebensmittelID(long lebensmittelID) {
		this.lebensmittelID = lebensmittelID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTyp() {
		return typ;
	}

	public void setTyp(String typ) {
		this.typ = typ;
	}

	

	public ArrayList <Datumsangaben> getDaten() {
		return daten;
	}

	public void setDaten(ArrayList <Datumsangaben> daten) {
		this.daten = daten;
	}

	public Inhaltsstoffe getInhaltsstoffe() {
		return inhaltsstoffe;
	}

	public void setInhaltsstoffe(Inhaltsstoffe inhaltsstoffe) {
		this.inhaltsstoffe = inhaltsstoffe;
	}

	
	public int getAnzahl() {
		return anzahl;
	}

	public void setAnzahl(int anzahl) {
		this.anzahl = anzahl;
	}
	
	public void anzahlErhöhen(){
		this.anzahl++;
	}
	
	public void anzahlVermindern(){
		this.anzahl--;
	}

	public boolean isLevelIsLow() {
		return levelIsLow;
	}

	public void setLevelIsLow(boolean levelIsLow) {
		this.levelIsLow = levelIsLow;
	}
	
	public Lebensmittel clone() {
	      Lebensmittel theClone = null;
	      try {
	        theClone = (Lebensmittel) super.clone();
	      }
	      catch(CloneNotSupportedException e) {
	      }
	      return theClone;
	    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


	
	
	
	
	
	
	
	

}
