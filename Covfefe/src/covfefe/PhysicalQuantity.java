package covfefe;

/**
 * Abstrakte Basisklasse f�r physikalische Gr��en
 * @author Hussein Kaviani
 * @version 12.06.2018
 */
public abstract class PhysicalQuantity {
	
	protected double value;
	protected UnitPrefix prefix;
	protected final String unit;
	
	/**
	 * Konstruktor zum Erzeugen von Objekten der Klasse PhysicalQuantity
	 * @param unit Einheit der physikalischen Gr��e
	 */
	public PhysicalQuantity(String unit) {
		setValue(0);
		prefix = new UnitPrefix();
		this.unit = unit;
	}
	
	/**
	 * Festlegen oder �ndern des Werts und Einheitenvorsatzes der physikalischen Gr��e
	 * @param value Wert der physikalischen Gr��e
	 * @param prefix Einheitenvorsatz der physikalischen Gr��e
	 */
	public void setValue(double value, UnitPrefix prefix) {
		this.prefix = prefix;
		setValue(value);
	}
	
	/**
	 * Festlegen oder �ndern des Werts der physikalischen Gr��e
	 * @param value Wert der physikalischen Gr��e
	 */
	public void setValue(double value) {
		//G�ltigkeitsbereich pr�fen (in entsprechende Einheitenvors�tze umformen)
		while(Math.abs(value) >= 1000) {
			value = value / 1000;
			if(value > 0) {
				prefix.setPrefix(prefix.getPower() + 3); //Einheitenvorsatz erh�hen
			}
			else {
				prefix.setPrefix(prefix.getPower() - 3); //Einheitenvorsatz reduzieren
			}
		}
		
		this.value = value;
	}
	
	/**
	 * R�ckgabe des Werts der physikalischen Gr��e
	 * @return Wert der physikalischen Gr��e
	 */
	public double getValue() {
		return value;
	}

	/**
	 * Festlegen oder �ndern des Einheitenvorsatzes
	 * @param power Zehnerpotenz
	 */
	public void setPrefix(int power) {
		prefix.setPrefix(power);
	}

	/**
	 * R�ckgabe des Einheitenvorsatzes
	 * @return Einheitenvorsatz
	 */
	public String getPrefix() {
		return prefix.getPrefix();
	}

	/**
	 * R�ckgabe der Einheit der physikalischen Gr��e
	 * @return Einheit der physikalischen Gr��e
	 */
	public String getUnit() {
		return unit;
	}
	
	/**
	 * R�ckgabe aller Attribute und Eigenschaften der Klasse als Zeichenkette
	 * @return Attribute und Eigenschaften der Klasse
	 */
	public String toString() {
		return value + " " + prefix.getPrefix() + unit;
	}
}