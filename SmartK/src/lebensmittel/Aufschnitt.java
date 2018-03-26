package lebensmittel;

import java.util.Calendar;


// Zum Verständnis: Aufschnitt = Wurst, Käse in Scheiben; Aufstrich = Leberwurst, Fritschkäse (also streichbar)

public class Aufschnitt extends Lebensmittel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static int LAGERUNG; // TODO Recherchieren // Nummer steht für Zone im Kühlschrank
	private double inhalt;
	private int scheiben;
	
	public Aufschnitt(String name, Calendar mhd, String typ, double inhalt, int scheiben){
		super(name, mhd, typ);
		this.inhalt = inhalt;
		this.scheiben = scheiben;
	}
	
	
	
	public String toLittleString(){
		String ausgabe = "";
		ausgabe = ausgabe.concat(this.getName() + " ");
		ausgabe = ausgabe.concat(this.getTyp() + " ");
		ausgabe = ausgabe.concat("(" + this.getAnzahl() + ")" + " ");
		return ausgabe;
	}
	
	// Getter und Setter

	public double getInhalt() {
		return inhalt;
	}

	public void setInhalt(double inhalt) {
		this.inhalt = inhalt;
	}

	public int getScheiben() {
		return scheiben;
	}

	public void setScheiben(int scheiben) {
		this.scheiben = scheiben;
	}

	public static int getLAGERUNG() {
		return LAGERUNG;
	}

	public static void setLAGERUNG(int lAGERUNG) {
		LAGERUNG = lAGERUNG;
	}
	
	/*
	 * Debug
	 * */
	public void displayUI(){
		System.out.println("ID:" +"\t" +"\t" +"\t" +this.getLebensmittelID());
		System.out.println("Name:" +"\t" +"\t" +"\t" + this.getName());
		System.out.println("Anzahl:" +"\t"+"\t"+"\t" + this.getAnzahl());
		System.out.println("Typ: " +"\t"+"\t"+"\t" + this.getTyp());
		
		
		System.out.println("Lagerungsempfehlung: " +"\t"+ Aufschnitt.getLAGERUNG()); // TODO: Lagerungsempfehlung
		System.out.println("Inhalt in Gramm:" +"\t"+ this.getInhalt());
		System.out.println("Inhalt in Scheiben:" +"\t"+ this.getScheiben());
		System.out.println("------------------------------------------");
		
	}
	
	
	
}
