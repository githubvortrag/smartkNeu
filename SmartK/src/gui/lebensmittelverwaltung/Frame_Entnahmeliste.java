package gui.lebensmittelverwaltung;



import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.border.LineBorder;

import geräte.Kühlschrank;
import lebensmittel.Lebensmittel;
import listen.Entnahmeliste;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Frame_Entnahmeliste extends JFrame {

	/**
	 * Klasse für den Lebensmittelverteiler
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Entnahmeliste entnahmeliste;
	

	/**
	 * Create the frame.
	 */
	public Frame_Entnahmeliste(Kühlschrank ausgewählterKühlschrank ,Entnahmeliste übergebeneListe) {
		setTitle(ausgewählterKühlschrank.getName() + " - Lebensmittelverteiler");
		
		this.entnahmeliste = new Entnahmeliste();
		this.entnahmeliste.setAnzahlEinkaufsliste(übergebeneListe.getAnzahlEinkaufsliste());
		this.entnahmeliste.setAnzahlEntnommen(übergebeneListe.getAnzahlEntnommen());
		this.entnahmeliste.setAnzahlLeer(übergebeneListe.getAnzahlLeer());
		this.entnahmeliste.setEinkaufsliste(übergebeneListe.getEinkaufsliste());
		this.entnahmeliste.setEntnahmeliste(übergebeneListe.getEntnahmeliste());
		this.entnahmeliste.setLeereLebensmittel(übergebeneListe.getLeereLebensmittel());
		
		
		setBounds(100, 100, 420, 550);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230,230,250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 2, 5, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(230,230,250));
		contentPane.add(panel);
		panel.setLayout(new GridLayout(3, 0, 0, 5));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(new Color(230,230,250));
		scrollPane.setBorder(new LineBorder(new Color(64, 64, 64)));
		panel.add(scrollPane);
		
		JList<String> listZurück = new JList<String>();
		scrollPane.setViewportView(listZurück);
		listZurück.setModel(createModel(entnahmeliste.getEntnahmeliste()));
		
		JLabel lblEntnommen = new JLabel("Zur\u00FCcklegen:");
		lblEntnommen.setBackground(new Color(230,230,250));
		scrollPane.setColumnHeaderView(lblEntnommen);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBorder(new LineBorder(new Color(64, 64, 64)));
		scrollPane_2.setBackground(new Color(230,230,250));
		panel.add(scrollPane_2);
		
		JLabel lblAusBestandEntfernen = new JLabel("Aus Bestand entfernen:");
		scrollPane_2.setColumnHeaderView(lblAusBestandEntfernen);
		
		JList<String> listLeer = new JList<String>();
		scrollPane_2.setViewportView(listLeer);
		listLeer.setModel(createModel(entnahmeliste.getLeereLebensmittel()));
		
		JButton btnAllesZurcklegen = new JButton("Alles zur\u00FCcklegen");
		btnAllesZurcklegen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				entnahmeliste.listenLeeren();
				fensterSchließen();
			}
		});
		panel.add(btnAllesZurcklegen);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(230,230,250));
		contentPane.add(panel_1);
		panel_1.setLayout(new GridLayout(3, 1, 0, 5));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBorder(new LineBorder(new Color(64, 64, 64)));
		panel_1.add(scrollPane_1);
		
		JList<String> listEinkauf = new JList<String>();
		scrollPane_1.setViewportView(listEinkauf);
		listEinkauf.setModel(createModel(entnahmeliste.getEinkaufsliste()));
		
		JLabel lblAufEinkaufsliste = new JLabel("Auf Einkaufsliste:");
		scrollPane_1.setColumnHeaderView(lblAufEinkaufsliste);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(230,230,250));
		panel_1.add(panel_2);
		panel_2.setLayout(new GridLayout(3, 0, 0, 10));
		
		JButton btnZurücklegen = new JButton("Lebensmittel zur\u00FCcklegen");
		btnZurücklegen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Überprüfung welches Lebensmittel auf welcher Liste ausgewählt wurde
				String markListeZurück = (String) listZurück.getSelectedValue();
				String markListeLeer= (String) listLeer.getSelectedValue();
				String markListeEinkauf = (String) listEinkauf.getSelectedValue();
				ArrayList <Lebensmittel> quelle = new ArrayList<Lebensmittel>();
				ArrayList<Lebensmittel> ziel = entnahmeliste.getEntnahmeliste();
				String lebensmittel = "";
				if(markListeZurück==null){
					if(markListeLeer == null){
						quelle = entnahmeliste.getEinkaufsliste();
						lebensmittel = wortAusschneiden(markListeEinkauf);
					}
					else{
						quelle = entnahmeliste.getLeereLebensmittel();
						lebensmittel = wortAusschneiden(markListeLeer);
					}
				}
				else{
					quelle = entnahmeliste.getEntnahmeliste();
					lebensmittel = wortAusschneiden(markListeZurück);
				}
				// Verschieben des Lebhensmittels von einer auf die andere Liste
				entnahmeliste.lebensmittelVerschieben(quelle, ziel, lebensmittel);
				
				// Aktualisieren der Listen im JFrame
				listZurück.setModel(createModel(entnahmeliste.getEntnahmeliste()));
				listLeer.setModel(createModel(entnahmeliste.getLeereLebensmittel()));
				listEinkauf.setModel(createModel(entnahmeliste.getEinkaufsliste()));
				
			}
		});
		panel_2.add(btnZurücklegen);
		
		JButton btnAufDieEinkaufsliste = new JButton("Auf die Einkaufsliste");
		btnAufDieEinkaufsliste.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Überprüfung welches Lebensmittel auf welcher Liste ausgewählt wurde
				String markListeZurück = (String) listZurück.getSelectedValue();
				String markListeLeer= (String) listLeer.getSelectedValue();
				String markListeEinkauf = (String) listEinkauf.getSelectedValue();
				ArrayList <Lebensmittel> quelle = new ArrayList<Lebensmittel>();
				ArrayList<Lebensmittel> ziel = entnahmeliste.getEinkaufsliste();
				String lebensmittel = "";
				if(markListeZurück == null){
					if(markListeLeer == null){
						quelle = entnahmeliste.getEinkaufsliste();
						lebensmittel = wortAusschneiden(markListeEinkauf);
					}
					else{
						quelle = entnahmeliste.getLeereLebensmittel();
						lebensmittel = wortAusschneiden(markListeLeer);
					}
				}
				else{
					quelle = entnahmeliste.getEntnahmeliste();
					lebensmittel = wortAusschneiden(markListeZurück);
				}
				// Verschieben des Lebhensmittels von einer auf die andere Liste
				entnahmeliste.lebensmittelVerschieben(quelle, ziel, lebensmittel);
				
				// Aktualisieren der Listen im JFrame
				listZurück.setModel(createModel(entnahmeliste.getEntnahmeliste()));
				listLeer.setModel(createModel(entnahmeliste.getLeereLebensmittel()));
				listEinkauf.setModel(createModel(entnahmeliste.getEinkaufsliste()));
			}
		});
		panel_2.add(btnAufDieEinkaufsliste);
		
		JButton btnAusBestandEntfernen = new JButton("Aus Bestand entfernen");
		btnAusBestandEntfernen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Überprüfung welches Lebensmittel auf welcher Liste ausgewählt wurde
				String markListeZurück = (String) listZurück.getSelectedValue();
				String markListeLeer= (String) listLeer.getSelectedValue();
				String markListeEinkauf = (String) listEinkauf.getSelectedValue();
				ArrayList <Lebensmittel> quelle = new ArrayList<Lebensmittel>();
				ArrayList<Lebensmittel> ziel = entnahmeliste.getLeereLebensmittel();
				String lebensmittel = "";
				if(markListeZurück == null){
					if(markListeLeer == null){
						quelle = entnahmeliste.getEinkaufsliste();
						lebensmittel = wortAusschneiden(markListeEinkauf);
					}
					else{
						quelle = entnahmeliste.getLeereLebensmittel();
						lebensmittel = wortAusschneiden(markListeLeer);
					}
				}
				else{
					quelle = entnahmeliste.getEntnahmeliste();
					lebensmittel = wortAusschneiden(markListeZurück);
				}
				// Verschieben des Lebhensmittels von einer auf die andere Liste
				entnahmeliste.lebensmittelVerschieben(quelle, ziel, lebensmittel);
				
				// Aktualisieren der Listen im JFrame
				listZurück.setModel(createModel(entnahmeliste.getEntnahmeliste()));
				listLeer.setModel(createModel(entnahmeliste.getLeereLebensmittel()));
				listEinkauf.setModel(createModel(entnahmeliste.getEinkaufsliste()));
			}
		});
		panel_2.add(btnAusBestandEntfernen);
		
		JButton btnnderungenSpeichern = new JButton("\u00C4nderungen speichern ");
		btnnderungenSpeichern.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				entnahmeliste.einkaufslisteÜbertragen(entnahmeliste.getEinkaufsliste(), ausgewählterKühlschrank.getEinkaufen().getEinkaufsliste());
				ausgewählterKühlschrank.getEinkaufen().einkäufeSpeichern(ausgewählterKühlschrank.getName()+"Einkaufsliste.ser");
				entnahmeliste.beständeAktualisieren(entnahmeliste.getEntnahmeliste(),entnahmeliste.getLeereLebensmittel(),entnahmeliste.getEinkaufsliste() ,ausgewählterKühlschrank.getBestände().getBestände());
				ausgewählterKühlschrank.getBestände().beständeSpeichern(ausgewählterKühlschrank.getName()+"Bestandsliste.ser");
				entnahmeliste.listenLeeren();
				fensterSchließen();
			}
		});
		panel_1.add(btnnderungenSpeichern);
	}
	
	private DefaultListModel<String> createModel(ArrayList<Lebensmittel> arraylist){
		DefaultListModel<String> model = new DefaultListModel<String>();
		String[] values = this.entnahmeliste.toSortedString(arraylist);
		for(int i = 0; i<values.length;i++){
			model.addElement(values[i]);
		}
		return model;
	}
	
	private String wortAusschneiden(String eingabe){
		String zurück = "";
		int i = 0;
		while(!(eingabe.charAt(i)==' ')){
			zurück = zurück + eingabe.charAt(i);
			i++;
		}
		return zurück;
	
	}
	
	private void fensterSchließen(){
		this.setVisible(false);
	}

}
