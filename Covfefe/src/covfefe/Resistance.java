package covfefe;

/**
 * Klasse, die einen Widerstand als physikalische Größe repräsentiert
 * @author Dominik Thörmer, Tobias Vöth
 * @version 05.07.2018
 */
public class Resistance extends PhysicalQuantity {
	
	/**
	 * Standardkonstruktor zum Erzeugen von Objekten der Klasse Resistance
	 */
	public Resistance() {
		super("\u03A9", true);
	}
}