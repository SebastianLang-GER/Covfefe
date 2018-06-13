package covfefe;

import java.lang.reflect.Parameter;

/**
 * Klasse, welche die Berechnungen durchführt.
 * @author Dominik Thörmer, Tobias Vöth
 * @version 13.06.2018
 */
public class VoltageDivider {
	
	private boolean calculationCompleted;
	private Resistor[] resistors;
	private Resistor[] minResistors;
	private Voltage[] voltages;
	private Voltage totalVoltage;
	private Resistor totalResistor;
	private double ratio;
	InputParameter validParameter;
	
	public VoltageDivider() {
		calculationCompleted = false;
		resistors = new Resistor[2];
		minResistors = new Resistor[2];
		voltages = new Voltage[2];
		ratio = 0;
		validParameter = new InputParameter();
	}
	
	/**
	 * @return the calculationCompleted
	 */
	public boolean getCalculationCompleted() {
		return calculationCompleted;
	}
	
	/**
	 * @param resistors the resistors to set
	 */
	public void setResistor(int index, Resistor value) {
		if (index == 0 && index == 1) {
			this.resistors[index] = value;
			if(index == 0) {
				validParameter.setResistor1(true);
			}
			else validParameter.setResistor2(true);
		}
	}
	
	/**
	 * @param voltages the voltages to set
	 */
	public void setTotalResistor(Resistor value) {
		this.totalResistor = value;
		validParameter.setTotalResistor(true);
	}
	
	/**
	 * @param voltages the voltages to set
	 */
	public Resistor getTotalResistor() {
		return this.totalResistor;
	}

	/**
	 * @return the resistors
	 */
	public Resistor getResistor(int index) {
		if (index == 0 && index == 1) {
			return this.resistors[index];
		}
		return null;
	}
	
	/**
	 * @param voltages the voltages to set
	 */
	public void setVoltage(int index, Voltage value) {
		if (index == 0 && index == 1) {
			this.voltages[index] = value;
			if(index == 0) {
				validParameter.setVoltage1(true);
			}
			else validParameter.setVoltage2(true);
		}
	}
	
	/**
	 * @param voltages the voltages to set
	 */
	public void setTotalVoltage(Voltage value) {
		this.totalVoltage = value;
		validParameter.setTotalVoltage(true);
	}
	
	/**
	 * @return the voltages
	 */
	public Voltage getVoltage(int index) {
		if (index == 0 && index == 1) {
			return this.voltages[index];
		}
		return null;
	}
	
	/**
	 * @param voltages the voltages to set
	 */
	public Voltage getTotalVoltage() {
		return this.totalVoltage;
	}
	
	/**
	 * @param minResistors the minResistors to set
	 */
	public void setMinResistor(int index, Resistor value) {
		if (index == 0 && index == 1) {
			this.minResistors[index] = value;
			if(index == 0) {
				validParameter.setMinResistor1(true);
			}
			else validParameter.setMinResistor2(true);
		}
	}

	/**
	 * @return the minResistors
	 */
	public Resistor getMinResistor(int index) {
		if (index == 0 && index == 1) {
			return this.minResistors[index];
		}
		return null;
	}
	
	/**
	 * @param ratio the ratio to set
	 */
	public void setRatio(double resistor1, double resistor2) {
		this.ratio = (resistor1/resistor2);
		validParameter.setRatio(true);
	}
	
	/**
	 * @param ratio the ratio to set
	 */
	public void setRatioResistor1toResistor2(double value) {
		this.ratio = value;
		validParameter.setRatio(true);
	}
	
	/**
	 * @param ratio the ratio to set
	 */
	public double getRatioResistor1toResistor2() {
		return this.ratio;
	}
	
	/**
	 * @param ratio the ratio to set
	 */
	public void setRatioResistor2toResistor1(double value) {
		this.ratio = (1/value);
		validParameter.setRatio(true);
	}
	
	/**
	 * @param ratio the ratio to set
	 */
	public double getRatioResistor2toResistor1() {
		return (1/this.ratio);
	}
	
	/**
	 * @param ratio the ratio to set
	 */
	public boolean isValidInput() {
		int counterVoltage = 0;
		int counterResistor = 0;
		int counterMinResistor = 0;
		
		if(validParameter.getTotalVoltage() == true) counterVoltage++;
		if(validParameter.getVoltage1() == true) counterVoltage++;
		if(validParameter.getVoltage2() == true) counterVoltage++;
		
		if(validParameter.getTotalResistor() == true) counterResistor++;
		if(validParameter.getResistor1() == true) counterResistor++;
		if(validParameter.getResistor2() == true) counterResistor++;
		
		if(validParameter.getMinResistor1() == true) counterMinResistor++;
		if(validParameter.getMinResistor2() == true) counterMinResistor++;
		
		if(validParameter.getRatio() == true) {
			
			if(counterVoltage != 1) return false;
			if((counterMinResistor == 0 && counterResistor == 1) || (counterMinResistor >= 0 && counterResistor == 0)) return true;
			return false;
		}
		else {
			if(counterVoltage == 2 && ((counterMinResistor == 0 && counterResistor == 1) || (counterMinResistor >= 0 && counterResistor == 0))) return true;
			if(counterResistor == 2 && counterMinResistor == 0 && counterVoltage == 1) return true;
			return false;
		}
	}
}
