package daten;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ListIterator;

import lebensmittel.Lebensmittel;
import listen.Bestandsliste;

public class MHD_Alarm {
	
	/*
	 * MHD-Alarm zum Aufspüren der Lebensmittel, die weniger als x Tage bis zum MHD haben
	 * */

	private int anzahlTage;
	private ArrayList<Lebensmittel> lebensmittel;
	

	public MHD_Alarm() {
		// Standardeinstellung = 7 Tage
		this.anzahlTage = 7;
	}
	
	// Methode zum Durchsuchen der Bestandsliste, Rückgabe der betroffenen Lebensmittel als Stringarray
	public String[] bestandslisteDurchsuchen(Bestandsliste bestandsliste){
		String ausgabe[] = new String[getAnzahl(bestandsliste)];
		int i = 0;
		if(!(bestandsliste.getAnzahl() == 0)){
			ListIterator<Lebensmittel> it = bestandsliste.getBestände().listIterator();
			
			while(it.hasNext()){
				Lebensmittel prüfung = it.next();
				if(prüfung.getAnzahl() >0) {
					ListIterator<Datumsangaben> it2 = prüfung.getDaten().listIterator();
					while(it2.hasNext()){
						
						Calendar mhdLebensmittel = it2.next().getMhd();
						Calendar heute = Calendar.getInstance(); 
						heute.setTime(heute.getTime());
						long zeit = mhdLebensmittel.getTime().getTime() - heute.getTime().getTime();
						long abstand = Math.round((double)zeit/(24.*60.*60.*1000.));
						if(abstand<anzahlTage){
							ausgabe[i] = new String(prüfung.getName() + "   |   " + abstand + " (Tage verbl.)");
							i++;
							
						}
					}
				}
					
				
			}
		}
		return ausgabe;
	}
	
	// Hilfsmethode zur Ermittlung der nötigen Größe des Rückgabestrings
	private int getAnzahl(Bestandsliste bestandsliste) {
		int i = 0;
		if(!(bestandsliste.getAnzahl() == 0)){
			ListIterator<Lebensmittel> it = bestandsliste.getBestände().listIterator();
			
			while(it.hasNext()){
				Lebensmittel prüfung = it.next();
				if(prüfung.getAnzahl() >0) {
					ListIterator<Datumsangaben> it2 = prüfung.getDaten().listIterator();
					while(it2.hasNext()){
						
						Calendar mhdLebensmittel = it2.next().getMhd();
						Calendar heute = Calendar.getInstance(); 
						heute.setTime(heute.getTime());
						long zeit = mhdLebensmittel.getTime().getTime() - heute.getTime().getTime();
						long abstand = Math.round((double)zeit/(24.*60.*60.*1000.));
						if(abstand<anzahlTage){
							i++;
							
						}
					}
				}
					
				
			}
		}
		return i;
	}
	
	// Serialsierung: Laden und Speichern der Tage, dienicht unterschritten werden dürfen
	
	public void einstellungenSpeichern() {
		try{
			FileOutputStream fileOut = new FileOutputStream("MHD_Settings.ser");
			ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeInt(this.getAnzahlTage());
			objectOut.close();
			
		}	
		catch(FileNotFoundException e){
			e.printStackTrace();
		}
		catch(IOException r){
			r.printStackTrace();
		}
	}
	
	public void einstellungenLaden() {
		try{
			FileInputStream fileIn = new FileInputStream("MHD_Settings.ser");
			ObjectInputStream objectIn = new ObjectInputStream(fileIn);
			int anzahlNeu = objectIn.readInt();
			
			this.setAnzahlTage(anzahlNeu);
			objectIn.close();
		
		}
		catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		catch(IOException r){
			r.printStackTrace();
		} 
		
	}

	/**
	 * @return the anzahlTage
	 */
	public int getAnzahlTage() {
		return anzahlTage;
	}

	/**
	 * @param anzahlTage the anzahlTage to set
	 */
	public void setAnzahlTage(int anzahlTage) {
		this.anzahlTage = anzahlTage;
	}

	/**
	 * @return the lebensmittel
	 */
	public ArrayList<Lebensmittel> getLebensmittel() {
		return lebensmittel;
	}

	/**
	 * @param lebensmittel the lebensmittel to set
	 */
	public void setLebensmittel(ArrayList<Lebensmittel> lebensmittel) {
		this.lebensmittel = lebensmittel;
	}
	
	
	
}
