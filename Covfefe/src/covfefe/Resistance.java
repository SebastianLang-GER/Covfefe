package covfefe;

/**
 * Klasse, welche die Einheit des Widerdstandes festlegt.
 * @author Dominik Thörmer, Tobias Vöth
 * @version 13.06.2018
 */

public abstract class Resistance extends PhysicalQuantity {
	
	/**
	 * Konstruktor zum Festlegen der Einheit "Ohm"
	 */
	public Resistance() {
		super("Ω");
	}
}
