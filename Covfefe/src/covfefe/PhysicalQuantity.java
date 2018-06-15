package covfefe;

/**
 * Abstrakte Basisklasse f�r physikalische Gr��en
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
	 * @param unit Einheit der physikalischen Gr��e
	 */
	public PhysicalQuantity(String unit) {
		this(unit, false);
	}
	
	/**
	 * Konstruktor zum Erzeugen von Objekten der Klasse PhysicalQuantity
	 * @param unit Einheit der physikalischen Gr��e
	 * @param positiveOnly Nur positive Werte
	 */
	public PhysicalQuantity(String unit, boolean positiveOnly) {
		setValue(0);
		prefix = new UnitPrefix();
		this.unit = unit;
		this.positiveOnly = positiveOnly;
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
		//G�ltigkeitsbereich pr�fen (nur positive Zahlen)
		if(positiveOnly == true) {
			value = Math.abs(value);
		}
		
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
	public UnitPrefix getPrefix() {
		return prefix;
	}

	/**
	 * R�ckgabe der Einheit der physikalischen Gr��e
	 * @return Einheit der physikalischen Gr��e
	 */
	public String getUnit() {
		return unit;
	}
	
	/**
	 * R�ckgabe, ob die physikalische Gr��e nur positive Werte besitzt
	 * @return Physikalische Gr��e besitzt nur positive Werte
	 */
	public boolean getPositiveOnly() {
		return positiveOnly;
	}
	
	/**
	 * R�ckgabe aller Attribute und Eigenschaften der Klasse als Zeichenkette
	 * @return Attribute und Eigenschaften der Klasse
	 */
	public String toString() {
		return value + " " + prefix.getPrefix() + unit;
	}
}