/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
* - Programmname: Covfefe (calculation of voltage from E-series for ease)                            *
* - Beschreibung: Programm zur Berechnung eines Spannungsteilers mit Widerstandswahl nach E-Reihen   *
* - Version: 1.0.0.0                                                                                 *
* - Datum: 15.06.2018                                                                                *
* - Autoren: Sebastian Lang, Tobias V�th, Dominik Th�rmer, Marc Gebert, Hussein Kaviani, Shayan Jani *
* - Kontakt: software@sebastianlang.net                                                              *
* - Copyright: � 2018 Hochschule Furtwangen University                                               *
* - Lizenz: MIT-Lizenz                                                                               *
*   Hiermit wird unentgeltlich jeder Person, die eine Kopie der Software und der zugeh�rigen         *
*   Dokumentationen (die "Software") erh�lt, die Erlaubnis erteilt, sie uneingeschr�nkt zu nutzen,   *
*   inklusive und ohne Ausnahme mit dem Recht, sie zu verwenden, zu kopieren, zu ver�ndern,          *
*   zusammenzuf�gen, zu ver�ffentlichen, zu verbreiten, zu unterlizenzieren und/oder zu verkaufen,   *
*   und Personen, denen diese Software �berlassen wird, diese Rechte zu verschaffen, unter den       *
*   folgenden Bedingungen:                                                                           *
*                                                                                                    *
*   Der obige Urheberrechtsvermerk und dieser Erlaubnisvermerk sind in allen Kopien oder Teilkopien  *
*   der Software beizulegen.                                                                         *
*                                                                                                    *
*   DIE SOFTWARE WIRD OHNE JEDE AUSDR�CKLICHE ODER IMPLIZIERTE GARANTIE BEREITGESTELLT,              *
*   EINSCHLIE�LICH DER GARANTIE ZUR BENUTZUNG F�R DEN VORGESEHENEN ODER EINEM BESTIMMTEN ZWECK SOWIE *
*   JEGLICHER RECHTSVERLETZUNG, JEDOCH NICHT DARAUF BESCHR�NKT. IN KEINEM FALL SIND DIE AUTOREN ODER *
*   COPYRIGHTINHABER F�R JEGLICHEN SCHADEN ODER SONSTIGE ANSPR�CHE HAFTBAR ZU MACHEN, OB INFOLGE DER *
*   ERF�LLUNG EINES VERTRAGES, EINES DELIKTES ODER ANDERS IM ZUSAMMENHANG MIT DER SOFTWARE ODER      *
*   SONSTIGER VERWENDUNG DER SOFTWARE ENTSTANDEN.                                                    *
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

//Notwendige Debugging-Konfiguration: VM arguments: -splash:src/res/Covfefe.png
//Notwendige Manifest-Erg�nzung in JAR-Datei: SplashScreen-Image: res/Covfefe.png

package covfefe;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import org.apache.batik.swing.*;

/**
 * Hauptklasse, die beim Programmstart aufgerufen wird und die grafische Benutzeroberfl�che enth�lt
 * @author Sebastian Lang
 * @version 15.06.2018
 */
@SuppressWarnings("serial")
public class Covfefe extends JFrame {
	private static final int delay = 3000; //Wartezeit in ms w�hrend des Startbildschirms
	private VoltageDivider voltageDivider;
	
	/**
	 * Standardkonstruktor zum Erzeugen von Objekten der Klasse Covfefe
	 */
	public Covfefe() {
		//Framedesign festlegen
		super("Covfefe"); //Fenstertitel festlegen
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Anwendung nach Klicken auf den Schlie�en-Button beenden
		setSize(800, 600); //Fenstergr��e festlegen
		setLocationRelativeTo(null); //Fenster auf Bildschirm zentrieren
		try {
			setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/res/Covfefe.png")));
		} catch (Exception e) {
			System.out.println("Fehler: Icon kann nicht geladen werden."); //Fehlerausgabe
			SystemSounds.play(SystemSounds.Sound.Hand);
			JOptionPane.showMessageDialog(null, "Icon kann nicht geladen werden.", "Covfefe: Fehler", JOptionPane.ERROR_MESSAGE); //Fehlermeldung
		}
		getContentPane().setLayout(new BorderLayout(0, 0)); //Layoutmanager festlegen
		
		//Startbildschirm anzeigen
		Thread t = new Thread() {
			public void run() {
				final SplashScreen splash = SplashScreen.getSplashScreen(); //Startbildschirm erzeugen
				if (splash == null) {
					System.out.println("Fehler: Startbildschirm kann nicht erzeugt werden."); //Fehlerausgabe
					SystemSounds.play(SystemSounds.Sound.Hand);
					JOptionPane.showMessageDialog(null, "Startbildschirm kann nicht erzeugt werden.", "Covfefe: Fehler", JOptionPane.ERROR_MESSAGE); //Fehlermeldung
				}
				else {
					try {
						Thread.sleep(delay); //Verz�gerung
					} catch (InterruptedException e) {
						e.printStackTrace(); //Fehlerausgabe
					}
				}
				Covfefe.this.setVisible(true); //Hauptfenster anzeigen und Startbildschirm schlie�en
				Covfefe.this.toFront(); //Hauptfenster in den Vordergrund bringen
			}
		};
		t.start(); //Thread starten

		//Steuerelemente hinzuf�gen
		JSplitPane splitPane = new JSplitPane();
		splitPane.setResizeWeight(0.5);
		getContentPane().add(splitPane);
		
		JPanel leftSplitPanePanel = new JPanel();
		splitPane.setLeftComponent(leftSplitPanePanel);
		leftSplitPanePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel groupResistorPanel = new JPanel();
		groupResistorPanel.setBorder(new TitledBorder(null, "Widerst\u00E4nde", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		leftSplitPanePanel.add(groupResistorPanel);
		groupResistorPanel.setLayout(new GridLayout(0, 3, 5, 5));
		
		JLabel lblStandardSeries = new JLabel("Normreihe:");
		groupResistorPanel.add(lblStandardSeries);
		
		JComboBox comboBoxESeries = new JComboBox();
		comboBoxESeries.setToolTipText("E-Reihe");
		groupResistorPanel.add(comboBoxESeries);
		
		JPanel placeHolderPanel1 = new JPanel();
		groupResistorPanel.add(placeHolderPanel1);
		
		JLabel lblResistor1 = new JLabel("Widerstand R1:");
		groupResistorPanel.add(lblResistor1);
		
		JSpinner spinnerResistor1 = new JSpinner();
		groupResistorPanel.add(spinnerResistor1);
		
		JComboBox comboBoxUnitWithPrefixResistor1 = new JComboBox();
		groupResistorPanel.add(comboBoxUnitWithPrefixResistor1);
		
		JLabel lblResistor2 = new JLabel("Widerstand R2:");
		groupResistorPanel.add(lblResistor2);
		
		JSpinner spinnerResistor2 = new JSpinner();
		groupResistorPanel.add(spinnerResistor2);
		
		JComboBox comboBoxUnitWithPrefixResistor2 = new JComboBox();
		groupResistorPanel.add(comboBoxUnitWithPrefixResistor2);
		
		JLabel lblTotalResistor = new JLabel("Gesamtwiderstand:");
		groupResistorPanel.add(lblTotalResistor);
		
		JSpinner spinnerTotalResistor = new JSpinner();
		groupResistorPanel.add(spinnerTotalResistor);
		
		JComboBox comboBoxUnitWithPrefixTotalResistor = new JComboBox();
		groupResistorPanel.add(comboBoxUnitWithPrefixTotalResistor);
		
		JPanel rightSplitPanePanel = new JPanel();
		splitPane.setRightComponent(rightSplitPanePanel);
		rightSplitPanePanel.setLayout(new BorderLayout(0, 0));
		
		JPanel buttonPanel = new JPanel();
		rightSplitPanePanel.add(buttonPanel, BorderLayout.SOUTH);
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnReset = new JButton("Zur\u00FCcksetzen");
		buttonPanel.add(btnReset);
		
		JButton btnCalculate = new JButton("Berechnen");
		buttonPanel.add(btnCalculate);
		
		JSVGCanvas svgCanvas = new JSVGCanvas();
		svgCanvas.setBackground(new Color(240, 240, 240));
		svgCanvas.setURI("file:src/res/Spannungsteiler.svg");
		rightSplitPanePanel.add(svgCanvas, BorderLayout.CENTER);
		
		reset(); //Neue Berechnung vorbereiten
	}

	/**
	 * Methode, die beim Programmstart ausgef�hrt wird und ein neues Objekt der Klasse Covfefe erstellt
	 * @param args Programmparameter
	 */
	public static void main(String[] args) {
		new Covfefe(); //Hauptfenster initialisieren
	}
	
	/**
	 * Alle Eingaben zur�cksetzen und Benutzersteuerelemente initialisieren
	 */
	public void reset() {
		voltageDivider = new VoltageDivider();
		
		//Eingabe-Benutzersteuerelemente zur�cksetzen
		//### Todo
	}
	
	/**
	 * Eingaben auf G�ltigkeit �berpr�fen und Berechnung durchf�hren
	 */
	public void calculate() {
		//Verf�gbare Eingabeparamater
		InputParameter parameter = new InputParameter();
		//### Todo
		
		if(voltageDivider.isValidInput(parameter)) {
			//Eingaben �bernehmen
			//### Todo: Werte aus GUI einlesen
			
			voltageDivider.calculateValues(); //Berechnung durchf�hren
			
			//Ergebnis ausgeben
			//### Todo: Werte in GUI �bernehmen
		}
		else {
			//Ung�ltige Eingabe anzeigen
			//### Todo: Benachrichtigung ausgeben, Anzeige auf GUI
		}
	}
}