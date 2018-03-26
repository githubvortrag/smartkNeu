package listen;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

import java.util.ListIterator;

import comparator.IDComperator;
import daten.Datumsangaben;

import lebensmittel.Aufschnitt;
import lebensmittel.Lebensmittel;

public class Bestandsliste implements Serializable{
	
	/**
	 * Klasse zum Aufnehmen von lebensmitteln, die im Bestand des Kühlschranks vorhanden sind
	 */
	private static final long serialVersionUID = 1L;
	private int anzahl;
	private ArrayList<Lebensmittel> bestände;
	private ListIterator<Lebensmittel> iterator;
	
	public Bestandsliste(){
		this.anzahl = 0;
		this.bestände = new ArrayList<Lebensmittel>();
		this.initialisiereBestandsliste();
	}
	
	
	
	//Konstruktor: long ID, String name, Date mhd, String typ, int lagerung, double inhalt, int scheiben
	
	/*
	 * Methode zum Hinzufügen eines neuen Lebensmittels, momentan nicht durch User nutzbar
	 * */
	public void neuAufschnittHinzufügen(String name, Calendar mhd, String typ, double inhalt, int scheiben){
		this.bestände.add(new Aufschnitt(name, mhd, typ, inhalt, scheiben));
		this.anzahl++;
	}
	
	/*
	 * Methode zum Hinzufügen eines in der Bestandsliste vorhandenen Lebensmittels (Suche über Namen)
	 * */
	public void vorhandenesLebensmittelHinzufügen(String neu, Calendar mhd){
		this.iterator = this.getBestände().listIterator();
		while(this.iterator.hasNext()){
			Lebensmittel prüfung = (Lebensmittel) this.iterator.next();
			if(wortAusschneiden(prüfung.getName()).equals(neu)){
				if(prüfung.getAnzahl() == 0){
					prüfung.anzahlErhöhen();
					prüfung.getDaten().remove(0);
					prüfung.getDaten().add(new Datumsangaben(mhd));
				}
				else{
					prüfung.anzahlErhöhen();
					prüfung.getDaten().add(new Datumsangaben(mhd));
				}
				this.anzahl ++;
			}
		}
	}
	
	
	// Anzahl des Lebensmittels wird dekrementiert
	public void lebensmittelEntfernen(String name){
		this.iterator = this.bestände.listIterator();
		while(this.iterator.hasNext()){
			Lebensmittel prüfung = (Lebensmittel)this.iterator.next();
			if(prüfung.getName().equals(name)){
				prüfung.anzahlVermindern();
				this.anzahl --;
			}
		}
	}
	
	// Lebensmittel mit Anzahl = 0 werden nicht aufgelistet
	public void displayUI(){
		if(this.anzahl == 0){
			System.out.println("Der Kühlschrank enthält keine Lebensmittel.");
		}
		else{
			Collections.sort(this.bestände, new IDComperator());
			this.iterator = this.bestände.listIterator();
			while(this.iterator.hasNext()){
				Lebensmittel prüfung = (Lebensmittel)this.iterator.next();
				if(prüfung.getAnzahl()>0){
				prüfung.displayUI();
				}
			}
		}
	}
	
	
	/*
	 * Bestandsliste wird gefüllt, File wurde vorher von System geschrieben und gespeichert
	 * */
	
	@SuppressWarnings("unchecked")
	public void initialisiereBestandsliste(){
		try{
			FileInputStream fileIn = new FileInputStream("initBestandsliste.ser");
			ObjectInputStream objectIn = new ObjectInputStream(fileIn);
			this.setBestände((ArrayList<Lebensmittel>)objectIn.readObject());
			objectIn.close();
		
		}
		catch(IOException r){
			
		}
		catch(ClassNotFoundException e){
			
		}
	}
	
	/*
	 * Laden einer beliebigen Bestandsliste
	 * */
	
	@SuppressWarnings("unchecked")
	public void beständeLaden(String dateiname){
		try{
			FileInputStream fileIn = new FileInputStream(dateiname);
			ObjectInputStream objectIn = new ObjectInputStream(fileIn);
			int anzahlNeu = objectIn.readInt();
			ArrayList<Lebensmittel> lebensmittelNeu =(ArrayList<Lebensmittel>) objectIn.readObject();
			this.setAnzahl(anzahlNeu);
			this.setBestände(lebensmittelNeu);
			objectIn.close();	
		
		}
		catch(IOException r){
			r.printStackTrace();
		} 
		catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
	}
	
	public void beständeSpeichern(String dateiname){
		try{
			FileOutputStream fileOut = new FileOutputStream(dateiname);
			ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeInt(this.getAnzahl());
			objectOut.writeObject(this.getBestände());
			objectOut.close();
			
		}	
		catch(FileNotFoundException e){
			e.printStackTrace();
		}
		catch(IOException r){
			r.printStackTrace();
		}
	}



	
	// Zur Rückgabe der Standardmäßigen Bestandsliste
	public String[] toListedString(){
		
		String[] ausgabe = new String[this.getBestände().size()];
		
			ListIterator<Lebensmittel> it = this.getBestände().listIterator();
			for (int i = 0; i<this.getBestände().size(); i++){
				Lebensmittel prüfung = it.next();
					ausgabe[i] = prüfung.toLittleString();
			}
			
		
		
		return ausgabe;
	}
	
	// Zur Rückgabe der Bestandsliste, Rückgabe nur bei vorhandenen Lebensmitteln
	public String[] toSortedString(){
		if(this.getAnzahl() >0){
			String[] ausgabe = new String[this.getAnzahl()];
			ListIterator<Lebensmittel> it = this.getBestände().listIterator();
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
	
	/*
	 * Getter und Setter
	 * */
		
		public int getAnzahl() {
			return anzahl;
		}



		public void setAnzahl(int anzahl) {
			this.anzahl = anzahl;
		}



		public ArrayList<Lebensmittel> getBestände() {
	 		return bestände;
		}



		public void setBestände(ArrayList<Lebensmittel> bestände) {
			this.bestände = bestände;
		}
	

}
