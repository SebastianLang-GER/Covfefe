package covfefe;

/**
 * Abstrakte Basisklasse für physikalische Größen
 * @author Hussein Kaviani
 * @version 11.06.2018
 */
public abstract class PhysicalQuantity {
	
	protected double value;
	protected UnitPrefix prefix;
	protected final String unit;
	
	/**
	 * Konstruktor zum Erzeugen von Objekten der Klasse PhysicalQuantity
	 * @param unit Einheit der physikalischen Größe
	 */
	public PhysicalQuantity(String unit) {
		setValue(0);
		prefix = new UnitPrefix();
		this.unit = unit;
	}
	
	/**
	 * Festlegen oder ändern des Werts der physikalischen Größe
	 * @param value Wert der physikalischen Größe
	 */
	public void setValue(double value) {
		this.value = value;
	}
	
	/**
	 * Rückgabe des Werts der physikalischen Größe
	 * @return Wert der physikalischen Größe
	 */
	public double getValue() {
		return value;
	}

	/**
	 * Festlegen oder ändern des Einheitenvorsatzes
	 * @param power Zehnerpotenz
	 */
	public void setPrefix(int power) {
		prefix.setPower(power);
	}

	/**
	 * Rückgabe des Einheitenvorsatzes
	 * @return Einheitenvorsatz
	 */
	public String getPrefix() {
		return prefix.getPrefix();
	}

	/**
	 * Rückgabe der Einheit der physikalischen Größe
	 * @return Einheit der physikalischen Größe
	 */
	public String getUnit() {
		return unit;
	}
	
	/**
	 * Rückgabe aller Attribute und Eigenschaften der Klasse als Zeichenkette
	 * @return Attribute und Eigenschaften der Klasse
	 */
	public String toString() {
		return value + " " + prefix.getPrefix() + unit.toString();
	}
}