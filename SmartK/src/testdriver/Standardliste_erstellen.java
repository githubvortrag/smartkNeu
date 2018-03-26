package testdriver;



import geräte.Geräte;


public class Standardliste_erstellen {
	
	/*
	 * Diese Klasse ist ein Überbleibsel aus der Prototypentwicklung
	 * */

	public static void main(String[] args) {
		
		
		Geräte inventar = new Geräte();
		inventar.kühlschrankHinzufügen("Tommi", "Garage", 100, 6,true, 7.0);
		inventar.kühlschrankHinzufügen("Olli", "Keller", 60.0, 5, false, 7.0);
	
		inventar.geräteSpeichern();
		
		System.out.println("Fertig");

	}

}
