package covfefe;

public class Resistor {
	
	private eSeriesValue resistance;
	private ESeriesTemplate series;
	
	/**
	 * Die Klasse Resistor repr�sentiert einen ohmschen Widerstand als Bauteil.
	 * @param value Widerstandswert
	 */
	public Resistor (double value){
		resistance = new Resistance();
		resistance.setValue(value);
		series = new ESeriesTemplate();
	}
	/**
	 * Methode zur R�ckgabe des Widerstandswertes
	 * @return Widerstandswert
	 */
	public double getValue() {
		return Resistance.getValue();
	}
	
	/**
	 * Festlegen der E-Reihe des Widerstands
	 * @param value E-Reihe
	 */
	public void setESeries(ESeriesTemplate value) {
		
	}
	
	/**
	 * Methode zur R�ckgabe der E-Reihe des Widerstands 
	 * @return E-Reihe
	 */
	public ESeriesTemplate getESeries() {
		return series;
	}
	
	/**
	 * Methode zur Festlegung von Einheitvors�tzen
	 * @param Potenz zur Bestimmung der Einheit
	 */
	public void setUnitPrefix(int power) {
		
	}
	
	/**
	 * Methode um Widerstandswert anhand der E-Reihe auszuw�hlen
	 * @param value Widerstandswert
	 */
	public void chooseResistor(Resistance value) {
		
	}
	/** 
	 * Methode zur R�ckgabe aller Attribute und Eigenschaften als Zeichenkette
	 */
	public String toString() {
		return Resistance.toString() + "\n" + series.toString();
	}
}
