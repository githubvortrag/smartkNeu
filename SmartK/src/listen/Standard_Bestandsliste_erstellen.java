package listen;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ListIterator;

import lebensmittel.Aufschnitt;
import lebensmittel.Aufstrich;
import lebensmittel.Desserts;
import lebensmittel.Ei;
import lebensmittel.Fisch;
import lebensmittel.Flüssigkeit;
import lebensmittel.Gemüse;
import lebensmittel.Lebensmittel;
import lebensmittel.Lebensmittel_am_Stück;
import lebensmittel.Obst;
import lebensmittel.Pilze;

public class Standard_Bestandsliste_erstellen {

	/* Diese Klasse dient zum Erstellen einer Standardmäßigen Bestandsliste, wobei alle Lebensmittel die Anzahl 0 bekommen.
	 * Dadurch wird ein Einlagermn der Lebensmittel erleichtert und beschleunigt.
	 */
	
	private ArrayList<Lebensmittel> leereBestandsliste;
	private static Calendar fiktivesMHD;
	private static String wurstTyp = "Wurst";
	private static String käseTyp = "Käse";
	private static String milchprodukt = "Milchprodukt";
	private static String pilz = "Speisepilz";
	private static String pflanzlich = "Obst/ Gemüse";
	private static String tierprodukt = "Tierprodukt";
	
	
	public Standard_Bestandsliste_erstellen(ArrayList<Lebensmittel> bestand){
		this.leereBestandsliste = bestand;
	}
	
	public void ausführen() {
		
		aufschnitteErstellen(this.leereBestandsliste);
		aufstricheErstellen(this.leereBestandsliste);
		dessertsErstellen(this.leereBestandsliste);
		eiErstellen(this.leereBestandsliste);
		fischErstellen(this.leereBestandsliste);
		flüssigkeitErstellen(this.leereBestandsliste);
		gemüseErstellen(this.leereBestandsliste);
		lebensmittel_Am_Stück_Erstellen(this.leereBestandsliste);
		obstErstellen(this.leereBestandsliste);
		pilzeErstellen(this.leereBestandsliste);
		
		ListIterator<Lebensmittel> it4 = this.leereBestandsliste.listIterator();
	     while(it4.hasNext()){
	            it4.next().setAnzahl(0);
	     }
	     
		this.standardErstellen();
	}
	
	
	
	
	// Konstruktor Aufschnitt: long ID, String name, Calendar mhd, String typ, double inhalt, int scheiben
	public static void aufschnitteErstellen(ArrayList<Lebensmittel> bestandsliste){
		String[] wurst = new String[41];
		wurst[0] = "Bierschinken";
		wurst[1] = "Bierwurst";
		wurst[2] = "Blutwurst";
		wurst[3] = "Bockwurst";
		wurst[4] = "Bratenaufschnitt";
		wurst[5] = "Cervelatwurst";
		wurst[6] = "Chorizo";
		wurst[7] = "Corned Beef";
		wurst[8] = "Eselssalami";
		wurst[9] = "Geflügelbierschinken";
		wurst[10] = "Hirschsalami";
		wurst[11] = "Kassler-Aufschnitt";
		wurst[12] = "Kochschinken";
		wurst[13] = "Lachsschinken";
		wurst[14] = "Leberkäse";
		wurst[15] = "Mortadella";
		wurst[16] = "Nusschinken";
		wurst[17] = "Paprika-Lyoner";
		wurst[18] = "Paprika-Salami";
		wurst[19] = "Parmaschinken";
		wurst[20] = "Pfeffersalami";
		wurst[21] = "Pferdesalami";
		wurst[22] = "Pizza-Wurst";
		wurst[23] = "Plockwurst";
		wurst[24] = "Praga-Schinken";
		wurst[25] = "Puten-Lyoner";
		wurst[26] = "Putensalami";
		wurst[27] = "Putenschinken";
		wurst[28] = "Räucherspeck";
		wurst[29] = "Rinderschinken";
		wurst[30] = "Rindssalami";
		wurst[31] = "Rindswurst";
		wurst[32] = "Salami";
		wurst[33] = "Schinkenwurst";
		wurst[34] = "Schweineschinken, geräuchert";
		wurst[35] = "Seranoschinken";
		wurst[36] = "Speck";
		wurst[37] = "Sucuk";
		wurst[38] = "Ungarische Salami";
		wurst[39] = "Wildschwein-Salami";
		wurst[40] = "Wildschwein-Schinken";
		
		for(int i = 0; i<wurst.length; i++){
			bestandsliste.add(new Aufschnitt(wurst[i], fiktivesMHD, wurstTyp, 200, 10));		
		}
		
		
		
		String[] käse = new String[50];
		
		käse[0] = "Gouda";
        käse[1] = "Maasdamer";
        käse[2] = "Tilsiter";
        käse[3] = "Schafskäse";
        käse[4] = "Mozzarella";
        käse[5] = "Harzer_Käse";
        käse[6] = "Parmesan";
        käse[7] = "Cheddar";
        käse[8] = "Bergkäse";
        käse[9] = "Camenbert";
        käse[10] = "Gorgonzola";
        käse[11] = "Edamer";
        käse[12] = "Ziegenkäse";
        käse[13] = "Appenzeller";
        käse[14] = "Etorki";
        käse[15] = "Fol_Epi";
        käse[16] = "Leerdamer";
        käse[17] = "Emmentaler";
        käse[18] = "Gruyère";
        käse[19] = "Comté";
        käse[20] = "Pecorino";
        käse[21] = "Provolone";
        käse[22] = "Roquefort";
        käse[23] = "Butterkäse";
        käse[24] = "Saint_Albray_Klosterkäse";
        käse[25] = "Esrom";
        käse[26] = "Tomme_de_Savoie";
        käse[27] = "Chavroux";
        käse[28] = "Brie";
        käse[29] = "Géramont";
        käse[30] = "Limburger";
        käse[31] = "Chaumes";
        käse[32] = "Romadur";
        käse[33] = "Old_Amsterdam";
        käse[34] = "Saint Agur";
        käse[35] = "Bavaria blu";
        käse[36] = "Klützer Gold";
        käse[37] = "Obatzda";
        käse[38] = "Beaufort";
        käse[39] = "Morbier";
        käse[40] = "Scamorza";
        käse[41] = "Dubliner";
        käse[42] = "Orla";
        käse[43] = "Stelvio";
        käse[44] = "Langres";
        käse[45] = "Allgäuer_Bergkäse";
        käse[46] = "Bördespeck";
        käse[47] = "Nieheimer_Käse";
        käse[48] = "Weißlacker";
        käse[49] = "Feta";
        
        for(int i = 0; i<käse.length; i++){
               bestandsliste.add(new Aufschnitt(käse[i], fiktivesMHD, käseTyp, 200, 10));          
        }
        

		
	}
	
	private static void aufstricheErstellen(ArrayList<Lebensmittel> bestandsliste){
		String[] schmierwurst = new String[8];
        schmierwurst[0] = "Teewurst-Fein";
        schmierwurst[1] = "Teewurst-Grob";
        schmierwurst[2] = "Leberwurst-Fein";
        schmierwurst[3] = "Leberwurst-Grob";
        schmierwurst[4] = "Leberwurst-Kräuter";
        schmierwurst[5] = "Mettwurst";
        schmierwurst[6] = "Zwiebelmettwurst";
        schmierwurst[7] = "Hackepeter";
        
        for(int i = 0; i<schmierwurst.length; i++){
               bestandsliste.add(new Aufstrich(schmierwurst[i], fiktivesMHD, wurstTyp, 200.0));             
        }
        
        
        String[] schmierkäse = new String[11];
        schmierkäse[0] = "Frischkäse-Kräuter";
        schmierkäse[1] = "Frischkäse-Knoblauch";
        schmierkäse[2] = "Frischkäse-Chili";
        schmierkäse[3] = "Frischkäse-Meerrettich";
        schmierkäse[4] = "Frischkäse-Lachs";
        schmierkäse[5] = "Frischkäse-Sahne";
        schmierkäse[6] = "Frischkäse-Natur";
        schmierkäse[7] = "Ricotta";
        schmierkäse[8] = "Mascarpone";
        schmierkäse[9] = "Frischkäse-Tomate";
        schmierkäse[10] = "Queso_de_Burgos";
        
        for(int i = 0; i<schmierkäse.length; i++){
               bestandsliste.add(new Aufstrich(schmierkäse[i], fiktivesMHD, käseTyp, 200.0));             
        }
       
        
        String[] marmelade = new String[10];
        marmelade[0] = "Erdbeermarmelade";
        marmelade[1] = "Himbeermarmelade";
        marmelade[2] = "Beerenmixmarmelade";
        marmelade[3] = "Pflaumenmus";
        marmelade[4] = "Quittengelee";
        marmelade[5] = "Heidelbeermarmelade";
        marmelade[6] = "Kirschmarmelade";
        marmelade[7] = "Mangomarmelade";
        marmelade[8] = "Holunderblütengelee";
        marmelade[9] = "Apfelgelee";
        
        for(int i = 0; i<marmelade.length; i++){
               bestandsliste.add(new Aufstrich(marmelade[i], fiktivesMHD, "pflanzlich", 200));             
        }
        
        

        
        String[] weitereAufstriche = new String[5];
        weitereAufstriche[0] = "Nuss-Nougat-Creme";
        weitereAufstriche[1] = "Butter";
        weitereAufstriche[2] = "Pesto-Basilikum";
        weitereAufstriche[3] = "Pesto-Tomate";
        weitereAufstriche[4] = "Pesto-Rucola Käse";
        
        for(int i = 0; i<weitereAufstriche.length; i++){
            bestandsliste.add(new Aufstrich(weitereAufstriche[i], fiktivesMHD, milchprodukt, 200));             
	     }
	    
        
        
        String[] pflanzlicheAufstriche = new String[8];
        pflanzlicheAufstriche[0] = "Humus";
        pflanzlicheAufstriche[1] = "Nuss-Nougat-Creme";
        pflanzlicheAufstriche[4] ="Hummus-Natur";
        pflanzlicheAufstriche[2] ="Hummus-Kräuter";
        pflanzlicheAufstriche[3] ="Hummus-Pikant";
        pflanzlicheAufstriche[5] ="Guacamole";
        pflanzlicheAufstriche[6] ="Pesto-Basilikum";
        pflanzlicheAufstriche[7] ="Pesto-Rucola Käse";
        
        for(int i = 0; i<pflanzlicheAufstriche.length; i++){
            bestandsliste.add(new Aufstrich(pflanzlicheAufstriche[i], fiktivesMHD, "pflanzlich", 200));             
	     }
	     

	}
    
	private static void dessertsErstellen(ArrayList<Lebensmittel> bestandsliste){
        String[] dessert = new String[38];
        dessert[0] = "Schokopudding";
        dessert[1] = "Vanillepudding";
        dessert[2] = "Cappucchinopudding";
        dessert[3] = "Joghurt-Pfirsich Maracuja";
        dessert[4] = "Joghurt-Apfel";
        dessert[5] = "Joghurt-Aprikose";
        dessert[6] = "Joghurt-Ananas";
        dessert[7] = "Joghurt-Erdbeer";
        dessert[8] = "Joghurt-Russischer Zupfkuchen";
        dessert[9] = "Joghurt-Amarena-Kirsch";
        dessert[10] = "Joghurt-Schoko";
        dessert[11] = "Joghurt-Waldbeeren";
        dessert[12] = "Joghurt-Aprikose";
        dessert[13] = "Joghurt-Pfirsich";
        dessert[14] = "Joghurt-Apfel-Haferflocken";
        dessert[15] = "Joghurt-Mango-Orange";
        dessert[16] = "Joghurt-Haselnuss";
        dessert[17] = "Joghurt-Orange";
        dessert[18] = "Joghurt-Stracciatella";
        dessert[19] = "Joghurt-Rhabarber-Vanille";
        dessert[20] = "Joghurt-Vanille";
        dessert[21] = "Joghurt-Banane";
        dessert[22] = "Joghurt-Himbeer";
        dessert[23] = "Joghurt-Birne";
        dessert[24] = "Joghurt-Kirsch";
        dessert[25] = "Joghurt-Limette";
        dessert[26] = "Joghurt-Heidelbeer";
        dessert[27] = "Joghurt-Zitrone";
        dessert[28] = "Joghurt-Obstsalat";
        dessert[29] = "Joghurt-Kirsch-Banane";
        dessert[30] = "Joghurt-Kiwi-Stachelbeere";
        dessert[31] = "Joghurt-Apfel-Birne";
        dessert[32] = "Schleckerschnee-Zitrone";
        dessert[33] = "Schleckerschnee-Himbeere";
        dessert[34] = "Mousse_au_chocolat-Vollmilch";
        dessert[35] = "Mousse_au_chocolat-Zartbitter";
        dessert[36] = "Mousse_au_chocolat-Weiße Schokolade";
        dessert[37] = "Creme_brulee";
        
        for(int i = 0; i<dessert.length; i++){
               bestandsliste.add(new Desserts(dessert[i], fiktivesMHD, milchprodukt, 200));             
        }
       
        
        /*Vegane Desserts*/
        for(int i = 0; i<dessert.length; i++){
            bestandsliste.add(new Aufschnitt(dessert[i], fiktivesMHD, pflanzlich, 200, 10));             
	     }
	     
	     

	}
	
	private static void eiErstellen(ArrayList<Lebensmittel> bestandsliste){
		String[] ei = new String[5];
        ei[0] = "Hühnereier";
        ei[1] = "Straußenei";
        ei[2] = "Wachtelei";
        ei[3] = "Gänseei";
        ei[4] = "Fasan-/Rebhuhnei";

		
		for(int i = 0; i<ei.length; i++){
            bestandsliste.add(new Ei(ei[i], fiktivesMHD, tierprodukt, 10));             
	     }
		
	}
	
	private static void fischErstellen(ArrayList<Lebensmittel> bestandsliste){
		String[] fisch = new String[20];
        fisch[0] = "Lachs";
        fisch[1] = "Heilbutt";
        fisch[2] = "Forelle";
        fisch[3] = "Hering";
        fisch[4] = "Seelachs";
        fisch[5] = "Hecht";
        fisch[6] = "Dorsch";
        fisch[7] = "Makrele";
        fisch[8] = "Zander";
        fisch[9] = "Sardine";
        fisch[10] = "Thunfisch";
        fisch[11] = "Pangasius";
        fisch[12] = "Seelachs";
        fisch[13] = "Scholle";
        fisch[14] = "Rotbarsch";
        fisch[15] = "Karpfen";
        fisch[16] = "Sprotten";
        fisch[17] = "Garnelen";
        fisch[18] = "Muscheln";
        fisch[19] = "Seeteufel";
        
        for(int i = 0; i<fisch.length; i++){
            bestandsliste.add(new Fisch(fisch[i], fiktivesMHD, "Fisch", 100));             
	     }
		
	}
	
	private static void flüssigkeitErstellen(ArrayList<Lebensmittel> bestandsliste){
		String[] flüssigkeit = new String[39];
        flüssigkeit[0] = "Weißbier";
        flüssigkeit[1] = "Cola";
        flüssigkeit[2] = "Fanta";
        flüssigkeit[3] = "Sprite";
        flüssigkeit[4] = "Apfelschorle";
        flüssigkeit[5] = "Mate";
        flüssigkeit[6] = "Sprudelwasser";
        flüssigkeit[7] = "Wasser";
        flüssigkeit[8] = "Apfelsaft";
        flüssigkeit[9] = "Orangensaft";
        flüssigkeit[10] = "ACE-Saft";
        flüssigkeit[11] = "Multivitaminsaft";
        flüssigkeit[12] = "Johannisbeersaft";
        flüssigkeit[13] = "Kiba";
        flüssigkeit[14] = "Bananensaft";
        flüssigkeit[15] = "Pfirsichsaft";
        flüssigkeit[16] = "Kirschsaft";
        flüssigkeit[17] = "Maracujasaft";
        flüssigkeit[18] = "Tomatensaft";
        flüssigkeit[19] = "Birnensaft";
        flüssigkeit[20] = "Himbeersaft";
        flüssigkeit[21] = "Brombeersaft";
        flüssigkeit[22] = "Heidelbeersaft";
        flüssigkeit[23] = "Schwarzbier";
        flüssigkeit[24] = "Granatapfelsaft";
        flüssigkeit[25] = "Erdbeersaft";
        flüssigkeit[26] = "Ananassaft";
        flüssigkeit[27] = "Grapefruitsaft";
        flüssigkeit[28] = "Cranberrysaft";
        flüssigkeit[29] = "Rotwein";
        flüssigkeit[30] = "Weißwein";
        flüssigkeit[31] = "Sekt";
        flüssigkeit[32] = "Prosecco";
        flüssigkeit[33] = "Champagner";
        flüssigkeit[34] = "Cider";
        flüssigkeit[35] = "Ketchup";
        flüssigkeit[36] = "Sweetchilisoße";
        flüssigkeit[37] = "Knoblauchsoße";
        flüssigkeit[38] = "Barbecuesoße";
        
        for(int i = 0; i<flüssigkeit.length; i++){
            bestandsliste.add(new Flüssigkeit(flüssigkeit[i], fiktivesMHD, "Flüssigkeit", 100));             
	     }
		

	}
	
	private static void gemüseErstellen(ArrayList<Lebensmittel> bestandsliste){
		String[] gemüse = new String[43];
        gemüse[0] = "Avocado";
        gemüse[1] = "Eisbergsalat";
        gemüse[2] = "Feldsalat";
        gemüse[3] = "Chinakohl";
        gemüse[4] = "Chicorée";
        gemüse[5] = "Rucola";
        gemüse[6] = "Gurke";
        gemüse[7] = "Tomate";
        gemüse[8] = "Paprika";
        gemüse[9] = "Zucchini";
        gemüse[10] = "Spinat";
        gemüse[11] = "Zuckerschoten";
        gemüse[12] = "Erbsen";
        gemüse[13] = "Zwiebeln";
        gemüse[14] = "Kartoffeln";
        gemüse[15] = "Knoblauch";
        gemüse[16] = "Brokkoli";
        gemüse[17] = "Spargel-grün";
        gemüse[18] = "Spargel-weiß";
        gemüse[19] = "Schmorgurken";
        gemüse[20] = "Radieschen";
        gemüse[21] = "Peperoni";
        gemüse[22] = "Oliven";
        gemüse[23] = "Frühlingszwiebeln";
        gemüse[24] = "Mangold";
        gemüse[25] = "Schalotten";
        gemüse[26] = "Staudensellerie";
        gemüse[27] = "Fenchel";
        gemüse[28] = "Rettich";
        gemüse[29] = "Süßkartoffeln";
        gemüse[30] = "Möhre";
        gemüse[31] = "Aubergine";
        gemüse[32] = "Kresse";
        gemüse[33] = "Kohlrabi";
        gemüse[34] = "Blumenkohl";
        gemüse[35] = "Rotkohl";
        gemüse[36] = "Rosenkohl";
        gemüse[37] = "Artischocke";
        gemüse[38] = "Romanesco";
        gemüse[39] = "Rote Bete";
        gemüse[40] = "Porree";
        gemüse[41] = "Bambussprossen";
        gemüse[42] = "Weißkohl";
        
        for(int i = 0; i<gemüse.length; i++){
            bestandsliste.add(new Gemüse(gemüse[i], fiktivesMHD, "Gemüse", 100));             
	     }
		
	}
	
	private static void lebensmittel_Am_Stück_Erstellen(ArrayList<Lebensmittel> bestandsliste){
        String[] lebensmittel_Am_Stück = new String[36];
        lebensmittel_Am_Stück[0] = "Wiener_Würstchen";
        lebensmittel_Am_Stück[1] = "Käse-Wiener";
        lebensmittel_Am_Stück[2] = "Kassler";
        lebensmittel_Am_Stück[3] = "Schweinelende";
        lebensmittel_Am_Stück[4] = "Krakauer";
        lebensmittel_Am_Stück[5] = "Krainer-Wurst";
        lebensmittel_Am_Stück[6] = "Lammbratwurst";
        lebensmittel_Am_Stück[7] = "Lammschinken";
        lebensmittel_Am_Stück[8] = "Mettenden";
        lebensmittel_Am_Stück[9] = "Minisalami";
        lebensmittel_Am_Stück[10] = "Nürnberger_Bratwürstchen";
        lebensmittel_Am_Stück[11] = "Berner Würstchen";
        lebensmittel_Am_Stück[12] = "Nusschinken";
        lebensmittel_Am_Stück[13] = "Salami";
        lebensmittel_Am_Stück[14] = "Pfälzer_Saumagen";
        lebensmittel_Am_Stück[15] = "Pfefferbeißer";
        lebensmittel_Am_Stück[16] = "Pfeffersalami";
        lebensmittel_Am_Stück[17] = "Pferdebockwurst";
        lebensmittel_Am_Stück[18] = "Pferdebratwurst";
        lebensmittel_Am_Stück[19] = "Pferdesalami";
        lebensmittel_Am_Stück[20] = "Prager_Schinken";
        lebensmittel_Am_Stück[21] = "Presswurst";
        lebensmittel_Am_Stück[22] = "Rauchfleisch";
        lebensmittel_Am_Stück[23] = "Thüringer Bratwurst";
        lebensmittel_Am_Stück[24] = "Rinderschinken";
        lebensmittel_Am_Stück[25] = "Sauerfleisch";
        lebensmittel_Am_Stück[26] = "Schweineschinken";
        lebensmittel_Am_Stück[27] = "Schinkenspieß";
        lebensmittel_Am_Stück[28] = "Stockwurst";
        lebensmittel_Am_Stück[29] = "Weckewurst";
        lebensmittel_Am_Stück[30] = "Weinwurst";
        lebensmittel_Am_Stück[31] = "Weißwurst";
        lebensmittel_Am_Stück[32] = "Westfälischer Knochenschinken";
        lebensmittel_Am_Stück[33] = "Wildschweinsalami";
        lebensmittel_Am_Stück[34] = "Wollwurst";
        lebensmittel_Am_Stück[35] = "Zungenwurst";

        for(int i = 0; i<lebensmittel_Am_Stück.length; i++){
            bestandsliste.add(new Lebensmittel_am_Stück(lebensmittel_Am_Stück[i], fiktivesMHD, wurstTyp, 100));             
	     }
		
	}
	
	private static void obstErstellen(ArrayList<Lebensmittel> bestandsliste){
		
            String[] obst = new String[50];
            obst[0] = "Ananas";
            obst[1] = "Apfel";
            obst[2] = "Acerola";
            obst[3] = "Aprikose";
            obst[4] = "Birne";
            obst[5] = "Banane";
            obst[6] = "Bergamotte";
            obst[7] = "Brombeere";
            obst[8] = "Boysenbeere";
            obst[9] = "Cherimoya";
            obst[10] = "Clementine";
            obst[11] = "Datteln";
            obst[12] = "Drachenfrucht";
            obst[13] = "Erdbeere";
            obst[14] = "Feige";
            obst[15] = "Grapefruit";
            obst[16] = "Granatapfel";
            obst[17] = "Guave";
            obst[18] = "Weiße_Trauben";
            obst[19] = "Heidelbeere";
            obst[20] = "Himbeere";
            obst[21] = "Zwetschge";
            obst[22] = "Honigmelone";
            obst[23] = "Kaki";
            obst[24] = "Kaktusfeige";
            obst[25] = "Kirsche";
            obst[26] = "Kiwi";
            obst[27] = "Kumquat";
            obst[28] = "Litschi";
            obst[29] = "Limette";
            obst[30] = "Mandarine";
            obst[31] = "Mango";
            obst[32] = "Maracuja";
            obst[33] = "Mirabelle";
            obst[34] = "Wassermelone";
            obst[35] = "Nektarine";
            obst[36] = "Orange";
            obst[37] = "Papaya";
            obst[38] = "Pfirsich";
            obst[39] = "Pflaume";
            obst[40] = "Physalis";
            obst[41] = "Pomelo";
            obst[42] = "Zitrone";
            obst[43] = "Quitte";
            obst[44] = "Rote_Trauben";
            obst[45] = "Johannisbeeren";
            obst[46] = "Sanddorn";
            obst[47] = "Stachelbeere";
            obst[48] = "Sternfrucht";
            obst[49] = "Sharonfrucht";
            
            for(int i = 0; i<obst.length; i++){
                bestandsliste.add(new Obst(obst[i], fiktivesMHD, "Obst", 100));             
    	     }
    		

	}
	
	private static void pilzeErstellen(ArrayList<Lebensmittel> bestandsliste){
        String[] pilze = new String[18];
        pilze[0] = "Maronen";
        pilze[1] = "Birkenpilz";
        pilze[2] = "Braunkappe";
        pilze[3] = "Breitblättrige Glucke";
        pilze[4] = "Schwarzhütiger Steinpilz";
        pilze[5] = "Butterpilz";
        pilze[6] = "Rotkappe";
        pilze[7] = "Steinpilz";
        pilze[8] = "Krause_Glucke";
        pilze[9] = "Kräuterseitling";
        pilze[10] = "Champignon-Braun";
        pilze[11] = "Champignon-Weiß";
        pilze[12] = "Schirmpilz / Parasolpilz";
        pilze[13] = "Pfifferlinge";
        pilze[14] = "Shiitake";
        pilze[15] = "Trüffel";
        pilze[16] = "Austernpilze";
        pilze[17] = "Portobello";
        
        for(int i = 0; i<pilze.length; i++){
            bestandsliste.add(new Pilze(pilze[i], fiktivesMHD, pilz, 100));             
	     }

	}
	
	private void standardErstellen(){
		try{
			FileOutputStream fileOut = new FileOutputStream("initBestandsliste.ser");
			ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeObject(this.leereBestandsliste);
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
	 * Debug
	 * */
	public static void main(String[] args) {
		ArrayList<Lebensmittel> bestand= new ArrayList<Lebensmittel>();
		Standard_Bestandsliste_erstellen bla = new Standard_Bestandsliste_erstellen(bestand);
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 30);
		cal.set(Calendar.MONTH, Calendar.DECEMBER);
		cal.set(Calendar.YEAR, 2020);
		Standard_Bestandsliste_erstellen.fiktivesMHD = cal;
		
		
		bla.ausführen();
		
		bla.standardErstellen();

	}

}
