package covfefe;

/**
 * Klasse, die Vors�tze einer physikalischen Einheit repr�sentiert
 * @author Shayan Jani
 * @version 11.06.2018
 */
public class UnitPrefix {

	private static final String prefix[] = {"�", "m","","k","M"};
	private int power;
	
	/**
	 * Standardkonstruktor zum Erzeugen von Objekten der Klasse UnitPrefix
	 */
	public UnitPrefix() {
		setPrefix(0);
	}
	
	/**
	 * Konstruktor zum Erzeugen von Objekten der Klasse UnitPrefix mit Festlegen der Zehnerpotenz
	 * @param power Zehnerpotenz
	 */
	public UnitPrefix(int power) {
		setPrefix(power);
	}
	
	/**
	 * Festlegen oder �ndern des Einheitenvorsatzes
	 * @param power Zehnerpotenz
	 */
	public void setPrefix(int power) {
		//G�ltigkeitsbereich pr�fen (nur 3er-Potenzen zulassen)
		if(power % 3 != 0) {
			if(power < 0) {
				power = 3 * (power / 3 - 1);
			}
			else {
				power = 3 * (power / 3);
			}
		}
		
		this.power = power;
	}
	
	/**
	 * R�ckgabe der Zehnerpotenz
	 * @return Zehnerpotenz
	 */
	public int getPower() {
		return power;
	}
	
	/**
	 * R�ckgabe des Einheitenvorsatzes
	 * @return Einheitenvorsatz
	 */
	public String getPrefix() {
		if(power < -3) {
			return prefix[0]; //�
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