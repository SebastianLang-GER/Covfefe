package covfefe;

/**
 * Klasse, die einen Spannung als physikalische Gr��e repr�sentiert
 * @author Dominik Th�rmer, Tobias V�th
 * @version 13.06.2018
 */
public abstract class Voltage extends PhysicalQuantity {
	
	/**
	 * Standardkonstruktor zum Erzeugen von Objekten der Klasse Voltage
	 */
	public Voltage() {
		super("V");
	}
}