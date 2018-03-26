package gui.lebensmittelverwaltung;



import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import geräte.Kühlschrank;
import lebensmittel.Lebensmittel;


import java.awt.GridLayout;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;

import javax.swing.DefaultListModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import javax.swing.JLabel;

public class Frame_Bestandsverwaltung extends JFrame {

	/**
	 * Klasse für die Bestandsverwaltung
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Kühlschrank ausgewählterKühlschrank;
	private JTextField txtSuchen;

	
	

	/**
	 * Create the frame.
	 */
	public Frame_Bestandsverwaltung(Kühlschrank kühlschrank) {
		
		setTitle(kühlschrank.getName() + " - Bestandsverwaltung");
		this.ausgewählterKühlschrank = new Kühlschrank();
		
		this.ausgewählterKühlschrank.setName(kühlschrank.getName());
		this.ausgewählterKühlschrank.setStandort(kühlschrank.getStandort());
		this.ausgewählterKühlschrank.setBestände(kühlschrank.getBestände());
		this.ausgewählterKühlschrank.setEinkaufen(kühlschrank.getEinkaufen());
		this.ausgewählterKühlschrank.setEntnommen(kühlschrank.getEntnommen());
		this.ausgewählterKühlschrank.setFächer(kühlschrank.getFächer());
		this.ausgewählterKühlschrank.setGefrierfachVorhanden(kühlschrank.isGefrierfachVorhanden());
		this.ausgewählterKühlschrank.setGröße(kühlschrank.getGröße());
		this.ausgewählterKühlschrank.setTemperatur(kühlschrank.getTemperatur());
		setBounds(100, 100, 420, 550);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230,230,250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane);
		
		JList<String> list = new JList<String>();
		
		scrollPane.setViewportView(list);
		
		list.setModel(createModel());
		
		
		
		list.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 12));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(230,230,250));
		contentPane.add(panel);
		panel.setLayout(new GridLayout(0, 2, 10, 0));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(230,230,250));
		panel.add(panel_2);
		
		txtSuchen = new JTextField();
		txtSuchen.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				txtSuchen.selectAll();
			}
		});
		txtSuchen.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					if(txtSuchen.getText().equals("") || txtSuchen.getText().equals("Suchen...")){
						ausgewählterKühlschrank.getBestände().beständeLaden(ausgewählterKühlschrank.getName() + "Bestandsliste.ser");
						list.setModel(createModel());
					}
					else{
						String suchbegriff = txtSuchen.getText();
						String[] ergebnisse = lebensmittelSuchen(suchbegriff);
						
						list.setModel(createModel(ergebnisse));
					}
				}
			}
		});
		panel_2.setLayout(new GridLayout(4, 1, 10, 10));
		txtSuchen.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 12));
		txtSuchen.setText("Suchen...");
		panel_2.add(txtSuchen);
		txtSuchen.setColumns(10);
		
		JButton btnLebensmittelHinzufgen = new JButton("Lebensmittel hinzuf\u00FCgen");
		btnLebensmittelHinzufgen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame neu = new Frame_LebensmittelHinzufügen(ausgewählterKühlschrank.getBestände(), ausgewählterKühlschrank.getName());
				
				neu.setVisible(true);
			}
		});
		btnLebensmittelHinzufgen.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 13));
		panel_2.add(btnLebensmittelHinzufgen);
		
		JButton btnLebensmittelinformationenAnzeigen = new JButton("<html><body>Lebensmittelinforma-<br>tionen anzeigen</body></html>");
		btnLebensmittelinformationenAnzeigen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String eingabe = wortAusschneiden(list.getSelectedValue());
				ListIterator<Lebensmittel> it = ausgewählterKühlschrank.getBestände().getBestände().listIterator();
				while(it.hasNext()){
					Lebensmittel prüfung = it.next();
					if(prüfung.getName().equals(eingabe)){
						JFrame neu = new Frame_Lebensmittelinformationen(prüfung);
						neu.setVisible(true);
					}
				}

			}
		});
		btnLebensmittelinformationenAnzeigen.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 12));
		panel_2.add(btnLebensmittelinformationenAnzeigen);
		
		JLabel label = new JLabel("");
		panel_2.add(label);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(230,230,250));
		panel.add(panel_3);
		
		JButton btnListeAktualisieren = new JButton("Liste aktualisieren");
		btnListeAktualisieren.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 12));
		btnListeAktualisieren.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtSuchen.getText().equals("") || txtSuchen.getText().equals("Suchen...")){
					ausgewählterKühlschrank.getBestände().beständeLaden(ausgewählterKühlschrank.getName() + "Bestandsliste.ser");
					list.setModel(createModel());
				}
				else{
					String suchbegriff = txtSuchen.getText();
					String[] ergebnisse = lebensmittelSuchen(suchbegriff);
					
					list.setModel(createModel(ergebnisse));
				}
			}
		});
		panel_3.setLayout(new GridLayout(4, 1, 10, 10));
		panel_3.add(btnListeAktualisieren);
		
		JButton btnLebensmittelEntnehmen = new JButton("<html><body>Markierte Lebensmittel <br>entnehmen</body></html>");
		btnLebensmittelEntnehmen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Übertragen der Lebensmittel auf die Entnahmeliste
				List<String> eingabeListe = list.getSelectedValuesList(); 
				String[] eingabe = new String[list.getSelectedIndices().length];
				Iterator <String> itList = eingabeListe.iterator();
				int i = 0;
				while(itList.hasNext()){
					eingabe[i] = wortAusschneiden(itList.next());
					i++;
				}
				ListIterator<Lebensmittel> it = ausgewählterKühlschrank.getBestände().getBestände().listIterator();
				i=0;
				while(it.hasNext()){
					Lebensmittel prüfung = it.next();
					if(i<eingabe.length && eingabe[i].equals(wortAusschneiden(prüfung.getName()))){
						ausgewählterKühlschrank.getEntnommen().lebensmittelHinzufügen(prüfung);
						i++;
					}
				}
				ausgewählterKühlschrank.getEntnommen().toLittleString(ausgewählterKühlschrank.getEntnommen().getEntnahmeliste());
				
				// Öffnen des Frames der Entnahmeliste
				JFrame neu = new Frame_Entnahmeliste(ausgewählterKühlschrank ,ausgewählterKühlschrank.getEntnommen());
				neu.setVisible(true);
				
			}
		});
		btnLebensmittelEntnehmen.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 12));
		panel_3.add(btnLebensmittelEntnehmen);
		
		JButton btnZurckZumHauptmen = new JButton("Zur\u00FCck zum Hauptmen\u00FC");
		btnZurckZumHauptmen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fensterSchließen();
			}
		});
		
		JButton btnLebensmittelAufEinkaufsliste = new JButton("<html><body>Lebensmittel auf <br>Einkaufsliste \u00FCbertragen</body></html>");
		btnLebensmittelAufEinkaufsliste.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String eingabe = list.getSelectedValue();
				String nameLebensmittel = wortAusschneiden(eingabe);
				Lebensmittel übergabe = lebensmittelErzeugen(kühlschrank.getBestände().getBestände(), nameLebensmittel).clone();
				übergabe.setAnzahl(1);
				kühlschrank.getEinkaufen().lebensmittelHinzufügen(übergabe);
				kühlschrank.getEinkaufen().einkäufeSpeichern(kühlschrank.getName()+"Einkaufsliste.ser");
			}
		});
		btnLebensmittelAufEinkaufsliste.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 12));
		panel_3.add(btnLebensmittelAufEinkaufsliste);
		btnZurckZumHauptmen.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 12));
		panel_3.add(btnZurckZumHauptmen);
	}
	
	private DefaultListModel<String> createModel(){
		DefaultListModel<String> model = new DefaultListModel<String>();
		String[] values = ausgewählterKühlschrank.getBestände().toSortedString();
		for(int i = 0; i<values.length;i++){
			model.addElement(values[i]);
		}
		return model;
	}
	
	private DefaultListModel<String> createModel(String[] einträge){
		DefaultListModel<String> model = new DefaultListModel<String>();
		String[] values = einträge;
		for(int i = 0; i<values.length;i++){
			model.addElement(values[i]);
		}
		return model;
	}
	
	private String[] lebensmittelSuchen(String suche){
		
		String[] vorhanden = this.ausgewählterKühlschrank.getBestände().toSortedString();
		String[] ausgabe = new String[vorhanden.length];
		int durchlauf = 0;
		int gefunden = 0;
		
		
		while(durchlauf < vorhanden.length && !(vorhanden[durchlauf]==null)){
			for (int i=0; i<=(vorhanden[durchlauf].length() - suche.length()); i++){
				if(suche.equals(vorhanden[durchlauf].substring(i, suche.length()+i))){
					ausgabe[gefunden] = vorhanden[durchlauf];
					gefunden++;
				}
			}
			durchlauf ++;
		}
		return ausgabe;
	}
	
	
	private void fensterSchließen(){
		this.setVisible(false);
	}
	
	private String wortAusschneiden(String eingabe){
		String zurück = "";
		int i = 0;
		while(i<eingabe.length()&&!(eingabe.charAt(i)==' ')){
			zurück = zurück + eingabe.charAt(i);
			i++;
		}
		return zurück;
	
	}
	
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
	

}
