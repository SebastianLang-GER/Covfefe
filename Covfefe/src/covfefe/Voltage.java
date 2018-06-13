package covfefe;

/**
 * Klasse, welche die Einheit der Spannung festlegt.
 * @author Dominik Thörmer, Tobias Vöth
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
