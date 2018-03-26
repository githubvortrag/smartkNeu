package gui;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;


import geräte.Geräte;

import gui.lebensmittelverwaltung.Frame_Hauptmenu_Kühlschrank;


import javax.swing.JLabel;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import javax.swing.JComboBox;

public class Frame_Kühlschrankverwaltung extends JFrame {

	/**
	 * Klasse für die Kühlschrankverwaltung, gleichzeitig Einstieg ins Programm
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Geräte inventar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Geräte vorhandeneGeräte = new Geräte();
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame_Kühlschrankverwaltung frame = new Frame_Kühlschrankverwaltung(vorhandeneGeräte);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Frame_Kühlschrankverwaltung(Geräte vorhandeneGeräte) {
		this.inventar = new Geräte();
		vorhandeneGeräte.geräteLaden();
		this.inventar.setAnzahl(vorhandeneGeräte.getAnzahl());
		this.inventar.setKühlschränke(vorhandeneGeräte.getKühlschränke());
		this.inventar.setEinkaufen(vorhandeneGeräte.getEinkaufen());
		
		setTitle("SmartK - K\u00FChlschrankverwaltung");
		setForeground(Color.DARK_GRAY);
		setBackground(Color.DARK_GRAY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 420, 550);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblWillkommenZuSmartk = new JLabel("Willkommen zu SmartK.");
		lblWillkommenZuSmartk.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 25));
		
		contentPane.setLayout(new GridLayout(0, 1, 10, 10));
		contentPane.add(lblWillkommenZuSmartk);
		
		JLabel lblKhlschrankauswahl = new JLabel("K\u00FChlschrankauswahl:");
		lblKhlschrankauswahl.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 13));
		contentPane.add(lblKhlschrankauswahl);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setForeground(Color.BLACK);
		comboBox.setBackground(Color.WHITE);
		comboBox.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 13));
		
		for(int i=0; i<this.inventar.getAnzahl(); i++){
			comboBox.addItem(this.inventar.getKühlschränke().get(i).getName());
		}
		
		contentPane.add(comboBox);
		
		JButton btnKS1 = new JButton("K\u00FChlschrank ausw\u00E4hlen");
		btnKS1.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 13));
		btnKS1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String auswahl = (String) comboBox.getSelectedItem();
				int index = 0;
				for (int i=0; i<inventar.getAnzahl(); i++){
					if(auswahl.equals(inventar.getKühlschränke().get(i).getName())){
						index = i;
					}
				}
				JFrame hauptmenü = new Frame_Hauptmenu_Kühlschrank(inventar.getKühlschränke().get(index));
				hauptmenü.setVisible(true);
			}
		});
		contentPane.add(btnKS1);
		
		JButton btnNewButton = new JButton("Neuen K\u00FChlschrank erstellen");
		btnNewButton.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 13));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrame neuerKühlschrank = new Frame_NeuerKühlschrank(inventar);
				neuerKühlschrank.setVisible(true);
				
			}
		});
		contentPane.add(btnNewButton);
		
		JButton btnAktualisieren = new JButton("Aktualisieren");
		btnAktualisieren.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				fensterAktualisieren();
			}
		});
		
		JButton btnKhlschrankEntfernen = new JButton("K\u00FChlschrank entfernen");
		btnKhlschrankEntfernen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame ksEntfernen = new Frame_KühlschrankEntfernen(inventar);
				ksEntfernen.setVisible(true);
			}
		});
		btnKhlschrankEntfernen.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 13));
		contentPane.add(btnKhlschrankEntfernen);
		btnAktualisieren.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 13));
		contentPane.add(btnAktualisieren);
		
		JButton btnEinkaufslisteAnzeigen = new JButton("Einkaufsliste anzeigen");
		btnEinkaufslisteAnzeigen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrame neu = new Frame_Einkaufsliste(inventar);
				neu.setVisible(true);
			}
		});
		btnEinkaufslisteAnzeigen.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 13));
		contentPane.add(btnEinkaufslisteAnzeigen);
	}
	
	
	private void fensterAktualisieren(){
		this.setVisible(false);
		JFrame aktualisiert = new Frame_Kühlschrankverwaltung(this.inventar);
		aktualisiert.setVisible(true);
	}
}