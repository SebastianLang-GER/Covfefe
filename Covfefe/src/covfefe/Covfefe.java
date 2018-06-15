/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
* - Programmname: Covfefe (calculation of voltage from E-series for ease)                            *
* - Beschreibung: Programm zur Berechnung eines Spannungsteilers mit Widerstandswahl nach E-Reihen.  *
* - Version: 1.0.0.0                                                                                 *
* - Datum: 15.06.2018                                                                                *
* - Autoren: Sebastian Lang, Tobias Vöth, Dominik Thörmer, Marc Gebert, Hussein Kaviani, Shayan Jani *
* - Kontakt: software@sebastianlang.net                                                              *
* - Copyright: © 2018 Hochschule Furtwangen                                                          *
* - Lizenz: MIT-Lizenz                                                                               *
*   Hiermit wird unentgeltlich jeder Person, die eine Kopie der Software und der zugehörigen         *
*   Dokumentationen (die "Software") erhält, die Erlaubnis erteilt, sie uneingeschränkt zu nutzen,   *
*   inklusive und ohne Ausnahme mit dem Recht, sie zu verwenden, zu kopieren, zu verändern,          *
*   zusammenzufügen, zu veröffentlichen, zu verbreiten, zu unterlizenzieren und/oder zu verkaufen,   *
*   und Personen, denen diese Software überlassen wird, diese Rechte zu verschaffen, unter den       *
*   folgenden Bedingungen:                                                                           *
*                                                                                                    *
*   Der obige Urheberrechtsvermerk und dieser Erlaubnisvermerk sind in allen Kopien oder Teilkopien  *
*   der Software beizulegen.                                                                         *
*                                                                                                    *
*   DIE SOFTWARE WIRD OHNE JEDE AUSDRÜCKLICHE ODER IMPLIZIERTE GARANTIE BEREITGESTELLT,              *
*   EINSCHLIEßLICH DER GARANTIE ZUR BENUTZUNG FÜR DEN VORGESEHENEN ODER EINEM BESTIMMTEN ZWECK SOWIE *
*   JEGLICHER RECHTSVERLETZUNG, JEDOCH NICHT DARAUF BESCHRÄNKT. IN KEINEM FALL SIND DIE AUTOREN ODER *
*   COPYRIGHTINHABER FÜR JEGLICHEN SCHADEN ODER SONSTIGE ANSPRÜCHE HAFTBAR ZU MACHEN, OB INFOLGE DER *
*   ERFÜLLUNG EINES VERTRAGES, EINES DELIKTES ODER ANDERS IM ZUSAMMENHANG MIT DER SOFTWARE ODER      *
*   SONSTIGER VERWENDUNG DER SOFTWARE ENTSTANDEN.                                                    *
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

//Notwendige Debugging-Konfiguration: VM arguments: -splash:src/Covfefe.png

package covfefe;

import java.awt.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

/**
 * Hauptklasse, die beim Programmstart aufgerufen wird und die grafische Benutzeroberfläche enthält
 * @author Sebastian Lang
 * @version 15.06.2018
 */
@SuppressWarnings("serial")
public class Covfefe extends JFrame {
	private static final int delay = 3000; //Wartezeit in ms während des Startbildschirms
	private VoltageDivider voltageDivider;
	
	/**
	 * Standardkonstruktor zum Erzeugen von Objekten der Klasse Covfefe
	 */
	public Covfefe() {
		//Framedesign festlegen
		super("Covfefe"); //Fenstertitel festlegen
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Anwendung nach Klicken auf den Schließen-Button beenden
		setSize(800, 600); //Fenstergröße festlegen
		setLocationRelativeTo(null); //Fenster auf Bildschirm zentrieren
		try {
			Image icon = ImageIO.read(ClassLoader.getSystemResourceAsStream("Covfefe.png")); //Icon laden
			this.setIconImage(icon); //Fenstericon festlegen
		} catch (IOException e) {
			e.printStackTrace(); //Fehlerausgabe
		}
		
		reset(); //Neue Berechnung vorbereiten
		
		//Startbildschirm anzeigen
		Thread t = new Thread() {
			public void run() {
				final SplashScreen splash = SplashScreen.getSplashScreen(); //Startbildschirm erzeugen
				if (splash == null) {
					System.out.println("Fehler: SplashScreen kann nicht erzeugt werden."); //Fehlerausgabe
					return; //Thread beenden
				}
				try {
					Thread.sleep(delay); //Verzögerung
				} catch (InterruptedException e) {
					e.printStackTrace(); //Fehlerausgabe
				}
				Covfefe.this.setVisible(true); //Hauptfenster anzeigen und Startbildschirm schließen
				Covfefe.this.toFront(); //Hauptfenster in den Vordergrund bringen
			}
		};
		t.start(); //Thread starten
	}

	/**
	 * Methode, die beim Programmstart ausgeführt wird und ein neues Objekt der Klasse Covfefe erstellt
	 * @param args Programmparameter
	 */
	public static void main(String[] args) {
		new Covfefe(); //Hauptfenster initialisieren
	}
	
	/**
	 * Alle Eingaben zurücksetzen und Benutzersteuerelemente initialisieren
	 */
	public void reset() {
		voltageDivider = new VoltageDivider();
		
		//Eingabe-Benutzersteuerelemente zurücksetzen
		//### Todo
	}
	
	/**
	 * Eingaben auf Gültigkeit überprüfen und Berechnung durchführen
	 */
	public void calculate() {
		//Verfügbare Eingabeparamater
		InputParameter parameter = new InputParameter();
		//### Todo
		
		if(voltageDivider.isValidInput(parameter)) {
			//Eingaben übernehmen
			//### Todo: Werte aus GUI einlesen
			
			voltageDivider.calculateValues(); //Berechnung durchführen
			
			//Ergebnis ausgeben
			//### Todo: Werte in GUI übernehmen
		}
		else {
			//Ungültige Eingabe anzeigen
			//### Todo: Benachrichtigung ausgeben, Anzeige auf GUI
		}
	}
}