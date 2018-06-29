package covfefe;

/**
 * Klasse, zur Übergabe von zur Verfügung stehenden Eingabeparametern
 * @author Shayan Jani
 * @version 13.06.2018
 */
public class InputParameter {
	
	private boolean totalVoltage;
	private boolean voltage1;
	private boolean voltage2;
	private boolean totalResistor;
	private boolean resistor1;
	private boolean resistor2;
	private boolean minResistor1;
	private boolean minResistor2;
	private boolean ratio;
	
	/**
	 * Standardkonstruktor zum Erzeugen von Objekten der Klasse InputParameter
	 */
	public InputParameter() {
		this(false, false, false, false,false, false, false, false, false);
	}
	
	/**
	 * Konstruktor zum Erzeugen von Objekten der Klasse InputParameter mit Festlegen der Parameter
	 * @param totalVoltage Attribut totalVoltage
	 * @param voltage1 Attribut voltage1
	 * @param voltage2 Attribut voltage2
	 * @param totalResistor Attribut totalResistor
	 * @param resistor1 Attribut resistor1
	 * @param resistor2 Attribut resistor2
	 * @param minResistor1 Attribut minResistor1
	 * @param minResistor2 Attribut minResistor2
	 * @param ratio Attribut ratio
	 */
	public InputParameter(boolean totalVoltage, boolean voltage1, boolean voltage2, boolean totalResistor, boolean resistor1, boolean resistor2, boolean minResistor1, boolean minResistor2, boolean ratio ) {
		this.totalVoltage = totalVoltage;
		this.voltage1 = voltage1;
		this.voltage2 = voltage2;
		this.totalResistor = totalResistor;
		this.resistor1 = resistor1;
		this.resistor2 = resistor2;
		this.minResistor1 = minResistor1;
		this.minResistor2 = minResistor2;
		this.ratio = ratio;
	}
	
	/**
	 * Festlegen oder ändern des Attributs totalVoltage
	 * @param value Attribut totalVoltage
	 */
	public void setTotalVoltage(boolean value) {
		totalVoltage = value;
	}
	
	/**
	 * Rückgabe des Attributs totalVoltage
	 * @return Attribut totalVoltage
	 */
	public boolean getTotalVoltage() {
		return totalVoltage;
	}
	
	/**
	 * Festlegen oder ändern des Attributs voltage1 
	 * @param value Attribut voltage1
	 */
	public void setVoltage1(boolean value) {
		voltage1 = value;
	}
	
	/**
	 * Rückgabe des Attributs voltage1
	 * @return Attribut voltage1
	 */
	public boolean getVoltage1() {
		return voltage1;
	}
	
	/**
	 * Festlegen oder ändern des Attributs voltage2 
	 * @param value Attribut voltage2
	 */
	public void setVoltage2(boolean value) {
		voltage2 = value;
	}
	
	/**
	 * Rückgabe des Attributs voltage2 
	 * @return Attribut voltage2
	 */
	public boolean getVoltage2() {
		return voltage2;
	}
		
	/**
	 * Festlegen oder ändern des Attributs totalResistor 
	 * @param value Attribut totalResistor
	 */
	public void setTotalResistor(boolean value) {
		totalResistor = value;
	}

	/**
	 * Rückgabe des Attributs totalResistor 
	 * @return Attribut totalResistor
	 */
	public boolean getTotalResistor() {
		return totalResistor;
	}
		
	/**
	 * Festlegen oder ändern des Attributs resistor1 
	 * @param value Attribut resistor1
	 */
	public void setResistor1(boolean value) {
		resistor1 = value;
	}

	/**
	 * Rückgabe des Attributs resistor1 
	 * @return Attribut resistor1
	 */
	public boolean getResistor1() {
		return resistor1;
	}
	
	/**
	 * Festlegen oder ändern des Attributs resistor2 
	 * @param value Attribut resistor2
	 */
	public void setResistor2(boolean value) {
		resistor2 = value;
	}
	
	/**
	 * Rückgabe des Attributs resistor2 
	 * @return Attribut resistor2
	 */
	public boolean getResistor2() {
		return resistor2;
	}
	
	/**
	 * Festlegen oder ändern des Attributs minResistor1
	 * @param value Attribut minResistor1
	 */
	public void setMinResistor1(boolean value) {
		minResistor1 = value;
	}
	
	/**
	 * Rückgabe des Attributs minResistor1 
	 * @return Attribut minResistor1
	 */
	public boolean getMinResistor1() {
		return minResistor1;
	}

	/**
	 * Festlegen oder ändern des Attributs minResistor2 
	 * @param value Attribut minResistor2
	 */
	public void setMinResistor2(boolean value) {
		minResistor2 = value;
	}
	
	/**
	 * Rückgabe des Attributs minResistor2 
	 * @return Attribut minResistor2
	 */
	public boolean getMinResistor2() {
		return minResistor2;
	}
	
	/**
	 * Festlegen oder ändern des Attributs ratio 
	 * @param value Attribut ratio
	 */
	public void setRatio(boolean value) {
		ratio = value;
	}
	
	/**
	 *  Rückgabe des Attributs ratio 
	 * @return Attribut ratio
	 */
	public boolean getRatio() {
		return ratio;
	}
}