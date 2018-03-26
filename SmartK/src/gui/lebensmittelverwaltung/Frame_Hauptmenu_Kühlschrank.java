package gui.lebensmittelverwaltung;



import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import daten.MHD_Alarm;

import geräte.Kühlschrank;
import gui.Frame_Einkaufsliste;
import gui.digitaluhr.Digitaluhr;

import java.awt.GridLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;

import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;

public class Frame_Hauptmenu_Kühlschrank extends JFrame {

	/**
	 * Klasse für das Hauptmenü eines spezifischen Kühlschranks
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Kühlschrank gerät;
	


	/**
	 * Create the frame.
	 */
	public Frame_Hauptmenu_Kühlschrank(Kühlschrank kühlschrank) {
		
		this.gerät = new Kühlschrank();
		this.gerät.setName(kühlschrank.getName());
		this.gerät.setStandort(kühlschrank.getStandort());
		this.gerät.setBestände(kühlschrank.getBestände());
		this.gerät.getBestände().beständeLaden(this.gerät.getName()+ "Bestandsliste.ser");
		this.gerät.setEinkaufen(kühlschrank.getEinkaufen());
		this.gerät.setEntnommen(kühlschrank.getEntnommen());
		this.gerät.setFächer(kühlschrank.getFächer());
		this.gerät.setGefrierfachVorhanden(kühlschrank.isGefrierfachVorhanden());
		this.gerät.setGröße(kühlschrank.getGröße());
		this.gerät.setTemperatur(kühlschrank.getTemperatur());
		
		setTitle(this.gerät.getName() + " - Hauptmenü");
		setBounds(100, 100, 420, 550);		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230,230,250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 2, 10, 0));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(230,230,250));
		contentPane.add(panel_2);
		panel_2.setLayout(new GridLayout(3, 1, 10, 10));
		
		JButton btnBestndeVerwalten = new JButton("Best\u00E4nde verwalten");
		btnBestndeVerwalten.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 13));
		btnBestndeVerwalten.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame bestände = new Frame_Bestandsverwaltung(gerät);
				bestände.setVisible(true);
			}
		});
		panel_2.add(btnBestndeVerwalten);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(230,230,250));
		contentPane.add(panel);
		panel.setLayout(new GridLayout(3, 1, 10, 10));
		
		JButton btnMeineEinkaufsliste = new JButton("Meine Einkaufsliste");
		btnMeineEinkaufsliste.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame neu = new Frame_Einkaufsliste(gerät);
				neu.setVisible(true);
			}
		});
		btnMeineEinkaufsliste.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 13));
		panel_2.add(btnMeineEinkaufsliste);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(230,230,250));
		panel_2.add(panel_3);
		panel_3.setLayout(new GridLayout(2, 0, 0, 10));
		
		
		
		
		Digitaluhr uhr = new Digitaluhr();
		uhr.setBorder(new LineBorder(new Color(0, 0, 0)));
		uhr.setBackground(Color.WHITE);
		
		panel.add(uhr);
		uhr.setLayout(new GridLayout(1, 0, 0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane);
		
		JList<String> list = new JList<String>();
		scrollPane.setViewportView(list);
		list.setModel(createModel());
		
		JLabel lblMidesthaltbarkeitsalarm = new JLabel("Midesthaltbarkeitsalarm:");
		scrollPane.setColumnHeaderView(lblMidesthaltbarkeitsalarm);
		
		JButton btnMindesthaltbarkeitsalarm = new JButton("<html><body>Mindesthaltbarkeitsalarm <br>aktualisieren </body></html>");
		btnMindesthaltbarkeitsalarm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				list.setModel(createModel());
			}
		});
		btnMindesthaltbarkeitsalarm.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 13));
		panel_3.add(btnMindesthaltbarkeitsalarm);
		
		
		
		JButton btnEinstellungen = new JButton("Einstellungen");
		btnEinstellungen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int anzahlTage = Integer.parseInt(JOptionPane.showInputDialog("Wie viele Tage sollen verbleiben, damit ein Lebensmittel im Lebensmittelalarm angezeigt werden soll?", null));
				MHD_Alarm neu = new MHD_Alarm();
				neu.setAnzahlTage(anzahlTage);
				neu.einstellungenSpeichern();
			}
		});
		btnEinstellungen.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 13));
		panel_3.add(btnEinstellungen);
		//Calendar cal = Calendar.getInstance();
		
		
		
		
		
		
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(230,230,250));
		panel.add(panel_1);
		panel_1.setLayout(new GridLayout(2, 2, 10, 10));
		
		JLabel label = new JLabel("");
		label.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 13));
		panel_1.add(label);
		
		JButton btnZurckZurKhlschrankauswahl = new JButton("<html><body>Zur\u00FCck zur <br>K\u00FChlschrankauswahl</body></html>");
		btnZurckZurKhlschrankauswahl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fensterSchließen();
			}
		});
		btnZurckZurKhlschrankauswahl.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 13));
		panel_1.add(btnZurckZurKhlschrankauswahl);
		
		
		
	}
	
	private void fensterSchließen(){
		this.setVisible(false);
	}
	
	private DefaultListModel<String> createModel() {
		MHD_Alarm mhdAlarm = new MHD_Alarm();
		mhdAlarm.einstellungenLaden();
		String[] ausgabe = mhdAlarm.bestandslisteDurchsuchen(gerät.getBestände());
		DefaultListModel<String> model = new DefaultListModel<String>();
		for(int i = 0; i<ausgabe.length; i++) {
			model.addElement(ausgabe[i]);
		}
		return model;
	}

}
