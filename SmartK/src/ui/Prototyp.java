package ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import geräte.Geräte;

public class Prototyp {
	
	/*
	 * Prototyp-Klasse für Review in Vorlesung
	 * */

	//private Geräte inventar;
	
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		Geräte inventar = new Geräte();
		inventar.geräteLaden();
		boolean wdh;
		do{
			wdh = hauptmenu(in, inventar);
		}
		while(wdh);

	}
	
	public static boolean hauptmenu(BufferedReader in, Geräte inventar) throws IOException{
		System.out.println("Willkommen. Wählen Sie eine Option: ");
		System.out.println("Kühlschrank-Info (1)");
		System.out.println("Bestände anzeigen (2)");
		System.out.println("Lebensmittel hinzufügen (3)");
		System.out.println("Lebensmittel entfernen (4)");
		int auswahl=0;
		try{
		auswahl = Integer.parseInt(in.readLine());
		}
		catch(IOException e){
			System.out.println("Fehler");
		}
		switch(auswahl){
		case 1: kühlschrankInfo(inventar); return true;
		case 2: beständeInfo(inventar); return true;
		case 3: lebensmittelHinzufügen(inventar, in); return true;
		case 4: lebensmittelEntfernen(inventar, in); return true;
		default: System.out.println("Tschö"); return false;
		}
		
		
	}
	
	
	public static void kühlschrankInfo(Geräte inventar){
		inventar.displayGeräte();
	}
	
	public static void beständeInfo(Geräte inventar){
		inventar.getKühlschränke().get(0).getBestände().displayUI();
	}
	
	public static void lebensmittelHinzufügen(Geräte inventar, BufferedReader in) throws IOException{
		inventar.getKühlschränke().get(0).getBestände().displayUI();;
		
		System.out.println("Wählen Sie die ID des Lebensmittels, welches hinzugefügt werden soll.");
	//	long einfügen = Long.parseUnsignedLong(in.readLine());
	//	inventar.getKühlschränke().get(0).vorhandenenAufschnittHinzufügen(einfügen);
	}
	
	public static void lebensmittelEntfernen(Geräte inventar, BufferedReader in) throws IOException{
		inventar.getKühlschränke().get(0).getBestände().displayUI();
		System.out.println("Wählen Sie die ID des Lebensmittels, welches entfernt werden soll.");
	//	long entfernen = Integer.parseInt(in.readLine());
	//	inventar.getKühlschränke().get(0).lebensmittelEntfernen(entfernen);
	}

}
