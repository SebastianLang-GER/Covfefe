package covfefe;

/**
 * Klasse, die einen Spannung als physikalische Größe repräsentiert
 * @author Dominik Thörmer, Tobias Vöth
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