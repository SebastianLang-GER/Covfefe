/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
* - Programmname: Covfefe (calculation of voltage from E-series for ease)                            *
* - Beschreibung: Programm zur Berechnung eines Spannungsteilers mit Widerstandswahl nach E-Reihen   *
* - Version: 1.0.0.0                                                                                 *
* - Datum: 15.06.2018                                                                                *
* - Autoren: Sebastian Lang, Tobias Vöth, Dominik Thörmer, Marc Gebert, Hussein Kaviani, Shayan Jani *
* - Kontakt: software@sebastianlang.net                                                              *
* - Copyright: © 2018 Hochschule Furtwangen University                                               *
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

/* Notwendige Debugging-Konfigurationen:
 * - Run-Configuration -> Arguments -> Program arguments: debugging
 * - Run-Configuration -> Arguments -> VM arguments: -splash:src/res/Covfefe.png
 * Notwendige Kompilier-Konfigurationen:
 * - Run-Configuration -> Arguments -> Program arguments: keine
 * - Manifest-Ergänzung in JAR-Datei: SplashScreen-Image: res/Covfefe.png
 */

package covfefe;

import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.*;
import javax.swing.border.*;
import org.apache.batik.swing.*;

/**
 * Hauptklasse, die beim Programmstart aufgerufen wird und die grafische Benutzeroberfläche enthält
 * @author Sebastian Lang, Tobias Vöth, Dominik Thörmer, Marc Gebert, Hussein Kaviani, Shayan Jani
 * @version 06.07.2018
 */
@SuppressWarnings("serial")
public class Covfefe extends JFrame {
	private static final int delay = 3000; //Wartezeit in ms während des Startbildschirms
	private static boolean debugging = false; //Debugging-Modus
	private VoltageDivider voltageDivider;
	
	//GUI-Komponenten
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private JSpinner spnValueResistor1 = new JSpinner();
	private JSpinner spnMinResistor1 = new JSpinner();
	private JSpinner spnValueResistor2 = new JSpinner();
	private JSpinner spnMinResistor2 = new JSpinner();
	private JSpinner spnValueTotalResistor = new JSpinner();
	private JSpinner spnValueVoltage1 = new JSpinner();
	private JSpinner spnValueVoltage2 = new JSpinner();
	private JSpinner spnValueTotalVoltage = new JSpinner();
	private JSpinner spnRatioA = new JSpinner();
	private JSpinner spnRatioB = new JSpinner();
	private JSpinner spnFactor = new JSpinner();
	private JComboBox<ESeries> cbESeries = new JComboBox<ESeries>();
	private JComboBox<Resistance> cbUnitWithPrefixResistor1 = new JComboBox<Resistance>();
	private JComboBox<Resistance> cbUnitWithPrefixMinResistor1 = new JComboBox<Resistance>();
	private JComboBox<Resistance> cbUnitWithPrefixResistor2 = new JComboBox<Resistance>();
	private JComboBox<Resistance> cbUnitWithPrefixMinResistor2 = new JComboBox<Resistance>();
	private JComboBox<Resistance> cbUnitWithPrefixTotalResistor = new JComboBox<Resistance>();
	private JComboBox<Voltage> cbUnitWithPrefixVoltage1 = new JComboBox<Voltage>();
	private JComboBox<Voltage> cbUnitWithPrefixVoltage2 = new JComboBox<Voltage>();
	private JComboBox<Voltage> cbUnitWithPrefixTotalVoltage = new JComboBox<Voltage>();

	/**
	 * Standardkonstruktor zum Erzeugen von Objekten der Klasse Covfefe
	 */
	public Covfefe() {
		//Framedesign festlegen
		super("Covfefe"); //Fenstertitel festlegen
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Anwendung nach Klicken auf den Schließen-Button beenden
		setSize(800, 600); //Fenstergröße festlegen
		setMinimumSize(new Dimension(610, 510)); //Minimale Fenstergröße festlegen
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
						Thread.sleep(delay); //Verzögerung
					} catch (InterruptedException e) {
						e.printStackTrace(); //Fehlerausgabe
					}
				}
				Covfefe.this.setVisible(true); //Hauptfenster anzeigen und Startbildschirm schließen
				Covfefe.this.toFront(); //Hauptfenster in den Vordergrund bringen
			}
		};
		t.start(); //Thread starten

		//Steuerelemente hinzufügen
		JSplitPane splitPane = new JSplitPane();
		splitPane.setResizeWeight(0.5);
		getContentPane().add(splitPane);
		
		JPanel leftSplitPanePanel = new JPanel();
		splitPane.setLeftComponent(leftSplitPanePanel);
		GridBagLayout gbl_leftSplitPanePanel = new GridBagLayout();
		gbl_leftSplitPanePanel.columnWeights = new double[]{1.0};
		gbl_leftSplitPanePanel.rowWeights = new double[]{1.0, 0.0, 0.0};
		leftSplitPanePanel.setLayout(gbl_leftSplitPanePanel);
		
		JPanel groupResistorPanel = new JPanel();
		groupResistorPanel.setBorder(new TitledBorder(null, "Widerst\u00E4nde", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_groupResistorPanel = new GridBagConstraints();
		gbc_groupResistorPanel.weightx = 0.5;
		gbc_groupResistorPanel.weighty = 0.5;
		gbc_groupResistorPanel.insets = new Insets(0, 0, 5, 0);
		gbc_groupResistorPanel.gridx = 0;
		gbc_groupResistorPanel.gridy = 0;
		leftSplitPanePanel.add(groupResistorPanel, gbc_groupResistorPanel);
		GridBagLayout gbl_groupResistorPanel = new GridBagLayout();
		gbl_groupResistorPanel.rowHeights = new int[] {26, 26, 26, 26, 26, 21};
		gbl_groupResistorPanel.columnWidths = new int[] {180, 120, 40};
		gbl_groupResistorPanel.columnWeights = new double[]{1.0, 1.0, 1.0};
		gbl_groupResistorPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		groupResistorPanel.setLayout(gbl_groupResistorPanel);
		
		JLabel lblStandardSeries = new JLabel("Normreihe:");
		GridBagConstraints gbc_lblStandardSeries = new GridBagConstraints();
		gbc_lblStandardSeries.fill = GridBagConstraints.BOTH;
		gbc_lblStandardSeries.insets = new Insets(0, 0, 5, 5);
		gbc_lblStandardSeries.gridx = 0;
		gbc_lblStandardSeries.gridy = 0;
		groupResistorPanel.add(lblStandardSeries, gbc_lblStandardSeries);
		cbESeries.setModel(new DefaultComboBoxModel(new String[] {"E3 (>\u00B120 %)", "E6 (\u00B120 %)", "E12 (\u00B110 %)", "E24 (\u00B15 %)", "E48 (\u00B12 %)", "E96 (\u00B11 %)", "E192 (\u00B10,5 %)"}));
		cbESeries.setSelectedIndex(3);
		cbESeries.setToolTipText("E-Reihe");
		cbESeries.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				
			}
		});

		GridBagConstraints gbc_cbESeries = new GridBagConstraints();
		gbc_cbESeries.fill = GridBagConstraints.BOTH;
		gbc_cbESeries.insets = new Insets(0, 0, 5, 5);
		gbc_cbESeries.gridx = 1;
		gbc_cbESeries.gridy = 0;
		groupResistorPanel.add(cbESeries, gbc_cbESeries);
		
		JLabel lblResistor1 = new JLabel("<html>Widerstand R<sub>1</sub>:</html>");
		GridBagConstraints gbc_lblResistor1 = new GridBagConstraints();
		gbc_lblResistor1.fill = GridBagConstraints.BOTH;
		gbc_lblResistor1.insets = new Insets(0, 0, 5, 5);
		gbc_lblResistor1.gridx = 0;
		gbc_lblResistor1.gridy = 1;
		groupResistorPanel.add(lblResistor1, gbc_lblResistor1);
		spnValueResistor1.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				Resistance value = new Resistance();
				value.setValue((double) spnValueResistor1.getValue());
				Resistor r = voltageDivider.getResistor(0);
				r.chooseResistor(value);
				voltageDivider.setResistor(0, r);
			}
		});
		
		spnValueResistor1.setModel(new SpinnerNumberModel(new Double(0), new Double(0), null, new Double(1)));
		GridBagConstraints gbc_spnValueResistor1 = new GridBagConstraints();
		gbc_spnValueResistor1.fill = GridBagConstraints.BOTH;
		gbc_spnValueResistor1.insets = new Insets(0, 0, 5, 5);
		gbc_spnValueResistor1.gridx = 1;
		gbc_spnValueResistor1.gridy = 1;
		groupResistorPanel.add(spnValueResistor1, gbc_spnValueResistor1);
		
		cbUnitWithPrefixResistor1.setModel(new DefaultComboBoxModel(new String[] {"\u00B5\u03A9", "m\u03A9", "\u03A9", "k\u03A9", "M\u03A9"}));
		cbUnitWithPrefixResistor1.setSelectedIndex(2);
		GridBagConstraints gbc_cbUnitWithPrefixResistor1 = new GridBagConstraints();
		gbc_cbUnitWithPrefixResistor1.fill = GridBagConstraints.BOTH;
		gbc_cbUnitWithPrefixResistor1.insets = new Insets(0, 0, 5, 0);
		gbc_cbUnitWithPrefixResistor1.gridx = 2;
		gbc_cbUnitWithPrefixResistor1.gridy = 1;
		groupResistorPanel.add(cbUnitWithPrefixResistor1, gbc_cbUnitWithPrefixResistor1);
		
		JLabel lblmindestwertR = new JLabel("<html>Mindestwert R<sub>1</sub>:</html>");
		GridBagConstraints gbc_lblmindestwertR = new GridBagConstraints();
		gbc_lblmindestwertR.fill = GridBagConstraints.BOTH;
		gbc_lblmindestwertR.insets = new Insets(0, 0, 5, 5);
		gbc_lblmindestwertR.gridx = 0;
		gbc_lblmindestwertR.gridy = 2;
		groupResistorPanel.add(lblmindestwertR, gbc_lblmindestwertR);
		spnMinResistor1.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				Resistance value = new Resistance();
				value.setValue((double) spnMinResistor1.getValue());
				voltageDivider.setMinResistor(0, value);
			}
		});
		
		spnMinResistor1.setModel(new SpinnerNumberModel(new Double(0), new Double(0), null, new Double(1)));
		GridBagConstraints gbc_spnMinResistor1 = new GridBagConstraints();
		gbc_spnMinResistor1.fill = GridBagConstraints.BOTH;
		gbc_spnMinResistor1.insets = new Insets(0, 0, 5, 5);
		gbc_spnMinResistor1.gridx = 1;
		gbc_spnMinResistor1.gridy = 2;
		groupResistorPanel.add(spnMinResistor1, gbc_spnMinResistor1);
		
		cbUnitWithPrefixMinResistor1.setModel(new DefaultComboBoxModel(new String[] {"\u00B5\u03A9", "m\u03A9", "\u03A9", "k\u03A9", "M\u03A9"}));
		cbUnitWithPrefixMinResistor1.setSelectedIndex(2);
		GridBagConstraints gbc_cbUnitWithPrefixMinResistor1 = new GridBagConstraints();
		gbc_cbUnitWithPrefixMinResistor1.insets = new Insets(0, 0, 5, 0);
		gbc_cbUnitWithPrefixMinResistor1.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbUnitWithPrefixMinResistor1.gridx = 2;
		gbc_cbUnitWithPrefixMinResistor1.gridy = 2;
		groupResistorPanel.add(cbUnitWithPrefixMinResistor1, gbc_cbUnitWithPrefixMinResistor1);
		
		JLabel lblResistor2 = new JLabel("<html>Widerstand R<sub>2</sub>:</html>");
		GridBagConstraints gbc_lblResistor2 = new GridBagConstraints();
		gbc_lblResistor2.fill = GridBagConstraints.BOTH;
		gbc_lblResistor2.insets = new Insets(0, 0, 5, 5);
		gbc_lblResistor2.gridx = 0;
		gbc_lblResistor2.gridy = 3;
		groupResistorPanel.add(lblResistor2, gbc_lblResistor2);
		spnValueResistor2.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				Resistance value = new Resistance();
				value.setValue((double) spnValueResistor2.getValue());
				Resistor r = voltageDivider.getResistor(1);
				r.chooseResistor(value);
				voltageDivider.setResistor(1, r);
			}
		});
		
		spnValueResistor2.setModel(new SpinnerNumberModel(new Double(0), new Double(0), null, new Double(1)));
		GridBagConstraints gbc_spnValueResistor2 = new GridBagConstraints();
		gbc_spnValueResistor2.fill = GridBagConstraints.BOTH;
		gbc_spnValueResistor2.insets = new Insets(0, 0, 5, 5);
		gbc_spnValueResistor2.gridx = 1;
		gbc_spnValueResistor2.gridy = 3;
		groupResistorPanel.add(spnValueResistor2, gbc_spnValueResistor2);
		
		cbUnitWithPrefixResistor2.setModel(new DefaultComboBoxModel(new String[] {"\u00B5\u03A9", "m\u03A9", "\u03A9", "k\u03A9", "M\u03A9"}));
		cbUnitWithPrefixResistor2.setSelectedIndex(2);
		GridBagConstraints gbc_cbUnitWithPrefixResistor2 = new GridBagConstraints();
		gbc_cbUnitWithPrefixResistor2.fill = GridBagConstraints.BOTH;
		gbc_cbUnitWithPrefixResistor2.insets = new Insets(0, 0, 5, 0);
		gbc_cbUnitWithPrefixResistor2.gridx = 2;
		gbc_cbUnitWithPrefixResistor2.gridy = 3;
		groupResistorPanel.add(cbUnitWithPrefixResistor2, gbc_cbUnitWithPrefixResistor2);
		
		JLabel lblmindestwertR_1 = new JLabel("<html>Mindestwert R<sub>2</sub>:</html>");
		GridBagConstraints gbc_lblmindestwertR_1 = new GridBagConstraints();
		gbc_lblmindestwertR_1.fill = GridBagConstraints.BOTH;
		gbc_lblmindestwertR_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblmindestwertR_1.gridx = 0;
		gbc_lblmindestwertR_1.gridy = 4;
		groupResistorPanel.add(lblmindestwertR_1, gbc_lblmindestwertR_1);
		spnMinResistor2.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				Resistance value = new Resistance();
				value.setValue((double) spnMinResistor2.getValue());
				voltageDivider.setMinResistor(1, value);
			}
		});
		
		spnMinResistor2.setModel(new SpinnerNumberModel(new Double(0), new Double(0), null, new Double(1)));
		GridBagConstraints gbc_spnMinResistor2 = new GridBagConstraints();
		gbc_spnMinResistor2.fill = GridBagConstraints.BOTH;
		gbc_spnMinResistor2.insets = new Insets(0, 0, 5, 5);
		gbc_spnMinResistor2.gridx = 1;
		gbc_spnMinResistor2.gridy = 4;
		groupResistorPanel.add(spnMinResistor2, gbc_spnMinResistor2);
		
		cbUnitWithPrefixMinResistor2.setModel(new DefaultComboBoxModel(new String[] {"\u00B5\u03A9", "m\u03A9", "\u03A9", "k\u03A9", "M\u03A9"}));
		cbUnitWithPrefixMinResistor2.setSelectedIndex(2);
		GridBagConstraints gbc_cbUnitWithPrefixMinResistor2 = new GridBagConstraints();
		gbc_cbUnitWithPrefixMinResistor2.insets = new Insets(0, 0, 5, 0);
		gbc_cbUnitWithPrefixMinResistor2.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbUnitWithPrefixMinResistor2.gridx = 2;
		gbc_cbUnitWithPrefixMinResistor2.gridy = 4;
		groupResistorPanel.add(cbUnitWithPrefixMinResistor2, gbc_cbUnitWithPrefixMinResistor2);
		
		JLabel lblTotalResistor = new JLabel("<html>Gesamtwiderstand (R<sub>1</sub> + R<sub>2</sub>):</html>");
		GridBagConstraints gbc_lblTotalResistor = new GridBagConstraints();
		gbc_lblTotalResistor.fill = GridBagConstraints.BOTH;
		gbc_lblTotalResistor.insets = new Insets(0, 0, 5, 5);
		gbc_lblTotalResistor.gridx = 0;
		gbc_lblTotalResistor.gridy = 5;
		groupResistorPanel.add(lblTotalResistor, gbc_lblTotalResistor);
		spnValueTotalResistor.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				Resistance value = new Resistance();
				value.setValue((double) spnValueTotalResistor.getValue());
				voltageDivider.setTotalResistor(value);;
			}
		});
		
		spnValueTotalResistor.setModel(new SpinnerNumberModel(new Double(0), new Double(0), null, new Double(1)));
		GridBagConstraints gbc_spnValueTotalResistor = new GridBagConstraints();
		gbc_spnValueTotalResistor.fill = GridBagConstraints.BOTH;
		gbc_spnValueTotalResistor.insets = new Insets(0, 0, 5, 5);
		gbc_spnValueTotalResistor.gridx = 1;
		gbc_spnValueTotalResistor.gridy = 5;
		groupResistorPanel.add(spnValueTotalResistor, gbc_spnValueTotalResistor);
		
		cbUnitWithPrefixTotalResistor.setModel(new DefaultComboBoxModel(new String[] {"\u00B5\u03A9", "m\u03A9", "\u03A9", "k\u03A9", "M\u03A9"}));
		cbUnitWithPrefixTotalResistor.setSelectedIndex(2);
		GridBagConstraints gbc_cbUnitWithPrefixTotalResistor = new GridBagConstraints();
		gbc_cbUnitWithPrefixTotalResistor.insets = new Insets(0, 0, 5, 0);
		gbc_cbUnitWithPrefixTotalResistor.fill = GridBagConstraints.BOTH;
		gbc_cbUnitWithPrefixTotalResistor.gridx = 2;
		gbc_cbUnitWithPrefixTotalResistor.gridy = 5;
		groupResistorPanel.add(cbUnitWithPrefixTotalResistor, gbc_cbUnitWithPrefixTotalResistor);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Spannungen", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.weightx = 0.5;
		gbc_panel_1.weighty = 0.5;
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 1;
		leftSplitPanePanel.add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.rowHeights = new int[] {26, 26, 21};
		gbl_panel_1.columnWidths = new int[] {180, 120, 40};
		gbl_panel_1.columnWeights = new double[]{0.0, 0.0, 0.0};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0};
		panel_1.setLayout(gbl_panel_1);
		
		JLabel lblfmax = new JLabel("<html>Spannung U<sub>1</sub>:</html>");
		GridBagConstraints gbc_lblfmax = new GridBagConstraints();
		gbc_lblfmax.fill = GridBagConstraints.BOTH;
		gbc_lblfmax.insets = new Insets(0, 0, 5, 5);
		gbc_lblfmax.gridx = 0;
		gbc_lblfmax.gridy = 0;
		panel_1.add(lblfmax, gbc_lblfmax);
		spnValueVoltage1.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				Voltage value = new Voltage();
				value.setValue((double) spnValueVoltage1.getValue());
				voltageDivider.setVoltage(0, value);
			}
		});
		
		spnValueVoltage1.setModel(new SpinnerNumberModel(new Double(0), new Double(0), null, new Double(1)));
		GridBagConstraints gbc_spnValueVoltage1 = new GridBagConstraints();
		gbc_spnValueVoltage1.fill = GridBagConstraints.BOTH;
		gbc_spnValueVoltage1.insets = new Insets(0, 0, 5, 5);
		gbc_spnValueVoltage1.gridx = 1;
		gbc_spnValueVoltage1.gridy = 0;
		panel_1.add(spnValueVoltage1, gbc_spnValueVoltage1);
		
		cbUnitWithPrefixVoltage1.setModel(new DefaultComboBoxModel(new String[] {"\u00B5V", "mV", "V", "kV", "MV"}));
		cbUnitWithPrefixVoltage1.setSelectedIndex(2);
		GridBagConstraints gbc_cbUnitWithPrefixVoltage1 = new GridBagConstraints();
		gbc_cbUnitWithPrefixVoltage1.fill = GridBagConstraints.BOTH;
		gbc_cbUnitWithPrefixVoltage1.insets = new Insets(0, 0, 5, 0);
		gbc_cbUnitWithPrefixVoltage1.gridx = 2;
		gbc_cbUnitWithPrefixVoltage1.gridy = 0;
		panel_1.add(cbUnitWithPrefixVoltage1, gbc_cbUnitWithPrefixVoltage1);
		
		JLabel lblspannungU = new JLabel("<html>Spannung U<sub>2</sub>:</html>");
		GridBagConstraints gbc_lblspannungU = new GridBagConstraints();
		gbc_lblspannungU.fill = GridBagConstraints.BOTH;
		gbc_lblspannungU.insets = new Insets(0, 0, 5, 5);
		gbc_lblspannungU.gridx = 0;
		gbc_lblspannungU.gridy = 1;
		panel_1.add(lblspannungU, gbc_lblspannungU);
		spnValueVoltage2.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				Voltage value = new Voltage();
				value.setValue((double) spnValueVoltage2.getValue());
				voltageDivider.setVoltage(1, value);
			}
		});
		
		spnValueVoltage2.setModel(new SpinnerNumberModel(new Double(0), new Double(0), null, new Double(1)));
		GridBagConstraints gbc_spnValueVoltage2 = new GridBagConstraints();
		gbc_spnValueVoltage2.fill = GridBagConstraints.BOTH;
		gbc_spnValueVoltage2.insets = new Insets(0, 0, 5, 5);
		gbc_spnValueVoltage2.gridx = 1;
		gbc_spnValueVoltage2.gridy = 1;
		panel_1.add(spnValueVoltage2, gbc_spnValueVoltage2);
		
		cbUnitWithPrefixVoltage2.setModel(new DefaultComboBoxModel(new String[] {"\u00B5V", "mV", "V", "kV", "MV"}));
		cbUnitWithPrefixVoltage2.setSelectedIndex(2);
		GridBagConstraints gbc_cbUnitWithPrefixVoltage2 = new GridBagConstraints();
		gbc_cbUnitWithPrefixVoltage2.fill = GridBagConstraints.BOTH;
		gbc_cbUnitWithPrefixVoltage2.insets = new Insets(0, 0, 5, 0);
		gbc_cbUnitWithPrefixVoltage2.gridx = 2;
		gbc_cbUnitWithPrefixVoltage2.gridy = 1;
		panel_1.add(cbUnitWithPrefixVoltage2, gbc_cbUnitWithPrefixVoltage2);
		
		JLabel lblGesamtspannungU = new JLabel("Gesamtspannung U:");
		GridBagConstraints gbc_lblGesamtspannungU = new GridBagConstraints();
		gbc_lblGesamtspannungU.fill = GridBagConstraints.BOTH;
		gbc_lblGesamtspannungU.insets = new Insets(0, 0, 0, 5);
		gbc_lblGesamtspannungU.gridx = 0;
		gbc_lblGesamtspannungU.gridy = 2;
		panel_1.add(lblGesamtspannungU, gbc_lblGesamtspannungU);
		spnValueTotalVoltage.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				Voltage value = new Voltage();
				value.setValue((double) spnValueTotalVoltage.getValue());
				voltageDivider.setTotalVoltage(value);
			}
		});
		
		spnValueTotalVoltage.setModel(new SpinnerNumberModel(new Double(0), new Double(0), null, new Double(1)));
		GridBagConstraints gbc_spnValueTotalVoltage = new GridBagConstraints();
		gbc_spnValueTotalVoltage.fill = GridBagConstraints.BOTH;
		gbc_spnValueTotalVoltage.insets = new Insets(0, 0, 0, 5);
		gbc_spnValueTotalVoltage.gridx = 1;
		gbc_spnValueTotalVoltage.gridy = 2;
		panel_1.add(spnValueTotalVoltage, gbc_spnValueTotalVoltage);
		
		cbUnitWithPrefixTotalVoltage.setModel(new DefaultComboBoxModel(new String[] {"\u00B5V", "mV", "V", "kV", "MV"}));
		cbUnitWithPrefixTotalVoltage.setSelectedIndex(2);
		GridBagConstraints gbc_cbUnitWithPrefixTotalVoltage = new GridBagConstraints();
		gbc_cbUnitWithPrefixTotalVoltage.fill = GridBagConstraints.BOTH;
		gbc_cbUnitWithPrefixTotalVoltage.gridx = 2;
		gbc_cbUnitWithPrefixTotalVoltage.gridy = 2;
		panel_1.add(cbUnitWithPrefixTotalVoltage, gbc_cbUnitWithPrefixTotalVoltage);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Verh\u00E4ltnis", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.weightx = 0.5;
		gbc_panel_2.insets = new Insets(0, 0, 5, 0);
		gbc_panel_2.weighty = 0.5;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 2;
		leftSplitPanePanel.add(panel_2, gbc_panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[] {180, 120, 40};
		gbl_panel_2.rowHeights = new int[] {26, 26, 26, 21};
		gbl_panel_2.columnWeights = new double[]{1.0, 1.0, 1.0};
		gbl_panel_2.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0};
		panel_2.setLayout(gbl_panel_2);
		
		JRadioButton rbRatio = new JRadioButton("Verh\u00E4ltnis:");
		rbRatio.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				spnRatioA.setEnabled(rbRatio.isSelected());
				spnRatioB.setEnabled(rbRatio.isSelected());
			}
		});
		buttonGroup.add(rbRatio);
		rbRatio.setSelected(true);
		GridBagConstraints gbc_rbRatio = new GridBagConstraints();
		gbc_rbRatio.fill = GridBagConstraints.BOTH;
		gbc_rbRatio.insets = new Insets(0, 0, 5, 5);
		gbc_rbRatio.gridx = 0;
		gbc_rbRatio.gridy = 0;
		panel_2.add(rbRatio, gbc_rbRatio);
		
		JPanel panel_3 = new JPanel();
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.gridwidth = 2;
		gbc_panel_3.insets = new Insets(0, 0, 5, 0);
		gbc_panel_3.gridx = 1;
		gbc_panel_3.gridy = 0;
		panel_2.add(panel_3, gbc_panel_3);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panel_3.rowHeights = new int[]{26, 0};
		gbl_panel_3.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_3.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_3.setLayout(gbl_panel_3);
		
		spnRatioA.setModel(new SpinnerNumberModel(new Double(0), new Double(0), null, new Double(1)));
		GridBagConstraints gbc_spnRatioA = new GridBagConstraints();
		gbc_spnRatioA.weightx = 0.5;
		gbc_spnRatioA.fill = GridBagConstraints.BOTH;
		gbc_spnRatioA.insets = new Insets(0, 0, 0, 5);
		gbc_spnRatioA.gridx = 0;
		gbc_spnRatioA.gridy = 0;
		panel_3.add(spnRatioA, gbc_spnRatioA);
		
		JLabel lblRatio = new JLabel(":");
		GridBagConstraints gbc_lblRatio = new GridBagConstraints();
		gbc_lblRatio.fill = GridBagConstraints.VERTICAL;
		gbc_lblRatio.insets = new Insets(0, 0, 0, 5);
		gbc_lblRatio.gridx = 1;
		gbc_lblRatio.gridy = 0;
		panel_3.add(lblRatio, gbc_lblRatio);
		
		spnRatioB.setModel(new SpinnerNumberModel(new Double(0), new Double(0), null, new Double(1)));
		GridBagConstraints gbc_spnRatioB = new GridBagConstraints();
		gbc_spnRatioB.weightx = 0.5;
		gbc_spnRatioB.fill = GridBagConstraints.BOTH;
		gbc_spnRatioB.gridx = 2;
		gbc_spnRatioB.gridy = 0;
		panel_3.add(spnRatioB, gbc_spnRatioB);
		
		JRadioButton rbFactor = new JRadioButton("Faktor:");
		rbFactor.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				spnFactor.setEnabled(rbFactor.isSelected());
			}
		});
		buttonGroup.add(rbFactor);
		GridBagConstraints gbc_rbFactor = new GridBagConstraints();
		gbc_rbFactor.fill = GridBagConstraints.BOTH;
		gbc_rbFactor.insets = new Insets(0, 0, 5, 5);
		gbc_rbFactor.gridx = 0;
		gbc_rbFactor.gridy = 1;
		panel_2.add(rbFactor, gbc_rbFactor);
		spnFactor.setEnabled(false);
		
		spnFactor.setModel(new SpinnerNumberModel(new Double(0), new Double(0), null, new Double(1)));
		GridBagConstraints gbc_spnFactor = new GridBagConstraints();
		gbc_spnFactor.fill = GridBagConstraints.BOTH;
		gbc_spnFactor.gridwidth = 2;
		gbc_spnFactor.insets = new Insets(0, 0, 5, 0);
		gbc_spnFactor.gridx = 1;
		gbc_spnFactor.gridy = 1;
		panel_2.add(spnFactor, gbc_spnFactor);
		
		JLabel lblRatioReference = new JLabel("Verh\u00E4ltnisbezug:");
		GridBagConstraints gbc_lblRatioReference = new GridBagConstraints();
		gbc_lblRatioReference.fill = GridBagConstraints.BOTH;
		gbc_lblRatioReference.insets = new Insets(0, 0, 5, 5);
		gbc_lblRatioReference.gridx = 0;
		gbc_lblRatioReference.gridy = 2;
		panel_2.add(lblRatioReference, gbc_lblRatioReference);
		
		JRadioButton rbRatioReference12 = new JRadioButton("<html>R<sub>1</sub> zu R<sub>2</sub></html>");
		rbRatioReference12.setSelected(true);
		buttonGroup_1.add(rbRatioReference12);
		GridBagConstraints gbc_rbRatioReference12 = new GridBagConstraints();
		gbc_rbRatioReference12.fill = GridBagConstraints.BOTH;
		gbc_rbRatioReference12.insets = new Insets(0, 0, 5, 5);
		gbc_rbRatioReference12.gridx = 1;
		gbc_rbRatioReference12.gridy = 2;
		panel_2.add(rbRatioReference12, gbc_rbRatioReference12);
		
		JRadioButton rbRatioReference21 = new JRadioButton("<html>R<sub>2</sub> zu R<sub>1</sub></html>");
		buttonGroup_1.add(rbRatioReference21);
		GridBagConstraints gbc_rbRatioReference21 = new GridBagConstraints();
		gbc_rbRatioReference21.fill = GridBagConstraints.BOTH;
		gbc_rbRatioReference21.insets = new Insets(0, 0, 0, 5);
		gbc_rbRatioReference21.gridx = 1;
		gbc_rbRatioReference21.gridy = 3;
		panel_2.add(rbRatioReference21, gbc_rbRatioReference21);
		
		JPanel rightSplitPanePanel = new JPanel();
		splitPane.setRightComponent(rightSplitPanePanel);
		rightSplitPanePanel.setLayout(new BorderLayout(0, 0));
		
		JPanel CanvasPanel = new JPanel();
		CanvasPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		rightSplitPanePanel.add(CanvasPanel, BorderLayout.CENTER);
		CanvasPanel.setLayout(new BorderLayout(0, 0));
		
		JSVGCanvas svgCanvas = new JSVGCanvas();
		svgCanvas.setUseUnixTextSelection(false);
		svgCanvas.setEnableImageZoomInteractor(false);
		CanvasPanel.add(svgCanvas);
		svgCanvas.setRecenterOnResize(false);
		svgCanvas.setBackground(CanvasPanel.getBackground());
		svgCanvas.setToolTipText("<html>Patrick-Emil Z\u00F6rner (Paddy)<br>https://commons.wikimedia.org/wiki/File:Spannungsteiler.svg</html>");
		/*
		File file = null;
	    String resource = "/com/myorg/foo.xml";
	    URL res = getClass().getResource(resource);
	    if (res.toString().startsWith("jar:")) {
	        try {
	            InputStream input = getClass().getResourceAsStream(resource);
	            file = File.createTempFile("tempfile", ".tmp");
	            OutputStream out = new FileOutputStream(file);
	            int read;
	            byte[] bytes = new byte[1024];

	            while ((read = input.read(bytes)) != -1) {
	                out.write(bytes, 0, read);
	            }
	            file.deleteOnExit();
	        } catch (IOException ex) {
	            Exceptions.printStackTrace(ex);
	        }
	    } else {
	        //this will probably work in your IDE, but not from a JAR
	        file = new File(res.getFile());
	    }
		*/
		svgCanvas.setURI((debugging ? "file:src/" : "") + "res/Spannungsteiler.svg");
	
		JPanel buttonPanelWithSeperator = new JPanel();
		rightSplitPanePanel.add(buttonPanelWithSeperator, BorderLayout.SOUTH);
		buttonPanelWithSeperator.setLayout(new BorderLayout(0, 0));
		
		JSeparator separator = new JSeparator();
		buttonPanelWithSeperator.add(separator, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		buttonPanelWithSeperator.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnReset = new JButton("Zur\u00FCcksetzen");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reset(); //Neue Berechnung vorbereiten
			}
		});
		panel.add(btnReset);
		
		JButton btnCalculate = new JButton("Berechnen");
		btnCalculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				calculate(); //Berechnung durchführen
			}
		});
		panel.add(btnCalculate);
		
		reset(); //Neue Berechnung vorbereiten
	}

	/**
	 * Methode, die beim Programmstart ausgeführt wird und ein neues Objekt der Klasse Covfefe erstellt
	 * @param args Programmparameter
	 */
	public static void main(String[] args) {
		//Kommandozeilenparameter auswerten
		for (int i = 0; i < args.length; i++) {
			if (args[i].equalsIgnoreCase("debugging")) {
				debugging = true;
				System.out.println("Debugging-Modus aktiviert");
			}
	      } 
		new Covfefe(); //Hauptfenster initialisieren
	}
	
	/**
	 * Alle Eingaben zurücksetzen
	 */
	public void reset() {
		voltageDivider = new VoltageDivider(); //Neue Berechnung starten
		cbESeries.setSelectedIndex(3); //E-Reihe auswählen
		refreshValues(); //Werte in GUI übernehmen
	}
	
	/**
	 * Eingaben auf Gültigkeit überprüfen und Berechnung durchführen
	 */
	public void calculate() {
		//Verfügbare Eingabeparamater
		InputParameter parameter = new InputParameter();
		//### Todo -^
		//### Todo: Werte aus GUI einlesen - changeEvents //Eingaben übernehmen
		
		//voltageDivider.isValidInput(parameter)) {
			voltageDivider.calculateValues(); //Berechnung durchführen
			System.out.println(voltageDivider.toString());
			refreshValues(); //Werte in GUI übernehmen
			SystemSounds.play(SystemSounds.Sound.Asterisk); //Sound abspielen
			/*
		}
		else {
			//Ungültige Eingabe anzeigen
			SystemSounds.play(SystemSounds.Sound.Hand);
			JOptionPane.showMessageDialog(null, "Die Eingabe ist ungültig.", "Covfefe: Fehler", JOptionPane.ERROR_MESSAGE); //Fehlermeldung
			//### Todo: Anzeige auf GUI
		}
		*/
	}
	
	private void refreshValues() {
		spnValueResistor1.setValue(voltageDivider.getResistor(0).getResistance().getValue()); //Widerstandswert R1
		//cbUnitWithPrefixResistor1.setSelectedIndex(3);
		spnMinResistor1.setValue(voltageDivider.getMinResistance(0).getValue()); //Mindestwert R1
		//cbUnitWithPrefixMinResistor1.setSelectedIndex(2);
		spnValueResistor2.setValue(voltageDivider.getResistor(1).getResistance().getValue()); //Widerstandswert R2
		//cbUnitWithPrefixResistor2.setSelectedIndex(3);
		spnMinResistor2.setValue(voltageDivider.getMinResistance(1).getValue()); //Mindestwert R2
		//cbUnitWithPrefixMinResistor2.setSelectedIndex(3);
		spnValueTotalResistor.setValue(voltageDivider.getTotalResistor().getValue());
		//cbUnitWithPrefixTotalResistor.setSelectedIndex(3);
		spnValueVoltage1.setValue(voltageDivider.getVoltage(0).getValue());
		//cbUnitWithPrefixVoltage1.setSelectedIndex(2);
		spnValueVoltage2.setValue(voltageDivider.getVoltage(1).getValue());
		//cbUnitWithPrefixVoltage2.setSelectedIndex(2);
		spnValueTotalVoltage.setValue(voltageDivider.getTotalVoltage().getValue());
		//cbUnitWithPrefixTotalVoltage.setSelectedIndex(2);
		spnRatioA.setValue(1.4);
		spnRatioB.setValue(1);
		spnFactor.setValue(1.4);
	}
}