package geräte;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

import lebensmittel.Lebensmittel;
import listen.Einkaufsliste;
import listen.Standard_Bestandsliste_erstellen;

// Sammlung der Kühlschränke, falls mehrere Verwaltet werden sollen

public class Geräte implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int anzahl;
	private ArrayList<Kühlschrank> kühlschränke;
	private Einkaufsliste einkaufen;
	
	public Geräte(){
		this.anzahl = 0;
		this.kühlschränke = new ArrayList<Kühlschrank>();
		this.einkaufen = new Einkaufsliste();
		Standard_Bestandsliste_erstellen sbe = new Standard_Bestandsliste_erstellen(new ArrayList<Lebensmittel>());
		sbe.ausführen();
		
	}
	
	// Hinzufügen eines Kühlschranks
	
	public boolean kühlschrankHinzufügen(String name, String standort, double größe, int fächer, boolean gefrierfach, double temp){
		//this.kühlschränke.add(new Kühlschrank(name, standort, größe, fächer, temp));
		this.kühlschränke.add(anzahl, new Kühlschrank(name, standort, größe, fächer,gefrierfach ,temp));
		if(this.getAnzahl() == 0) {
			Standard_Bestandsliste_erstellen sbe = new Standard_Bestandsliste_erstellen(new ArrayList<Lebensmittel>());
			sbe.ausführen();
		}
		this.kühlschränke.get(this.getAnzahl()).getBestände().beständeSpeichern(name+"Bestandsliste.ser");
		this.kühlschränke.get(this.getAnzahl()).getEinkaufen().einkäufeSpeichern(name+"Einkaufsliste.ser");
		this.anzahl++;
		return true;
	}
	
	// Hinzufügen eines Kühlschranks
	public boolean kühlschrankHinzufügen(Kühlschrank neuerKühlschrank){
		this.kühlschränke.add(neuerKühlschrank);
		if(this.getAnzahl() == 0) {
			Standard_Bestandsliste_erstellen sbe = new Standard_Bestandsliste_erstellen(new ArrayList<Lebensmittel>());
			sbe.ausführen();
		}
		this.kühlschränke.get(this.getAnzahl()).getBestände().beständeSpeichern(this.kühlschränke.get(anzahl).getName()+"Bestandsliste.ser");
		this.kühlschränke.get(this.getAnzahl()).getEinkaufen().einkäufeSpeichern(this.kühlschränke.get(anzahl).getName()+"Einkaufsliste.ser");
		this.anzahl++;
		return true;
	}
	
	
	// Entfernen eines Kühlschranks, falls der Index vorhanden ist
	public boolean kühlschrankEntfernen(int index){
		if(index <= this.kühlschränke.size()){
			Kühlschrank kühlschrank = this.kühlschränke.get(index);
			String nameKühlschrank = kühlschrank.getName();
			delete(nameKühlschrank + "Bestandsliste.ser");
			delete(nameKühlschrank + "Einkaufsliste.ser");
			this.kühlschränke.remove(index);
			this.anzahl--;
			this.geräteSpeichern();
			return true;
		}
		else{
			return false;
		}
	}
	
	// TODO Implementieren
	public void kühlschrankKonfigurieren(){
		
	}
	
	
	
	// Serialisierung
	
	public void geräteSpeichern(){
		
		try{
			FileOutputStream fileOut = new FileOutputStream("Kühlschränke.ser");
			ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeInt(this.getAnzahl());
			objectOut.writeObject(this.getKühlschränke());
			objectOut.writeObject(this.getEinkaufen());
			objectOut.close();
			
		}	
		catch(FileNotFoundException e){
			e.printStackTrace();
		}
		catch(IOException r){
			r.printStackTrace();
		}
		
	}
	
	public void geräteLaden(){
		try{
			FileInputStream fileIn = new FileInputStream("Kühlschränke.ser");
			ObjectInputStream objectIn = new ObjectInputStream(fileIn);
			this.setAnzahl(objectIn.readInt());
			@SuppressWarnings("unchecked")
			ArrayList<Kühlschrank> kühlschränke2 = (ArrayList<Kühlschrank>) objectIn.readObject();
			this.setKühlschränke(kühlschränke2);
			this.setEinkaufen((Einkaufsliste)objectIn.readObject());
			objectIn.close();	
		
		}
		catch(IOException r){
			r.printStackTrace();
		} 
		catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
	}
	
	// Hilfsklasse zum Zusammenführen der einzelnen Einkaufslisten
	
	public void einkaufslisteZusammentragen(){
		ArrayList <Lebensmittel> zs = new ArrayList<Lebensmittel>();
		ListIterator<Kühlschrank> it = this.getKühlschränke().listIterator();
		while(it.hasNext()){
			Kühlschrank kühlschrank = it.next();
			kühlschrank.getEinkaufen().einkäufeLaden(kühlschrank.getName()+"Einkaufsliste.ser");
			Einkaufsliste liste = kühlschrank.getEinkaufen();
			if(liste.getEinkaufsliste().size() >0) {
				ListIterator <Lebensmittel> itLeb = liste.getEinkaufsliste().listIterator();
				while(itLeb.hasNext()){
					zs.add(itLeb.next());
				}
			}
		}
		this.einkaufen.setEinkaufsliste(zs);
	}
	
	public void einkaufslisteZusammentragen2(){
		
		Einkaufsliste zusammengefügteEinkaufsliste = new Einkaufsliste();
		ListIterator<Kühlschrank> it = this.getKühlschränke().listIterator();
		while(it.hasNext()){
			Kühlschrank kühlschrank = it.next();
			kühlschrank.getEinkaufen().einkäufeLaden(kühlschrank.getName()+"Einkaufsliste.ser");
			Einkaufsliste liste = kühlschrank.getEinkaufen();
			ListIterator <Lebensmittel> itLeb = liste.getEinkaufsliste().listIterator();
			
			while(itLeb.hasNext()){
				Lebensmittel prüfung = itLeb.next();
				if(prüfung.getAnzahl()>0) {
					for (int i = 0; i<prüfung.getAnzahl();i++) {
						zusammengefügteEinkaufsliste.lebensmittelHinzufügen(prüfung);
					}
				}
			}
			
		}
		this.setEinkaufen(zusammengefügteEinkaufsliste);
	}
	
	public static void delete(String löschen){
		File löschenFile = new File(löschen);
			löschenFile.delete();
	}
	
	/*
	 * Debug
	 * */
	
	// Auflistung der vorhandenen Kühlschränke auf Konsole
		public void displayGeräte(){
			Iterator<Kühlschrank> en = this.kühlschränke.iterator();
			while(en.hasNext()) {
				en.next().displayUI();
			}
			System.out.println("---------------------------------------");
		}
	
	
	
	// Getter und Setter

	public int getAnzahl() {
		return anzahl;
	}

	public void setAnzahl(int anzahl) {
		this.anzahl = anzahl;
	}

	public ArrayList<Kühlschrank> getKühlschränke() {
		return kühlschränke;
	}

	public void setKühlschränke(ArrayList<Kühlschrank> kühlschränke) {
		this.kühlschränke = kühlschränke;
	}

	public Einkaufsliste getEinkaufen() {
		return einkaufen;
	}

	public void setEinkaufen(Einkaufsliste einkaufen) {
		this.einkaufen = einkaufen;
	}
	
	

}
