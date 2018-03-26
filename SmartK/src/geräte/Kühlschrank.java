package geräte;

import java.io.Serializable;
import java.util.Calendar;


import listen.Bestandsliste;
import listen.Einkaufsliste;
import listen.Entnahmeliste;

public class Kühlschrank implements Serializable{
	
	/**
	 * Klasse Kühlschrank
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String standort;
	private double größe; // in Liter
	private int fächer;
	private boolean gefrierfachVorhanden;
	private double temperatur;
	
	private Bestandsliste bestände;
	private Entnahmeliste entnommen;
	private Einkaufsliste einkaufen;
	
	public Kühlschrank(){
		
	}
	
	public Kühlschrank(String name, String standort, double größe, int fächer,boolean gefrierfach, double temperatur){
		
		this.name = name;
		this.standort = standort;
		this.größe = größe;
		this.fächer = fächer;
		this.gefrierfachVorhanden = gefrierfach;
		this.temperatur = temperatur;
		
		this.bestände = new Bestandsliste();
		this.bestände.initialisiereBestandsliste();
		this.bestände.beständeSpeichern(this.getName()+"Bestandsliste.ser");
		this.entnommen = new Entnahmeliste();
		this.einkaufen = new Einkaufsliste();
	}
	
	// Methode zum Anzeigen der Kühlschrankattribute im UI-Modus (textlich)
	public void displayUI(){
		System.out.println("Name: " +"\t"+"\t" + this.name);
		System.out.println("Standort: " +"\t"+ this.standort);
		System.out.println("Größe " +"\t"+"\t" + this.größe);
		System.out.println("Fächer: "+"\t" + this.fächer);
		System.out.println("Temp:" +"\t"+"\t" + this.temperatur);
	}
	
	
	
	// Methode, um Lebensmittel zu Bestandsliste hinzuzufügen
	public void newAufschnittHinzufügen(String name, Calendar mhd, String typ, double inhalt, int scheiben){
		this.bestände.neuAufschnittHinzufügen( name, mhd, typ, inhalt, scheiben);
	}
	
	// Methoden zum Einfügen der Lebensmittel
	public void vorhandenenAufschnittHinzufügen(String name, Calendar mhd){
		this.bestände.vorhandenesLebensmittelHinzufügen(name, mhd);
	}
	
	// Lebensmittel löschen
	public void lebensmittelEntfernen(String name){
		this.bestände.lebensmittelEntfernen(name);
	}
	
	
	
	
	
	// Getter und Setter

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStandort() {
		return standort;
	}

	public void setStandort(String standort) {
		this.standort = standort;
	}

	public double getGröße() {
		return größe;
	}

	public void setGröße(double größe) {
		this.größe = größe;
	}

	public int getFächer() {
		return fächer;
	}

	public void setFächer(int fächer) {
		this.fächer = fächer;
	}

	public boolean isGefrierfachVorhanden() {
		return gefrierfachVorhanden;
	}

	public void setGefrierfachVorhanden(boolean gefrierfachVorhanden) {
		this.gefrierfachVorhanden = gefrierfachVorhanden;
	}

	public double getTemperatur() {
		return temperatur;
	}

	public void setTemperatur(double temperatur) {
		this.temperatur = temperatur;
	}

	public Bestandsliste getBestände() {
		return bestände;
	}

	public void setBestände(Bestandsliste bestände) {
		this.bestände = bestände;
	}

	public Entnahmeliste getEntnommen() {
		return entnommen;
	}

	public void setEntnommen(Entnahmeliste entnommen) {
		this.entnommen = entnommen;
	}

	public Einkaufsliste getEinkaufen() {
		return einkaufen;
	}

	public void setEinkaufen(Einkaufsliste einkaufen) {
		this.einkaufen = einkaufen;
	}
	
	
	
	

}
