/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
* - Programmname: Covfefe (calculation of voltage from E-series for ease)                            *
* - Beschreibung: Programm zur Berechnung eines Spannungsteilers mit Widerstandswahl nach E-Reihen.  *
* - Version: 1.0.0.0                                                                                 *
* - Datum: 30.04.2018                                                                                *
* - Autoren: Sebastian Lang, Tobias V�th, Dominik Th�rmer, Marc Gebert, Hussein Kaviani, Shayan Jani *
* - Kontakt: software@sebastianlang.net                                                              *
* - Copyright: � 2018 Hochschule Furtwangen                                                          *
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

//Notwendige Debugging-Konfiguration VM arguments: -splash:src/Covfefe.png

package covfefe;

import java.awt.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

public class Covfefe extends JFrame {
	private static final long serialVersionUID = 5968568959703649378L; //Serialisierbare Versionsnummer
	private static final int delay = 3000; //Wartezeit in ms w�hrend des Startbildschirms
	
	public Covfefe() {
		//Framedesign festlegen
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Anwendung nach Klicken auf den Schlie�en-Button beenden
		this.setSize(800, 600); //Fenstergr��e festlegen
		this.setLocationRelativeTo(null); //Fenster auf Bildschirm zentrieren
		this.setTitle("Covfefe"); //Fenstertitel festlegen
		try {
			Image icon = ImageIO.read(ClassLoader.getSystemResourceAsStream("Covfefe.png")); //Icon laden
			this.setIconImage(icon); //Fenstericon festlegen
		} catch (IOException e) {
			e.printStackTrace(); //Fehlerausgabe
		}
		
		//Startbildschirm anzeigen
		Thread t = new Thread() {
			public void run() {
				final SplashScreen splash = SplashScreen.getSplashScreen(); //Startbildschirm erzeugen
				if (splash == null) {
					System.out.println("Fehler: SplashScreen kann nicht erzeugt werden."); //Fehlerausgabe
					return; //Thread beenden
				}
				try {
					Thread.sleep(delay); //Verz�gerung
				} catch (InterruptedException e) {
					e.printStackTrace(); //Fehlerausgabe
				}
				Covfefe.this.setVisible(true); //Hauptfenster anzeigen und Startbildschirm schlie�en
				Covfefe.this.toFront(); //Hauptfenster in den Vordergrund bringen
			}
		};
		t.start(); //Thread starten
	}

	public static void main(String[] args) {
		new Covfefe(); //Hauptfenster initialisieren
	}
}
