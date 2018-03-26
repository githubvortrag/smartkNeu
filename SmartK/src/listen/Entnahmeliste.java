package listen;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

import java.util.ListIterator;

import javax.swing.JOptionPane;

import comparator.DateComparator;
import daten.Datumsangaben;
import lebensmittel.Lebensmittel;

public class Entnahmeliste implements Serializable{
	
	/**
	 * Enthält alle Lebensmittel, die vorerst aus dem Bestand genommen wurden
	 * Verteilung auf die anderen Listen erfolgt hier
	 * Dient als Zwischenspeicher für den "Lebensmittelverteiler" (GUI-Klasse)
	 */
	private static final long serialVersionUID = 1L;
	private int anzahlEntnommen;
	private int anzahlLeer;
	private int anzahlEinkaufsliste;
	private ArrayList<Lebensmittel> entnahmeliste;
	private ArrayList<Lebensmittel> leereLebensmittel;
	private ArrayList<Lebensmittel> einkaufsliste;

	public Entnahmeliste(){
		this.anzahlEntnommen = 0;
		this.anzahlLeer = 0;
		this.anzahlEinkaufsliste = 0;
		this.entnahmeliste = new ArrayList<Lebensmittel>();
		this.leereLebensmittel = new ArrayList<Lebensmittel>();
		this.einkaufsliste = new ArrayList<Lebensmittel>();
	}
	
	
	// Lebensmittel zu Entnahmeliste hinzufügen hinzufügen
	public void lebensmittelHinzufügen(Lebensmittel lebensmittel){
		this.entnahmeliste.add(lebensmittel);
		this.anzahlEntnommen ++;
	}
	
	
	//Lebensmittel zu Liste leerer Lebensmittel hinzufügen
	public void leeresLebensmittelHinzufügen(Lebensmittel leeresLebensmittel){
		this.leereLebensmittel.add(leeresLebensmittel);
		this.anzahlLeer ++;
	}
	
	/*
	 * Keine Ahnung woher diese Methode kommt, muss eine Überlegung im früheren Stadium der Entwicklung gewesen sein
	 * Ich habe aber Angst, sie zu löschen... 
	 * 		- Kommentar des Entwicklers
	 * */
	public Lebensmittel[] lebensmittelAbrufen(ArrayList<Lebensmittel> liste){
		ListIterator<Lebensmittel> it = liste.listIterator();
		Lebensmittel[] zs = new Lebensmittel[liste.size()];
		int i = 0;
		while(it.hasNext()){
			zs[i] = (Lebensmittel) it.next();
			i++;
		}
		return zs;
	}
	
	// Vielleicht löschbar, dient als Hilfsklasse zur Rückgabe der standardmäßigen Entnahmeliste
	public String[] toLittleString(ArrayList<Lebensmittel> liste){
		String[] ausgabe = new String[this.getAnzahlEntnommen()];
		ListIterator <Lebensmittel>it = liste.listIterator();
		
		for(int i =0; i<liste.size();i++){
			Lebensmittel prüfung = it.next();
			ausgabe[i] = prüfung.getName();
			System.out.println(ausgabe[i]);
		}
		return ausgabe;
	}
	
	
	/**
	 * Verschieben der Lebensmittel innerhalb der Entnahmeliste -> Unterscheidung zwischen Zurücklegen,
	 * Auf die Einkaufsliste schrieben und Aus Bestand entfernen.
	 * */
	public void lebensmittelVerschieben(ArrayList<Lebensmittel> quelle, ArrayList<Lebensmittel> ziel, String lebensmittel){
		
		// Wird nur ausgeführt, wenn Quelle und Ziel ungleich sind -> ungewollte Erhöhung der Anzahl der Lebensmittel vermeiden
		if(!(quelle==ziel)){
			// Überprüfung, wie oft Lebensmittel in Quelle und Ziel sind (über Hilfsmethode siehe unten)
			int anzahlVorhandenQuelle = lebensmittelSuchen(quelle, lebensmittel);
			int anzahlVorhandenZiel = lebensmittelSuchen(ziel, lebensmittel);
			// Wenn das Lebensmittel 1 Mal in der Quell liste vorhanden ist
			if(anzahlVorhandenQuelle == 1){
				// Erzeugung des Lebensmittelobjekts über eine Hilfsmethode (siehe weiter unten im Code)
				Lebensmittel lebensmittelObjekt = lebensmittelErzeugen(quelle, lebensmittel);
				// Entfernen des Lebensmittels aus der Quelle, da Anzahl = 0;
				lebensmittelObjekt.anzahlVermindern();
				Datumsangaben entferntesDatum = findOldestDate(lebensmittelObjekt.getDaten());
				lebensmittelObjekt.getDaten().remove(entferntesDatum);
				quelle.remove(lebensmittelObjekt);
				// Überprufung, ob Lebensmittel in Ziel enthalten ist
				if(anzahlVorhandenZiel < 1){
					// Wenn nicht: Hinzufügen des Lebensmittels mit der Anzahl 1
				/*	Lebensmittel lebensmittelObjektDuplikat = lebensmittelObjekt.clone();
					lebensmittelObjektDuplikat.setAnzahl(1);
					lebensmittelObjektDuplikat.getDaten().add(entferntesDatum);
					ziel.add(lebensmittelObjektDuplikat); */
					
					lebensmittelObjekt.setAnzahl(1);
					lebensmittelObjekt.getDaten().add(entferntesDatum);
					ziel.add(lebensmittelObjekt);
					
				}
				else{
					// Wenn doch: Erhöhung der Anzahl des Lebensmittels nach Ermittlung des Lebensmittelobjekts
					Lebensmittel lebensmittelObjektZiel = lebensmittelErzeugen(ziel,lebensmittel);
					lebensmittelObjektZiel.getDaten().add(entferntesDatum);
					lebensmittelObjektZiel.setAnzahl(anzahlVorhandenZiel+1);
				}
			}
			// Wenn das Lebensmittel mehrmals in der Quellliste liegt:
			else if (anzahlVorhandenQuelle >1){
				// Ermittlung des Objektes in der Quellliste und Vermiderung der Anzahl
				Lebensmittel lebensmittelObjekt = lebensmittelErzeugen(quelle, lebensmittel);
				Datumsangaben entferntesDatum = findOldestDate(lebensmittelObjekt.getDaten());
				lebensmittelObjekt.getDaten().remove(entferntesDatum);
				lebensmittelObjekt.anzahlVermindern();
				// Wenn Lebensmittel in Zielliste noch nicht vorhanden
				if(anzahlVorhandenZiel<1){
					// Duplizieren des Quellobjektes, da 2 unterschiedliche Anzahlen benötigt werden
					Lebensmittel lebensmittelObjektDuplikat = lebensmittelObjekt.clone();
					// Einfügen des duplizierten Objektes in die neue Liste mit der ANzhal 1
				//	lebensmittelObjektDuplikat.getDaten().add(entferntesDatum);
					lebensmittelObjektDuplikat.setAnzahl(1);
					ziel.add(lebensmittelObjektDuplikat);
				}
				// Wenn Lebensmittel bereits vorhanden
				else{
					// Ermittlung des Zielobjektes über die o.g. Hilfsmethode und Erhöhung der Anzahl um 1
					Lebensmittel lebensmittelObjektZiel = lebensmittelErzeugen(ziel, lebensmittel);
					lebensmittelObjektZiel.getDaten().add(entferntesDatum);
					lebensmittelObjektZiel.setAnzahl(anzahlVorhandenZiel+1);
				}
			}
			else{
				// Fehlermeldung, falls das Lebensmittel nicht in der Quellliste gefunden werden kann
				JOptionPane.showMessageDialog(null, "Das Lebensmittel konnte nicht Gefunden werden.");
			}
		}
		
		
	}
	
	public void einkaufslisteÜbertragen(ArrayList<Lebensmittel> quelle, ArrayList<Lebensmittel> ziel){
		
		
		for(int i = 0; i<quelle.size(); i++) {
			Lebensmittel prüfung = quelle.get(i);
			for(int a = 0; a<prüfung.getAnzahl();a++){
				lebensmittelVerschieben(quelle, ziel, wortAusschneiden(prüfung.getName()));
				this.leereLebensmittel.add(prüfung);
			}
			
		}
		
		
	}
	
	// Methode zum Aktualisieren der Bestände (Ausführen der Änderungen durch Verschieben der Lebensmittel)
	public void beständeAktualisieren (ArrayList<Lebensmittel> zurücklegen,ArrayList<Lebensmittel> entfernen , ArrayList<Lebensmittel> einkaufen,ArrayList<Lebensmittel> bestand){
		ListIterator <Lebensmittel> itBestand = bestand.listIterator();
		while(itBestand.hasNext()) {
			Lebensmittel prüfung = itBestand.next();
			String namePrüfung = wortAusschneiden(prüfung.getName());
			int anzahlZurück = lebensmittelSuchen(zurücklegen, namePrüfung);
			int anzahlEntfernen = lebensmittelSuchen(entfernen, namePrüfung);
			int anzahlEinkaufen = lebensmittelSuchen(einkaufen, namePrüfung);
			int anzahlVorhanden = lebensmittelSuchen(bestand, namePrüfung);
			
			if(anzahlVorhanden > 0) {	
				if(anzahlZurück > 0) {
					prüfung.setAnzahl(anzahlZurück);
				}
				else {
					if(anzahlEntfernen > 0) {
						prüfung.setAnzahl(0);
					}
					else {
						if(anzahlEinkaufen > 0) {
							prüfung.setAnzahl(0);
						}
					}
				}
			}
		}
	}
	
	// Leeren der Listen, dienten vorher als Zwischenspeicher
	public void listenLeeren(){
		for(int i = 0; i<this.getEinkaufsliste().size();i++){
			this.getEinkaufsliste().remove(i);
		}
		
		for(int i = 0; i<this.getEntnahmeliste().size();i++){
			this.getEntnahmeliste().remove(i);
		}
		
		for(int i = 0; i<this.getLeereLebensmittel().size();i++){
			this.getLeereLebensmittel().remove(i);
		}
	}
	
	// Rückgabe der Lebensmittel mit Anzahl > 1
	public String[] toSortedString(ArrayList<Lebensmittel> liste){
		String[] ausgabe = new String[1+liste.size()];
		if(liste.size() >0){
			ListIterator<Lebensmittel> it = liste.listIterator();
			for (int i = 0; i<liste.size(); i++){
				Lebensmittel prüfung = it.next();
				if(prüfung.getAnzahl()>0){
					ausgabe[i] = prüfung.toLittleString();
				}
			}
			
		}
		else{
			
			ausgabe[0] = "Die Liste ist leer.";
		}
		return ausgabe;
	}
	
	/** 
	 * Hilfsmethode für die Ermittlung der Anzahl der Lebensmittel in einer Liste
	 * **/
	
	private int lebensmittelSuchen(ArrayList<Lebensmittel> liste, String lebensmittel){
		ListIterator<Lebensmittel> itListe = liste.listIterator();
		while(itListe.hasNext()){
			Lebensmittel prüfung = itListe.next();
			if(wortAusschneiden(prüfung.getName()).equals(lebensmittel)){
				return prüfung.getAnzahl();
			}
		}
		return 0;
	}
	
	/**
	 * Hilsmethode zum finden des Lebensmittelobjektes in einer Liste
	 *  **/
	private Lebensmittel lebensmittelErzeugen(ArrayList<Lebensmittel> liste, String lebensmittel){
		ListIterator<Lebensmittel> itListe = liste.listIterator();
		while(itListe.hasNext()){
			Lebensmittel prüfung = itListe.next();
			if(wortAusschneiden(prüfung.getName()).equals(lebensmittel)){
				return prüfung;
			}
		}
		return null;
	
	}
	
	private Datumsangaben findOldestDate(ArrayList<Datumsangaben> daten){
		
		Collections.sort(daten, new DateComparator());
		
		return daten.get(0);
	}
	
	
	/*
	 * Getter und Setter
	 * */

	

	public int getAnzahlEntnommen() {
		return anzahlEntnommen;
	}

	public void setAnzahlEntnommen(int anzahlEntnommen) {
		this.anzahlEntnommen = anzahlEntnommen;
	}

	public int getAnzahlLeer() {
		return anzahlLeer;
	}

	public void setAnzahlLeer(int anzahlLeer) {
		this.anzahlLeer = anzahlLeer;
	}

	public ArrayList<Lebensmittel> getLeereLebensmittel() {
		return leereLebensmittel;
	}

	public void setLeereLebensmittel(ArrayList<Lebensmittel> leereLebensmittel) {
		this.leereLebensmittel = leereLebensmittel;
	}

	public ArrayList<Lebensmittel> getEntnahmeliste() {
		return entnahmeliste;
	}

	public void setEntnahmeliste(ArrayList<Lebensmittel> entnahmeliste) {
		this.entnahmeliste = entnahmeliste;
	}

	public int getAnzahlEinkaufsliste() {
		return anzahlEinkaufsliste;
	}

	public void setAnzahlEinkaufsliste(int anzahlEinkaufsliste) {
		this.anzahlEinkaufsliste = anzahlEinkaufsliste;
	}

	public ArrayList<Lebensmittel> getEinkaufsliste() {
		return einkaufsliste;
	}

	public void setEinkaufsliste(ArrayList<Lebensmittel> einkaufsliste) {
		this.einkaufsliste = einkaufsliste;
	}
	
	private String wortAusschneiden(String eingabe){
		String zurück = "";
		int i = 0;
		while(i<eingabe.length()&& !(eingabe.charAt(i)==' ')){
			zurück = zurück + eingabe.charAt(i);
			i++;
		}
		return zurück;
	
	}
	
	
	
	
}
