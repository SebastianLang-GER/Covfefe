package covfefe;

/**
 * Klasse, die Vorsätze einer physikalischen Einheit repräsentiert
 * @author Shayan Jani
 * @version 11.06.2018
 */
public class UnitPrefix {

	private static final String prefix[] = {"µ", "m","","k","M"};
	private int power;
	
	/**
	 * Standardkonstruktor zum Erzeugen von Objekten der Klasse UnitPrefix
	 */
	public UnitPrefix() {
		setPower(0);
	}
	
	/**
	 * Konstruktor zum Erzeugen von Objekten der Klasse UnitPrefix mit Festlegen der Zehnerpotenz
	 * @param power Zehnerpotenz
	 */
	public UnitPrefix(int power) {
		setPower(power);
	}
	
	/**
	 * Festlegen oder ändern der Zehnerpotenz
	 * @param value Zehnerpotenz
	 */
	public void setPower(int value) {
		//Gültigkeitsbereich prüfen (nur 3er-Potenzen zulassen)
		if(value % 3 != 0) {
			if(value < 0) {
				value = 3 * (value / 3 - 1);
			}
			else {
				value = 3 * (value / 3);
			}
		}
		power = value;
	}
	
	/**
	 * Rückgabe der Zehnerpotenz
	 * @return Zehnerpotenz
	 */
	public int getPower() {
		return power;
	}
	
	/**
	 * Rückgabe des Einheitenvorsatzes
	 * @return Einheitenvorsatz
	 */
	public String getPrefix() {
		if(power < -3) {
			return prefix[0]; //µ
		}
		else if(power >= -3 && power < 0) {
			return prefix[1]; //m
		}
		else if(power >= 0 && power < 3) {
			return prefix[2]; //nichts
		}
		else if(power >= 3 && power < 6) {
			return prefix[3]; //k
		}
		else if(power >= 6) {
			return prefix[4]; //M
		}
		return ""; //nichts
	}
}