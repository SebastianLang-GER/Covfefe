package covfefe;

public class UnitPrefix {

	private static final String[] prefix = {"µ", "m","","k","M"};
	private int power;
	
	/**
	 * 	Die Klasse UnitPrefix repräsentiert die Vorsätze einer physikalischen Einheit. 
	 * @param power Potenz
	 */
	public UnitPrefix(int power) {
		setPower(power);
	}
	
	/**
	 * Festlegen des Werts der Potenz
	 * @param value Potenz
	 */
	public void setPower(int value) {
		power = value;
	}
	/**
	 * 	Rückgabe des Werts von power 
	 * @return Potenz
	 */
	public int getPower() {
		return power;
	}
	/**
	 * Rückgabe der Vorsätze einer physikalischen Einheit
	 * @return Auswahl des Vorsatzes anhand der Potenz
	 */
	public String getPrefix() {
		if(power <= -6) {
			return prefix[0];
		}
		else if(power >= -3 && power < 0) {
			return prefix[1];			
		}
		else if(power >= 0 && power<3) {
			return prefix[2];	
		}
		else if(power >=3 && power<6) {
			return prefix[3];
		}
		else if(power >= 6) {
			return prefix[4];
		}
		return "";
	}
}