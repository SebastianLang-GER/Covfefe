package covfefe;

import java.lang.reflect.Parameter;

/**
 * Klasse, welche die Berechnungen durchführt.
 * @author Dominik Thörmer, Tobias Vöth
 * @version 14.06.2018
 */
public class VoltageDivider {
	
	private boolean calculationCompleted;
	private Resistor[] resistors;
	private Resistor[] minResistors;
	private Voltage[] voltages;
	private Voltage totalVoltage;
	private Resistor totalResistor;
	private double ratio;								//Resistor 1 zu Resistor 2
	private InputParameter validParameter;
	
	/**
	 *  Konstruktor zum Erzeugen von Objekten der Klasse VoltageDivider
	 */
	public VoltageDivider() {
		calculationCompleted = false;
		resistors = new Resistor[2];
		minResistors = new Resistor[2];
		voltages = new Voltage[2];
		ratio = 0;
	}
	
	/**
	 * Rückgabe, ob die Berechnung beendet ist
	 * @return Berechnung beendet
	 */
	public boolean getCalculationCompleted() {
		return calculationCompleted;
	}
	
	/**
	 * Festlegen oder ändern eines Widerstandes
	 * @param index	Nummer des Widerstands (0 oder 1)
	 * @param value Widerstand
	 */
	public void setResistor(int index, Resistor value) {
		if (index == 0 && index == 1) {
			this.resistors[index] = value;
		}
	}
	
	/**
	 * Festlegen oder ändern des Gesamtwiderstandes
	 * @param value Gesamtwiderstand
	 */
	public void setTotalResistor(Resistor value) {
		this.totalResistor = value;
	}
	
	/**
	 * Rückgabe des Gesamtwiderstands
	 * @return Gesamtwiderstand
	 */
	public Resistor getTotalResistor() {
		return this.totalResistor;
	}

	/**
	 * Rückgabe eines Widerstandes
	 * @param index Nummer des Widerstands (0 oder 1)
	 * @return Widerstand
	 */
	public Resistor getResistor(int index) {
		if (index == 0 && index == 1) {
			return this.resistors[index];
		}
		return null;
	}
	
	/**
	 * Festlegen oder ändern einer Spannung
	 * @param index Nummer der Spannung (0 oder 1)
	 * @param value Spannung
	 */
	public void setVoltage(int index, Voltage value) {
		if (index == 0 && index == 1) {
			this.voltages[index] = value;
		}
	}
	
	/**
	 * Rückgabe des Gesamtwiderstands
	 * @param value Gesamtwiderstand
	 */
	public void setTotalVoltage(Voltage value) {
		this.totalVoltage = value;
	}
	
	/**
	 * Rückgabe einer Spannung
	 * @param index Nummer der Spannung (0 oder 1)
	 * @return Spannung
	 */
	public Voltage getVoltage(int index) {
		if (index == 0 && index == 1) {
			return this.voltages[index];
		}
		return null;
	}
	
	/**
	 *  Rückgabe der Gesamtspannung
	 * @return Gesamtspannung
	 */
	public Voltage getTotalVoltage() {
		return this.totalVoltage;
	}
	
	/**
	 * Festlegen oder ändern eines Mindestwiderstandes
	 * @param index Nummer des Mindestwiderstandes (0 oder 1)
	 * @param value Mindestwiderstand
	 */
	public void setMinResistor(int index, Resistor value) {
		if (index == 0 && index == 1) {
			this.minResistors[index] = value;
		}
	}

	/**
	 * Rückgabe des Mindestwiderstands
	 * @param index Nummer des Mindestwiderstandes (0 oder 1)
	 * @return Mindestwiderstand
	 */
	public Resistor getMinResistor(int index) {
		if (index == 0 && index == 1) {
			return this.minResistors[index];
		}
		return null;
	}
	
	/**
	 * Festlegen oder ändern des Verhätlnisses
	 * @param resistor1 Widerstand 1
	 * @param resistor2 Widerstand 2
	 */
	public void setRatio(double resistor1, double resistor2) {
		this.ratio = (resistor1/resistor2);
	}
	
	/**
	 * Festlegen oder ändern des Verhätlnisses
	 * @param value Verhältnis zwischen Widerstand 1 zu Widerstand 2
	 */
	public void setRatioResistor1toResistor2(double value) {
		this.ratio = value;
	}
	
	/**
	 * Rückgabe des Verhätnisses zwischen Widerstand 1 und Widerstand 2
	 * @return Verhältnis zwischen Widerstand 1 und Widerstand 2
	 */
	public double getRatioResistor1toResistor2() {
		return this.ratio;
	}
	
	/**
	 * Festlegen oder ändern des Verhätlnisses zwischen Widerstand 2 und Widerstand 1
	 * @param value Verhältnis zwischen Widerstand 2 und Widerstand 1
	 */
	public void setRatioResistor2toResistor1(double value) {
		this.ratio = (1/value);
	}
	
	/**
	 * Rückgabe des Verhätnisses zwischen Widerstand 2 und Widerstand 1
	 * @return Verhältnis zwischen Widerstand 2 und Widerstand 1
	 */
	public double getRatioResistor2toResistor1() {
		return (1/this.ratio);
	}
	
	/**
	 * Prüft ob Eingabe gültig ist
	 * @param parameter Zu prüfender Parameter
	 * @return Gültigkeit
	 */
	public boolean isValidInput(InputParameter parameter) {
		this.validParameter = parameter;
		return isValidInput();
	}
	
	/**
	 * Prüft ob Eingabe gültig ist
	 * @return Gültigkeit
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
	
	/**
	 * Berechnet alle fehlenden Werte
	 */
	public void calculateValues() {
		int counterVoltage = 0;
		int counterResistor = 0;
		Resistance Probe = new Resistance();
			
		if(validParameter.getTotalVoltage() == true) counterVoltage++;
		if(validParameter.getVoltage1() == true) counterVoltage++;
		if(validParameter.getVoltage2() == true) counterVoltage++;
		
		if(validParameter.getTotalResistor() == true) counterResistor++;
		if(validParameter.getResistor1() == true) counterResistor++;
		if(validParameter.getResistor2() == true) counterResistor++;
		
		
		if(validParameter.getRatio()==false) {
			if(counterVoltage == 2) {
				if(validParameter.getVoltage2() == false) {
					this.voltages[1].setValue(this.totalVoltage.getValue() - this.voltages[0].getValue());
				}
				
				if(validParameter.getVoltage1() == false) {
					this.voltages[0].setValue(this.totalVoltage.getValue() - this.voltages[1].getValue());					
				}
				
				if(validParameter.getTotalVoltage() == false) {
					this.totalVoltage.setValue(this.voltages[0].getValue() + this.voltages[1].getValue());				
				}
				
				this.ratio = (this.voltages[0].getValue() / this.voltages[1].getValue());
				if(validParameter.getTotalResistor() == true) {
					Probe.setValue(this.totalResistor.getResistance().getValue()/(this.ratio + 1));
					this.resistors[1].chooseResistor(Probe);
					Probe.setValue(this.totalResistor.getResistance().getValue() - this.resistors[1].getResistance().getValue());
					this.resistors[0].chooseResistor(Probe);
				}
				
				if(validParameter.getResistor1() == true) {
					Probe.setValue(this.resistors[0].getResistance().getValue() * this.ratio);
					this.resistors[1].chooseResistor(Probe);
					Probe.setValue(this.resistors[0].getResistance().getValue() + this.resistors[1].getResistance().getValue());
					this.totalResistor.chooseResistor(Probe);
				}
				
				if(validParameter.getResistor2() == true) {
					Probe.setValue(this.resistors[1].getResistance().getValue() / this.ratio);
					this.resistors[0].chooseResistor(Probe);
					Probe.setValue(this.resistors[0].getResistance().getValue() + this.resistors[1].getResistance().getValue());
					this.totalResistor.chooseResistor(Probe);
				}
				
				if(validParameter.getMinResistor1() == true && validParameter.getMinResistor2() == false) {
					Probe.setValue(this.minResistors[0].getResistance().getValue());
					this.resistors[0].chooseResistor(Probe);
					Probe.setValue(this.resistors[0].getResistance().getValue() / this.ratio);
					this.resistors[1].chooseResistor(Probe);
					Probe.setValue(this.resistors[0].getResistance().getValue() + this.resistors[1].getResistance().getValue());
					this.totalResistor.chooseResistor(Probe);	
				}
				
				if(validParameter.getMinResistor1() == false && validParameter.getMinResistor2() == true) {
					Probe.setValue(this.minResistors[1].getResistance().getValue());
					this.resistors[1].chooseResistor(Probe);
					Probe.setValue(this.resistors[1].getResistance().getValue() * this.ratio);
					this.resistors[0].chooseResistor(Probe);
					Probe.setValue(this.resistors[0].getResistance().getValue() + this.resistors[1].getResistance().getValue());
					this.totalResistor.chooseResistor(Probe);	
				}
				
				if(validParameter.getMinResistor1() == false && validParameter.getMinResistor2() == false) {
					Probe.setValue(1.0);
					this.resistors[0].chooseResistor(Probe);
					Probe.setValue(this.resistors[0].getResistance().getValue() * this.ratio);
					this.resistors[1].chooseResistor(Probe);
					Probe.setValue(this.resistors[0].getResistance().getValue() + this.resistors[1].getResistance().getValue());
					this.totalResistor.chooseResistor(Probe);	
				}
				
				if(validParameter.getMinResistor1() == true && validParameter.getMinResistor2() == true) {
					Probe.setValue(this.minResistors[0].getResistance().getValue());
					this.resistors[0].chooseResistor(Probe);
					Probe.setValue(this.resistors[0].getResistance().getValue() / this.ratio);
					this.resistors[1].chooseResistor(Probe);
					if(this.resistors[1].getResistance().getValue() < this.minResistors[1].getResistance().getValue()) {
						Probe.setValue(this.minResistors[1].getResistance().getValue());
						this.resistors[1].chooseResistor(Probe);
						Probe.setValue(this.resistors[1].getResistance().getValue() * this.ratio);
						this.resistors[0].chooseResistor(Probe);
					}
					Probe.setValue(this.resistors[0].getResistance().getValue() + this.resistors[1].getResistance().getValue());
					this.totalResistor.chooseResistor(Probe);
				}
			}
			
			if(counterResistor == 2) {
				if(validParameter.getResistor2() == false) {
					Probe.setValue(this.totalResistor.getResistance().getValue() - this.resistors[0].getResistance().getValue()) ;
					this.resistors[1].chooseResistor(Probe);
				}
				if(validParameter.getResistor1() == false) {
					Probe.setValue(this.totalResistor.getResistance().getValue() - this.resistors[1].getResistance().getValue());
					this.resistors[0].chooseResistor(Probe);
				}
				if(validParameter.getTotalResistor() == false) {
					Probe.setValue(this.resistors[0].getResistance().getValue() + this.resistors[1].getResistance().getValue());	
					this.totalResistor.chooseResistor(Probe);
				}
				this.ratio = (this.resistors[0].getResistance().getValue() / this.resistors[1].getResistance().getValue());
				if(validParameter.getTotalVoltage() == true) {
					this.voltages[1].setValue(this.totalVoltage.getValue() / (this.ratio+1));
					this.voltages[0].setValue(this.totalVoltage.getValue() - this.voltages[1].getValue());
				}
				if(validParameter.getVoltage1() == true) {
					this.voltages[0].setValue(this.voltages[1].getValue() * this.ratio);
					this.totalVoltage.setValue(this.voltages[0].getValue() + this.voltages[1].getValue());
				}
				if(validParameter.getVoltage2() == true) {
					this.voltages[1].setValue(this.voltages[0].getValue() / this.ratio);
					this.totalVoltage.setValue(this.voltages[1].getValue() + this.voltages[0].getValue());
				}
			}
		}
		
		else {
			if(validParameter.getTotalVoltage() == true) {
				this.voltages[1].setValue(this.totalVoltage.getValue() / (this.ratio+1));
				this.voltages[0].setValue(this.totalVoltage.getValue() - this.voltages[1].getValue());
			}
			if(validParameter.getVoltage1() == true) {
				this.voltages[0].setValue(this.voltages[1].getValue() * this.ratio);
				this.totalVoltage.setValue(this.voltages[0].getValue() + this.voltages[1].getValue());
			}
			if(validParameter.getVoltage2() == true) {
				this.voltages[1].setValue(this.voltages[0].getValue() / this.ratio);
				this.totalVoltage.setValue(this.voltages[1].getValue() + this.voltages[0].getValue());
			}
			
			if(validParameter.getTotalResistor() == true) {
				Probe.setValue(this.totalResistor.getResistance().getValue()/(this.ratio + 1));
				this.resistors[1].chooseResistor(Probe);
				Probe.setValue(this.totalResistor.getResistance().getValue() - this.resistors[1].getResistance().getValue());
				this.resistors[0].chooseResistor(Probe);
			}
			if(validParameter.getResistor1() == true) {
				Probe.setValue(this.resistors[1].getResistance().getValue() * this.ratio);
				this.resistors[1].chooseResistor(Probe);
				Probe.setValue(this.resistors[0].getResistance().getValue() + this.resistors[1].getResistance().getValue());
				this.totalResistor.chooseResistor(Probe);
			}
			if(validParameter.getResistor2() == true) {
				Probe.setValue(this.resistors[0].getResistance().getValue() / this.ratio);
				this.resistors[1].chooseResistor(Probe);
				Probe.setValue(this.resistors[0].getResistance().getValue() + this.resistors[1].getResistance().getValue());
				this.totalResistor.chooseResistor(Probe);
			}
			if(validParameter.getMinResistor1() == true && validParameter.getMinResistor2() == false) {
				Probe.setValue(this.minResistors[0].getResistance().getValue());
				this.resistors[0].chooseResistor(Probe);
				Probe.setValue(this.resistors[0].getResistance().getValue() / this.ratio);
				this.resistors[1].chooseResistor(Probe);
				Probe.setValue(this.resistors[0].getResistance().getValue() + this.resistors[1].getResistance().getValue());
				this.totalResistor.chooseResistor(Probe);	
			}
			
			if(validParameter.getMinResistor1() == false && validParameter.getMinResistor2() == true) {
				Probe.setValue(this.minResistors[1].getResistance().getValue());
				this.resistors[1].chooseResistor(Probe);
				Probe.setValue(this.resistors[1].getResistance().getValue() * this.ratio);
				this.resistors[0].chooseResistor(Probe);
				Probe.setValue(this.resistors[0].getResistance().getValue() + this.resistors[1].getResistance().getValue());
				this.totalResistor.chooseResistor(Probe);	
			}
			
			if(validParameter.getMinResistor1() == false && validParameter.getMinResistor2() == false) {
				Probe.setValue(1.0);
				this.resistors[0].chooseResistor(Probe);
				Probe.setValue(this.resistors[0].getResistance().getValue() * this.ratio);
				this.resistors[1].chooseResistor(Probe);
				Probe.setValue(this.resistors[0].getResistance().getValue() + this.resistors[1].getResistance().getValue());
				this.totalResistor.chooseResistor(Probe);
			}
			
			if(validParameter.getMinResistor1() == true && validParameter.getMinResistor2() == true) {
				Probe.setValue(this.minResistors[0].getResistance().getValue());
				this.resistors[0].chooseResistor(Probe);
				Probe.setValue(this.resistors[0].getResistance().getValue() / this.ratio);
				this.resistors[1].chooseResistor(Probe);
				if(this.resistors[1].getResistance().getValue() < this.minResistors[1].getResistance().getValue()) {
					Probe.setValue(this.minResistors[1].getResistance().getValue());
					this.resistors[1].chooseResistor(Probe);
					Probe.setValue(this.resistors[1].getResistance().getValue() * this.ratio);
					this.resistors[0].chooseResistor(Probe);
				}
				Probe.setValue(this.resistors[0].getResistance().getValue() + this.resistors[1].getResistance().getValue());
				this.totalResistor.chooseResistor(Probe);
			}
		}
		this.calculationCompleted = true;	
	}
	
	/**
	 * Rückgabe aller Attribute und Eigenschaften der Klasse als Zeichenkette 
	 */
	public String toString() {
		return ("Total Voltage: "  + this.totalVoltage.toString() + "\nVoltage 1: " + this.voltages[0].toString() +"\nVoltage 2: " + this.voltages[1].toString()
				+ "\nTotal Resistance: " + this.totalResistor.toString() + "\nResistor 1: " + this.resistors[0].toString() + "\nResistor 2: " + this.resistors[1].toString()
				+ "\nMin Resistance 1: " + this.minResistors[0].toString() + "\nResistance 2: " + this.minResistors[1].toString()
				+ "\nRatio :" + this.ratio + "\nCalculation Completed: " + this.calculationCompleted);
	}
}