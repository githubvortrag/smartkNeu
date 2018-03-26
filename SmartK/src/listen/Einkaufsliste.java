package listen;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import java.util.ListIterator;


import lebensmittel.Lebensmittel;

public class Einkaufsliste implements Serializable{
	
	/**
	 * Klasse zum Aufnehmen von Lebensmitteln, die auf der Einkaufsliste des Kühlschranks/ aller Kühlschränke vorhanden sind
	 */
	 
	private static final long serialVersionUID = 1L;
	private int anzahl;
	private ArrayList<Lebensmittel> einkaufsliste;
	
	public Einkaufsliste(){
		this.einkaufsliste = new ArrayList<Lebensmittel>();
		this.initialisiereEinkaufsliste();
		this.anzahl = 0;
	}
	
	// Hinzufügen eines Lebensmittels
	public void lebensmittelHinzufügen(Lebensmittel lebensmittel){
		
			String neu = lebensmittel.getName();
			int zähler = 0;
			boolean gefunden = false;
			while(zähler<this.getEinkaufsliste().size()){
				Lebensmittel prüfung = this.getEinkaufsliste().get(zähler);
				if(wortAusschneiden(prüfung.getName()).equals(neu)){
					prüfung.anzahlErhöhen();
					this.anzahl ++;
					gefunden = true;
				}
				zähler++;
			}
			if(gefunden==false) {
				this.einkaufsliste.add(lebensmittel);
				this.anzahl ++;
			}
	}
	
	//Entfernen eines Lebensmittels
	public void lebensmittelEntfernen(int index){
		this.einkaufsliste.remove(index);
		this.anzahl--;
	}
	
	public void lebensmittelVermindern(Lebensmittel lebensmittel) {
		if(lebensmittel.getAnzahl() > 0) {
			lebensmittel.anzahlVermindern();
		}
	}
	
	// Anzahl eines Lebensmittels verändern
	public void lebensmittelAnzahlVerändern(int index, int anzahl){
		this.einkaufsliste.get(index).setAnzahl(anzahl);
	}
	
	// Rückgabe der Einkaufsliste; Rückgabe nur, wenn Anzahl >1
	public String[] toSortedString(ArrayList<Lebensmittel> liste){
		if(this.getAnzahl() >0){
			String[] ausgabe = new String[this.getAnzahl()];
			ListIterator<Lebensmittel> it = liste.listIterator();
			int zähler = 0;
			while(it.hasNext()){
				Lebensmittel prüfung = it.next();
				if(prüfung.getAnzahl()>0){
					ausgabe[zähler] = prüfung.toLittleString();
					zähler ++;
				}
			}
			return ausgabe;
			
		}
		else{
			String[] ausgabe = new String[1];
			ausgabe[0] = "Der Kühlschrank ist leer.";
			return ausgabe;
		}
		
	}
	
	// Serialisierung
	
	/*
	 * Bestandsliste wird gefüllt, File wurde vorher von System geschrieben und gespeichert
	 * */
	
	@SuppressWarnings("unchecked")
	public void initialisiereEinkaufsliste(){
		try{
			FileInputStream fileIn = new FileInputStream("initBestandsliste.ser");
			ObjectInputStream objectIn = new ObjectInputStream(fileIn);
			this.setEinkaufsliste((ArrayList<Lebensmittel>)objectIn.readObject());
			objectIn.close();
		
		}
		catch(IOException r){
			
		}
		catch(ClassNotFoundException e){
			
		}
	}
	
	@SuppressWarnings("unchecked")
	public void einkäufeLaden(String dateiname){
		try{
			FileInputStream fileIn = new FileInputStream(dateiname);
			ObjectInputStream objectIn = new ObjectInputStream(fileIn);
			int anzahlNeu = objectIn.readInt();
			ArrayList<Lebensmittel> lebensmittelNeu =(ArrayList<Lebensmittel>) objectIn.readObject();
			this.setAnzahl(anzahlNeu);
			this.setEinkaufsliste(lebensmittelNeu);
			objectIn.close();	
		
		}
		catch(IOException r){
			r.printStackTrace();
		} 
		catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
	}
	
	public void einkäufeSpeichern(String dateiname){
		try{
			FileOutputStream fileOut = new FileOutputStream(dateiname);
			ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeInt(this.getAnzahl());
			objectOut.writeObject(this.getEinkaufsliste());
			objectOut.close();
			
		}	
		catch(FileNotFoundException e){
			e.printStackTrace();
		}
		catch(IOException r){
			r.printStackTrace();
		}
	}
	
	/*
	 * Getter und Setter
	 * */

	public int getAnzahl() {
		return anzahl;
	}

	public void setAnzahl(int anzahl) {
		this.anzahl = anzahl;
	}

	public ArrayList<Lebensmittel> getEinkaufsliste() {
		return einkaufsliste;
	}

	public void setEinkaufsliste(ArrayList<Lebensmittel> einkaufsliste) {
		this.einkaufsliste = einkaufsliste;
	}
	
	// Hilfsmethode zum Ausschneiden eines Lebensmittelnamen
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
