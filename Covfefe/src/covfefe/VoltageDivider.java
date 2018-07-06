package covfefe;

/**
 * Klasse, die einen Spannungsteiler repr�sentiert und Methoden zu dessen Berechnungen enth�lt
 * @author Dominik Th�rmer, Tobias V�th
 * @version 06.07.2018
 */
public class VoltageDivider {
	
	private ESeriesTemplate eSeries;
	private boolean inputParameterChecked;
	private boolean calculationCompleted;
	private Resistor[] resistors;
	private Resistance[] minResistance;
	private Voltage[] voltages;
	private Voltage totalVoltage;
	private Resistance totalResistor;
	private double ratio; //Resistor 1 zu Resistor 2
	private InputParameter validParameter;
	
	/**
	 *  Konstruktor zum Erzeugen von Objekten der Klasse VoltageDivider
	 */
	public VoltageDivider() {
		eSeries = ESeries.E24;
		inputParameterChecked = false;
		calculationCompleted = false;
		resistors = new Resistor[2];
		for (int i = 0; i < resistors.length; i++) {
			resistors[i] = new Resistor(new Resistance(), eSeries);
		}
		minResistance = new Resistance[2];
		for (int i = 0; i < minResistance.length; i++) {
			minResistance[i] = new Resistance();
		}
		voltages = new Voltage[2];
		for (int i = 0; i < voltages.length; i++) {
			voltages[i] = new Voltage();
		}
		totalVoltage = new Voltage();
		totalResistor = new Resistance();
		ratio = 0;
	}
	
	/**
	 * R�ckgabe der E-Reihe
	 * @return E-Reihe
	 */
	public ESeriesTemplate getESeries() {
		return eSeries;
	}
	
	/**
	 * Festelegen der E-Reihe
	 * @param eSeries E-Reihe
	 */
	public void setESeries(ESeriesTemplate eSeries) {
		this.eSeries = eSeries;
	}
	
	/**
	 * R�ckgabe, ob die eingegebenen Parameter gepr�ft wurden
	 * @return Eingegebene Parameter gepr�ft
	 */
	public boolean getInputParameterChecked() {
		return inputParameterChecked;
	}
	
	/**
	 * R�ckgabe, ob die Berechnung beendet ist
	 * @return Berechnung beendet
	 */
	public boolean getCalculationCompleted() {
		return calculationCompleted;
	}
	
	/**
	 * Festlegen oder �ndern eines Widerstandes
	 * @param index	Nummer des Widerstands (0 oder 1)
	 * @param value Widerstand
	 */
	public void setResistor(int index, Resistor value) {
		if (index == 0 || index == 1) {
			this.resistors[index] = value;
			inputParameterChecked = false;
		}
	}
	
	/**
	 * Festlegen oder �ndern des Gesamtwiderstandes
	 * @param value Gesamtwiderstand
	 */
	public void setTotalResistor(Resistance value) {
		this.totalResistor = value;
		inputParameterChecked = false;
	}
	
	/**
	 * R�ckgabe des Gesamtwiderstands
	 * @return Gesamtwiderstand
	 */
	public Resistance getTotalResistor() {
		return this.totalResistor;
	}

	/**
	 * R�ckgabe eines Widerstandes
	 * @param index Nummer des Widerstands (0 oder 1)
	 * @return Widerstand
	 */
	public Resistor getResistor(int index) {
		if (index == 0 || index == 1) {
			return this.resistors[index];
		}
		return null;
	}
	
	/**
	 * Festlegen oder �ndern einer Spannung
	 * @param index Nummer der Spannung (0 oder 1)
	 * @param value Spannung
	 */
	public void setVoltage(int index, Voltage value) {
		if (index == 0 || index == 1) {
			this.voltages[index] = value;
			inputParameterChecked = false;
		}
	}
	
	/**
	 * R�ckgabe des Gesamtwiderstands
	 * @param value Gesamtwiderstand
	 */
	public void setTotalVoltage(Voltage value) {
		this.totalVoltage = value;
	}
	
	/**
	 * R�ckgabe einer Spannung
	 * @param index Nummer der Spannung (0 oder 1)
	 * @return Spannung
	 */
	public Voltage getVoltage(int index) {
		if (index == 0 || index == 1) {
			return this.voltages[index];
		}
		return null;
	}
	
	/**
	 * R�ckgabe der Gesamtspannung
	 * @return Gesamtspannung
	 */
	public Voltage getTotalVoltage() {
		return this.totalVoltage;
	}
	
	/**
	 * Festlegen oder �ndern eines Mindestwiderstandes
	 * @param index Nummer des Mindestwiderstandes (0 oder 1)
	 * @param value Mindestwiderstand
	 */
	public void setMinResistor(int index, Resistance value) {
		if (index == 0 || index == 1) {
			this.minResistance[index] = value;
			inputParameterChecked = false;
		}
	}

	/**
	 * R�ckgabe des Mindestwerts f�r einen Widerstand
	 * @param index Nummer des Widerstands (0 oder 1)
	 * @return Mindest-Widertandswert
	 */
	public Resistance getMinResistance(int index) {
		if (index == 0 || index == 1) {
			return this.minResistance[index];
		}
		return null;
	}
	
	/**
	 * Festlegen oder �ndern des Verh�tlnisses
	 * @param resistor1 Widerstand 1
	 * @param resistor2 Widerstand 2
	 */
	public void setRatio(double resistor1, double resistor2) {
		this.ratio = (resistor1/resistor2);
		inputParameterChecked = false;
	}
	
	/**
	 * Festlegen oder �ndern des Verh�tlnisses
	 * @param value Verh�ltnis zwischen Widerstand 1 zu Widerstand 2
	 */
	public void setRatioResistor1toResistor2(double value) {
		this.ratio = value;
		inputParameterChecked = false;
	}
	
	/**
	 * R�ckgabe des Verh�tnisses zwischen Widerstand 1 und Widerstand 2
	 * @return Verh�ltnis zwischen Widerstand 1 und Widerstand 2
	 */
	public double getRatioResistor1toResistor2() {
		return this.ratio;
	}
	
	/**
	 * Festlegen oder �ndern des Verh�tlnisses zwischen Widerstand 2 und Widerstand 1
	 * @param value Verh�ltnis zwischen Widerstand 2 und Widerstand 1
	 */
	public void setRatioResistor2toResistor1(double value) {
		this.ratio = (1/value);
		inputParameterChecked = false;
	}
	
	/**
	 * R�ckgabe des Verh�tnisses zwischen Widerstand 2 und Widerstand 1
	 * @return Verh�ltnis zwischen Widerstand 2 und Widerstand 1
	 */
	public double getRatioResistor2toResistor1() {
		return (1/this.ratio);
	}
	
	/**
	 * Pr�ft, ob die eingegebenen Parameter eine g�ltige Berechnungsgrundlage bieten
	 * @param parameter Zu pr�fende Eingabeparameter
	 * @return G�ltigkeit
	 */
	public boolean isValidInput(InputParameter parameter) {
		inputParameterChecked = true;
		validParameter = parameter;
		
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
		
		//G�ltigkeit pr�fen
		if(validParameter.getRatio() == true && counterVoltage != 1) return false;
		if(validParameter.getRatio() == true && ((counterMinResistor == 0 && counterResistor == 1) || (counterMinResistor >= 0 && counterResistor == 0))) return true;
		if(validParameter.getRatio() == false && (counterVoltage == 2 && ((counterMinResistor == 0 && counterResistor == 1) || (counterMinResistor >= 0 && counterResistor == 0)))) return true;
		if(validParameter.getRatio() == false && (counterResistor == 2 && counterMinResistor == 0 && counterVoltage == 1)) return true;
		return false;
	}
	
	/**
	 * Berechnung aller fehlenden Werte
	 */
	public void calculateValues() {
		if(inputParameterChecked == true) {
		
			int counterVoltage = 0;
			int counterResistor = 0;
			Resistance Probe = new Resistance();
				
			if(validParameter.getTotalVoltage() == true) counterVoltage++;
			if(validParameter.getVoltage1() == true) counterVoltage++;
			if(validParameter.getVoltage2() == true) counterVoltage++;
			
			if(validParameter.getTotalResistor() == true) counterResistor++;
			if(validParameter.getResistor1() == true) counterResistor++;
			if(validParameter.getResistor2() == true) counterResistor++;
			
			if(validParameter.getRatio() == false) {
				if(counterVoltage == 2) {
					if(validParameter.getVoltage1() == false) {
						this.voltages[0].setValue(this.totalVoltage.getValue() - this.voltages[1].getValue());					
					}
					if(validParameter.getVoltage2() == false) {
						this.voltages[1].setValue(this.totalVoltage.getValue() - this.voltages[0].getValue());
					}
					
					if(validParameter.getTotalVoltage() == false) {
						this.totalVoltage.setValue(this.voltages[0].getValue() + this.voltages[1].getValue());				
					}
					
					this.ratio = (this.voltages[0].getValue() / this.voltages[1].getValue());
					if(validParameter.getTotalResistor() == true) {
						Probe.setValue(this.totalResistor.getValue()/(this.ratio + 1));
						this.resistors[1].chooseResistor(Probe);
						Probe.setValue(this.totalResistor.getValue() - this.resistors[1].getResistance().getValue());
						this.resistors[0].chooseResistor(Probe);
					}
					
					if(validParameter.getResistor1() == true) {
						Probe.setValue(this.resistors[0].getResistance().getValue() * this.ratio);
						this.resistors[1].chooseResistor(Probe);
						Probe.setValue(this.resistors[0].getResistance().getValue() + this.resistors[1].getResistance().getValue());
						this.totalResistor = Probe;
					}
					if(validParameter.getResistor2() == true) {
						Probe.setValue(this.resistors[1].getResistance().getValue() / this.ratio);
						this.resistors[0].chooseResistor(Probe);
						Probe.setValue(this.resistors[0].getResistance().getValue() + this.resistors[1].getResistance().getValue());
						this.totalResistor = Probe;
					}
					
					if(validParameter.getMinResistor1() == true && validParameter.getMinResistor2() == false) {
						Probe.setValue(this.minResistance[0].getValue());
						this.resistors[0].chooseResistor(Probe);
						Probe.setValue(this.resistors[0].getResistance().getValue() / this.ratio);
						this.resistors[1].chooseResistor(Probe);
						Probe.setValue(this.resistors[0].getResistance().getValue() + this.resistors[1].getResistance().getValue());
						this.totalResistor = Probe;	
					}
					if(validParameter.getMinResistor1() == false && validParameter.getMinResistor2() == true) {
						Probe.setValue(this.minResistance[1].getValue());
						this.resistors[1].chooseResistor(Probe);
						Probe.setValue(this.resistors[1].getResistance().getValue() * this.ratio);
						this.resistors[0].chooseResistor(Probe);
						Probe.setValue(this.resistors[0].getResistance().getValue() + this.resistors[1].getResistance().getValue());
						this.totalResistor = Probe;	
					}
					if(validParameter.getMinResistor1() == false && validParameter.getMinResistor2() == false) {
						Probe.setValue(10);
						this.resistors[0].chooseResistor(Probe);
						Probe.setValue(this.resistors[0].getResistance().getValue() / this.ratio);
						this.resistors[1].chooseResistor(Probe);
						Probe.setValue(this.resistors[0].getResistance().getValue() + this.resistors[1].getResistance().getValue());
						this.totalResistor = Probe;	
					}
					if(validParameter.getMinResistor1() == true && validParameter.getMinResistor2() == true) {
						Probe.setValue(this.minResistance[0].getValue());
						this.resistors[0].chooseResistor(Probe);
						Probe.setValue(this.resistors[0].getResistance().getValue() / this.ratio);
						this.resistors[1].chooseResistor(Probe);
						if(this.resistors[1].getResistance().getValue() < this.minResistance[1].getValue()) {
							Probe.setValue(this.minResistance[1].getValue());
							this.resistors[1].chooseResistor(Probe);
							Probe.setValue(this.resistors[1].getResistance().getValue() * this.ratio);
							this.resistors[0].chooseResistor(Probe);
						}
						Probe.setValue(this.resistors[0].getResistance().getValue() + this.resistors[1].getResistance().getValue());
						this.totalResistor = Probe;
					}
				}
				if(counterResistor == 2) {
					if(validParameter.getResistor1() == false) {
						Probe.setValue(this.totalResistor.getValue() - this.resistors[1].getResistance().getValue());
						this.resistors[0].chooseResistor(Probe);
					}
					if(validParameter.getResistor2() == false) {
						Probe.setValue(this.totalResistor.getValue() - this.resistors[0].getResistance().getValue()) ;
						this.resistors[1].chooseResistor(Probe);
					}
					
					if(validParameter.getTotalResistor() == false) {
						Probe.setValue(this.resistors[0].getResistance().getValue() + this.resistors[1].getResistance().getValue());	
						this.totalResistor = Probe;
					}
					
					this.ratio = (this.resistors[0].getResistance().getValue() / this.resistors[1].getResistance().getValue());
					if(validParameter.getTotalVoltage() == true) {
						this.voltages[1].setValue(this.totalVoltage.getValue() / (this.ratio+1));
						this.voltages[0].setValue(this.totalVoltage.getValue() - this.voltages[1].getValue());
					}
					
					if(validParameter.getVoltage1() == true) {
						this.voltages[1].setValue(this.voltages[0].getValue() / this.ratio);
						this.totalVoltage.setValue(this.voltages[0].getValue() + this.voltages[1].getValue());
					}
					if(validParameter.getVoltage2() == true) {
						this.voltages[0].setValue(this.voltages[1].getValue() * this.ratio);
						this.totalVoltage.setValue(this.voltages[0].getValue() + this.voltages[1].getValue());
					}
				}
			}
			else {
				if(validParameter.getTotalVoltage() == true) {
					this.voltages[1].setValue(this.totalVoltage.getValue() / (this.ratio+1));
					this.voltages[0].setValue(this.totalVoltage.getValue() - this.voltages[1].getValue());
				}
				
				if(validParameter.getVoltage1() == true) {
					this.voltages[1].setValue(this.voltages[0].getValue() / this.ratio);
					this.totalVoltage.setValue(this.voltages[0].getValue() + this.voltages[1].getValue());
				}
				if(validParameter.getVoltage2() == true) {
					this.voltages[0].setValue(this.voltages[1].getValue() * this.ratio);
					this.totalVoltage.setValue(this.voltages[1].getValue() + this.voltages[0].getValue());
				}
				
				if(validParameter.getTotalResistor() == true) {
					Probe.setValue(this.totalResistor.getValue()/(this.ratio + 1));
					this.resistors[1].chooseResistor(Probe);
					Probe.setValue(this.totalResistor.getValue() - this.resistors[1].getResistance().getValue());
					this.resistors[0].chooseResistor(Probe);
				}
				
				if(validParameter.getResistor1() == true) {
					Probe.setValue(this.resistors[1].getResistance().getValue() * this.ratio);
					this.resistors[1].chooseResistor(Probe);
					Probe.setValue(this.resistors[0].getResistance().getValue() + this.resistors[1].getResistance().getValue());
					this.totalResistor = Probe;
				}
				if(validParameter.getResistor2() == true) {
					Probe.setValue(this.resistors[0].getResistance().getValue() / this.ratio);
					this.resistors[1].chooseResistor(Probe);
					Probe.setValue(this.resistors[0].getResistance().getValue() + this.resistors[1].getResistance().getValue());
					this.totalResistor = Probe;
				}
				
				if(validParameter.getMinResistor1() == true && validParameter.getMinResistor2() == false) {
					Probe.setValue(this.minResistance[0].getValue());
					this.resistors[0].chooseResistor(Probe);
					Probe.setValue(this.resistors[0].getResistance().getValue() / this.ratio);
					this.resistors[1].chooseResistor(Probe);
					Probe.setValue(this.resistors[0].getResistance().getValue() + this.resistors[1].getResistance().getValue());
					this.totalResistor = Probe;	
				}
				if(validParameter.getMinResistor1() == false && validParameter.getMinResistor2() == true) {
					Probe.setValue(this.minResistance[1].getValue());
					this.resistors[1].chooseResistor(Probe);
					Probe.setValue(this.resistors[1].getResistance().getValue() * this.ratio);
					this.resistors[0].chooseResistor(Probe);
					Probe.setValue(this.resistors[0].getResistance().getValue() + this.resistors[1].getResistance().getValue());
					this.totalResistor = Probe;
				}
				if(validParameter.getMinResistor1() == false && validParameter.getMinResistor2() == false) {
					Probe.setValue(10);
					this.resistors[0].chooseResistor(Probe);
					Probe.setValue(this.resistors[0].getResistance().getValue() / this.ratio);
					this.resistors[1].chooseResistor(Probe);
					Probe.setValue(this.resistors[1].getResistance().getValue() + this.resistors[1].getResistance().getValue());
					this.totalResistor = Probe;
				}
				if(validParameter.getMinResistor1() == true && validParameter.getMinResistor2() == true) {
					Probe.setValue(this.minResistance[0].getValue());
					this.resistors[0].chooseResistor(Probe);
					Probe.setValue(this.resistors[0].getResistance().getValue() / this.ratio);
					this.resistors[1].chooseResistor(Probe);
					if(this.resistors[1].getResistance().getValue() < this.minResistance[1].getValue()) {
						Probe.setValue(this.minResistance[1].getValue());
						this.resistors[1].chooseResistor(Probe);
						Probe.setValue(this.resistors[1].getResistance().getValue() * this.ratio);
						this.resistors[0].chooseResistor(Probe);
					}
					Probe.setValue(this.resistors[0].getResistance().getValue() + this.resistors[1].getResistance().getValue());
					this.totalResistor = Probe;
				}
			}
			this.calculationCompleted = true;
		}
	}

	/**
	 * R�ckgabe aller Attribute und Eigenschaften der Klasse als Zeichenkette 
	 */
	public String toString() {
		return "Gesamtspannung: " + totalVoltage
				+ "\nSpannung U1: " + voltages[0]
				+ "\nSpannung U2: " + voltages[1]
				+ "\nGesamtwiderstand: " + totalResistor
				+ "\nWiderstand R1: " + resistors[0]
				+ "\nWiderstand R2: " + resistors[1]
				+ "\nMindestwert R1: " + minResistance[0]
				+ "\nMindestwert R2: " + minResistance[1]
				+ "\nVerh�ltnisfaktor R1 zu R2: " + ratio
				+ "\nCalculation Completed: " + calculationCompleted;
	}
}