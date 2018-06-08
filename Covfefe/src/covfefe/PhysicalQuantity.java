package covfefe;

public class PhysicalQuantity {
	
	protected double value;
	protected UnitPrefix prefix;
	protected String unit;
	
	/**
	 * Die Klasse PhysicalQuantity ist eine Basisklasse f�r physikalische Gr��en. 
	 * 
	 */
	
	public PhysicalQuantity(){
		unit = new unit();
		unit.setValue(value);
	}
	
	/**
	 * Methode zur R�ckgabe des Werts von value
	 * @param value Widerstandswert
	 * @return
	 */
	public double getValue(double value) {
		return value;
	}

	/**
	 * Methode zur Festlegung des Werts von value
	 * @param value Widerstandswert
	 */
	public void setValue(double value) {
		this.value = value;
	}
	
	/**
	 * Methode zur R�ckgabe des Werts von prefix 
	 * @param prefix 
	 * @return
	 */
	public UnitPrefix getPrefix(UnitPrefix prefix) {
		return prefix;
	}

	/**
	 * Methode zum Festlegen des Werts von prefix  
	 * @param prefix
	 */
	public void setPrefix(UnitPrefix prefix) {
		this.prefix = prefix;
	}

	/**
	 * Methode zur R�ckgabe des Werts von unit
	 * @param unit Einheit
	 * @return
	 */
	public String getUnit(String unit) {
		return unit;
	}
	
	/**
	 * Methode zur R�ckgabe aller Attribute und Eigenschaften als Zeichenkette
	 */
	public String toString() {
		return Value.toString() +" "+ Prefix.toString() +" "+ unit.toString();
	}

	
	
	

}
