package covfefe;

/**
 * Klasse, zur �bergabe von zur Verf�gung stehenden Eingabeparametern
 * @author Shayan Jani
 * @version 13.06.2018
 */
public class InputParameter {
	
	private boolean totalVoltage = false;
	private boolean voltage1 = false;
	private boolean voltage2 = false;
	private boolean totalResistor = false;
	private boolean resistor1 = false;
	private boolean resistor2 = false;
	private boolean minResistor1 = false;
	private boolean minResistor2 = false;
	private boolean ratio = false;
	
	/**
	 * Festlegen oder �ndern des Attributs totalVoltage
	 * @param value Attribut totalVoltage
	 */
	public void setTotalVoltage(boolean value) {
		totalVoltage = value;
	}
	
	/**
	 * R�ckgabe des Attributs totalVoltage
	 * @return Attribut totalVoltage
	 */
	public boolean getTotalVoltage() {
		return totalVoltage;
	}
	
	/**
	 * Festlegen oder �ndern des Attributs voltage1 
	 * @param value Attribut voltage1
	 */
	public void setVoltage1(boolean value) {
		voltage1 = value;
	}
	
	/**
	 * R�ckgabe des Attributs voltage1
	 * @return Attribut voltage1
	 */
	public boolean getVoltage1() {
		return voltage1;
	}
	
	/**
	 * Festlegen oder �ndern des Attributs voltage2 
	 * @param value Attribut voltage2
	 */
	public void setVoltage2(boolean value) {
		voltage2 = value;
	}
	
	/**
	 * R�ckgabe des Attributs voltage2 
	 * @return Attribut voltage2
	 */
	public boolean getVoltage2() {
		return voltage2;
	}
		
	/**
	 * Festlegen oder �ndern des Attributs totalResistor 
	 * @param value Attribut totalResistor
	 */
	public void setTotalResistor(boolean value) {
		totalResistor = value;
	}

	/**
	 * R�ckgabe des Attributs totalResistor 
	 * @return Attribut totalResistor
	 */
	public boolean getTotalResistor() {
		return totalResistor;
	}
		
	/**
	 * Festlegen oder �ndern des Attributs resistor1 
	 * @param value Attribut resistor1
	 */
	public void setResistor1(boolean value) {
		resistor1 = value;
	}

	/**
	 * R�ckgabe des Attributs resistor1 
	 * @return Attribut resistor1
	 */
	public boolean getResistor1() {
		return resistor1;
	}
	
	/**
	 * Festlegen oder �ndern des Attributs resistor2 
	 * @param value Attribut resistor2
	 */
	public void setResistor2(boolean value) {
		resistor2 = value;
	}
	
	/**
	 * R�ckgabe des Attributs resistor2 
	 * @return Attribut resistor2
	 */
	public boolean getResistor2() {
		return resistor2;
	}
	
	/**
	 * Festlegen oder �ndern des Attributs minResistor1
	 * @param value Attribut minResistor1
	 */
	public void setMinResistor1(boolean value) {
		minResistor1 = value;
	}
	
	/**
	 * R�ckgabe des Attributs minResistor1 
	 * @return Attribut minResistor1
	 */
	public boolean getMinResistor1() {
		return minResistor1;
	}

	/**
	 * Festlegen oder �ndern des Attributs minResistor2 
	 * @param value Attribut minResistor2
	 */
	public void setMinResistor2(boolean value) {
		minResistor2 = value;
	}
	
	/**
	 * R�ckgabe des Attributs minResistor2 
	 * @return Attribut minResistor2
	 */
	public boolean getMinResistor2() {
		return minResistor2;
	}
	
	/**
	 * Festlegen oder �ndern des Attributs ratio 
	 * @param value Attribut ratio
	 */
	public void setRatio(boolean value) {
		ratio = value;
	}
	
	/**
	 *  R�ckgabe des Attributs ratio 
	 * @return Attribut ratio
	 */
	public boolean getRatio() {
		return ratio;
	}
}