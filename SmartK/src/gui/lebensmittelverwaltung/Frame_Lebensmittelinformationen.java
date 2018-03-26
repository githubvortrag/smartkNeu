package gui.lebensmittelverwaltung;



import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import daten.Datumsangaben;
import lebensmittel.Lebensmittel;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.ListIterator;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Frame_Lebensmittelinformationen extends JFrame {

	/**
	 * Fenster, um Lebensmittelinformationen anzeigen zu lassen
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Lebensmittel lebensmittel;


	/**
	 * Create the frame.
	 */
	public Frame_Lebensmittelinformationen(Lebensmittel lebensmittel) {
		setTitle(lebensmittel.getName()+" - Informationen");
		this.lebensmittel = lebensmittel;
		setBounds(100, 100, 420, 550);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230,230,250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(230,230,250));
		contentPane.add(panel);
		panel.setLayout(new GridLayout(1, 2, 10, 0));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(230,230,250));
		panel.add(panel_1);
		panel_1.setLayout(new GridLayout(6, 0, 0, 10));
		
		JLabel lblName = new JLabel("Name:");
		panel_1.add(lblName);
		
		JLabel lblTyp = new JLabel("Typ:");
		panel_1.add(lblTyp);
		
		JLabel lblAnzahl = new JLabel("Anzahl:");
		panel_1.add(lblAnzahl);
		
		JLabel lblMidesthaltbarkeitsdaten = new JLabel("Midesthaltbarkeitsdaten:");
		panel_1.add(lblMidesthaltbarkeitsdaten);
		
		JLabel lblLagerungsempfehlung = new JLabel("Lagerungsempfehlung:");
		panel_1.add(lblLagerungsempfehlung);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(230,230,250));
		panel.add(panel_2);
		panel_2.setLayout(new GridLayout(6, 0, 0, 10));
		
		JLabel lblBezeichnung = new JLabel(lebensmittel.getName());
		panel_2.add(lblBezeichnung);
		
		JLabel lblTypangabe = new JLabel(lebensmittel.getTyp());
		panel_2.add(lblTypangabe);
		
		String anzahl = Integer.toString(lebensmittel.getAnzahl()); 
		JLabel lblAnzahlangabe = new JLabel(anzahl);
		panel_2.add(lblAnzahlangabe);
		
		JScrollPane scrollPane = new JScrollPane();
		panel_2.add(scrollPane);
		
		JList<String> list = new JList<String>();
		list.setModel(createModel());
		scrollPane.setViewportView(list);
		
		JLabel lblDaten = new JLabel("Daten:");
		scrollPane.setColumnHeaderView(lblDaten);
		
		JLabel lblLagerungsempfehlungangabe = new JLabel();
		panel_2.add(lblLagerungsempfehlungangabe);
		
		JButton btnZurck = new JButton("Zur\u00FCck");
		btnZurck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fensterSchlieﬂen();
			}
		});
		panel_2.add(btnZurck);
	}
	
	private DefaultListModel<String> createModel(){
		DefaultListModel<String> model = new DefaultListModel<String>();
		ArrayList<Datumsangaben> daten = this.lebensmittel.getDaten();
		ListIterator<Datumsangaben> it = daten.listIterator();
		while(it.hasNext()){
			Datumsangaben datum = it.next();
			String ausgabe = "";
			ausgabe = datum.getMhd().getTime().toString();
			model.addElement(ausgabe);
		}
		return model;
	}
	
	private void fensterSchlieﬂen(){
		this.setVisible(false);
	}

}
