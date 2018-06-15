package covfefe;

/**
 * Abstrakte Basisklasse für physikalische Größen
 * @author Hussein Kaviani
 * @version 13.06.2018
 */
public abstract class PhysicalQuantity {
	
	private double value;
	private UnitPrefix prefix;
	private final String unit;
	private boolean positiveOnly;
	
	/**
	 * Konstruktor zum Erzeugen von Objekten der Klasse PhysicalQuantity
	 * @param unit Einheit der physikalischen Größe
	 */
	public PhysicalQuantity(String unit) {
		this(unit, false);
	}
	
	/**
	 * Konstruktor zum Erzeugen von Objekten der Klasse PhysicalQuantity
	 * @param unit Einheit der physikalischen Größe
	 * @param positiveOnly Nur positive Werte
	 */
	public PhysicalQuantity(String unit, boolean positiveOnly) {
		setValue(0);
		prefix = new UnitPrefix();
		this.unit = unit;
		this.positiveOnly = positiveOnly;
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
		//Gültigkeitsbereich prüfen (nur positive Zahlen)
		if(positiveOnly == true) {
			value = Math.abs(value);
		}
		
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
	public UnitPrefix getPrefix() {
		return prefix;
	}

	/**
	 * Rückgabe der Einheit der physikalischen Größe
	 * @return Einheit der physikalischen Größe
	 */
	public String getUnit() {
		return unit;
	}
	
	/**
	 * Rückgabe, ob die physikalische Größe nur positive Werte besitzt
	 * @return Physikalische Größe besitzt nur positive Werte
	 */
	public boolean getPositiveOnly() {
		return positiveOnly;
	}
	
	/**
	 * Rückgabe aller Attribute und Eigenschaften der Klasse als Zeichenkette
	 * @return Attribute und Eigenschaften der Klasse
	 */
	public String toString() {
		return value + " " + prefix.getPrefix() + unit;
	}
}