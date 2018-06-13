package covfefe;

/**
 * Klasse, welche die Einheit der Spannung festlegt.
 * @author Dominik Th�rmer, Tobias V�th
 * @version 13.06.2018
 */

public abstract class Voltage extends PhysicalQuantity {
	
	/**
	 * Konstruktor zum Festlegen der Einheit "Volt"
	 */
	public Voltage() {
		super("V");
	}
}
