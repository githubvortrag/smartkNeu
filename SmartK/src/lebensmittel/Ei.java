package lebensmittel;

import java.util.Calendar;

public class Ei extends Lebensmittel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static private int LAGERUNG; //TODO Recherchieren Lagerung
	private int anzahlInKarton; // Anzahl Eier im Karton
	
	public Ei(String name, Calendar mhd, String typ, int anzahl){
		super(name, mhd, typ);
		this.anzahlInKarton = anzahl;
	}
	
	
	
	public String toLittleString(){
		String ausgabe = "";
		ausgabe = ausgabe.concat(this.getName() + " ");
		ausgabe = ausgabe.concat(this.getTyp() + " ");
		ausgabe = ausgabe.concat("(" + this.getAnzahl() + ")" + " ");
		return ausgabe;
	}

	

	public static int getLAGERUNG() {
		return LAGERUNG;
	}

	public static void setLAGERUNG(int lAGERUNG) {
		LAGERUNG = lAGERUNG;
	}

	public int getAnzahlInKarton() {
		return anzahlInKarton;
	}

	public void setAnzahlInKarton(int anzahlInKarton) {
		this.anzahlInKarton = anzahlInKarton;
	}

	/*
	 * Debug
	 * */
	
	public void displayUI(){
		System.out.println("Name:" +"\t"+ this.getName());
		System.out.println("ID:" +"\t"+ this.getLebensmittelID());
		System.out.println("Anzahl:" +"\t"+ this.getAnzahl());
		System.out.println("Typ" +"\t"+ this.getTyp());
		/*for(int i = 0; i<this.getAnzahl();i++){
		//	this.getDaten().indexOf(i).displayUI();
		}*/
		
		System.out.println("Lagerungsempfehlung" +"\t"+ Ei.getLAGERUNG()); // TODO: Lagerungsempfehlung
		System.out.println("Inhalt in Gramm:" +"\t"+ this.getAnzahl());
		
	}

	
	
	
}
