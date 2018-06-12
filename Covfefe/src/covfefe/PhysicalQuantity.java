package covfefe;

/**
 * Abstrakte Basisklasse für physikalische Größen
 * @author Hussein Kaviani
 * @version 12.06.2018
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
	 * Festlegen oder ändern des Werts und Einheitenvorsatzes der physikalischen Größe
	 * @param value Wert der physikalischen Größe
	 * @param prefix Einheitenvorsatz der physikalischen Größe
	 */
	public void setValue(double value, UnitPrefix prefix) {
		this.prefix = prefix;
		setValue(value);
	}
	
	/**
	 * Festlegen oder ändern des Werts der physikalischen Größe
	 * @param value Wert der physikalischen Größe
	 */
	public void setValue(double value) {
		//Gültigkeitsbereich prüfen (in entsprechende Einheitenvorsätze umformen)
		while(Math.abs(value) >= 1000) {
			value = value / 1000;
			if(value > 0) {
				prefix.setPrefix(prefix.getPower() + 3); //Einheitenvorsatz erhöhen
			}
			else {
				prefix.setPrefix(prefix.getPower() - 3); //Einheitenvorsatz reduzieren
			}
		}
		
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
		prefix.setPrefix(power);
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
		return value + " " + prefix.getPrefix() + unit;
	}
}