package covfefe;

/**
 * Klasse, die einen ohmschen Widerstand als Bauteil repr�sentiert
 * @author Sebastian Lang, Hussein Kaviani
 * @version 14.06.2018
 */
public class Resistor {
	
	private Resistance eSeriesResistance;
	private ESeriesTemplate eSeries;
	
	/**
	 * Konstruktor zum Erzeugen von Objekten der Klasse Resistor
	 * @param resistance Widerstand (physikalische Gr��e)
	 * @param eSeries E-Reihe des Widerstands
	 */
	public Resistor(Resistance resistance, ESeriesTemplate eSeries) {
		eSeriesResistance = new Resistance();
		setESeries(eSeries);
		chooseResistor(resistance);
	}
	
	/**
	 * R�ckgabe des Widerstands (physikalische Gr��e)
	 * @return Widerstand (physikalische Gr��e)
	 */
	public Resistance getResistance() {
		return eSeriesResistance;
	}
	
	/**
	 * Festlegen oder �ndern der E-Reihe des Widerstands
	 * @param value E-Reihe des Widerstands
	 */
	public void setESeries(ESeriesTemplate value) {
		eSeries = value;
	}
	
	/**
	 * R�ckgabe der E-Reihe des Widerstands
	 * @return E-Reihe des Widerstands
	 */
	public ESeriesTemplate getESeries() {
		return eSeries;
	}
	
	/**
	 * Ausw�hlen eines Widerstands anhand der E-Reihe
	 * @param resistance Widerstand (physikalische Gr��e)
	 */
	public void chooseResistor(Resistance resistance) {
		eSeriesResistance.setPrefix(resistance.getPrefix().getPower()); //Einheitenvorsatz �bernehmen
		int decimalFactor = getDecimalFactor(resistance.getValue()); //Dezimalfaktor der E-Reihe angleichen
		
		//Widerstand mit der geringsten Abweichung zur E-Reihe ermitteln
		double minDifference = Double.MAX_VALUE;
		for(int i = 0; i < eSeries.getItems(); i++) {
			double difference = Math.abs(eSeries.getValue(i) * Math.pow(10, decimalFactor) - resistance.getValue()); //Abweichung berechnen
			if(difference < minDifference) {
				minDifference = difference;
				eSeriesResistance.setValue(eSeries.getValue(i) * Math.pow(10, decimalFactor)); //Widerstandswert �bernehmen
			}
		}
	}
	
	/**
	 * Ausw�hlen des n�chst gr��eren Widerstands anhand der E-Reihe
	 * @param resistance Widerstand (physikalische Gr��e)
	 */
	public void chooseNextGreaterResistor(Resistance resistance) {
		eSeriesResistance.setPrefix(resistance.getPrefix().getPower()); //Einheitenvorsatz �bernehmen
		int decimalFactor = getDecimalFactor(resistance.getValue()); //Dezimalfaktor der E-Reihe angleichen
		
		//N�chst gr��eren Widerstand der E-Reihe ermitteln
		for(int i = 0; i < eSeries.getItems(); i++) {
			if(eSeries.getValue(i) * Math.pow(10, decimalFactor) >= resistance.getValue()) {
				eSeriesResistance.setValue(eSeries.getValue(i) * Math.pow(10, decimalFactor)); //Widerstandswert �bernehmen
				return;
			}
		}
		eSeriesResistance.setValue(eSeries.getValue(0) * Math.pow(10, decimalFactor + 1)); //Widerstandswert �bernehmen
	}
	
	/**
	 * Ausw�hlen des n�chst kleineren Widerstands anhand der E-Reihe
	 * @param resistance Widerstand (physikalische Gr��e)
	 */
	public void chooseNextSmallerResistor(Resistance resistance) {
		eSeriesResistance.setPrefix(resistance.getPrefix().getPower()); //Einheitenvorsatz �bernehmen
		int decimalFactor = getDecimalFactor(resistance.getValue()); //Dezimalfaktor der E-Reihe angleichen
		
		//N�chst kleineren Widerstand der E-Reihe ermitteln
		for(int i = eSeries.getItems() - 1; i >= 0; i--) {
			if(eSeries.getValue(i) * Math.pow(10, decimalFactor) <= resistance.getValue()) {
				eSeriesResistance.setValue(eSeries.getValue(i) * Math.pow(10, decimalFactor)); //Widerstandswert �bernehmen
				return;
			}
		}
		eSeriesResistance.setValue(eSeries.getValue(eSeries.getItems() - 1) * Math.pow(10, decimalFactor - 1)); //Widerstandswert �bernehmen
	}
	
	/**
	 * R�ckgabe aller Attribute und Eigenschaften der Klasse als Zeichenkette
	 */
	public String toString() {
		return "Wert: " + eSeriesResistance + "\nE-Reihe: " + getESeries().getName();
	}
	
	/**
	 * R�ckgabe des Dezimalfaktors einer Gleitkommazahl
	 * @param value Gleitkommazahl dessen Dezimalfaktor zur�ckgegeben werden soll
	 * @return Dezimalfaktor der Gleitkommazahl
	 */
	private static int getDecimalFactor(double value) {
		int decimalFactor = 0;
		while(Math.abs(value) >= 10) {
			value = value / 10;
			if(value > 0) {
				decimalFactor++; //Dezimalfaktor erh�hen
			}
			else {
				decimalFactor--; //Dezimalfaktor reduzieren
			}
		}
		return decimalFactor;
	}
}